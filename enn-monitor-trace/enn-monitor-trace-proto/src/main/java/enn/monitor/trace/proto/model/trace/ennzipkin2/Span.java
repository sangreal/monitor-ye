package enn.monitor.trace.proto.model.trace.ennzipkin2;

import enn.monitor.trace.proto.model.trace.ennzipkin2.codec.SpanBytesEncoder;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.Nullable;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.*;

/**
 * A trace is a series of spans (often RPC calls) which form a latency tree.
 *
 * Spans are usually created by instrumentation in RPC clients or servers, but can also represent
 * in-process activity. Annotations in spans are similar to log statements, and are sometimes
 * created directly by application developers to indicate events of interest, such as a cache miss.
 *
 * The root span is where {#parentId} is null; it usually has the longest {#duration}
 * in the trace.
 *
 * Span identifiers are packed into longs, but should be treated opaquely. ID encoding is 16 or
 * 32 character lower-hex, to avoid signed interpretation.
 *
 * Relationship to {zipkin.Span}
 *
 * This type is intended to replace use of {zipkin.Span}. Particularly, tracers represent a
 * single-host view of an operation. By making one endpoint implicit for all data, this type does
 * not need to repeat endpoints on each data like {zipkin.Span} does. This results in simpler
 * and smaller data.
 */
//@Immutable
public final class Span implements Serializable { // for Spark and Flink jobs
    static final Charset UTF_8 = Charset.forName("UTF-8");

    static final int FLAG_DEBUG = 1 << 1;
    static final int FLAG_DEBUG_SET = 1 << 2;
    static final int FLAG_SHARED = 1 << 3;
    static final int FLAG_SHARED_SET = 1 << 4;

    private static final long serialVersionUID = 0L;

    /**
     * Trace identifier, set on all spans within it.
     *
     * Encoded as 16 or 32 lowercase hex characters corresponding to 64 or 128 bits. For example, a
     * 128bit trace ID looks like {4e441824ec2b6a44ffdc9bb9a6453df3}.
     *
     * Some systems downgrade trace identifiers to 64bit by dropping the left-most 16 characters.
     * For example, {4e441824ec2b6a44ffdc9bb9a6453df3} becomes {ffdc9bb9a6453df3}.
     */
    public String traceId() {
        return traceId;
    }

    /**
     * The parent's {#id} or null if this the root span in a trace.
     *
     * This is the same encoding as {@link #id}. For example {ffdc9bb9a6453df3}
     */
    @Nullable
    public String parentId() {
        return parentId;
    }

    /**
     * Unique 64bit identifier for this operation within the trace.
     *
     * Encoded as 16 lowercase hex characters. For example {ffdc9bb9a6453df3}
     *
     * A span is uniquely identified in storage by ({#traceId}, {#id()}).
     */
    public String id() {
        return id;
    }

    /** Indicates the primary span type. */
    public enum Kind {
        CLIENT,
        SERVER,
        /**
         * When present, {#timestamp()} is the moment a producer sent a message to a destination.
         * {#duration()} represents delay sending the message, such as batching, while {
         * #remoteEndpoint()} indicates the destination, such as a broker.
         *
         * Unlike {#CLIENT}, messaging spans never share a span ID. For example, the {
         * #CONSUMER} of the same message has {#parentId()} set to this span's {#id()}.
         */
        PRODUCER,
        /**
         * When present, {#timestamp()} is the moment a consumer received a message from an
         * origin. {#duration()} represents delay consuming the message, such as from backlog,
         * while {#remoteEndpoint()} indicates the origin, such as a broker.
         *
         * Unlike {#SERVER}, messaging spans never share a span ID. For example, the {
         * #PRODUCER} of this message is the {#parentId()} of this span.
         */
        CONSUMER
    }

    /** When present, used to interpret {#remoteEndpoint} */
    @Nullable public Kind kind() {
        return kind;
    }

    public String belongToBizline() { return belongToBizline; }

    public String belongToService() { return belongToService; }

    public String belongToInstance() { return belongToInstance;}

    /**
     * Span resource in lowercase, rpc method for example.
     *
     * Conventionally, when the span resource isn't known, resource = "unknown".
     */
    @Nullable public String resource() {
        return resource;
    }

    /**
     * Epoch microseconds of the start of this span, possibly absent if this an incomplete span.
     *
     * This value should be set directly by instrumentation, using the most precise value possible.
     * For example, {gettimeofday} or multiplying {System#currentTimeMillis} by 1000.
     *
     * There are three known edge-cases where this could be reported absent:
     *
     * Note: timestamps at or before epoch (0L == 1970) are invalid
     */
    @Nullable public Long timestamp() {
        return timestamp > 0 ? timestamp : null;
    }

    /**
     * Like {@link #timestamp()} except returns a primitive where zero implies absent.
     *
     * Using this method will avoid allocation, so is encouraged when copying data.
     */
    public long timestampAsLong() {
        return timestamp;
    }

    /**
     * Measurement in microseconds of the critical path, if known. Durations of less than one
     * microsecond must be rounded up to 1 microsecond.
     *
     * This value should be set directly, as opposed to implicitly via annotation timestamps. Doing
     * so encourages precision decoupled from problems of clocks, such as skew or NTP updates causing
     * time to move backwards.
     *
     * If this field is persisted as unset, zipkin will continue to work, except duration query
     * support will be implementation-specific. Similarly, setting this field non-atomically is
     * implementation-specific.
     *
     * This field is i64 vs i32 to support spans longer than 35 minutes.
     *
     * see #durationAsLong()
     */
    @Nullable public Long duration() {
        return duration > 0 ? duration : null;
    }

    /**
     * Like {@link #duration()} except returns a primitive where zero implies absent.
     *
     * Using this method will avoid allocation, so is encouraged when copying data.
     */
    public long durationAsLong() {
        return duration;
    }

    /**
     * The host that recorded this span, primarily for query by service name.
     *
     * Instrumentation should always record this and be consistent as possible with the service
     * name as it is used in search. This is nullable for legacy reasons.
     */
    // Nullable for data conversion especially late arriving data which might not have an annotation
    @Nullable public Endpoint localEndpoint() {
        return localEndpoint;
    }

    /**
     * When an RPC (or messaging) span, indicates the other side of the connection.
     *
     * By recording the remote endpoint, your trace will contain network context even if the peer
     * is not tracing. For example,For example, you can record the IP from the {@code X-Forwarded-For}
     * header or or the service name and socket of a remote peer.
     */
    @Nullable public Endpoint remoteEndpoint() {
        return remoteEndpoint;
    }

    /**
     * Events that explain latency with a timestamp. Unlike log statements, annotations are often
     * short or contain codes: for example "brave.flush". Annotations are sorted ascending by
     * timestamp.
     */
    public List<Annotation> annotations() {
        return annotations;
    }

    /**
     * Tags a span with context, usually to support query or aggregation.
     *
     * For example, a tag key could be {@code "http.path"}.
     */
    public Map<String, String> tags() {
        return tags;
    }

    /** True is a request to store this span even if it overrides sampling policy. */
    @Nullable public Boolean debug() {
        return (flags & FLAG_DEBUG_SET) == FLAG_DEBUG_SET
                ? (flags & FLAG_DEBUG) == FLAG_DEBUG
                : null;
    }

    /**
     * True if we are contributing to a span started by another tracer (ex on a different host).
     * Defaults to null. When set, it is expected for {@link #kind()} to be {@link Kind#SERVER}.
     *
     * When an RPC trace is client-originated, it will be sampled and the same span ID is used for
     * the server side. However, the server shouldn't set span.timestamp or duration since it didn't
     * start the span.
     */
    @Nullable public Boolean shared() {
        return (flags & FLAG_SHARED_SET) == FLAG_SHARED_SET
                ? (flags & FLAG_SHARED) == FLAG_SHARED
                : null;
    }

    @Nullable public String localServiceName() {
        Endpoint localEndpoint = localEndpoint();
        return localEndpoint != null ? localEndpoint.serviceName() : null;
    }

    @Nullable public String remoteServiceName() {
        Endpoint remoteEndpoint = remoteEndpoint();
        return remoteEndpoint != null ? remoteEndpoint.serviceName() : null;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        String traceId, parentId, id;
        Kind kind;
        String belongToBizline, belongToService, belongToInstance;
        String resource;
        long timestamp, duration; // zero means null
        Endpoint localEndpoint, remoteEndpoint;
        ArrayList<Annotation> annotations;
        TreeMap<String, String> tags;
        int flags = 0; // bit field for timestamp and duration

        public Builder clear() {
            traceId = null;
            parentId = null;
            id = null;
            kind = null;
            belongToBizline = null;
            belongToService = null;
            belongToInstance = null;
            resource = null;
            timestamp = 0L;
            duration = 0L;
            localEndpoint = null;
            remoteEndpoint = null;
            if (annotations != null) annotations.clear();
            if (tags != null) tags.clear();
            flags = 0;
            return this;
        }

        @Override public Builder clone() {
            Builder result = new Builder();
            result.traceId = traceId;
            result.parentId = parentId;
            result.id = id;
            result.kind = kind;
            result.belongToBizline = belongToBizline;
            result.belongToService = belongToService;
            result.belongToInstance = belongToInstance;
            result.resource = resource;
            result.timestamp = timestamp;
            result.duration = duration;
            result.localEndpoint = localEndpoint;
            result.remoteEndpoint = remoteEndpoint;
            if (annotations != null) {
                result.annotations = (ArrayList) annotations.clone();
            }
            if (tags != null) {
                result.tags = (TreeMap) tags.clone();
            }
            result.flags = flags;
            return result;
        }

        Builder(Span source) {
            traceId = source.traceId;
            parentId = source.parentId;
            id = source.id;
            kind = source.kind;
            belongToBizline = source.belongToBizline;
            belongToService = source.belongToService;
            belongToInstance = source.belongToInstance;
            resource = source.resource;
            timestamp = source.timestamp;
            duration = source.duration;
            localEndpoint = source.localEndpoint;
            remoteEndpoint = source.remoteEndpoint;
            if (!source.annotations.isEmpty()) {
                annotations = new ArrayList<>(source.annotations.size());
                annotations.addAll(source.annotations);
            }
            if (!source.tags.isEmpty()) {
                tags = new TreeMap<>();
                tags.putAll(source.tags);
            }
            flags = source.flags;
        }

        @Nullable public Kind kind() {
            return kind;
        }

        @Nullable public Endpoint localEndpoint() {
            return localEndpoint;
        }

        /**
         * throws IllegalArgumentException if not lower-hex format
         * see Span#id()
         */
        public Builder traceId(String traceId) {
            this.traceId = normalizeTraceId(traceId);
            return this;
        }

        /**
         * throws IllegalArgumentException if not lower-hex format
         * see Span#parentId()
         */
        public Builder parentId(@Nullable String parentId) {
            if (parentId == null) {
                this.parentId = null;
                return this;
            }
            int length = parentId.length();
            if (length > 16) throw new IllegalArgumentException("parentId.length > 16");
            validateHex(parentId);
            this.parentId = length < 16 ? padLeft(parentId, 16) : parentId;
            return this;
        }

        /**
         * throws IllegalArgumentException if not lower-hex format
         * see Span#id()
         */
        public Builder id(String id) {
            if (id == null) throw new NullPointerException("id == null");
            int length = id.length();
            if (length > 16) throw new IllegalArgumentException("id.length > 16");
            validateHex(id);
            this.id = length < 16 ? padLeft(id, 16) : id;
            return this;
        }

        /** see Span#kind */
        public Builder kind(@Nullable Kind kind) {
            this.kind = kind;
            return this;
        }

        /** see Span#belongToBizline */
        public Builder belongToBizline(String belongToBizline) {
            this.belongToBizline = belongToBizline;
            return this;
        }

        /** see Span#belongToService */
        public Builder belongToService(String belongToService) {
            this.belongToService = belongToService;
            return this;
        }

        /** see Span#belongToInstance */
        public Builder belongToInstance(String belongToInstance) {
            this.belongToInstance = belongToInstance;
            return this;
        }

        /** see Span#resource */
        public Builder resource(@Nullable String resource) {
            this.resource = resource == null || resource.isEmpty() ? null : resource.toLowerCase(Locale.ROOT);
            return this;
        }

        /** see Span#timestampAsLong() */
        public Builder timestamp(long timestamp) {
            if (timestamp < 0L) timestamp = 0L;
            this.timestamp = timestamp;
            return this;
        }

        /** see Span#timestamp() */
        public Builder timestamp(@Nullable Long timestamp) {
            if (timestamp == null || timestamp == 0L) timestamp = 0L;
            this.timestamp = timestamp;
            return this;
        }

        /** see Span#durationAsLong() */
        public Builder duration(long duration) {
            if (duration < 0L) duration = 0L;
            this.duration = duration;
            return this;
        }

        /** see Span#duration() */
        public Builder duration(@Nullable Long duration) {
            if (duration == null || duration == 0L) duration = 0L;
            this.duration = duration;
            return this;
        }

        /** see Span#localEndpoint */
        public Builder localEndpoint(@Nullable Endpoint localEndpoint) {
            this.localEndpoint = localEndpoint;
            return this;
        }

        /** see Span#remoteEndpoint */
        public Builder remoteEndpoint(@Nullable Endpoint remoteEndpoint) {
            this.remoteEndpoint = remoteEndpoint;
            return this;
        }

        /** see Span#annotations */
        public Builder addAnnotation(long timestamp, String value) {
            if (annotations == null) annotations = new ArrayList<>(2);
            annotations.add(Annotation.create(timestamp, value));
            return this;
        }

        /** @see Span#tags */
        public Builder putTag(String key, String value) {
            if (tags == null) tags = new TreeMap<>();
            if (key == null) throw new NullPointerException("key == null");
            if (value == null) throw new NullPointerException("value of " + key + " == null");
            this.tags.put(key, value);
            return this;
        }

        /** see Span#debug */
        public Builder debug(boolean debug) {
            flags |= FLAG_DEBUG_SET;
            if (debug) {
                flags |= FLAG_DEBUG;
            } else {
                flags &= ~FLAG_DEBUG;
            }
            return this;
        }

        /** see Span#debug */
        public Builder debug(@Nullable Boolean debug) {
            if (debug != null) return debug((boolean) debug);
            flags &= ~FLAG_DEBUG_SET;
            return this;
        }

        /** see Span#shared */
        public Builder shared(boolean shared) {
            flags |= FLAG_SHARED_SET;
            if (shared) {
                flags |= FLAG_SHARED;
            } else {
                flags &= ~FLAG_SHARED;
            }
            return this;
        }

        /** see Span#shared */
        public Builder shared(@Nullable Boolean shared) {
            if (shared != null) return shared((boolean) shared);
            flags &= ~FLAG_SHARED_SET;
            return this;
        }

        public Span build() {
            String missing = "";
            if (traceId == null) missing += " traceId";
            if (id == null) missing += " id";
            if (!"".equals(missing)) throw new IllegalStateException("Missing :" + missing);
            return new Span(this);
        }

        Builder() {
        }
    }

    @Override public String toString() {
        return new String(SpanBytesEncoder.JSON_V2.encode(this), UTF_8);
    }

    /**
     * Returns a valid lower-hex trace ID, padded left as needed to 16 or 32 characters.
     *
     * throws IllegalArgumentException if oversized or not lower-hex
     */
    public static String normalizeTraceId(String traceId) {
        if (traceId == null) throw new NullPointerException("traceId == null");
        int length = traceId.length();
        if (length > 32) throw new IllegalArgumentException("traceId.length > 32");
        validateHex(traceId);
        if (length == 32 || length == 16) {
            return traceId;
        } else if (length < 16) {
            return padLeft(traceId, 16);
        } else {
            return padLeft(traceId, 32);
        }
    }

    static String padLeft(String id, int desiredLength) {
        StringBuilder builder = new StringBuilder(desiredLength);
        int offset = desiredLength - id.length();

        for (int i = 0; i < offset; i++) builder.append('0');
        builder.append(id);
        return builder.toString();
    }

    static void validateHex(String id) {
        for (int i = 0, length = id.length(); i < length; i++) {
            char c = id.charAt(i);
            if ((c < '0' || c > '9') && (c < 'a' || c > 'f')) {
                throw new IllegalArgumentException(id + " should be lower-hex encoded with no prefix");
            }
        }
    }

    static <T extends Comparable<? super T>> List<T> sortedList(@Nullable List<T> in) {
        if (in == null || in.isEmpty()) return Collections.emptyList();
        if (in.size() == 1) return Collections.singletonList(in.get(0));
        Object[] array = in.toArray();
        Arrays.sort(array);
        List result = Arrays.asList(array);
        return Collections.unmodifiableList(result);
    }

    // Custom impl to reduce GC churn and Kryo which cannot handle AutoValue subclass
    // See https://github.com/openzipkin/zipkin/issues/1879
    final String traceId, parentId, id;
    final String belongToBizline, belongToService, belongToInstance;
    final Kind kind;
    final String resource;
    final long timestamp, duration; // zero means null, saving 2 object references
    final Endpoint localEndpoint, remoteEndpoint;
    final List<Annotation> annotations;
    final Map<String, String> tags;
    final int flags; // bit field for timestamp and duration, saving 2 object references

    Span(Builder builder) {
        traceId = builder.traceId;
        parentId = builder.parentId;
        id = builder.id;
        belongToBizline = builder.belongToBizline;
        belongToService = builder.belongToService;
        belongToInstance = builder.belongToInstance;
        kind = builder.kind;
        resource = builder.resource;
        timestamp = builder.timestamp;
        duration = builder.duration;
        localEndpoint = builder.localEndpoint;
        remoteEndpoint = builder.remoteEndpoint;
        annotations = sortedList(builder.annotations);
        tags = builder.tags == null ? Collections.emptyMap() : new LinkedHashMap<>(builder.tags);
        flags = builder.flags;
    }

    @Override public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Span)) return false;
        Span that = (Span) o;
        return (traceId.equals(that.traceId))
                && ((parentId == null) ? (that.parentId == null) : parentId.equals(that.parentId))
                && (id.equals(that.id))
                && ((kind == null) ? (that.kind == null) : kind.equals(that.kind))
                && ((belongToBizline == null) ? (that.belongToBizline == null) : belongToBizline.equals(that.belongToBizline))
                && ((belongToService == null) ? (that.belongToService == null) : belongToService.equals(that.belongToService))
                && ((belongToInstance == null) ? (that.belongToInstance == null) : belongToInstance.equals(that.belongToInstance))
                && ((resource == null) ? (that.resource == null) : resource.equals(that.resource))
                && (timestamp == that.timestamp)
                && (duration == that.duration)
                && ((localEndpoint == null)
                ? (that.localEndpoint == null) : localEndpoint.equals(that.localEndpoint))
                && ((remoteEndpoint == null)
                ? (that.remoteEndpoint == null) : remoteEndpoint.equals(that.remoteEndpoint))
                && (annotations.equals(that.annotations))
                && (tags.equals(that.tags))
                && (flags == that.flags);
    }

    @Override public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= traceId.hashCode();
        h *= 1000003;
        h ^= (parentId == null) ? 0 : parentId.hashCode();
        h *= 1000003;
        h ^= id.hashCode();
        h *= 1000003;
        h ^= (kind == null) ? 0 : kind.hashCode();
        h *= 1000003;
        h ^= (belongToBizline == null) ? 0 : belongToBizline.hashCode();
        h *= 1000003;
        h ^= (belongToService == null) ? 0 : belongToService.hashCode();
        h *= 1000003;
        h ^= (belongToInstance == null) ? 0 : belongToInstance.hashCode();
        h *= 1000003;
        h ^= (resource == null) ? 0 : resource.hashCode();
        h *= 1000003;
        h ^= (int) (h ^ ((timestamp >>> 32) ^ timestamp));
        h *= 1000003;
        h ^= (int) (h ^ ((duration >>> 32) ^ duration));
        h *= 1000003;
        h ^= (localEndpoint == null) ? 0 : localEndpoint.hashCode();
        h *= 1000003;
        h ^= (remoteEndpoint == null) ? 0 : remoteEndpoint.hashCode();
        h *= 1000003;
        h ^= annotations.hashCode();
        h *= 1000003;
        h ^= tags.hashCode();
        h *= 1000003;
        h ^= flags;
        return h;
    }

//    // This is an immutable object, and our encoder is faster than java's: use a serialization proxy.
//    final Object writeReplace() throws ObjectStreamException {
//        return new SerializedForm(SpanBytesEncoder.JSON_V2.encode(this));
//    }
//
//    private static final class SerializedForm implements Serializable {
//        private static final long serialVersionUID = 0L;
//
//        final byte[] bytes;
//
//        SerializedForm(byte[] bytes) {
//            this.bytes = bytes;
//        }
//
//        Object readResolve() throws ObjectStreamException {
//            try {
//                return SpanBytesDecoder.JSON_V2.decodeOne(bytes);
//            } catch (IllegalArgumentException e) {
//                throw new StreamCorruptedException(e.getMessage());
//            }
//        }
//    }
}


