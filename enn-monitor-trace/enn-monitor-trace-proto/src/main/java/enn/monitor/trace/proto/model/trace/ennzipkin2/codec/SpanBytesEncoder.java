
package enn.monitor.trace.proto.model.trace.ennzipkin2.codec;


import enn.monitor.trace.proto.model.trace.ennzipkin2.Span;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.Buffer;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonCodec;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.V1SpanWriter;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.V2SpanWriter;

import java.util.List;

/** Limited interface needed by those writing span reporters */
@SuppressWarnings("ImmutableEnumChecker") // because span is immutable
public enum SpanBytesEncoder implements BytesEncoder<Span> {
  /** Corresponds to the Zipkin v1 json format (with tags as binary annotations) */
  JSON_V1 {
    final Buffer.Writer<Span> writer = new V1SpanWriter();

    @Override public Encoding encoding() {
      return Encoding.JSON;
    }

    @Override public int sizeInBytes(Span input) {
      return writer.sizeInBytes(input);
    }

    @Override public byte[] encode(Span span) {
      return JsonCodec.write(writer, span);
    }

    @Override public byte[] encodeList(List<Span> spans) {
      return JsonCodec.writeList(writer, spans);
    }

    @Override public int encodeList(List<Span> spans, byte[] out, int pos) {
      return JsonCodec.writeList(writer, spans, out, pos);
    }
  },
  /** Corresponds to the Zipkin v2 json format */
  JSON_V2 {
    final Buffer.Writer<Span> writer = new V2SpanWriter();

    @Override public Encoding encoding() {
      return Encoding.JSON;
    }

    @Override public int sizeInBytes(Span input) {
      return writer.sizeInBytes(input);
    }

    @Override public byte[] encode(Span span) {
      return JsonCodec.write(writer, span);
    }

    @Override public byte[] encodeList(List<Span> spans) {
      return JsonCodec.writeList(writer, spans);
    }

    @Override public int encodeList(List<Span> spans, byte[] out, int pos) {
      return JsonCodec.writeList(writer, spans, out, pos);
    }
  };

  /** Allows you to encode a list of spans onto a specific offset. For example, when nesting */
  public abstract int encodeList(List<Span> spans, byte[] out, int pos);
}
