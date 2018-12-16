package com.example.tiamo.week3lx.model;

import com.example.tiamo.week3lx.okhttp.MyCallBack;

public interface IModel {
    void requestData(String path, Class clazz, MyCallBack myCallBack);
}
