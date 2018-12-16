package com.example.lianxi.model;

import com.example.lianxi.okhttp.ICallBack;
import com.example.lianxi.okhttp.MyCallBack;
import com.example.lianxi.okhttp.OkHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void requestData(String path, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().postQueue(path, params, clazz, new ICallBack() {
            @Override
            public void failed(Exception e) {
                myCallBack.sucess(e);
            }

            @Override
            public void sucess(Object data) {
                myCallBack.sucess(data);
            }
        });
    }
}
