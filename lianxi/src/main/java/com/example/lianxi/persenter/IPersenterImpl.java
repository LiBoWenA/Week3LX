package com.example.lianxi.persenter;

import com.example.lianxi.model.IModel;
import com.example.lianxi.model.IModelImpl;
import com.example.lianxi.okhttp.MyCallBack;
import com.example.lianxi.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private IModel iModel;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }


    @Override
    public void showRequestData(String path, Map<String, String> pamars, Class clazz) {
        iModel.requestData(path, pamars, clazz, new MyCallBack() {
            @Override
            public void sucess(Object data) {
                iView.startRequestData(data);
            }
        });
    }
}
