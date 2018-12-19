package enn.monitor.streaming.sink.elasticsearch;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.Serializable;

/**
 * Created by weize on 17-9-25.
 */
public class ESPoolFactory extends BasePooledObjectFactory<ESSender> implements Serializable {
    private String esHostList;
    private String clusterName = null;
    private String logTopic = null;
    private int insertBatch = 2000;
    public ESPoolFactory(String esHostList, String clusterName,
                         String logTopic, int insertBatch) {
        this.esHostList = esHostList;
        this.clusterName = clusterName;
        this.logTopic = logTopic;
        this.insertBatch = insertBatch;
    }

    @Override
    public ESSender create() throws Exception {
        return new ESSender(esHostList, clusterName, logTopic, insertBatch);
    }

    @Override
    public PooledObject<ESSender> wrap(ESSender esSender) {
        return new DefaultPooledObject<>(esSender);
    }

    @Override
    public void destroyObject(PooledObject<ESSender> p) throws Exception {
        p.getObject().close();
    }
}
