package enn.monitor.trace.streaming.dependency;

import zipkin2.internal.Nullable;
import zipkin2.internal.Node;

import java.util.*;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Level.FINE;

/**
 * Created by weize on 18-1-3.
 */
public class EnnNode<V> {
    /** Set via {@link #addChild(EnnNode)} */
    private EnnNode<V> parent;
    /** mutable as some transformations, such as clock skew, adjust this. */
    private V value;
    /** mutable to avoid allocating lists for childless nodes */
    private List<EnnNode<V>> children = Collections.emptyList();
    private boolean missingRootDummyNode;

    /** Returns the parent, or null if root */
    @Nullable
    public EnnNode<V> parent() {
        return parent;
    }

    /** Returns the value, or null if {@link #isSyntheticRootForPartialTree} */
    @Nullable public V value() {
        return value;
    }

    public EnnNode<V> value(V newValue) {
        if (newValue == null) throw new NullPointerException("newValue == null");
        this.value = newValue;
        return this;
    }

    public EnnNode<V> addChild(EnnNode<V> child) {
        if (child == this) throw new IllegalArgumentException("circular dependency on " + this);
        child.parent = this;
        if (children.equals(Collections.emptyList())) children = new ArrayList<>();
        children.add(child);
        return this;
    }

    /** Returns the children of this node. */
    public Collection<EnnNode<V>> children() {
        return children;
    }

    /** Traverses the tree, breadth-first. */
    public Iterator<EnnNode<V>> traverse() {
        return new EnnNode.BreadthFirstIterator<>(this);
    }

    public boolean isSyntheticRootForPartialTree() {
        return missingRootDummyNode;
    }

    static final class BreadthFirstIterator<V> implements Iterator<EnnNode<V>> {
        private final Queue<EnnNode<V>> queue = new ArrayDeque<>();

        BreadthFirstIterator(EnnNode<V> root) {
            queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public EnnNode<V> next() {
            EnnNode<V> result = queue.remove();
            queue.addAll(result.children);
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    interface MergeFunction<V> {
        V merge(@Nullable V existing, @Nullable V update);
    }

    static final EnnNode.MergeFunction FIRST_NOT_NULL = new EnnNode.MergeFunction() {
        @Override public Object merge(@Nullable Object existing, @Nullable Object update) {
            return existing != null ? existing : update;
        }
    };

    /**
     * Some operations do not require the entire span object. This creates a tree given (parent id,
     * id) pairs.
     *
     * @param <V> same type as {@link EnnNode#value}
     */
    public static final class TreeBuilder<V> {
        final EnnNode.MergeFunction<V> mergeFunction;
        final String traceId;

        public TreeBuilder(String traceId) {
            this(FIRST_NOT_NULL, traceId);
        }

        TreeBuilder(EnnNode.MergeFunction<V> mergeFunction, String traceId) {
            this.mergeFunction = mergeFunction;
            this.traceId = traceId;
        }

        String rootId = null;
        EnnNode<V> rootNode = null;
        // EnnNodes representing the trace tree
        Map<String, EnnNode<V>> idToNode = new LinkedHashMap<>();
        // Collect the parent-child relationships between all spans.
        Map<String, String> idToParent = new LinkedHashMap<>(idToNode.size());

        /** Returns false after logging to FINE if the value couldn't be added */
        public boolean addNode(@Nullable String parentId, String id, V value) {
            if (parentId == null) {
                if (rootId != null) {
//                    if (logger.isLoggable(FINE)) {
//                        logger.fine(format(
//                                "attributing span missing parent to root: traceId=%s, rootSpanId=%s, spanId=%s",
//                                traceId, rootId, id));
//                    }
                } else {
                    rootId = id;
                }
            } else if (parentId.equals(id)) {
//                if (logger.isLoggable(FINE)) {
//                    logger.fine(
//                            format("skipping circular dependency: traceId=%s, spanId=%s", traceId, id));
//                }
                return false;
            }

            EnnNode<V> node = new EnnNode<V>().value(value);
            // special-case root, and attribute missing parents to it. In
            // other words, assume that the first root is the "real" root.
            if (parentId == null && rootNode == null) {
                rootNode = node;
                rootId = id;
            } else if (parentId == null && rootId.equals(id)) {
                rootNode.value(mergeFunction.merge(rootNode.value, node.value));
            } else {
                EnnNode<V> previous = idToNode.put(id, node);
                if (previous != null) node.value(mergeFunction.merge(previous.value, node.value));
                idToParent.put(id, parentId);
            }
            return true;
        }

        /** Builds a tree from calls to {@link #addNode}, or returns an empty tree. */
        public EnnNode<V> build() {
            // Materialize the tree using parent - child relationships
            for (Map.Entry<String, String> entry : idToParent.entrySet()) {
                EnnNode<V> node = idToNode.get(entry.getKey());
                EnnNode<V> parent = idToNode.get(entry.getValue());
                if (parent == null) { // handle headless
                    if (rootNode == null) {
//                        if (logger.isLoggable(FINE)) {
//                            logger.fine("substituting dummy node for missing root span: traceId=" + traceId);
//                        }
                        rootNode = new EnnNode<>();
                        rootNode.missingRootDummyNode = true;
                    }
                    rootNode.addChild(node);
                } else {
                    parent.addChild(node);
                }
            }
            return rootNode != null ? rootNode : new EnnNode<>();
        }
    }
}
