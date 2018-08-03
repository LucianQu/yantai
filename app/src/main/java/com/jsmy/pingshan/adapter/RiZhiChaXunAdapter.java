package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.RiZhiChaXunActivity;
import com.jsmy.pingshan.bean.RiZhiChaXunBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/14.
 */

public class RiZhiChaXunAdapter extends RecyclerAdapter<RiZhiChaXunBean.DataBean.ListBean> {
    private RiZhiChaXunActivity context;

    public RiZhiChaXunAdapter(RiZhiChaXunActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<RiZhiChaXunBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new RiZhiChaXunHolder(parent);
    }

    class RiZhiChaXunHolder extends BaseViewHolder<RiZhiChaXunBean.DataBean.ListBean> {
        private TextView tvName;
        private TextView tvTime;
        private TextView tvIp;

        public RiZhiChaXunHolder(ViewGroup parent) {
            super(parent, R.layout.activity_ri_zhi_cha_xun_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvTime = (TextView) findViewById(R.id.tv_time);
            tvIp = (TextView) findViewById(R.id.tv_ip);
        }

        @Override
        public void setData(RiZhiChaXunBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getYhm());
            tvTime.setText(object.getDldt());
            tvIp.setText("登录IP:" + object.getIp());
        }

        @Override
        public void onItemViewClick(RiZhiChaXunBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
        }
    }
}
