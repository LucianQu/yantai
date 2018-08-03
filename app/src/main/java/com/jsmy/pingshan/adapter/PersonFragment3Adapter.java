package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ConversationListActivity;
import com.jsmy.pingshan.activity.DiscussInfoActivity;
import com.jsmy.pingshan.bean.TaoLunZuBean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017/11/7.
 */

public class PersonFragment3Adapter extends RecyclerAdapter<TaoLunZuBean.DataBean.ListBean> {
    private ConversationListActivity context;

    public PersonFragment3Adapter(ConversationListActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<TaoLunZuBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new PersonFragment3Holder(parent);
    }

    class PersonFragment3Holder extends BaseViewHolder<TaoLunZuBean.DataBean.ListBean> {
        public PersonFragment3Holder(ViewGroup parent) {
            super(parent, R.layout.fragment_person3_item);
        }

        private ImageView imgHead;
        private TextView tvName;
        private TextView tvZhi;
        private ImageView imgMsg;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            imgHead = (ImageView) findViewById(R.id.img_head);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvZhi = (TextView) findViewById(R.id.tv_zhi);
            imgMsg = (ImageView) findViewById(R.id.img_msg);
        }

        @Override
        public void setData(final TaoLunZuBean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getDiscussName());
            tvZhi.setText("(" + object.getZrs() + "人)");
            imgMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DiscussInfoActivity.class);
                    intent.putExtra("discussName",object.getDiscussName());
                    intent.putExtra("discussID", object.getDiscussId());
                    context.startActivityForResult(intent, 101);
                }
            });
        }

        @Override
        public void onItemViewClick(TaoLunZuBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
            RongIM.getInstance().startDiscussionChat(context, object.getDiscussId(), object.getDiscussName());
        }
    }
}
