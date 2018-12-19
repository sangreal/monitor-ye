package enn.monitor.trace.proto.model.trace.ennzipkin;

import enn.monitor.trace.proto.model.trace.ennzipkin.internal.JsonCodec;
import enn.monitor.trace.proto.model.trace.ennzipkin.internal.ThriftCodec;

/**
 * Methods make an attempt to perform codec operations, failing to null.
 */
public interface Codec extends SpanDecoder {

    JsonCodec JSON = new JsonCodec();
    ThriftCodec THRIFT = new ThriftCodec();

    int sizeInBytes(Span value);

    byte[] writeSpan(Span value);
}

