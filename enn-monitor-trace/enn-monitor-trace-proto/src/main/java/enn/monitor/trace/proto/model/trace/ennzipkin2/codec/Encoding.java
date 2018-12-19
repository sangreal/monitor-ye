package enn.monitor.trace.proto.model.trace.ennzipkin2.codec;

import java.util.List;


public enum Encoding {
    JSON {
        /** Encoding overhead of a single element is brackets */
        @Override public int listSizeInBytes(int encodedSizeInBytes) {
            return 2 + encodedSizeInBytes;
        }

        /** Encoding overhead is brackets and a comma for each span over 1 */
        @Override public int listSizeInBytes(List<byte[]> values) {
            int sizeInBytes = 2; // brackets
            for (int i = 0, length = values.size(); i < length; ) {
                sizeInBytes += values.get(i++).length;
                if (i < length) sizeInBytes++;
            }
            return sizeInBytes;
        }
    };

    /** Like {@link #listSizeInBytes(List)}, except for a single element. */
    public abstract int listSizeInBytes(int encodedSizeInBytes);

    public abstract int listSizeInBytes(List<byte[]> values);
}
