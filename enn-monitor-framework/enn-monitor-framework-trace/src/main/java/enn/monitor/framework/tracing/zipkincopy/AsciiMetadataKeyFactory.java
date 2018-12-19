package enn.monitor.framework.tracing.zipkincopy;

import brave.propagation.Propagation;
import io.grpc.Metadata;

/**
 * Created by weize on 18-2-8.
 */
public enum AsciiMetadataKeyFactory implements Propagation.KeyFactory<Metadata.Key<String>> {
    INSTANCE;

    @Override public Metadata.Key<String> create(String name) {
        return Metadata.Key.of(name, Metadata.ASCII_STRING_MARSHALLER);
    }
}

