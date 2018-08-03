package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.SendMingLingActivity;
import com.jsmy.pingshan.bean.PersonBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/1.
 */

public class SendMingLingAdapter extends RecyclerAdapter<PersonBean.DataBean.ListBean> {
    private SendMingLingActivity context;

    public SendMingLingAdapter(SendMingLingActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<PersonBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new SendMingLingHolder(parent);
    }

    class SendMingLingHolder extends BaseViewHolder<PersonBean.DataBean.ListBean> {
        private TextView tvName;
        private ImageView imgDelete;

        public SendMingLingHolder(ViewGroup parent) {
            super(parent, R.layout.activity_send_ming_ling_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            imgDelete = (ImageView) findViewById(R.id.img_delete);
        }

        @Override
        public void setData(PersonBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getUserName());
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.removePerson(getLayoutPosition());
                }
            });
        }

        @Override
        public void onItemViewClick(PersonBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
        }
    }
}
