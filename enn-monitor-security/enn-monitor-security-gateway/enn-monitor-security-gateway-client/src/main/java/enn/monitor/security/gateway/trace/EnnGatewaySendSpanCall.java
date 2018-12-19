package enn.monitor.security.gateway.trace;

import java.io.IOException;
import java.util.List;

import zipkin2.Call;
import zipkin2.Callback;
import zipkin2.reporter.internal.AwaitableCallback;
import zipkin2.reporter.internal.BaseCall;

/**
 * Created by weize on 17-12-15.
 */
public class EnnGatewaySendSpanCall extends BaseCall {
    private final List<byte[]> messages;

    EnnGatewaySendSpanCall(List<byte[]> messages) {
        this.messages = messages;
    }

    @Override
    protected Object doExecute() throws IOException {
        final AwaitableCallback callback = new AwaitableCallback();
        // do send;
        for (byte[] message : messages) {
            GrpcCallDispatcher.getInstance().request(message, callback);
            callback.await();
        }
        return null;
    }

    @Override
    protected void doEnqueue(Callback callback) {
        // do send;
        // trigger callback;
    }

    @Override
    public Call clone() {
        return null;
    }
}
