package enn.monitor.trace.dataapi.trace;

import enn.monitor.trace.proto.model.trace.ennzipkin2.Endpoint;
import enn.monitor.trace.proto.model.trace.ennzipkin2.Span;
import org.apache.log4j.Logger;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class SearchResultConverter {

    public static List<Span> convert(SearchHit[] searchHits) {
        List<Span> spans = new ArrayList<>();

        for (SearchHit hit : searchHits) {
            try {
                Span span = hitToSpan(hit);
                if (span != null)
                    spans.add(span);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return spans;
    }

    private static Span hitToSpan(SearchHit hit) {
        Span.Builder result = Span.newBuilder();

        Map<String,Object> source = hit.getSource();

        result.traceId((source.get("traceId").toString()));

        if (source.containsKey("parentId")) {
            result.parentId(source.get("parentId").toString());
        }

        result.id((source.get("id").toString()));

        if (source.containsKey("kind")) {
            result.kind(Span.Kind.valueOf(source.get("kind").toString()));
        }

        if (source.containsKey("name")) {
            result.resource(source.get("name").toString());
        }

        if (source.containsKey("timestamp"))
            result.timestamp(((Number)source.get("timestamp")).longValue());
        else
            return null;

        if (source.containsKey("duration"))
            result.duration(((Number)source.get("duration")).longValue());
        else {
            result.duration(0);
        }

        Map<String, Object> localEndpoint = null;

        if (source.containsKey("localEndpoint")) {
            localEndpoint = (Map<String, Object>)source.get("localEndpoint");
            result.belongToService((String) localEndpoint.get("serviceName"));
            result.localEndpoint(parseEndpoint(localEndpoint));
        }

        if (source.containsKey("remoteEndpoint")) {
            Object tmp = source.get("remoteEndpoint");
            result.remoteEndpoint(parseEndpoint((Map<String, Object>)tmp));
        }

        if (source.containsKey("annotations")) {
            Map<Long, String> annotationMap = (Map<Long, String>) source.get("anntations");

            Iterator<Map.Entry<Long, String>> iterator = annotationMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Long, String> entry = iterator.next();
                result.addAnnotation(entry.getKey(), entry.getValue());
            }
        }

        if (source.containsKey("tags")) {
            Map<String, String> oriTags = (Map<String, String>) source.get("tags");

            if (oriTags.containsKey("bizLine")) {
                result.belongToBizline(oriTags.get("bizLine"));
                oriTags.remove("bizLine");
            }

            if (oriTags.containsKey("instance")) {
                result.belongToInstance(oriTags.get("instance"));
                oriTags.remove("instance");
            }

            Iterator<Map.Entry<String, String>> iterator = oriTags.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                result.putTag(entry.getKey(), entry.getValue());
            }
        }

        if (source.containsKey("debug")) {
            result.debug(Boolean.valueOf(source.get("debug").toString()));
        }

        if (source.containsKey("shared")) {
            result.shared(Boolean.valueOf(source.get("shared").toString()));
        }

        return result.build();
    }

    private static Endpoint parseEndpoint(Map<String, Object> localEndpoint) {
        String serviceName = null, ipv4 = null, ipv6 = null;
        Integer port = null;

        serviceName = (String) localEndpoint.get("serviceName");

        ipv4 = (String) localEndpoint.get("ipv4");

        if (localEndpoint.containsKey("ipv6"))
            ipv6 = (String) localEndpoint.get("ipv6");

        if (localEndpoint.containsKey("port") && localEndpoint.get("port") instanceof Integer)
            port = (Integer)localEndpoint.get("port");
        else if (localEndpoint.containsKey("port") && localEndpoint.get("port") instanceof String)
            port = Integer.valueOf((String) localEndpoint.get("port"));

        return Endpoint.newBuilder().serviceName(serviceName).ip(ipv4).ip(ipv6).port(port).build();
    }

}
