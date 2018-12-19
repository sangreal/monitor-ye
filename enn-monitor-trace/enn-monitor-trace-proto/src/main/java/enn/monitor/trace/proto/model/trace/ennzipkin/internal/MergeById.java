
package enn.monitor.trace.proto.model.trace.ennzipkin.internal;

import enn.monitor.trace.proto.model.trace.ennzipkin.Span;

import java.util.*;

import static enn.monitor.trace.proto.model.trace.ennzipkin.internal.Util.sortedList;

public final class MergeById {

  public static List<Span> apply(Iterable<Span> spans) {
    if (spans == null || !spans.iterator().hasNext()) return Collections.emptyList();
    List<Span> result = new ArrayList<>();
    Map<String, List<Span>> spanIdToSpans = new LinkedHashMap<>();
    for (Span span : spans) {
      if (!spanIdToSpans.containsKey(span.id)) {
        spanIdToSpans.put(span.id, new ArrayList<>());
      }
      spanIdToSpans.get(span.id).add(span);
    }

    for (List<Span> spansToMerge : spanIdToSpans.values()) {
      if (spansToMerge.size() == 1) {
        result.add(spansToMerge.get(0));
      } else {
        Span.Builder builder = spansToMerge.get(0).toBuilder();
        for (int i = 1, length = spansToMerge.size(); i < length; i++) {
          builder.merge(spansToMerge.get(i));
        }
        result.add(builder.build());
      }
    }

    // Apply timestamp so that sorting will be helpful
    for (int i = 0; i < result.size(); i++) {
      result.set(i, ApplyTimestampAndDuration.apply(result.get(i)));
    }
    return sortedList(result);
  }

  private MergeById() {
  }
}
