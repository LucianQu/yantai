package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.SendMLDataActivity;
import com.jsmy.pingshan.activity.XunChaMingLingActivity;
import com.jsmy.pingshan.bean.XCMLfsBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/1.
 */

public class MingLingSendAdapter extends RecyclerAdapter<XCMLfsBean.DataBean.ListBean> {
    private XunChaMingLingActivity context;

    public MingLingSendAdapter(XunChaMingLingActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<XCMLfsBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new MingLingSendHolder(parent);
    }

    class MingLingSendHolder extends BaseViewHolder<XCMLfsBean.DataBean.ListBean> {
        private TextView tvName;
        private TextView tvTime;
        private TextView tvDada;
        private TextView tvState;


        public MingLingSendHolder(ViewGroup parent) {
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
        public void setData(XCMLfsBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getJsrmc());
            tvTime.setText(object.getSendTime());
            tvDada.setText(object.getSendContent());
            if ("Y".equals(object.getIsRead())) {
                tvState.setText("已读");
            } else {
                tvState.setText("未读");
            }
        }

        @Override
        public void onItemViewClick(XCMLfsBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
            Intent intent = new Intent(context, SendMLDataActivity.class);
            intent.putExtra("id", object.getSendid());
            context.startActivityForResult(intent, 101);
        }
    }

}
