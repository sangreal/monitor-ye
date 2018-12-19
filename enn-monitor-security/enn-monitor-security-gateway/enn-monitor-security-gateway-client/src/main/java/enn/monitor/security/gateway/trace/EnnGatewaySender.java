package enn.monitor.security.gateway.trace;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import zipkin2.Call;
import zipkin2.codec.Encoding;
import zipkin2.reporter.BytesMessageEncoder;
import zipkin2.reporter.Sender;

/**
 * Created by weize on 17-12-15.
 */
public class EnnGatewaySender extends Sender {
    private Encoding encoding;
    private int maxBytes;

    private EnnGatewaySender(int maxBytes, Encoding encoding) {
        this.maxBytes = maxBytes;
        this.encoding = encoding;
    }

    @Override
    public Encoding encoding() {
        return encoding;
    }

    @Override
    public int messageMaxBytes() {
        return maxBytes;
    }

    @Override
    public int messageSizeInBytes(List<byte[]> encodedSpans) {
        return encoding().listSizeInBytes(encodedSpans);
    }

    /** close is typically called from a different thread */
    volatile boolean closeCalled;

    @Override
    public Call<Void> sendSpans(List<byte[]> encodedSpans) {
//        Logger.getRootLogger().info("sending spans, list size: " + encodedSpans.size());
        if (closeCalled) throw new IllegalStateException("closed");
        List<List<byte[]>> batches = split(encodedSpans, batchSize);
        List<byte[]> encodedBatches = new ArrayList<>();
        for (List<byte[]> batch : batches) {
            byte[] message = encoder().encode(batch);
            encodedBatches.add(message);
        }
        return new EnnGatewaySendSpanCall(encodedBatches);
    }

    private int batchSize = 500;
    private <T> List<List<T>> split(List<T> dataList, int batch) {
        int i, j;

        List<T> tmpList = null;
        List<List<T>> result = new ArrayList<List<T>>();

        if (dataList == null || dataList.size() <= batch ) {
            result.add(dataList);
        } else {
            for (i = 0; i < dataList.size(); i += batch) {
                tmpList = new ArrayList<T>();
                for (j = i; j < i + batch && j < dataList.size(); ++j) {
                    tmpList.add(dataList.get(j));
                }
                result.add(tmpList);
            }
        }

        return result;
    }

    private BytesMessageEncoder encoder() {
        return BytesMessageEncoder.forEncoding(encoding());
    }

    public static class Builder {
        private int maxBytes;
        private Encoding encoding;

        public Builder maxBytes(int bytes) {
            this.maxBytes = bytes;
            return this;
        }

        public Builder encoding(Encoding encoding) {
            this.encoding = encoding;
            return this;
        }

        public final EnnGatewaySender build() {
            return new EnnGatewaySender(maxBytes, encoding);
        }

        Builder() {
        }
    }

}
