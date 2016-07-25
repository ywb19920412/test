package com.example.ywb.mytreasure;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2016/7/20.
 */
public class TreasureApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //百度地图的初始化
        SDKInitializer.initialize(getApplicationContext());
    }
}
