package enn.monitor.streaming.sink.elasticsearch;

import com.google.gson.Gson;
import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.framework.log.proto.ESLog;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by weize on 17-8-25.
 */
public class ESSender implements Serializable {
    private static TransportClient esTransportClient = null;
    private static String logTopic = null;
    private BulkRequestBuilder bulkRequest = null;
    private int esInsertBatch = 2000;
    public ESSender(String esHostList, String clusterName,
                     String logTopic, int insertBatch) {
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", false)
                .build();

        esTransportClient = new PreBuiltXPackTransportClient(settings);

        String[] hostList = esHostList.split(",");
        String[] ipPort = null;
        for (int i = 0; i < hostList.length; ++i) {
            ipPort = hostList[i].split(":");
            try {
                esTransportClient.addTransportAddress(new InetSocketTransportAddress(
                        InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        this.logTopic = logTopic;
        esInsertBatch = insertBatch;
    }

    public void send(ESLog esLog) {
        try {
            if (esTransportClient == null) {
                throw new Exception("the esTransportClient is null");
            }

            if (esLog.getDateTime() != null) {
                esLog.setDateTime(esLog.getDateTime().replaceAll(" ", "T"));
                esLog.setDateTime(esLog.getDateTime() + "+0800");
            }

            if (bulkRequest == null) {
                bulkRequest = esTransportClient.prepareBulk();
            }

            String date;
            if (esLog.getCreateTime() != 0l) {
                date = EnnDatetimeUtil.convertLongToStringWithDate(esLog.getCreateTime(), "-", EnnTimezoneUtil.getChinaTimeZone());
            } else {
                date = "";
            }

            String index = esLog.getClusterName() + "-" + esLog.getNameSpace() + "-" + date;
            if (Strings.isNotEmpty(esLog.getBizLine()) && Strings.isNotEmpty(esLog.getService())) {
                index = "enn-trace-log-" + date;
            } else if (logTopic.equals("log-k8s-system") || logTopic.equals("wst-log")) {
                index = esLog.getClusterName() + "-" + esLog.getAppName() + "-" + date;
            }
            bulkRequest.add(esTransportClient.prepareIndex(index, logTopic, null).setSource(new Gson().toJson(esLog)));

            if (bulkRequest.numberOfActions() >= esInsertBatch) {
                insertItem();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            insertItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        flush();
        esTransportClient.close();
        esTransportClient = null;
    }

    private void insertItem() throws Exception {
        if (bulkRequest == null) {
            return;
        }
        Logger.getRootLogger().info(Thread.currentThread().getId() + " insertItem ： " + bulkRequest.numberOfActions() + " " + new Date());

//        esItemPutMetrics.markEvents(bulkRequest.numberOfActions());
        BulkResponse bulkResponse = bulkRequest.get();
        Logger.getRootLogger().info(Thread.currentThread().getId() + " insert fin ： " + bulkRequest.numberOfActions() + " " + new Date());
        if (bulkResponse.hasFailures()) {
            Logger.getRootLogger().error(bulkResponse.buildFailureMessage());
        }
        bulkRequest = null;
    }
}