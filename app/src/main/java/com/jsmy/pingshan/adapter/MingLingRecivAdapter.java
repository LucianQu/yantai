package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.RecivMingLingActivity;
import com.jsmy.pingshan.activity.XunChaMingLingActivity;
import com.jsmy.pingshan.bean.XCMLjsBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/1.
 */

public class MingLingRecivAdapter extends RecyclerAdapter<XCMLjsBean.DataBean.ListBean> {
    private XunChaMingLingActivity context;

    public MingLingRecivAdapter(XunChaMingLingActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<XCMLjsBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new MingLingRecivHolder(parent);
    }

    class MingLingRecivHolder extends BaseViewHolder<XCMLjsBean.DataBean.ListBean> {
        private TextView tvName;
        private TextView tvTime;
        private TextView tvDada;
        private TextView tvState;


        public MingLingRecivHolder(ViewGroup parent) {
            super(parent, R.layout.activity_xun_cha_ming_ling_item_reciv);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvTime = (TextView) findViewById(R.id.tv_time);
            tvDada = (TextView) findViewById(R.id.tv_dada);
            tvState = (TextView) findViewById(R.id.tv_state);
        }

        @Override
        public void setData(XCMLjsBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getSendName());
            tvTime.setText(object.getSendTime());
            tvDada.setText(object.getSendContent());
            if ("Y".equals(object.getIsRead())) {
                tvState.setText("已读");
            } else {
                tvState.setText("未读");
            }
        }

        @Override
        public void onItemViewClick(XCMLjsBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
            Intent intent = new Intent(context, RecivMingLingActivity.class);
            intent.putExtra("id", object.getJsid());
            context.startActivityForResult(intent, 101);
        }
    }
}
