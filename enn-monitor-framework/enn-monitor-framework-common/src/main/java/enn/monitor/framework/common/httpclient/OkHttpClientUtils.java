package enn.monitor.framework.common.httpclient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * @author wangchunyang@gmail.com
 */
public class OkHttpClientUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static long connectTimeoutMillis = 120000;
    private static long readTimeoutMillis = 120000;
    private static long writeTimeoutMillis = 120000;

    public static long getConnectTimeoutMillis() {
        return connectTimeoutMillis;
    }

    public static void setConnectTimeoutMillis(long connectTimeoutMillis) {
        OkHttpClientUtils.connectTimeoutMillis = connectTimeoutMillis;
    }

    public static long getReadTimeoutMillis() {
        return readTimeoutMillis;
    }

    public static void setReadTimeoutMillis(long readTimeoutMillis) {
        OkHttpClientUtils.readTimeoutMillis = readTimeoutMillis;
    }

    public static long getWriteTimeoutMillis() {
        return writeTimeoutMillis;
    }

    public static void setWriteTimeoutMillis(long writeTimeoutMillis) {
        OkHttpClientUtils.writeTimeoutMillis = writeTimeoutMillis;
    }

    public static OkHttpClient getUnsafeOkHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        return getUnsafeOkHttpClient(connectTimeoutMillis, readTimeoutMillis, writeTimeoutMillis);
    }

    public static OkHttpClient getUnsafeOkHttpClient(long connectTimeoutMillis,
                                                     long readTimeoutMillis,
                                                     long writeTimeoutMillis) throws KeyManagementException, NoSuchAlgorithmException {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];

                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.hostnameVerifier((hostname, session) -> true);
            httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
            httpClientBuilder.connectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS);
            httpClientBuilder.readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS);
            httpClientBuilder.writeTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS);

            return httpClientBuilder.build();
        } catch (Exception e) {
            throw e;
        }
    }
}
