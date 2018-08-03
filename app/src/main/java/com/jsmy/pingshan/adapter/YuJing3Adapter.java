package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.activity.XiangYingFankuiActivity;
import com.jsmy.pingshan.activity.ZaiQingTongJiActivity;
import com.jsmy.pingshan.bean.YuJingBean;
import com.jsmy.pingshan.bean.YuJingBean3;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2018/1/9.
 */

public class YuJing3Adapter extends RecyclerAdapter<YuJingBean3.DataBean> {
    private MainActivity context;

    public YuJing3Adapter(MainActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<YuJingBean3.DataBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new YuJing3Holder(parent);
    }

    class YuJing3Holder extends BaseViewHolder<YuJingBean3.DataBean> {
        private TextView tvName;
        private TextView tvDate;
        private ImageView imgInfo;
        private ImageView imgZhu;


        public YuJing3Holder(ViewGroup parent) {
            super(parent, R.layout.fragment_yujing_expand_father);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvDate = (TextView) findViewById(R.id.tv_date);
            imgInfo = (ImageView) findViewById(R.id.img_info);
            imgZhu = (ImageView) findViewById(R.id.img_zhu);
        }

        @Override
        public void setData(final YuJingBean3.DataBean object) {
            super.setData(object);
            tvName.setText(object.getADNM());
            if ("1".equals(object.getJb())) {
                tvDate.setText("立即转移");
            } else {
                tvDate.setText("准备转移");
            }
            imgInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, XiangYingFankuiActivity.class);
                    intent.putExtra("zid", object.getRegionId());
                    context.startActivity(intent);
                }
            });
            imgZhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ZaiQingTongJiActivity.class);
                    intent.putExtra("zid", object.getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
