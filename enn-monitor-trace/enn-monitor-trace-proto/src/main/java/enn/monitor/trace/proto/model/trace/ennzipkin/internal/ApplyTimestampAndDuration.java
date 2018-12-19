
package enn.monitor.trace.proto.model.trace.ennzipkin.internal;

import enn.monitor.trace.proto.model.trace.ennzipkin.Annotation;
import enn.monitor.trace.proto.model.trace.ennzipkin.Constants;
import enn.monitor.trace.proto.model.trace.ennzipkin.Span;

public class ApplyTimestampAndDuration {

  /**
   * For RPC two-way spans, the duration between "cs" and "cr" is authoritative. RPC one-way spans
   * lack a response, so the duration is between "cs" and "sr". We special-case this to avoid
   * setting incorrect duration when there's skew between the client and the server.
   *
   * Note: this should only be used for query, not storage commands!
   */
  public static Span apply(Span span) {
    // Don't overwrite authoritatively set timestamp and duration!
    if (span.timestamp != null && span.duration != null) {
      return span;
    }

    // We cannot backfill duration on a span with less than two annotations. However, we can
    // backfill timestamp.
    if (span.annotations.size() < 2) {
      if (span.timestamp != null) return span;
      Long guess = guessTimestamp(span);
      if (guess == null) return span;
      return span.toBuilder().timestamp(guess).build();
    }

    // Prefer RPC one-way (cs -> sr) vs arbitrary annotations.
    Long first = span.annotations.get(0).timestamp;
    Long last = span.annotations.get(span.annotations.size() - 1).timestamp;
    for (int i = 0, length = span.annotations.size(); i < length; i++) {
      Annotation annotation = span.annotations.get(i);
      if (annotation.value.equals(Constants.CLIENT_SEND)) {
        first = annotation.timestamp;
      } else if (annotation.value.equals(Constants.CLIENT_RECV)) {
        last = annotation.timestamp;
      }
    }
    long ts = span.timestamp != null ? span.timestamp : first;
    Long dur = span.duration != null ? span.duration : last.equals(first) ? null : last - first;
    return span.toBuilder().timestamp(ts).duration(dur).build();
  }

  public static Long guessTimestamp(Span span) {
    if (span.timestamp != null || span.annotations.isEmpty()) return span.timestamp;
    Long rootServerRecv = null;
    for (int i = 0, length = span.annotations.size(); i < length; i++) {
      Annotation annotation = span.annotations.get(i);
      if (annotation.value.equals(Constants.CLIENT_SEND)) {
        return annotation.timestamp;
      } else if (annotation.value.equals(Constants.SERVER_RECV)) {
        rootServerRecv = annotation.timestamp;
      }
    }
    return rootServerRecv;
  }

  /** When performing updates, don't overwrite an authoritative timestamp with a guess! */
  public static Long authoritativeTimestamp(Span span) {
    if (span.timestamp != null) return span.timestamp;
    for (int i = 0, length = span.annotations.size(); i < length; i++) {
      Annotation a = span.annotations.get(i);
      if (a.value.equals(Constants.CLIENT_SEND)) {
        return a.timestamp;
      }
    }
    return null;
  }

  private ApplyTimestampAndDuration() {
  }
}
