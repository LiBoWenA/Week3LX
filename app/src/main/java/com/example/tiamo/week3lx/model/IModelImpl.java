package com.example.tiamo.week3lx.model;

import com.example.tiamo.week3lx.okhttp.ICallBack;
import com.example.tiamo.week3lx.okhttp.MyCallBack;
import com.example.tiamo.week3lx.okhttp.OkHttpUtils;

public class IModelImpl implements IModel {
    @Override
    public void requestData(String path, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().gerEqueue(path, clazz, new ICallBack() {
            @Override
            public void sucess(Object data) {
                myCallBack.sucess(data);
            }

            @Override
            public void failed(Exception e) {
                myCallBack.sucess(e);
            }
        });
    }
}
