package enn.monitor.security.gateway.trace.util;

import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.trace.TracerBuilder;

/**
 * Created by weize on 17-11-17.
 *
 */
public class TraceManager {
    private static EnnTracer ennTracer;

    public synchronized static EnnTracer init() {
        if (ennTracer == null) {
            ennTracer = TracerBuilder.get();
        }
        return ennTracer;
    }

    public static EnnTracer getInstance() {
        return ennTracer;
    }

}
