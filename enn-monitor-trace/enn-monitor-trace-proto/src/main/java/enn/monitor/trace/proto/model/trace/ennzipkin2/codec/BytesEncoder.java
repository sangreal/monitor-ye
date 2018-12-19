package enn.monitor.trace.proto.model.trace.ennzipkin2.codec;

import java.util.List;

public interface BytesEncoder<T> {
    Encoding encoding();

    int sizeInBytes(T input);

    /** Serializes an object into its binary form. */
    byte[] encode(T input);

    /** Serializes a list of objects into their binary form. */
    byte[] encodeList(List<T> input);
}
