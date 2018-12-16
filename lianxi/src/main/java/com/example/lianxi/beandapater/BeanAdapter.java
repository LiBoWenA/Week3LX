package com.example.lianxi.beandapater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi.R;
import com.example.lianxi.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class BeanAdapter extends RecyclerView.Adapter<BeanAdapter.ViewHolder> {
    private Context context;
    private List<Bean.DataBean> list;
    private boolean isLinear;

    public BeanAdapter(Context context,boolean isLinear) {
        this.context = context;
        this.isLinear = isLinear;
        list = new ArrayList<>();
    }

    public List<Bean.DataBean> getData(){
        return list;
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
    public BeanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (isLinear){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_linear,viewGroup,false);
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid,viewGroup,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BeanAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.t_title.setText(list.get(i).getTitle());
        viewHolder.t_price.setText(list.get(i).getPrice()+"");
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.click(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView t_title,t_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            t_title = itemView.findViewById(R.id.title);
            t_price = itemView.findViewById(R.id.price);
        }
    }
    OnClick onClick;

    public void setOnClick(OnClick onClick){
        this.onClick = onClick;
    }


    public interface OnClick{
        void click(int position);
    }

    public void removeData(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
