package enn.monitor.trace.dataapi.trace;

import enn.monitor.trace.proto.model.trace.TraceSpanList;
import enn.monitor.trace.proto.model.trace.ennzipkin.Span;

import java.util.*;

public class TraceUtil {

    public static void groupSpanByTraceId(List<Span> spanSet, Map<String, List<Span>> traceOriMap) {
        String traceId = null;

        for (Span span : spanSet) {

            traceId = span.getTraceId();
            if (traceOriMap.containsKey(traceId)) {
                traceOriMap.get(traceId).add(span);
            }
            else {
                List<Span> list = new ArrayList<>();
                list.add(span);
                traceOriMap.put(traceId, list);
            }
        }
    }

    private static Integer calculateDepth(List<Span> traceSpan, int index, String id) {
        if (traceSpan.get(index).getParentId() == null || index == 0) return 1;

        if (traceSpan.get(index).getId().equals(id))
            return calculateDepth(traceSpan, index-1, traceSpan.get(index).getParentId()) + 1;
        else
            return calculateDepth(traceSpan, index-1, traceSpan.get(index).getParentId());
    }

    public static List<Span> parseDepth(List<Span> traceSpan) {
        Span span = null;
        Span.Builder spanBuilder = null;
        List<Span> curSpan = new ArrayList<>();

        for (int num = 0; num < traceSpan.size(); ++num) {
            span = traceSpan.get(num);
            spanBuilder = span.toBuilder();
            spanBuilder.depth(calculateDepth(traceSpan, num, traceSpan.get(num).getId()));
            curSpan.add(spanBuilder.build());
        }

        return curSpan;
    }

    public static List<Span> setStartTime(TraceSpanList response, List<Span> traceSpan) {

        long maxDuration = 0;
        long time = 0;
        long curDuration = 0;
        long startTime = traceSpan.get(0).getTimestamp();

        for (int num=0 ; num < traceSpan.size(); ++num) {
            if (traceSpan.get(num).getTimestamp() < startTime)
                startTime = traceSpan.get(num).getTimestamp();
        }

        response.setTimeStamp(startTime);

        Span span = null;
        Span.Builder spanBuilder = null;
        List<Span> curSpan = new ArrayList<>();

        for (int num=0 ; num < traceSpan.size(); ++num) {

            span = traceSpan.get(num);
            spanBuilder = span.toBuilder();
            time = span.getTimestamp() - startTime;
            spanBuilder.startTime(time);

            if (span.getDuration() != null)
                curDuration = time + span.getDuration();
            else {
                curDuration = time + 0;
            }

            maxDuration = maxDuration < curDuration ? curDuration : maxDuration;

            curSpan.add(spanBuilder.build());
        }

        response.setDuration(maxDuration);

        return curSpan;
    }

    public static void setServiceNumOfTrace(TraceSpanList response) {
        Set<String> serviceNameSet = new HashSet<>();

        for (Span span : response.getSpans()) {
            serviceNameSet.add(span.getBelongToService());
        }

        response.setServiceNum(serviceNameSet.size());
    }
}
