package enn.monitor.trace.streaming.elasticsearch;

import enn.monitor.trace.streaming.ThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zipkin2.Callback;
import zipkin2.CheckResult;
import zipkin2.Component;
import zipkin2.Span;
import zipkin.storage.elasticsearch.http.ElasticsearchHttpStorage;
import zipkin2.reporter.internal.AwaitableCallback;
import zipkin2.storage.StorageComponent;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weize on 18-1-2.
 */
public class StorageConsumer extends Component implements Serializable  {
    private static final Logger log = LoggerFactory.getLogger(StorageConsumer.class);

    Logger log() { // Override for testing. Instance variables won't work as Logger isn't serializable
        return log;
    }

    transient volatile StorageComponent instance; // not serializable

    private String esRest = "";

    public StorageConsumer(String esRest) {
        this.esRest = esRest;
    }

    /** Subclasses should initialize this from serializable state. */
    protected StorageComponent tryCompute() {
        List<String> hosts = new ArrayList<>();
        hosts.add("http://" + esRest);
//        hosts.add("http://127.0.0.1:9200");
        return ElasticsearchHttpStorage
                .builder()
                .index("zipkin")
                .hosts(hosts).build();
    }

    public final void accept(Iterable<Span> spansSharingId) {
        List<Span> list = asList(spansSharingId);
        if (list.isEmpty()) {
            log().debug("Input was empty");
            return;
        }

        // Blocking as it is simpler to reason with thread this way while work is in progress
        Callback<Void> blockingCallback = new AwaitableCallback();
        try {
            get().spanConsumer().accept(list).enqueue(blockingCallback);
            log().debug("Wrote {} spans", list.size());
        } catch (Exception e) {
            Throwable toLog = e.getClass().equals(RuntimeException.class) && e.getCause() != null
                    ? e.getCause() // callback captor wraps checked exceptions
                    : e;
            String message = "Dropped " + list.size() + " spans: " + toLog.getMessage();

            // TODO: We are dropping vs diverting to a dead letter queue or otherwise. Do we want this?
            if (log().isWarnEnabled()) {
                log().warn(message, toLog);
            } else {
                log().warn(message);
            }
        }
    }

    final StorageComponent get() {
        StorageComponent result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = tryCompute();
                }
            }
        }
        return result;
    }

    @Override public CheckResult check() {
        return get().check();
    }

    @Override public final void close() throws IOException {
        synchronized (this) {
            if (instance != null) instance.close();
        }
    }

    static <E> List<E> asList(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) list.add(item);
        return list;
    }
}