package com.example.tiamo.week3lx.baseadapter;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiamo.week3lx.R;
import com.example.tiamo.week3lx.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {
    private Context context;
    private List<Bean.DataBean> list;
    private ObjectAnimator animator;

    public LinearAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<Bean.DataBean> lists){
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }

    public void addData(List<Bean.DataBean> lists){
        list.addAll(lists);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LinearAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LinearAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.text.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null){
                    onClick.click(v,i);
                }
            }
        });
        /*viewHolder.t_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.click(i);
                animator = ObjectAnimator.ofFloat(viewHolder.itemView,"alpha",1f,0f);
                animator.setDuration(2000);
                animator.setRepeatCount(0);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView text,t_cha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.text);
            t_cha = itemView.findViewById(R.id.cha);
        }
    }
    OnClick onClick;

    public void setOnClick(OnClick mOnClick){
        onClick = mOnClick;
    }

    public interface OnClick{
        void click(View v,int position);
    }

    public void removeData(int pos){
     list.remove(pos);
     notifyDataSetChanged();
     /*notifyItemRangeChanged(pos,list.size());
     notifyItemRemoved(pos);*/
    }
}
