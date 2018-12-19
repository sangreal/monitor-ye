package enn.monitor.trace.proto.model.trace.ennzipkin.internal;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enn.monitor.trace.proto.model.trace.ennzipkin.*;
import enn.monitor.trace.proto.model.trace.ennzipkin.BinaryAnnotation.Type;
import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.Buffer;

import static enn.monitor.trace.proto.model.trace.ennzipkin.internal.Util.writeBase64Url;
import static enn.monitor.trace.proto.model.trace.ennzipkin2.internal.Buffer.asciiSizeInBytes;
import static enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonCodec.writeList;
import static enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonEscaper.jsonEscape;
import static enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonEscaper.jsonEscapedSizeInBytes;
import static enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonEscaper.needsJsonEscaping;
import static enn.monitor.trace.proto.model.trace.ennzipkin2.internal.JsonCodec.write;
import static java.lang.Double.doubleToRawLongBits;
import static java.lang.String.format;
import static org.apache.commons.codec.CharEncoding.UTF_8;

/**
 * This explicitly constructs instances of model classes via manual parsing for a number of
 * reasons.
 *
 * <ul>
 *   <li>Eliminates the need to keep separate model classes for thrift vs json</li>
 *   <li>Avoids magic field initialization which, can miss constructor guards</li>
 *   <li>Allows us to safely re-use the json form in toString methods</li>
 *   <li>Encourages logic to be based on the thrift shape of objects</li>
 *   <li>Ensures the order and naming of the fields in json is stable</li>
 * </ul>
 *
 * <p> There is the up-front cost of creating this, and maintenance of this to consider. However,
 * this should be easy to justify as these objects don't change much at all.
 */
public final class JsonCodec implements Codec {
    static final long MAX_SAFE_INTEGER = 9007199254740991L;  // 53 bits
    static final String ENDPOINT_HEADER = ",\"endpoint\":";

    static final JsonReaderAdapter<Endpoint> ENDPOINT_READER = new JsonReaderAdapter<Endpoint>() {
        @Override public Endpoint fromJson(JsonReader reader) throws IOException {
            Endpoint.Builder result = Endpoint.builder();
            reader.beginObject();
            while (reader.hasNext()) {
                String nextName = reader.nextName();
                if (nextName.equals("serviceName")) {
                    result.serviceName(reader.nextString());
                } else if (nextName.equals("ipv4") || nextName.equals("ipv6")) {
                    result.parseIp(reader.nextString());
                } else if (nextName.equals("port")) {
                    result.port(reader.nextInt());
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return result.build();
        }
    };

    static final Buffer.Writer<Endpoint> ENDPOINT_WRITER = new Buffer.Writer<Endpoint>() {
        @Override public int sizeInBytes(Endpoint v) {
            enn.monitor.trace.proto.model.trace.ennzipkin2.Endpoint value = v.toV2();
            int sizeInBytes = 17; // {"serviceName":""
            if (value.serviceName() != null) {
                sizeInBytes += jsonEscapedSizeInBytes(value.serviceName());
            }
            if (value.ipv4() != null) {
                if (sizeInBytes != 1) sizeInBytes++; // ,
                sizeInBytes += 9; // "ipv4":""
                sizeInBytes += value.ipv4().length();
            }
            if (value.ipv6() != null) {
                if (sizeInBytes != 1) sizeInBytes++; // ,
                sizeInBytes += 9; // "ipv6":""
                sizeInBytes += value.ipv6().length();
            }
            if (value.port() != null) {
                if (sizeInBytes != 1) sizeInBytes++; // ,
                sizeInBytes += 7; // "port":
                sizeInBytes += asciiSizeInBytes(value.port());
            }
            return ++sizeInBytes; // }
        }

        @Override public void write(Endpoint v, Buffer b) {
            enn.monitor.trace.proto.model.trace.ennzipkin2.Endpoint value = v.toV2();
            b.writeAscii("{\"serviceName\":\"");
            if (value.serviceName() != null) {
                b.writeUtf8(jsonEscape(value.serviceName()));
            }
            b.writeByte('"');
            if (value.ipv4() != null) {
                b.writeAscii(",\"ipv4\":\"");
                b.writeAscii(value.ipv4()).writeByte('"');
            }
            if (value.ipv6() != null) {
                b.writeAscii(",\"ipv6\":\"");
                b.writeAscii(value.ipv6()).writeByte('"');
            }
            if (value.port() != null) {
                b.writeAscii(",\"port\":").writeAscii(value.port());
            }
            b.writeByte('}');
        }
    };

    static final JsonReaderAdapter<Annotation> ANNOTATION_READER =
            new JsonReaderAdapter<Annotation>() {
                @Override public Annotation fromJson(JsonReader reader) throws IOException {
                    Annotation.Builder result = Annotation.builder();
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String nextName = reader.nextName();
                        if (nextName.equals("timestamp")) {
                            result.timestamp(reader.nextLong());
                        } else if (nextName.equals("value")) {
                            result.value(reader.nextString());
                        } else if (nextName.equals("endpoint") && reader.peek() != JsonToken.NULL) {
                            result.endpoint(ENDPOINT_READER.fromJson(reader));
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    return result.build();
                }
            };

    static final Buffer.Writer<Annotation> ANNOTATION_WRITER = new Buffer.Writer<Annotation>() {
        @Override public int sizeInBytes(Annotation value) {
            int sizeInBytes = 0;
            sizeInBytes += "{\"timestamp\":".length() + asciiSizeInBytes(value.timestamp);
            sizeInBytes += ",\"value\":\"".length() + jsonEscapedSizeInBytes(value.value) + 1;
            if (value.endpoint != null) {
                sizeInBytes += ENDPOINT_HEADER.length() + ENDPOINT_WRITER.sizeInBytes(value.endpoint);
            }
            return ++sizeInBytes;// end curly-brace
        }

        @Override public void write(Annotation value, Buffer b) {
            b.writeAscii("{\"timestamp\":").writeAscii(value.timestamp);
            b.writeAscii(",\"value\":\"").writeUtf8(jsonEscape(value.value)).writeByte('"');
            if (value.endpoint != null) {
                b.writeAscii(ENDPOINT_HEADER);
                ENDPOINT_WRITER.write(value.endpoint, b);
            }
            b.writeByte('}');
        }
    };

    static final JsonReaderAdapter<BinaryAnnotation> BINARY_ANNOTATION_READER =
            new JsonReaderAdapter<BinaryAnnotation>() {
                @Override public BinaryAnnotation fromJson(JsonReader reader) throws IOException {
                    BinaryAnnotation.Builder result = BinaryAnnotation.builder();
                    String key = null;
                    Type type = Type.STRING;
                    boolean valueSet = false;
                    String number = null;
                    String string = null;

                    reader.beginObject();
                    while (reader.hasNext()) {
                        String nextName = reader.nextName();
                        if (nextName.equals("key")) {
                            result.key(key = reader.nextString());
                        } else if (nextName.equals("value")) {
                            valueSet = true;
                            switch (reader.peek()) {
                                case BOOLEAN:
                                    type = Type.BOOL;
                                    result.value(reader.nextBoolean() ? new byte[] {1} : new byte[] {0});
                                    break;
                                case STRING:
                                    string = reader.nextString();
                                    break;
                                case NUMBER:
                                    number = reader.nextString();
                                    break;
                                default:
                                    throw new MalformedJsonException(
                                            "Expected value to be a boolean, string or number but was " + reader.peek()
                                                    + " at path " + reader.getPath());
                            }
                        } else if (nextName.equals("type")) {
                            type = Type.valueOf(reader.nextString());
                        } else if (nextName.equals("endpoint") && reader.peek() != JsonToken.NULL) {
                            result.endpoint(ENDPOINT_READER.fromJson(reader));
                        } else {
                            reader.skipValue();
                        }
                    }
                    if (key == null) {
                        throw new MalformedJsonException("No key at " + reader.getPath());
                    } else if (!valueSet) {
                        throw new MalformedJsonException("No value for key " + key + " at " + reader.getPath());
                    }
                    reader.endObject();
                    result.type(type);
                    switch (type) {
                        case BOOL:
                            return result.build();
                        case STRING:
                            return result.value(string.getBytes(UTF_8)).build();
                        case BYTES:
                            return result.value(Base64.decode(string)).build();
                        default:
                            break;
                    }
                    final byte[] value;
                    if (type == Type.I16) {
                        short v = Short.parseShort(number);
                        value = ByteBuffer.allocate(2).putShort(0, v).array();
                    } else if (type == Type.I32) {
                        int v = Integer.parseInt(number);
                        value = ByteBuffer.allocate(4).putInt(0, v).array();
                    } else if (type == Type.I64 || type == Type.DOUBLE) {
                        if (number == null) number = string;
                        long v = type == Type.I64
                                ? Long.parseLong(number)
                                : doubleToRawLongBits(Double.parseDouble(number));
                        value = ByteBuffer.allocate(8).putLong(0, v).array();
                    } else {
                        throw new AssertionError("BinaryAnnotationType " + type + " was added, but not handled");
                    }
                    return result.value(value).build();
                }
            };

    static final Buffer.Writer<BinaryAnnotation> BINARY_ANNOTATION_WRITER =
            new Buffer.Writer<BinaryAnnotation>() {
                @Override public int sizeInBytes(BinaryAnnotation value) {
                    int sizeInBytes = 0;
                    sizeInBytes += "{\"key\":\"".length() + jsonEscapedSizeInBytes(value.key);
                    sizeInBytes += "\",\"value\":".length();
                    switch (value.type) {
                        case BOOL:
                            sizeInBytes += value.value[0] == 1 ? 4 /* true */ : 5 /* false */;
                            break;
                        case STRING:
                            int escapedSize = 0;
                            try {
                                escapedSize = needsJsonEscaping(value.value)
                                        ? jsonEscapedSizeInBytes(new String(value.value, UTF_8))
                                        : value.value.length;
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            sizeInBytes += escapedSize + 2; //for quotes
                            break;
                        case BYTES:
                            sizeInBytes += (/* base64 */(value.value.length + 2) / 3 * 4) + 2; //for quotes
                            break;
                        case I16:
                            sizeInBytes += asciiSizeInBytes(ByteBuffer.wrap(value.value).getShort());
                            break;
                        case I32:
                            sizeInBytes += asciiSizeInBytes(ByteBuffer.wrap(value.value).getInt());
                            break;
                        case I64:
                            long number = ByteBuffer.wrap(value.value).getLong();
                            sizeInBytes += asciiSizeInBytes(number);
                            if (number > MAX_SAFE_INTEGER) sizeInBytes += 2; //for quotes
                            break;
                        case DOUBLE:
                            double wrapped = Double.longBitsToDouble(ByteBuffer.wrap(value.value).getLong());
                            sizeInBytes += Double.toString(wrapped).length();
                            break;
                        default:
                    }
                    if (value.type != BinaryAnnotation.Type.STRING
                            && value.type != BinaryAnnotation.Type.BOOL) {
                        sizeInBytes += ",\"type\":\"".length() + value.type.name().length() + 1;
                    }
                    if (value.endpoint != null) {
                        sizeInBytes += ENDPOINT_HEADER.length() + ENDPOINT_WRITER.sizeInBytes(value.endpoint);
                    }
                    return ++sizeInBytes;// end curly-brace
                }

                @Override public void write(BinaryAnnotation value, Buffer b) {
                    b.writeAscii("{\"key\":\"").writeUtf8(jsonEscape(value.key));
                    b.writeAscii("\",\"value\":");
                    switch (value.type) {
                        case BOOL:
                            b.writeAscii(value.value[0] == 1 ? "true" : "false");
                            break;
                        case STRING:
                            try {
                                b.writeByte('"').writeUtf8(jsonEscape(new String(value.value, UTF_8))).writeByte('"');
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            break;
                        case BYTES:
                            b.writeByte('"').writeAscii(writeBase64Url(value.value)).writeByte('"');
                            break;
                        case I16:
                            b.writeAscii(ByteBuffer.wrap(value.value).getShort());
                            break;
                        case I32:
                            b.writeAscii(ByteBuffer.wrap(value.value).getInt());
                            break;
                        case I64:
                            long number = ByteBuffer.wrap(value.value).getLong();
                            if (number > MAX_SAFE_INTEGER) b.writeByte('"');
                            b.writeAscii(number);
                            if (number > MAX_SAFE_INTEGER) b.writeByte('"');
                            break;
                        case DOUBLE:
                            double wrapped = Double.longBitsToDouble(ByteBuffer.wrap(value.value).getLong());
                            b.writeAscii(Double.toString(wrapped));
                            break;
                        default:
                    }
                    if (value.type != BinaryAnnotation.Type.STRING
                            && value.type != BinaryAnnotation.Type.BOOL) {
                        b.writeAscii(",\"type\":\"").writeAscii(value.type.name()).writeByte('"');
                    }
                    if (value.endpoint != null) {
                        b.writeAscii(ENDPOINT_HEADER);
                        ENDPOINT_WRITER.write(value.endpoint, b);
                    }
                    b.writeByte('}');
                }
            };

    static final class SpanReader implements JsonReaderAdapter<Span> {
        Span.Builder builder;

        @Override public Span fromJson(JsonReader reader) throws IOException {
            if (builder == null) {
                builder = Span.builder();
            } else {
                builder.clear();
            }
            reader.beginObject();
            while (reader.hasNext()) {
                String nextName = reader.nextName();
                if (nextName.equals("traceId")) {
                    String traceId = reader.nextString();
                    builder.traceId(traceId);
                } else if (nextName.equals("name")) {
                    builder.resource(reader.nextString());
                } else if (nextName.equals("id")) {
                    builder.id(reader.nextString());
                } else if (nextName.equals("parentId") && reader.peek() != JsonToken.NULL) {
                    builder.parentId(reader.nextString());
                } else if (nextName.equals("timestamp") && reader.peek() != JsonToken.NULL) {
                    builder.timestamp(reader.nextLong());
                } else if (nextName.equals("duration") && reader.peek() != JsonToken.NULL) {
                    builder.duration(reader.nextLong());
                } else if (nextName.equals("annotations")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        builder.addAnnotation(ANNOTATION_READER.fromJson(reader));
                    }
                    reader.endArray();
                } else if (nextName.equals("binaryAnnotations")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        builder.addBinaryAnnotation(BINARY_ANNOTATION_READER.fromJson(reader));
                    }
                    reader.endArray();
                } else if (nextName.equals("debug") && reader.peek() != JsonToken.NULL) {
                    if (reader.nextBoolean()) builder.debug(true);
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return builder.build();
        }

        @Override public String toString() {
            return "Span";
        }
    }

    static final Buffer.Writer<Span> SPAN_WRITER = new Buffer.Writer<Span>() {
        @Override public int sizeInBytes(Span value) {
            int sizeInBytes = 0;
            sizeInBytes += "{\"traceId\":\"".length() + 16; // fixed-width hex
            sizeInBytes += "\",\"id\":\"".length() + 16;
            sizeInBytes += "\",\"resource\":\"".length() + jsonEscapedSizeInBytes(value.resource) + 1;
            if (value.parentId != null) {
                sizeInBytes += ",\"parentId\":\"".length() + 16 + 1;
            }
            if (value.timestamp != null) {
                sizeInBytes += ",\"timestamp\":".length() + asciiSizeInBytes(value.timestamp);
            }
            if (value.duration != null) {
                sizeInBytes += ",\"duration\":".length() + asciiSizeInBytes(value.duration);
            }
            if (!value.annotations.isEmpty()) {
                sizeInBytes += 17; // ,"annotations":[]
                int length = value.annotations.size();
                if (length > 1) sizeInBytes += length - 1; // comma to join elements
                for (int i = 0; i < length; i++) {
                    sizeInBytes += ANNOTATION_WRITER.sizeInBytes(value.annotations.get(i));
                }
            }
            if (!value.binaryAnnotations.isEmpty()) {
                sizeInBytes += 23; // ,"binaryAnnotations":[]
                int length = value.binaryAnnotations.size();
                if (length > 1) sizeInBytes += length - 1; // comma to join elements
                for (int i = 0; i < length; i++) {
                    sizeInBytes += BINARY_ANNOTATION_WRITER.sizeInBytes(value.binaryAnnotations.get(i));
                }
            }
            if (value.debug != null && value.debug) {
                sizeInBytes += ",\"debug\":true".length();
            }
            return ++sizeInBytes;// end curly-brace
        }

        @Override public void write(Span value, Buffer b) {
            b.writeAscii("{\"traceId\":\"").writeAscii(value.getTraceId());
            b.writeAscii("\",\"id\":\"").writeAscii(value.getId());
            b.writeAscii("\",\"resource\":\"").writeUtf8(jsonEscape(value.resource)).writeByte('"');
            if (value.parentId != null) {
                b.writeAscii(",\"parentId\":\"").writeAscii(value.getParentId());
                b.writeByte('"');
            }
            if (value.timestamp != null) {
                b.writeAscii(",\"timestamp\":").writeAscii(value.timestamp);
            }
            if (value.duration != null) {
                b.writeAscii(",\"duration\":").writeAscii(value.duration);
            }
            if (!value.annotations.isEmpty()) {
                b.writeAscii(",\"annotations\":");
                writeList(ANNOTATION_WRITER, value.annotations, b);
            }
            if (!value.binaryAnnotations.isEmpty()) {
                b.writeAscii(",\"binaryAnnotations\":");
                writeList(BINARY_ANNOTATION_WRITER, value.binaryAnnotations, b);
            }
            if (value.debug != null && value.debug) {
                b.writeAscii(",\"debug\":true");
            }
            b.writeByte('}');
        }

        @Override public String toString() {
            return "Span";
        }
    };

    @Override
    public Span readSpan(byte[] bytes) {
        return read(new SpanReader(), bytes);
    }

    @Override public int sizeInBytes(Span value) {
        return SPAN_WRITER.sizeInBytes(value);
    }

    @Override
    public byte[] writeSpan(Span value) {
        return write(SPAN_WRITER, value);
    }

    /** Exposed for {@link Endpoint#toString()} */
    public static byte[] writeEndpoint(Endpoint value) {
        return write(ENDPOINT_WRITER, value);
    }

    /** Exposed for {@link Annotation#toString()} */
    public static byte[] writeAnnotation(Annotation value) {
        return write(ANNOTATION_WRITER, value);
    }

    /** Exposed for {@link BinaryAnnotation#toString()} */
    public static byte[] writeBinaryAnnotation(BinaryAnnotation value) {
        return write(BINARY_ANNOTATION_WRITER, value);
    }

    @Override
    public List<Span> readSpans(byte[] bytes) {
        return readList(new SpanReader(), bytes);
    }

//    @Override
//    public byte[] writeSpans(List<Span> value) {
//        return writeList(SPAN_WRITER, value);
//    }
//
//    @Override
//    public byte[] writeTraces(List<List<Span>> traces) {
//        // Get the encoded size of the nested list so that we don't need to grow the buffer
//        int length = traces.size();
//        int sizeInBytes = 2; // []
//        if (length > 1) sizeInBytes += length - 1; // comma to join elements
//
//        for (int i = 0; i < length; i++) {
//            List<Span> spans = traces.get(i);
//            int jLength = spans.size();
//            sizeInBytes += 2; // []
//            if (jLength > 1) sizeInBytes += jLength - 1; // comma to join elements
//            for (int j = 0; j < jLength; j++) {
//                sizeInBytes += sizeInBytes(spans.get(j));
//            }
//        }
//
//        byte[] out = new byte[sizeInBytes];
//        int pos = 0;
//        out[pos++] = '['; // start list of traces
//        for (int i = 0; i < length; i++) {
//            pos += writeList(SPAN_WRITER, traces.get(i), out, pos);
//            if (i + 1 < length) out[pos++] = ',';
//        }
//        out[pos] = ']'; // stop list of traces
//        return out;
//    }

//    public List<List<Span>> readTraces(byte[] bytes) {
//        return readList(new SpanListReader(), bytes);
//    }

//    static final class SpanListReader implements JsonReaderAdapter<List<Span>> {
//        SpanReader spanReader;
//
//        @Override public List<Span> fromJson(JsonReader reader) throws IOException {
//            reader.beginArray();
//            if (!reader.hasNext()) {
//                reader.endArray();
//                return Collections.emptyList();
//            }
//            List<Span> result = new ArrayList<>(); // to make error-prone happy (it hates LinkedList)
//            if (spanReader == null) spanReader = new SpanReader();
//            while (reader.hasNext()) result.add(spanReader.fromJson(reader));
//            reader.endArray();
//            return result;
//        }
//
//        @Override public String toString() {
//            return "List<Span>";
//        }
//    }

    static final JsonReaderAdapter<String> STRING_READER = new JsonReaderAdapter<String>() {
        @Override public String fromJson(JsonReader reader) throws IOException {
            return reader.nextString();
        }

        @Override public String toString() {
            return "String";
        }
    };

    static final Buffer.Writer<String> STRING_WRITER = new Buffer.Writer<String>() {
        @Override public int sizeInBytes(String value) {
            return jsonEscapedSizeInBytes(value) + 2; // For quotes
        }

        @Override public void write(String value, Buffer buffer) {
            buffer.writeByte('"').writeUtf8(jsonEscape(value)).writeByte('"');
        }

        @Override public String toString() {
            return "String";
        }
    };

    public List<String> readStrings(byte[] bytes) {
        return readList(STRING_READER, bytes);
    }

    public byte[] writeStrings(List<String> value) {
        return writeList(STRING_WRITER, value);
    }

    public interface JsonReaderAdapter<T> {
        T fromJson(JsonReader reader) throws IOException;
    }

    public static <T> T read(JsonReaderAdapter<T> adapter, byte[] bytes) {
        if (bytes.length == 0) throw new IllegalArgumentException("Empty input reading " + adapter);
        try {
            return adapter.fromJson(jsonReader(bytes));
        } catch (Exception e) {
            throw exceptionReading(adapter.toString(), e);
        }
    }

    public static <T> List<T> readList(JsonReaderAdapter<T> adapter, byte[] bytes) {
        if (bytes.length == 0) {
            throw new IllegalArgumentException("Empty input reading List<" + adapter + ">");
        }
        JsonReader reader = null;
        try {
            reader = jsonReader(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<T> result;
        try {
            reader.beginArray();
            result = reader.hasNext() ? new ArrayList<>() : Collections.emptyList();
            while (reader.hasNext()) result.add(adapter.fromJson(reader));
            reader.endArray();
            return result;
        } catch (Exception e) {
            throw exceptionReading("List<" + adapter + ">", e);
        }
    }

    static JsonReader jsonReader(byte[] bytes) throws IOException {
        return new JsonReader(new InputStreamReader(new ByteArrayInputStream(bytes), UTF_8));
    }

    static final byte[] HEX_DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static void writeHexByte(Buffer buffer, byte b) {
        buffer.writeByte(HEX_DIGITS[(b >> 4) & 0xf]);
        buffer.writeByte(HEX_DIGITS[b & 0xf]);
    }

    static void writeLowerHex(Buffer b, long v) {
        writeHexByte(b, (byte) ((v >>> 56L) & 0xff));
        writeHexByte(b, (byte) ((v >>> 48L) & 0xff));
        writeHexByte(b, (byte) ((v >>> 40L) & 0xff));
        writeHexByte(b, (byte) ((v >>> 32L) & 0xff));
        writeHexByte(b, (byte) ((v >>> 24L) & 0xff));
        writeHexByte(b, (byte) ((v >>> 16L) & 0xff));
        writeHexByte(b, (byte) ((v >>> 8L) & 0xff));
        writeHexByte(b, (byte) (v & 0xff));
    }

    static IllegalArgumentException exceptionReading(String type, Exception e) {
        String cause = e.getMessage() == null ? "Error" : e.getMessage();
        if (cause.indexOf("malformed") != -1) cause = "Malformed";
        String message = format("%s reading %s from json", cause, type);
        throw new IllegalArgumentException(message, e);
    }
}


