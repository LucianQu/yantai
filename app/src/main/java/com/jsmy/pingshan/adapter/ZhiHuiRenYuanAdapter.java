package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ZhiHuiRenYuanActivity;
import com.jsmy.pingshan.bean.ZhiHuiRenYuanBean;
import com.jsmy.pingshan.util.MesssageUtil;
import com.jsmy.pingshan.util.OfficeUtil;
import com.jsmy.pingshan.view.CircleImageView;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ZhiHuiRenYuanAdapter extends RecyclerAdapter<ZhiHuiRenYuanBean.DataBean.ListBean> {
    private ZhiHuiRenYuanActivity context;

    public ZhiHuiRenYuanAdapter(ZhiHuiRenYuanActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<ZhiHuiRenYuanBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ZhiHuiRenYuanHolder(parent);
    }

    class ZhiHuiRenYuanHolder extends BaseViewHolder<ZhiHuiRenYuanBean.DataBean.ListBean> {
        private CircleImageView imgHead;
        private TextView tvName;
        private TextView tvPhone;
        private TextView tvZhi;
        private TextView tvDan;
        private ImageView imgPhone;
        private ImageView imgSms;

        public ZhiHuiRenYuanHolder(ViewGroup parent) {
            super(parent, R.layout.activity_zhi_hui_ren_yuan_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            imgHead = (CircleImageView) findViewById(R.id.img_head);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvPhone = (TextView) findViewById(R.id.tv_phone);
            tvZhi = (TextView) findViewById(R.id.tv_zhi);
            tvDan = (TextView) findViewById(R.id.tv_dan);
            imgPhone = (ImageView) findViewById(R.id.img_phone);
            imgSms = (ImageView) findViewById(R.id.img_sms);
        }

        @Override
        public void setData(final ZhiHuiRenYuanBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getXm());
            tvPhone.setText(object.getDh());
            tvZhi.setText(object.getZw());
            tvDan.setText(object.getDw());
            imgPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MesssageUtil.call(context, object.getDh());
                }
            });
            imgSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MesssageUtil.sendSMS(context, object.getDh());
                }
            });
        }

        @Override
        public void onItemViewClick(ZhiHuiRenYuanBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
        }
    }
}
