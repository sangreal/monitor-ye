package enn.monitor.framework.tracing.model;

import brave.Span;
import brave.propagation.TraceContext;
import zipkin2.Endpoint;

/**
 * Created by weize on 18-1-19.
 */
public class EnnSpan extends Span {
    private Span delegate;
    private String name;

    public EnnSpan(Span span) {
        delegate = span;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean isNoop() {
        return delegate.isNoop();
    }

    @Override
    public TraceContext context() {
        return delegate.context();
    }

    @Override
    public Span start() {
        delegate = delegate.start();
        return this;
    }

    @Override
    public Span start(long timestamp) {
        delegate = delegate.start(timestamp);
        return this;
    }

    @Override
    public Span name(String name) {
        this.name = name;
        delegate = delegate.name(name);
        return this;
    }

    @Override
    public Span kind(Kind kind) {
        delegate = delegate.kind(kind);
        return this;
    }

    @Override
    public Span annotate(String value) {
        delegate = delegate.annotate(value);
        return this;
    }

    @Override
    public Span annotate(long timestamp, String value) {
        delegate = delegate.annotate(timestamp, value);
        return this;
    }

    @Override
    public Span tag(String key, String value) {
        delegate = delegate.tag(key, value);
        return this;
    }

    @Override
    public Span remoteEndpoint(Endpoint endpoint) {
        delegate = delegate.remoteEndpoint(endpoint);
        return this;
    }

    @Override
    public void finish() {
        delegate.finish();
    }

    @Override
    public void abandon() {
        delegate.abandon();
    }

    @Override
    public void finish(long timestamp) {
        delegate.finish(timestamp);
    }

    @Override
    public void flush() {
        delegate.flush();
    }
}
