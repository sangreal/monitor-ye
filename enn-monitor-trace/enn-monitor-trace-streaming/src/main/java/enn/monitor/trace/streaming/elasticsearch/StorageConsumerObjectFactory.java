package enn.monitor.trace.streaming.elasticsearch;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.Serializable;

/**
 * Created by weize on 18-4-3.
 */
public class StorageConsumerObjectFactory extends BasePooledObjectFactory<StorageConsumer> implements Serializable {
    private String esRest;
    public StorageConsumerObjectFactory(String esRest) {
        this.esRest = esRest;
    }

    @Override
    public StorageConsumer create() throws Exception {
        return new StorageConsumer(esRest);
    }

    @Override
    public PooledObject<StorageConsumer> wrap(StorageConsumer consumer) {
        return new DefaultPooledObject<>(consumer);
    }

    @Override
    public void destroyObject(PooledObject<StorageConsumer> p) throws Exception {
        p.getObject().close();
    }
}
