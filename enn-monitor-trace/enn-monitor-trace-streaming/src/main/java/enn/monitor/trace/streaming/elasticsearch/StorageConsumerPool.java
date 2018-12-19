package enn.monitor.trace.streaming.elasticsearch;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.Serializable;

/**
 * Created by weize on 18-4-3.
 */
public class StorageConsumerPool extends GenericObjectPool<StorageConsumer> implements Serializable {
    private static StorageConsumerPool pool;
    public StorageConsumerPool(PooledObjectFactory<StorageConsumer> factory) {
        super(factory);
        this.setBlockWhenExhausted(true);
        this.setMaxWaitMillis(5000);
        this.setLifo(false);
        this.setMaxIdle(5);//// 最大空闲连接数
        this.setMinIdle(5);// 最小空闲连接数
        this.setMaxTotal(10);// 整个池的最大值,最大连接数
        this.setTestOnBorrow(true);
        this.setTestOnCreate(true);
        this.setTestOnReturn(true);
        this.setTestWhileIdle(false);
    }

    public static StorageConsumerPool getInstance(PooledObjectFactory<StorageConsumer> factory) {
        if (pool == null) {
            synchronized (StorageConsumerPool.class) {
                if (pool == null) {
                    pool = new StorageConsumerPool(factory);
                }
            }
        }
        return pool;
    }
}
