package enn.monitor.trace.streaming.dependency;

import enn.monitor.trace.proto.model.common.Aggregates;
import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.proto.model.dependency.EnnEndpoint;
import enn.monitor.trace.proto.model.dependency.Pair;
import org.apache.directory.api.util.Strings;
import org.apache.log4j.Logger;
import zipkin2.Span;
import zipkin2.internal.Nullable;

import java.util.*;

/**
 * Created by weize on 18-1-3.
 */
public class EnnDependencyLinker {
    static final EnnNode.MergeFunction<Span> MERGE_RPC = new MergeRpc();
    private final Map<Pair, Aggregates> aggMap = new LinkedHashMap<>();
//    private final Map<Pair, Long> callCounts = new LinkedHashMap<>();
//    private final Map<Pair, Long> errorCounts = new LinkedHashMap<>();


    static final class MergeRpc implements EnnNode.MergeFunction<Span> {
        @Override public Span merge(@Nullable Span left, @Nullable Span right) {
            if (left == null) return right;
            if (right == null) return left;
            if (left.kind() == null) {
                return copyError(left, right);
            }
            if (right.kind() == null) {
                return copyError(right, left);
            }
            Span server = left.kind() == Span.Kind.SERVER ? left : right;
            Span client = left == server ? right : left;
            if (server.remoteServiceName() != null) {
                return copyError(client, server);
            }
            return copyError(client, server).toBuilder().remoteEndpoint(client.localEndpoint()).build();
        }

        static Span copyError(Span maybeError, Span result) {
            String error = maybeError.tags().get("error");
            if (error != null) {
                return result.toBuilder().putTag("error", error).build();
            }
            return result;
        }
    }

    /**
     * @param spans spans where all spans have the same trace id
     */
    public EnnDependencyLinker putTrace(Iterator<Span> spans) {
        if (!spans.hasNext()) return this;
        Span first = spans.next();

        // Build a tree based on spanId and parentId values
        EnnNode.TreeBuilder<Span> builder = new EnnNode.TreeBuilder<>(MERGE_RPC, first.traceId());
        builder.addNode(first.parentId(), first.id(), first);
        while (spans.hasNext()) {
            Span next = spans.next();
            builder.addNode(next.parentId(), next.id(), next);
        }

        EnnNode<Span> tree = builder.build();

//        if (logger.isLoggable(FINE)) logger.fine("traversing trace tree, breadth-first");
        for (Iterator<EnnNode<Span>> i = tree.traverse(); i.hasNext(); ) {
            EnnNode<Span> current = i.next();
            if (current.isSyntheticRootForPartialTree()) {
//                logger.fine("skipping synthetic node for broken span tree");
                continue;
            }
            Span currentSpan = current.value();
            if (currentSpan == null) {
//                logger.fine("skipping null span in " + first.traceId());
                continue;
            }
//            if (logger.isLoggable(FINE)) {
//                logger.fine("processing " + currentSpan);
//            }

            Span.Kind kind = currentSpan.kind();
            if (Span.Kind.CLIENT.equals(kind) && !current.children().isEmpty()) {
//                logger.fine("deferring link to rpc child span");
                continue;
            }

            String serviceName = currentSpan.localServiceName();
            String bizLine = currentSpan.tags().getOrDefault("bizLine", "unknown");
            String serviceResource = getResourse(currentSpan);
            String serviceInstance = currentSpan.tags().getOrDefault("instance", "unknown");

            String remoteServiceName = currentSpan.remoteServiceName();
            String remoteBizLine = null;
            String remoteServiceResource = null;
            String remoteServiceInstance = null;
            if (remoteServiceName != null && current.parent() != null && current.parent().value() != null) {
                Span remoteSpan = current.parent().value();
                remoteBizLine = remoteSpan.tags().getOrDefault("bizLine", "unknown");
                remoteServiceResource = getResourse(remoteSpan);
                remoteServiceInstance = remoteSpan.tags().getOrDefault("instance", "unknown");
            }

            if (kind == null) {
                // Treat unknown type of span as a client span if we know both sides
                if (serviceName != null && remoteServiceName != null) {
                    kind = Span.Kind.CLIENT;
                } else {
//                    logger.fine("non-rpc span; skipping");
                    continue;
                }
            }

            EnnEndpoint child = null;
            EnnEndpoint parent = null;
            switch (kind) {
                case SERVER:
                case CONSUMER:
                    if (serviceName != null) {
                        child = new EnnEndpoint(bizLine, serviceName, serviceInstance, serviceResource);
                    }
                    if (remoteServiceName != null) {
                        parent = new EnnEndpoint(remoteBizLine,
                                remoteServiceName, remoteServiceInstance, remoteServiceResource);
                    }
//                    child = new EnnEndpoint(bizLine, serviceName, serviceInstance, serviceResource);
//                    parent = new EnnEndpoint(remoteBizLine,
//                            remoteServiceName, remoteServiceInstance, remoteServiceResource);
                    if (current == tree) { // we are the root-most span.
                        if (remoteServiceName == null) {
//                            logger.fine("root's peer is unknown; skipping");
                            continue;
                        }
                    }
                    break;
                case CLIENT:
                case PRODUCER:
                    if (serviceName != null) {
                        parent = new EnnEndpoint(bizLine, serviceName, serviceInstance, serviceResource);
                    }
                    if (remoteServiceName != null) {
                        child = new EnnEndpoint(remoteBizLine,
                                remoteServiceName, remoteServiceInstance, remoteServiceResource);
                    }
//                    child = new EnnEndpoint(bizLine, serviceName, serviceInstance, serviceResource);
//                    parent = new EnnEndpoint(remoteBizLine,
//                            remoteServiceName, remoteServiceInstance, remoteServiceResource);
                    break;
                default:
//                    logger.fine("unknown kind; skipping");
                    continue;
            }

            boolean isError = currentSpan.tags().containsKey("error");
            if (kind == Span.Kind.PRODUCER || kind == Span.Kind.CONSUMER) {
                if (parent == null || child == null) {
//                    logger.fine("cannot link messaging span to its broker; skipping");
                } else {
                    addLink(parent, child, currentSpan.timestamp(), currentSpan.duration(), isError);
                    continue;
                }
            }

//            if (logger.isLoggable(FINE) && parent == null) {
//                logger.fine("cannot determine parent, looking for first server ancestor");
//            }

            Span rpcAncestor = findRpcAncestor(current);
            String rpcAncestorName;
            if (rpcAncestor != null && (rpcAncestorName = rpcAncestor.localServiceName()) != null) {
                // Some users accidentally put the remote service name on client annotations.
                // Check for this and backfill a link from the nearest remote to that service as necessary.
                if (kind == Span.Kind.CLIENT && serviceName != null && !rpcAncestorName.equals(serviceName)) {
//                    logger.fine("detected missing link to client span");
//                    addLink(rpcAncestorName, serviceName, false); // we don't know if there's an error here
                }

                // Local spans may be between the current node and its remote parent
                if (parent == null) {
                    String instance = currentSpan.tags().getOrDefault("instance", "unknown");
                    String resource = Strings.isNotEmpty(rpcAncestor.name()) ? rpcAncestor.name() : "default";
                    parent = new EnnEndpoint(rpcAncestor.tags().get("bizLine"),
                            rpcAncestor.localServiceName(), instance, resource);
                }

                // When an RPC is split between spans, we skip the child (server side). If our parent is a
                // client, we need to check it for errors.
                if (!isError && Span.Kind.CLIENT.equals(rpcAncestor.kind()) &&
                        currentSpan.parentId() != null && currentSpan.parentId().equals(rpcAncestor.id())) {
                    isError = rpcAncestor.tags().containsKey("error");
                }
            }

            if (parent == null || child == null) {
//                Logger.getRootLogger().info("cannot find server ancestor; skipping");
                continue;
            }

            addLink(parent, child, currentSpan.timestamp(), currentSpan.duration(), isError);
        }
        return this;
    }

    public static String getResourse(Span span) {
        if (Strings.isNotEmpty(span.tags().get("http.path"))) {
            return span.tags().get("http.path");
        } else if (Strings.isNotEmpty(span.tags().get("resource"))) {
            return span.tags().get("resource");
        }  else if (Strings.isNotEmpty(span.name())) {
            return span.name();
        } else {
            return "default";
        }

    }

    Span findRpcAncestor(EnnNode<Span> current) {
        EnnNode<Span> ancestor = current.parent();
        while (ancestor != null) {
//            if (logger.isLoggable(FINE)) {
//                logger.fine("processing ancestor " + ancestor.value());
//            }
            if (!ancestor.isSyntheticRootForPartialTree()) {
                Span maybeRemote = ancestor.value();
                if (maybeRemote.kind() != null) return maybeRemote;
            }
            ancestor = ancestor.parent();
        }
        return null;
    }

    void addLink(EnnEndpoint parent, EnnEndpoint child, long timestamp, long duration, boolean isError) {
//        if (logger.isLoggable(FINE)) {
//            logger.fine("incrementing " + (isError ? "error " : "") + "link " + parent + " -> " + child);
//        }
        long minute = timestamp / 1000000 / 60 * 60;
        Pair key =  Pair.of(parent, child, minute);
        if (aggMap.containsKey(key)) {
            Aggregates agg = aggMap.get(key);
            if (!isError) {
                agg.errorCount ++;
            }
            agg.avgLatency = (agg.callCount * agg.avgLatency + duration)
                    / (agg.callCount + 1);
            agg.callCount ++;
        } else {
            aggMap.put(key, new Aggregates(1, isError ? 1 : 0, duration)) ;
        }

//        if (callCounts.containsKey(key)) {
//            callCounts.put(key, callCounts.get(key) + 1);
//        } else {
//            callCounts.put(key, 1L);
//        }
//        if (!isError) return;
//        if (errorCounts.containsKey(key)) {
//            errorCounts.put(key, errorCounts.get(key) + 1);
//        } else {
//            errorCounts.put(key, 1L);
//        }
    }

    public List<EnnDependencyLink> link() {
        return link(aggMap);
    }

    /** links are merged by mapping to parent/child and summing corresponding links */
    public static List<EnnDependencyLink> merge(Iterable<EnnDependencyLink> in) {
        Map<Pair, Aggregates> aggMap = new LinkedHashMap<>();
//        Map<Pair, Long> errorCounts = new LinkedHashMap<>();

        for (EnnDependencyLink link : in) {
            Pair parentChild =  Pair.of(link.caller(), link.callee(), link.minute());
            if (aggMap.containsKey(parentChild)) {
                Aggregates agg = aggMap.get(parentChild);
                agg.avgLatency = (agg.callCount * agg.avgLatency + link.callCount() * link.avgLatency())
                        / (agg.callCount + link.callCount());
                agg.callCount += link.callCount();
                agg.errorCount += link.errorCount();
            } else {
                aggMap.put(parentChild, link.aggregates());
            }
//            long callCount = callCounts.containsKey(parentChild) ? callCounts.get(parentChild) : 0L;
//            callCount += link.callCount();
//            callCounts.put(parentChild, callCount);
//            long errorCount = errorCounts.containsKey(parentChild) ? errorCounts.get(parentChild) : 0L;
//            errorCount += link.errorCount();
//            errorCounts.put(parentChild, errorCount);
        }

        return link(aggMap);
    }

    static List<EnnDependencyLink> link(Map<Pair, Aggregates> aggregates) {
        List<EnnDependencyLink> result = new ArrayList<>(aggregates.size());
        for (Map.Entry<Pair, Aggregates> entry : aggregates.entrySet()) {
            Pair parentChild = entry.getKey();
            result.add(EnnDependencyLink.newBuilder()
                    .caller(parentChild.left())
                    .callee(parentChild.right())
                    .aggregates(entry.getValue())
                    .minute(parentChild.minute())
                    .build());
        }
        return result;
    }
}
