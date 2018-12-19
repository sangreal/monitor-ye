package enn.monitor.streaming.sink.opentsdb;

import com.google.gson.Gson;
import enn.monitor.streaming.common.manager.ThreadManager;
import enn.monitor.streaming.common.proto.Metric;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by weize on 17-8-22.
 */
public class OpenTSDBSender implements Serializable {
    private static OpenTSDBSender sender;
    private static List<OpenTSDBHttpClient> clients;
    public static OpenTSDBSender getInstance(String hostPort) {
        if (sender == null) {
            synchronized (OpenTSDBSender.class) {
                if (sender == null) {
                    sender = new OpenTSDBSender(hostPort);
                }
            }
        }
        return sender;
    }
    private OpenTSDBSender(String hostPort) {
        clients = new ArrayList<>();
        for (int i = 0; i < 16; i ++) {
            OpenTSDBHttpClient client = new OpenTSDBHttpClient(hostPort);
            clients.add(client);
        }
    }

    public void send(List<Metric> data) {
        OpenTSDBHttpClient client = clients.get(new Random().nextInt(16));
        ThreadManager.getInstance().runTaskInPool(() -> {
                List<List<Metric>> batches = split(data, 10);
                batches.forEach(list -> {
                    try {
                        client.sendData(new Gson().toJson(list));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        });
    }

    private <T> List<List<T>> split(List<T> dataList, int batch) {
        int i, j;

        List<T> tmpOpentsdbJsonList = null;
        List<List<T>> result = new ArrayList<List<T>>();

        if (dataList == null || dataList.size() <= batch ) {
            result.add(dataList);
        } else {
            for (i = 0; i < dataList.size(); i += batch) {
                tmpOpentsdbJsonList = new ArrayList<T>();
                for (j = i; j < i + batch && j < dataList.size(); ++j) {
                    tmpOpentsdbJsonList.add(dataList.get(j));
                }
                result.add(tmpOpentsdbJsonList);
            }
        }

        return result;
    }

    public void send(String data) {
        OpenTSDBHttpClient client = clients.get(new Random().nextInt(16));
        ThreadManager.getInstance().runTaskInPool(() -> {
            try {
                client.sendData(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
