package enn.monitor.trace.streaming.elasticsearch;

import com.google.gson.Gson;
import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.proto.model.service.ServiceResourceHistogram;
import enn.monitor.trace.proto.model.service.ServiceResourceInstancePercentiles;
import enn.monitor.trace.proto.model.service.ServiceResourcePercentiles;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

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
                .put("client.transport.sniff", true)
                .build();

        esTransportClient = new PreBuiltXPackTransportClient(settings, Arrays.asList(), new TransportClient.HostFailureListener() {
            @Override
            public void onNodeDisconnected(DiscoveryNode discoveryNode, Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        });

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

    public <T> void send(List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        try {
            if (esTransportClient == null) {
                throw new Exception("the esTransportClient is null");
            }
            if (bulkRequest == null) {
                bulkRequest = esTransportClient.prepareBulk();
            }

            for (T data : list) {
                if (data instanceof EnnDependencyLink) {
                    EnnDependencyLink link = (EnnDependencyLink) data;
                    link.timestamp(TimeStamp.getCurrentTime().getTime());
                    Map<String, Object> record = prepareRecord(link);
                    String index = "enn-dependency" + "-" + link.day();
                    String id = link.timestampedId();
//                    String update =
//                        "ctx._source.avg_latency = (ctx._source.count * ctx._source.avg_latency + params.count * params.avg_latency)" +
//                                " / (ctx._source.count + params.count);" +
//                                "ctx._source.count += params.count;" +
//                                " ctx._source.error += params.error";
//                    Script upScript = new Script(Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG, update, record);
                    bulkRequest.add(
                            esTransportClient
                                    .prepareIndex(index, logTopic, id)
                                    .setSource(new Gson().toJson(record), XContentType.JSON)
                    );
                } else if (data instanceof ServiceResourcePercentiles) {
                    ServiceResourcePercentiles srp = (ServiceResourcePercentiles) data;
                    String index = srp.getESIndex();
                    bulkRequest.add(esTransportClient
                            .prepareIndex(index, logTopic, srp.id())
                            .setSource(new Gson().toJson(srp), XContentType.JSON)
                    );
                } else if (data instanceof ServiceResourceInstancePercentiles) {
                    ServiceResourceInstancePercentiles srip = (ServiceResourceInstancePercentiles) data;
                    String index = srip.getESIndex();
                    bulkRequest.add(esTransportClient
                            .prepareIndex(index, logTopic, srip.id())
                            .setSource(new Gson().toJson(srip), XContentType.JSON)
                    );
                } else if (data instanceof ServiceResourceHistogram) {
                    ServiceResourceHistogram srh = (ServiceResourceHistogram) data;
                    srh.setTimestamp(TimeStamp.getCurrentTime().getTime());
                    String index = srh.getESIndex();
                    bulkRequest.add(
                            esTransportClient
                                    .prepareIndex(index, logTopic, srh.id())
                                    .setSource(new Gson().toJson(srh), XContentType.JSON)
                    );
                } else {
                    continue;
                }
            }
            if (bulkRequest.numberOfActions() == 0) {
                return;
            }
            BulkResponse bulkResponse = bulkRequest.get();
            System.out.println(bulkResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> prepareRecord(EnnDependencyLink link) {
        Map<String, Object> record = new HashMap<>();
        record.put("minute", link.minute());
        record.put("count", link.callCount());
        record.put("error", link.errorCount());
        record.put("avg_latency", link.avgLatency());
        record.put("caller_biz", link.caller().getBizLine());
        record.put("caller_svc", link.caller().getServiceName());
        record.put("caller_ins", link.caller().getInstance());
        record.put("caller_res", link.caller().getResource());

        record.put("callee_biz", link.callee().getBizLine());
        record.put("callee_svc", link.callee().getServiceName());
        record.put("callee_ins", link.callee().getInstance());
        record.put("callee_res", link.callee().getResource());
        record.put("timestamp", link.timestamp());

        return record;
    }

    public void flush() {
        try {
            insertItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
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