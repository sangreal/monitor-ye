package enn.monitor.trace.proto.model.trace.ennzipkin;

import enn.monitor.trace.proto.model.trace.ennzipkin.internal.JsonCodec;
import enn.monitor.trace.proto.model.trace.ennzipkin.internal.ThriftCodec;

import java.util.List;

/** Decodes spans from serialized bytes. */
public interface SpanDecoder {
    SpanDecoder JSON_DECODER = new JsonCodec();
    SpanDecoder THRIFT_DECODER = new ThriftCodec();

    /** throws {@linkplain IllegalArgumentException} if a span couldn't be decoded */
    Span readSpan(byte[] span);

    /** throws {@linkplain IllegalArgumentException} if the spans couldn't be decoded */
    List<Span> readSpans(byte[] span);
}
