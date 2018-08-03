package com.jsmy.pingshan.adapter;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.LineChartActivity;
import com.jsmy.pingshan.activity.ShuiQingActivity;
import com.jsmy.pingshan.activity.YuQingActivity;
import com.jsmy.pingshan.bean.ShuiQingBean;
import com.jsmy.pingshan.bean.YuQingBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShuiQingAdapter extends RecyclerAdapter<ShuiQingBean.DataBean.ListBean> {
    private ShuiQingActivity context;

    public ShuiQingAdapter(ShuiQingActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<ShuiQingBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ShuiQingHolder(parent);
    }

    class ShuiQingHolder extends BaseViewHolder<ShuiQingBean.DataBean.ListBean> {

        public ShuiQingHolder(ViewGroup parent) {
            super(parent, R.layout.activity_shui_qing_item);
        }

        private TextView tvName;
        private TextView tvOne;
        private TextView tvTwo;
        private TextView tvThree;
        private TextView tvFour;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvOne = (TextView) findViewById(R.id.tv_one);
            tvTwo = (TextView) findViewById(R.id.tv_two);
            tvThree = (TextView) findViewById(R.id.tv_three);
            tvFour = (TextView) findViewById(R.id.tv_four);
        }

        @Override
        public void setData(ShuiQingBean.DataBean.ListBean data) {
            super.setData(data);
            tvName.setText(data.getJcz());
            tvOne.setText(data.getSw());
            tvTwo.setText(data.getJxsw());
            tvThree.setText(data.getWxsw());
            tvFour.setText(data.getLl());
        }

        @Override
        public void onItemViewClick(ShuiQingBean.DataBean.ListBean data) {
            super.onItemViewClick(data);
            Intent i = new Intent(context, LineChartActivity.class);
            i.putExtra("stationId", data.getStationId());
            context.startActivity(i);
        }
    }
}
