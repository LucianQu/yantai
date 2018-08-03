package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ShiPinHuiShangActivity;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.bean.TongXunLuBean;
import com.jsmy.pingshan.view.CircleImageView;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/22.
 */

public class ShiPinHuiShangAdapter extends RecyclerAdapter<PersonBean.DataBean.ListBean> {
    private ShiPinHuiShangActivity context;

    public ShiPinHuiShangAdapter(ShiPinHuiShangActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<PersonBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ShiPinHuiShangHolder(parent);
    }

    class ShiPinHuiShangHolder extends BaseViewHolder<PersonBean.DataBean.ListBean> {
        public ShiPinHuiShangHolder(ViewGroup parent) {
            super(parent, R.layout.activity_shi_pin_hui_shang_item);
        }

        private CircleImageView imgHead;
        private TextView tvName;
        private TextView tvZhi;
        private CheckBox imgMsg;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            imgHead = (CircleImageView) findViewById(R.id.img_head);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvZhi = (TextView) findViewById(R.id.tv_zhi);
            imgMsg = (CheckBox) findViewById(R.id.img_msg);
        }

        @Override
        public void setData(final PersonBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getUserName());
            tvZhi.setText(object.getZh());
            imgMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        context.getListId(getLayoutPosition(), object.getUserid());
                    } else {
                        context.getListId(getLayoutPosition(), "aaa");
                    }
                }
            });
        }
    }
}
