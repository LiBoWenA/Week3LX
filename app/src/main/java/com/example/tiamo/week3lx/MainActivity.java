package com.example.tiamo.week3lx;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tiamo.week3lx.baseadapter.LinearAdapter;
import com.example.tiamo.week3lx.bean.Bean;
import com.example.tiamo.week3lx.persenter.IPersenter;
import com.example.tiamo.week3lx.persenter.IPersenterImpl;
import com.example.tiamo.week3lx.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private String path ="http://www.xieast.com/api/news/news.php?page=%d";
    private IPersenterImpl iPersenter;
    private XRecyclerView xRecyclerView;
    private LinearAdapter adapter;
    private ImageView b_xin;
    private int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        page = 1;
        iPersenter = new IPersenterImpl(this);
        xRecyclerView = findViewById(R.id.xview);
        b_xin = findViewById(R.id.xin);
        b_xin.setOnClickListener(this);
        adapter = new LinearAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.setAdapter(adapter);
        iPersenter.showRequestData(String.format(path,page),Bean.class);
        adapter.setOnClick(new LinearAdapter.OnClick() {
            @Override
            public void click(final View v, final int position) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(1000);
                animatorSet.playTogether( alpha);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.setAlpha(1.0f);
                        adapter.removeData(position);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
        });

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                iPersenter.showRequestData(String.format(path,page),Bean.class);
            }

            @Override
            public void onLoadMore() {
                iPersenter.showRequestData(String.format(path,page),Bean.class);
            }
        });

    }

    private void animationaa() {
        final float x = b_xin.getX();
        final float y = b_xin.getY();
        ObjectAnimator translate_x = ObjectAnimator.ofFloat(b_xin,"translationX",0,-500);
       ObjectAnimator translate_Y = ObjectAnimator.ofFloat(b_xin,"translationY",0,800);
       ObjectAnimator alpha = ObjectAnimator.ofFloat(b_xin,"alpha",1f,0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translate_x,translate_Y,alpha);
        animatorSet.setDuration(5000);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                b_xin.setX(x);
                b_xin.setY(y);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(b_xin,"alpha",0f,1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(alpha);
                animatorSet.setDuration(10);
                animatorSet.start();
                b_xin.setImageResource(R.drawable.xin);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

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
            xRecyclerView.loadMoreComplete();
            xRecyclerView.refreshComplete();

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xin:
                //动画
                animationaa();
                break;
        }

    }
}
