package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.bean.FuntionBean;

import java.util.List;

import cn.lemon.view.adapter.BaseViewHolder;


/**
 * Created by Administrator on 2017/12/8.
 */

public class GongZuoTaiFragment2Adapter extends RecyclerView.Adapter<GongZuoTaiFragment2Adapter.GongZuoTaiFragment2Holder> {
    private MainActivity context;
    private List<FuntionBean.DataBean> list;

    public GongZuoTaiFragment2Adapter(MainActivity context, List<FuntionBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public GongZuoTaiFragment2Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_gongzuotai2_item, null);
        return new GongZuoTaiFragment2Holder(view);
    }

    @Override
    public void onBindViewHolder(GongZuoTaiFragment2Holder holder, final int position) {
        holder.tvItem.setText(list.get(position).getFunction());
        holder.tvItem.setBackgroundResource(R.drawable.recycler_bg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            holder.tvItem.setCompoundDrawablesRelativeWithIntrinsicBounds(null, context.getGZTdrawable(list.get(position).getNumber()), null, null);
        } else {
            holder.tvItem.setCompoundDrawablesWithIntrinsicBounds(null, context.getGZTdrawable(list.get(position).getNumber()), null, null);
        }
        holder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.turnToActivity(list.get(position).getNumber());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class GongZuoTaiFragment2Holder extends RecyclerView.ViewHolder {
        private TextView tvItem;

        public GongZuoTaiFragment2Holder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
