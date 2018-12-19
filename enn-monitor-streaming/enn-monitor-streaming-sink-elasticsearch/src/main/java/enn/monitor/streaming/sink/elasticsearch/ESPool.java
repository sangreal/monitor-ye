package enn.monitor.streaming.sink.elasticsearch;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.Serializable;

/**
 * Created by weize on 17-9-26.
 */
public class ESPool extends GenericObjectPool<ESSender> implements Serializable {
    private static ESPool pool;
    public ESPool(PooledObjectFactory<ESSender> factory) {
        super(factory);
        this.setBlockWhenExhausted(true);
        this.setMaxWaitMillis(1000);
        this.setLifo(false);
        this.setMaxIdle(10);//// 最大空闲连接数
        this.setMinIdle(10);// 最小空闲连接数
        this.setMaxTotal(20);// 整个池的最大值,最大连接数
        this.setTestOnBorrow(true);
        this.setTestOnCreate(true);
        this.setTestOnReturn(true);
        this.setTestWhileIdle(false);
    }

    public static ESPool getInstance(PooledObjectFactory<ESSender> factory) {
        if (pool == null) {
            synchronized (ESSender.class) {
                if (pool == null) {
                    pool = new ESPool(factory);
                }
            }
        }
        return pool;
    }
}
