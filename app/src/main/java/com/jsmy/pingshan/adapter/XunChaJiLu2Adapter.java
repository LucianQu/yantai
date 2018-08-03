package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.XunChaJiLu2Activity;
import com.jsmy.pingshan.bean.XunChaBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/16.
 */

public class XunChaJiLu2Adapter extends RecyclerAdapter<XunChaBean.DataBean.ListBean.PointBean> {
    private XunChaJiLu2Activity context;

    public XunChaJiLu2Adapter(XunChaJiLu2Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<XunChaBean.DataBean.ListBean.PointBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new XunChaJiLu2Holder(parent);
    }

    class XunChaJiLu2Holder extends BaseViewHolder<XunChaBean.DataBean.ListBean.PointBean> {
        public XunChaJiLu2Holder(ViewGroup parent) {
            super(parent, R.layout.activity_xun_cha_ji_lu2_item);
        }

        private TextView tvNum;
        private TextView tvName;
        private TextView tvMs;
        private ImageView imgData;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvNum = (TextView) findViewById(R.id.tv_num);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvMs = (TextView) findViewById(R.id.tv_ms);
            imgData = (ImageView) findViewById(R.id.img_data);
        }

        @Override
        public void setData(XunChaBean.DataBean.ListBean.PointBean object) {
            super.setData(object);
            tvNum.setText(getLayoutPosition() + 1 + "");
            tvName.setText(object.getAddress());
            tvMs.setText(object.getContentDesc());
            imgData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.goToImag(getLayoutPosition());
                }
            });
        }
    }
}
