package com.example.lianxi.model;

import com.example.lianxi.okhttp.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String path, Map<String,String> params, Class clazz, MyCallBack myCallBack);
}
