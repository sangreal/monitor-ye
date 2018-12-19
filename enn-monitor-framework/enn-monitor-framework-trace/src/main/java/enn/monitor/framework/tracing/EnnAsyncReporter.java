package enn.monitor.framework.tracing;

import enn.monitor.framework.tracing.exceptionhandler.CatchTraceException;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;

import java.util.HashMap;
import java.util.Map;

/**
 * Works as a proxy for Zipkin AsyncReporter
 * add extra tags before report;
 *
 * Created by weize on 17-12-13.
 */
public class EnnAsyncReporter extends AsyncReporter {
    private AsyncReporter reporter;
    private Map<String, String> extension;
    @Override
    public void flush() {
        reporter.flush();
    }

    @Override
    public void close() {
        reporter.close();
    }

    @Override
    @CatchTraceException
    public void report(Object span) {
        if (span instanceof Span) {
           Span.Builder builder = ((Span) span).toBuilder();
//           if (((Span) span).tags().isEmpty()) {
//               builder.putTag("lc", "");
//           }
//           if (builder.kind() == null) {
//               builder.kind(Span.Kind.CLIENT);
//           }
           extension.forEach((key, value) -> {
               builder.putTag(key, value);
           });
           span = builder.build();
        }
        reporter.report(span);
    }

    public EnnAsyncReporter(AsyncReporter reporter, String bizLine, String instance) {
        this.reporter = reporter;
        extension = new HashMap<>();
        extension.put("bizLine", bizLine);
        extension.put("instance", instance);
    }
}
