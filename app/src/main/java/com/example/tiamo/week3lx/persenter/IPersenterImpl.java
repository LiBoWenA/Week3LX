package com.example.tiamo.week3lx.persenter;

import com.example.tiamo.week3lx.model.IModel;
import com.example.tiamo.week3lx.model.IModelImpl;
import com.example.tiamo.week3lx.okhttp.MyCallBack;
import com.example.tiamo.week3lx.view.IView;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private IModel iModel;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void showRequestData(String path, Class clazz) {
     iModel.requestData(path, clazz, new MyCallBack() {
         @Override
         public void sucess(Object data) {
             iView.startRequestData(data);
         }
     });
    }
}
