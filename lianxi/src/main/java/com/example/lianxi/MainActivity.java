package com.example.lianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.lianxi.bean.Bean;
import com.example.lianxi.beandapater.BeanAdapter;
import com.example.lianxi.persenter.IPersenterImpl;
import com.example.lianxi.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private String path = "http://www.zhaoapi.cn/product/searchProducts";
    private IPersenterImpl iPersenter;
    private XRecyclerView xRecyclerView;
    private BeanAdapter adapter;
    private int page;
    private boolean isLinear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page = 1;
        init();
    }

    private void init() {
        //获取资源ID
        xRecyclerView = findViewById(R.id.xview);
        findViewById(R.id.b_qh).setOnClickListener(this);
        iPersenter = new IPersenterImpl(this);
        //刷新加载
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        changeLiGr();
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnClick(new BeanAdapter.OnClick() {
            @Override
            public void click(int position) {
                adapter.removeData(position);
            }
        });

        initData();
    }

    private void initData() {
        Map<String,String> pamars = new HashMap<>();
        pamars.put("keywords","手机");
        pamars.put("page",page+"");
        iPersenter.showRequestData(path,pamars,Bean.class);
    }

    private void changeLiGr(){
        if (isLinear){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(layoutManager);
        }else{
            GridLayoutManager manager = new GridLayoutManager(this,2);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(manager);
        }
        adapter = new BeanAdapter(this,isLinear);
        xRecyclerView.setAdapter(adapter);
        //状态反选
        isLinear = !isLinear;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_qh:
                List<Bean.DataBean> data = adapter.getData();
                changeLiGr();
                adapter.setData(data);
                break;
                default:
                    break;
        }

    }

    @Override
    public void startRequestData(Object data) {
       if (data instanceof Bean){
            Bean bean = (Bean) data;
            if (page == 1){
                adapter.setData(bean.getData());
            }else{
                adapter.addData(bean.getData());
            }
            page++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();;
       }
    }
}
