package com.example.parttime;

import android.app.Application;

import com.facebook.stetho.Stetho;


public class AppApplication extends Application {
    private static final String SYNC_LOCK = "SYNC_LOCK";
    private static AppApplication instance = null;
    public AppApplication(){}

    public static AppApplication getInstance() {
        if (instance == null){
            synchronized (SYNC_LOCK){
                if (instance == null){
                    instance = new AppApplication();
                }
            }
        }
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){//配合chrome对网络请求和数据库进行监听
            Stetho.initializeWithDefaults(this);
        }
        instance = this;


    }


}
