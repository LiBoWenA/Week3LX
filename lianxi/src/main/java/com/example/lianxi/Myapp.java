package com.example.lianxi;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
