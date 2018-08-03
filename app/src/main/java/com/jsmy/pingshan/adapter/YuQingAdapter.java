package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ColumnChartActivity;
import com.jsmy.pingshan.activity.YuQingActivity;
import com.jsmy.pingshan.bean.YuQingBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YuQingAdapter extends RecyclerAdapter<YuQingBean.DataBean.ListBean> {
    private YuQingActivity context;

    public YuQingAdapter(YuQingActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<YuQingBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new YuQingHolder(parent);
    }

    class YuQingHolder extends BaseViewHolder<YuQingBean.DataBean.ListBean> {

        public YuQingHolder(ViewGroup parent) {
            super(parent, R.layout.activity_yu_qing_item);
        }

        private TextView tvName;
        private TextView tvOne;
        private TextView tvTwo;
        private TextView tvThree;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvOne = (TextView) findViewById(R.id.tv_one);
            tvTwo = (TextView) findViewById(R.id.tv_two);
            tvThree = (TextView) findViewById(R.id.tv_three);
        }

        @Override
        public void setData(YuQingBean.DataBean.ListBean data) {
            super.setData(data);
            tvName.setText(data.getJcz());
            tvOne.setText(data.getYl());
            tvTwo.setText(data.getJjyl());
            tvThree.setText(data.getWxyl());
        }

        @Override
        public void onItemViewClick(YuQingBean.DataBean.ListBean data) {
            super.onItemViewClick(data);
            Intent intent = new Intent(context, ColumnChartActivity.class);
            intent.putExtra("stationId", data.getStationId());
            context.startActivity(intent);
        }
    }
}
