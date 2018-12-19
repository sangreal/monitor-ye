import brave.Span;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.trace.TracerBuilder;

/**
 * Created by wst_dreg on 2018-04-04.
 */
public class Test {
    public static void main(String[] args) {
        EnnTracer tracer = TracerBuilder.get();
        Span root = tracer.newRootSpan("test");
        root.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        root.finish();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
