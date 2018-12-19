package enn.monitor.trace.streaming;

import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.streaming.dependency.EnnDependencyLinker;
import zipkin.Codec;
import zipkin2.Span;
import zipkin2.codec.SpanBytesDecoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weize on 18-4-24.
 */
public class Test {
    public static void main(String[] args) {
        String test = "[{\"traceId\":\"bf9663a42922b247\",\"id\":\"bf9663a42922b247\",\"name\":\"prepare\",\"kind\":\"CLIENT\",\"timestamp\":1524564036332000,\"duration\":1000,\"tags\":{\"bizLine\":\"song-test\",\"instance\":\"browser\"}}]\n" +
                "[{\"traceId\":\"bf9663a42922b247\",\"parentId\":\"3ce19eee37f8ed8d\",\"id\":\"624c51f2a29565fb\",\"name\":\"get opentsdb\",\"timestamp\":1524564036314002,\"duration\":1653727,\"localEndpoint\":{\"serviceName\":\"pod-monitor-dash\",\"ipv4\":\"172.19.208.8\"},\"tags\":{\"bizLine\":\"song-test\",\"instance\":\"172.19.208.8\"}}]\n" +
                "[{\"traceId\":\"bf9663a42922b247\",\"parentId\":\"bf9663a42922b247\",\"id\":\"3ce19eee37f8ed8d\",\"kind\":\"SERVER\",\"name\":\"get\",\"timestamp\":1524564036313025,\"duration\":1655929,\"localEndpoint\":{\"serviceName\":\"pod-monitor-dash\",\"ipv4\":\"172.19.208.8\"},\"remoteEndpoint\":{\"ipv4\":\"172.22.144.0\",\"port\":37100},\"tags\":{\"bizLine\":\"song-test\",\"http.path\":\"/api/v1/kubernetes/metric/CPU\",\"instance\":\"172.19.208.8\"},\"shared\":true}]\n";
        String[] strs = test.split("\n");
        List<Span> spanList = new ArrayList<>();
        for (String str : strs) {
            byte[] bytes = str.getBytes();
            try {
                if (bytes[0] == '[') {
                    spanList.addAll(SpanBytesDecoder.JSON_V2.decodeList(bytes));
                } else {
                    if (bytes[0] == 12 /* TType.STRUCT */) {
//                        return Codec.THRIFT.readSpans(bytes);
                    } else { // historical kafka encoding of single thrift span per message
//                        return Collections.singletonList(Codec.THRIFT.readSpan(bytes));
                    }
                }
            } catch (RuntimeException e) {
//                return Collections.EMPTY_LIST;
            }
        }
        EnnDependencyLinker linker = new EnnDependencyLinker();
        linker.putTrace(spanList.iterator());
        List<EnnDependencyLink> links = linker.link();
        links = linker.merge(links);

    }
}
