package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ConversationListActivity;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.view.CircleImageView;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/6.
 */

public class PersonFragment2Adapter extends RecyclerAdapter<PersonBean.DataBean.ListBean> {
    private ConversationListActivity context;

    public PersonFragment2Adapter(ConversationListActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<PersonBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new PersonFragment2Holder(parent);
    }

    class PersonFragment2Holder extends BaseViewHolder<PersonBean.DataBean.ListBean> {
        public PersonFragment2Holder(ViewGroup parent) {
            super(parent, R.layout.fragment_person2_item);
        }

        private CircleImageView imgHead;
        private TextView tvName;
        private TextView tvZhi;
        private ImageView imgMsg;


        @Override
        public void onInitializeView() {
            super.onInitializeView();
            imgHead = (CircleImageView) findViewById(R.id.img_head);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvZhi = (TextView) findViewById(R.id.tv_zhi);
            imgMsg = (ImageView) findViewById(R.id.img_msg);
        }

        @Override
        public void setData(PersonBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getUserName());
            tvZhi.setText(object.getZh());
        }

        @Override
        public void onItemViewClick(PersonBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
            context.startPrivateChat(object.getUserid(), object.getUserName());
        }

    }
}
