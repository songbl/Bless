package com.example.parttime.net;


import com.example.parttime.net.convert.ApiErrorConverterFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppHttpClient {

    private static AppHttpClient mInstance;

    private Retrofit mRetrofit;

    private OkHttpClient okHttpClient;


    private AppHttpClient() {
    }

    /**
     * 获取单例
     */
    public static AppHttpClient getInstance() {
        if (mInstance == null) {
            synchronized (AppHttpClient.class) {
                if (mInstance == null) {
                    mInstance = new AppHttpClient();
                }
            }
        }
        return mInstance;
    }
    /**
     * Rx2.0
     */
    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HOST_URL)
                    .addConverterFactory(new ApiErrorConverterFactory(GsonConverterFactory.create()))//处理了code和Error
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 获取OkHttpClient
     *
     * @return OkHttpClient
     */
    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.addNetworkInterceptor(new StethoInterceptor()).build();////配合chrome监听网络请

            okHttpClient = builder.build();
        }

        return okHttpClient;
    }

}
