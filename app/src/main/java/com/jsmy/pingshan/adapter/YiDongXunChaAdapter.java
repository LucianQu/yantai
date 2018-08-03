package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.XunChaJiLu2Activity;
import com.jsmy.pingshan.activity.XunChaJiLuActivity;
import com.jsmy.pingshan.activity.YiDongXunChaActivity;
import com.jsmy.pingshan.bean.YiDongXunChaBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YiDongXunChaAdapter extends RecyclerAdapter<YiDongXunChaBean.DataBean.ListBean> {
    private YiDongXunChaActivity context;

    public YiDongXunChaAdapter(YiDongXunChaActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<YiDongXunChaBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new YiDongXunChaHolder(parent);
    }

    class YiDongXunChaHolder extends BaseViewHolder<YiDongXunChaBean.DataBean.ListBean> {
        public YiDongXunChaHolder(ViewGroup parent) {
            super(parent, R.layout.activity_yi_dong_xun_cha_item);
        }

        private TextView tvName;
        private TextView tvTime;
        private TextView tvLocation;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvTime = (TextView) findViewById(R.id.tv_time);
            tvLocation = (TextView) findViewById(R.id.tv_location);
        }

        @Override
        public void setData(YiDongXunChaBean.DataBean.ListBean data) {
            super.setData(data);
            tvName.setText(data.getUsername());
            tvTime.setText(data.getBeginDt());
            tvLocation.setText(data.getTitleDesc());
        }

        @Override
        public void onItemViewClick(YiDongXunChaBean.DataBean.ListBean data) {
            super.onItemViewClick(data);
            Intent intent = new Intent(context,XunChaJiLu2Activity.class);
            intent.putExtra("id",data.getJlid());
            context.startActivity(intent);
        }
    }
}
