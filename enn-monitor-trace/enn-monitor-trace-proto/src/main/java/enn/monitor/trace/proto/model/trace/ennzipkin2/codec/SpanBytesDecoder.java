
package enn.monitor.trace.proto.model.trace.ennzipkin2.codec;

import enn.monitor.trace.proto.model.trace.ennzipkin2.Span;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonCodec;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.Nullable;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.V2SpanReader;

import java.util.Collection;
import java.util.List;

/** This is separate from {@link SpanBytesEncoder}, as it isn't needed for instrumentation */
@SuppressWarnings("ImmutableEnumChecker") // because span is immutable
public enum SpanBytesDecoder implements BytesDecoder<Span> {
  /** Corresponds to the Zipkin v2 json format */
  JSON_V2 {
    @Override public Encoding encoding() {
      return Encoding.JSON;
    }

    @Override
    public boolean decode(byte[] span, Collection<Span> out) { // ex decode span in dependencies job
      return JsonCodec.read(new V2SpanReader(), span, out);
    }

    @Override public boolean decodeList(byte[] spans, Collection<Span> out) { // ex getTrace
      return JsonCodec.readList(new V2SpanReader(), spans, out);
    }

    /** Visible for testing. This returns the first span parsed from the serialized object or null */
    @Override @Nullable
    public Span decodeOne(byte[] span) {
      return JsonCodec.readOne(new V2SpanReader(), span);
    }

    /** Convenience method for {@link #decode(byte[], Collection)} */
    @Override public List<Span> decodeList(byte[] spans) {
      return JsonCodec.readList(new V2SpanReader(), spans);
    }
  }
}
