package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.DingWeiTongJiActivity;
import com.jsmy.pingshan.activity.PreActivity;
import com.jsmy.pingshan.activity.YuAnGuanLiActivity;
import com.jsmy.pingshan.bean.PreBean;

import java.util.List;

public class PreAdapter  extends RecyclerView.Adapter<PreAdapter.MviewHolder>{
    List<PreBean.DataBean.ListBean> list;
    LayoutInflater inflater;
    Context context;
    int from;
    public PreAdapter(List list, Context context,int from) {
        this.list = list;
        this.context = context;
        this.from=from;
    }

    @Override
    public MviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MviewHolder(LayoutInflater.from(context).inflate(R.layout.item_pre,parent,false));
    }

    @Override
    public void onBindViewHolder(MviewHolder holder, final int position) {
        holder.tvXian.setText(list.get(position).getRegionNm());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(from== PreActivity.FROM_DINGWEI){
                    context.startActivity(new Intent(context, DingWeiTongJiActivity.class));
                }else if(from==PreActivity.FROM_YUAN){
                    YuAnGuanLiActivity.startActivity(context,list.get(position).getRegionCd());
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MviewHolder extends RecyclerView.ViewHolder{
        TextView tvXian;
        public MviewHolder(View itemView) {
            super(itemView);
            tvXian=(TextView) itemView.findViewById(R.id.tv_xian);
        }
    }
}
