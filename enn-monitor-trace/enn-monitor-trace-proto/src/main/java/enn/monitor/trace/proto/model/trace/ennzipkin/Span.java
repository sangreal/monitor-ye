package enn.monitor.trace.proto.model.trace.ennzipkin;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.*;

import enn.monitor.trace.proto.model.trace.ennzipkin.internal.Nullable;

import static enn.monitor.trace.proto.model.trace.ennzipkin.internal.Util.*;

/**
 * A trace is a series of spans (often RPC calls) which form a latency tree.
 *
 * Spans are usually created by instrumentation in RPC clients or servers, but can also
 * represent in-process activity. Annotations in spans are similar to log statements, and are
 * sometimes created directly by application developers to indicate events of interest, such as a
 * cache miss.
 *
 * The root span is where {@link #parentId} is null; it usually has the longest {@link #duration} in the
 * trace.
 *
 */
public final class Span implements Comparable<Span>, Serializable { // for Spark jobs
    private static final long serialVersionUID = 0L;

    public final String traceId;

    public final String belongToBizline;

    public final String belongToService;

    public final String belongToInstance;

    public final String resource;

    public final String id;

    @Nullable
    public final String parentId;

    @Nullable
    public final Long timestamp;

    public final long startTime;

    @Nullable
    public final Long duration;

    public final int depth;

    public final List<Annotation> annotations;

    /**
     * Tags a span with context, usually to support query or aggregation.
     */
    public final List<BinaryAnnotation> binaryAnnotations;

    /**
     * True is a request to store this span even if it overrides sampling policy.
     */
    @Nullable
    public final Boolean debug;

    Span(Builder builder) {
        this.traceId = checkNotNull(builder.traceId, "traceId");
        this.resource = checkNotNull(builder.resource, "resource").isEmpty() ? ""
                : builder.resource.toLowerCase(Locale.ROOT);
        this.id = checkNotNull(builder.id, "id");
        this.parentId = builder.parentId;
        this.belongToBizline = builder.belongToBizline;
        this.belongToService = builder.belongToService;
        this.belongToInstance = builder.belongToInstance;
        this.timestamp = builder.timestamp;
        this.startTime = builder.startTime;
        this.duration = builder.duration;
        this.depth = builder.depth;
        this.annotations = sortedList(builder.annotations);
        this.binaryAnnotations = sortedList(builder.binaryAnnotations);
        this.debug = builder.debug;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTraceId() {
        return traceId;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getResource() {
        return resource;
    }

    public String getBelongToBizline() {
        return belongToBizline;
    }

    public String getBelongToService() {
        return belongToService;
    }

    public String getBelongToInstance() {
        return belongToInstance;
    }


    public Long getTimestamp() {
        return timestamp;
    }

    public long getStartTime() {
        return startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public Integer getDepth() {
        return depth;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public List<BinaryAnnotation> getBinaryAnnotations() {
        return binaryAnnotations;
    }

    public static final class Builder {
        String traceId;
        String resource;
        String id;
        String parentId;
        String belongToBizline;
        String belongToService;
        String belongToInstance;
        Long timestamp;
        long startTime;
        Long duration;
        int  depth;
        ArrayList<Annotation> annotations;
        ArrayList<BinaryAnnotation> binaryAnnotations;
        Boolean debug;
        boolean isClientSpan; // internal

        Builder() {
        }

        public Builder clear() {
            traceId = null;
            resource = null;
            id = null;
            parentId = null;
            belongToBizline = null;
            belongToService = null;
            belongToInstance = null;
            timestamp = null;
            startTime = 0;
            duration = null;
            depth = 0;
            if (annotations != null) annotations.clear();
            if (binaryAnnotations != null) binaryAnnotations.clear();
            debug = null;
            isClientSpan = false;
            return this;
        }

        Builder(Span source) {
            this.traceId = source.traceId;
            this.resource = source.resource;
            this.id = source.id;
            this.parentId = source.parentId;
            this.belongToBizline = source.belongToBizline;
            this.belongToService = source.belongToService;
            this.belongToInstance = source.belongToInstance;
            this.timestamp = source.timestamp;
            this.startTime = source.startTime;
            this.duration = source.duration;
            this.depth = source.depth;
            if (!source.annotations.isEmpty()) {
                this.annotations(source.annotations);
            }
            if (!source.binaryAnnotations.isEmpty()) {
                this.binaryAnnotations(source.binaryAnnotations);
            }
            this.debug = source.debug;
        }

        public Builder merge(Span that) {
            if (this.traceId == null) {
                this.traceId = that.traceId;
            }
            if (this.belongToBizline == null) {
                this.belongToBizline = that.belongToBizline;
            }
            if (this.id == null) {
                this.id = that.id;
            }
            if (this.parentId == null) {
                this.parentId = that.parentId;
            }

            // When we move to span model 2, remove this code in favor of using Span.kind == CLIENT
            boolean thisIsClientSpan = isClientSpan, thatIsClientSpan = false, thatIsServerSpan = false;

            // This guards to ensure we don't add duplicate annotations or binary annotations on merge
            if (!that.annotations.isEmpty()) {
                boolean thisHadNoAnnotations = this.annotations == null;
                for (Annotation a : that.annotations) {
                    if (a.value.equals(Constants.CLIENT_SEND)) thatIsClientSpan = true;
                    if (a.value.equals(Constants.SERVER_RECV)) thatIsServerSpan = true;
                    if (thisHadNoAnnotations || !this.annotations.contains(a)) {
                        addAnnotation(a);
                    }
                }
            }

            if (resourceUnknown(this)) {
                this.resource = that.resource;
            } else if (thisIsClientSpan && thatIsServerSpan) {
                if (!that.resource.isEmpty()) {
                    this.resource = that.resource; // prefer the server's span resource on collision
                }

                if (!that.belongToService.isEmpty()) {
                    this.belongToService = that.belongToService;
                }

                if (!that.belongToInstance.isEmpty()) {
                    this.belongToInstance = that.belongToInstance;
                }
            }

            if (!that.binaryAnnotations.isEmpty()) {
                boolean thisHadNoBinaryAnnotations = this.binaryAnnotations == null;
                for (BinaryAnnotation a : that.binaryAnnotations) {
                    if (thisHadNoBinaryAnnotations || !this.binaryAnnotations.contains(a)) {
                        addBinaryAnnotation(a);
                    }
                }
            }

            // Single timestamp makes duration easy: just choose max
            if (this.timestamp == null || that.timestamp == null || this.timestamp.equals(
                    that.timestamp)) {
                this.timestamp = this.timestamp != null ? this.timestamp : that.timestamp;
                if (this.duration == null) {
                    this.duration = that.duration;
                } else if (that.duration != null) {
                    this.duration = Math.max(this.duration, that.duration);
                }
            } else {
                // We have 2 different timestamps. If we have client data in either one of them, use that,
                // else set timestamp and duration to null
                if (thatIsClientSpan) {
                    this.timestamp = that.timestamp;
                    this.duration = that.duration;
                } else if (!thisIsClientSpan) {
                    this.timestamp = null;
                    this.duration = null;
                }
            }

            if (this.debug == null) {
                this.debug = that.debug;
            }
            return this;
        }

        /** @see Span#resource */
        public Builder resource(String resource) {
            this.resource = resource;
            return this;
        }

        /** @see Span#traceId */
        public Builder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        /** @see Span#id */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /** @see Span#parentId */
        public Builder parentId(@Nullable String parentId) {
            this.parentId = parentId;
            return this;
        }

        /** @see Span#belongToBizline */
        public Builder belongToBizline(String belongToBizline) {
            this.belongToBizline = belongToBizline;
            return this;
        }

        /** @see Span#belongToService */
        public Builder belongToService(String belongToService) {
            this.belongToService = belongToService;
            return this;
        }

        /** @see Span#belongToInstance */
        public Builder belongToInstance(String belongToInstance) {
            this.belongToInstance = belongToInstance;
            return this;
        }

        /** @see Span#timestamp */
        public Builder timestamp(@Nullable Long timestamp) {
            this.timestamp = timestamp != null && timestamp == 0L ? null : timestamp;
            return this;
        }

        /** @see Span#startTime */
        public Builder startTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        /** @see Span#duration */
        public Builder duration(@Nullable Long duration) {
            this.duration = duration != null && duration == 0L ? null : duration;
            return this;
        }

        /** @see Span#depth */
        public Builder depth(int depth) {
            this.depth = depth;
            return this;
        }

        /**
         * Replaces currently collected annotations.
         *
         * @see Span#annotations
         */
        public Builder annotations(Collection<Annotation> annotations) {
            if (this.annotations != null) this.annotations.clear();
            for (Annotation a : annotations) addAnnotation(a);
            return this;
        }

        /** @see Span#annotations */
        public Builder addAnnotation(Annotation annotation) {
            if (annotations == null) annotations = new ArrayList<>(4);
            if (annotation.value.equals(Constants.CLIENT_SEND)) isClientSpan = true;
            annotations.add(annotation);
            return this;
        }

        /**
         * Replaces currently collected binary annotations.
         *
         * @see Span#binaryAnnotations
         */
        public Builder binaryAnnotations(Collection<BinaryAnnotation> binaryAnnotations) {
            if (this.binaryAnnotations != null) this.binaryAnnotations.clear();
            for (BinaryAnnotation b : binaryAnnotations) addBinaryAnnotation(b);
            return this;
        }

        /** @see Span#binaryAnnotations */
        public Builder addBinaryAnnotation(BinaryAnnotation binaryAnnotation) {
            if (binaryAnnotations == null) binaryAnnotations = new ArrayList<>(4);
            binaryAnnotations.add(binaryAnnotation);
            return this;
        }

        /** @see Span#debug */
        public Builder debug(@Nullable Boolean debug) {
            this.debug = debug;
            return this;
        }

        public Span build() {
            return new Span(this);
        }
    }

    @Override
    public String toString() {
        return new String(Codec.JSON.writeSpan(this), UTF_8);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Span)) return false;
        Span that = (Span) o;
        return  (this.traceId.equals(that.traceId))
                && (this.resource.equals(that.resource))
                && (this.id.equals(that.id))
                && equal(this.parentId, that.parentId)
                && (this.belongToBizline.equals(that.belongToBizline))
                && (this.belongToService.equals(that.belongToService))
                && (this.belongToInstance.equals(that.belongToInstance))
                && equal(this.timestamp, that.timestamp)
                && equal(this.duration, that.duration)
                && (this.annotations.equals(that.annotations))
                && (this.binaryAnnotations.equals(that.binaryAnnotations))
                && equal(this.debug, that.debug);
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (traceId == null) ? 0 : traceId.hashCode();
        h *= 1000003;
        h ^= resource.hashCode();
        h *= 1000003;
        h ^= (id == null) ? 0 : id.hashCode();
        h *= 1000003;
        h ^= (parentId == null) ? 0 : parentId.hashCode();
        h *= 1000003;
        h ^= (belongToBizline == null) ? 0 : belongToBizline.hashCode();
        h *= 1000003;
        h ^= (belongToService == null) ? 0 : belongToService.hashCode();
        h *= 1000003;
        h ^= (belongToInstance == null) ? 0 : belongToInstance.hashCode();
        h *= 1000003;
        h ^= (timestamp == null) ? 0 : timestamp.hashCode();
        h *= 1000003;
        h ^= (duration == null) ? 0 : duration.hashCode();
        h *= 1000003;
        h ^= annotations.hashCode();
        h *= 1000003;
        h ^= binaryAnnotations.hashCode();
        h *= 1000003;
        h ^= (debug == null) ? 0 : debug.hashCode();
        return h;
    }

    /** Compares by {@link #timestamp}, then {@link #resource}. */
    @Override
    public int compareTo(Span that) {
        if (this == that) return 0;
        long x = this.timestamp == null ? Long.MIN_VALUE : this.timestamp;
        long y = that.timestamp == null ? Long.MIN_VALUE : that.timestamp;
        int byTimestamp = x < y ? -1 : x == y ? 0 : 1;  // Long.compareTo is JRE 7+
        if (byTimestamp != 0) return byTimestamp;
        return this.resource.compareTo(that.resource);
    }

    // Since this is an immutable object, and we have thrift handy, defer to a serialization proxy.
    final Object writeReplace() throws ObjectStreamException {
        return new SerializedForm(Codec.THRIFT.writeSpan(this));
    }

    static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0L;

        private final byte[] bytes;

        SerializedForm(byte[] bytes) {
            this.bytes = bytes;
        }

        Object readResolve() throws ObjectStreamException {
            try {
                return Codec.THRIFT.readSpan(bytes);
            } catch (IllegalArgumentException e) {
                throw new StreamCorruptedException(e.getMessage());
            }
        }
    }

    static boolean resourceUnknown(Span.Builder span) {
        return span.resource == null || span.resource.length() == 0 || span.resource.equals("unknown");
    }
}
