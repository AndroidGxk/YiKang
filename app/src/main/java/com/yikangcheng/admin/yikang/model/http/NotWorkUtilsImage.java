package com.yikangcheng.admin.yikang.model.http;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zxk
 * on 2019/1/22 19:14
 * QQ:666666
 * Describe:网络请求工具类
 */
public class NotWorkUtilsImage {
    private static NotWorkUtilsImage instance;
    private Retrofit retrofit;

    private NotWorkUtilsImage() {
        init();
    }

    public static NotWorkUtilsImage getInstance() {
        if (instance == null) {
            instance = new NotWorkUtilsImage();
        }
        return instance;
    }

    private void init() {
        OkHttpClient okHttpClient = getUnsafeOkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://image.yikch.com/")//base_url:http+域名
                // .baseUrl("https://172.17.8.100/techApi/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }


    public static OkHttpClient getUnsafeOkHttpClient() {

        try {

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数，请求结果
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
//                    .addInterceptor(interceptor)
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
