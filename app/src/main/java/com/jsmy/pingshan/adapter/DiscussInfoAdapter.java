package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.DiscussInfoActivity;
import com.jsmy.pingshan.bean.DiscussUserBean;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/8.
 */

public class DiscussInfoAdapter extends RecyclerAdapter<DiscussUserBean.DataBean.ListBean> {
    private DiscussInfoActivity context;

    public DiscussInfoAdapter(DiscussInfoActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<DiscussUserBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new DiscussInfoHolder(parent);
    }

    class DiscussInfoHolder extends BaseViewHolder<DiscussUserBean.DataBean.ListBean> {
        public DiscussInfoHolder(ViewGroup parent) {
            super(parent, R.layout.activity_discuss_info_item);
        }

        private ImageView imgHead;
        private TextView tvName;
        private ImageView imgDelete;


        @Override
        public void onInitializeView() {
            super.onInitializeView();
            imgHead = (ImageView) findViewById(R.id.img_head);
            tvName = (TextView) findViewById(R.id.tv_name);
            imgDelete = (ImageView) findViewById(R.id.img_delete);
        }

        @Override
        public void setData(final DiscussUserBean.DataBean.ListBean object) {
            super.setData(object);
            if (getLayoutPosition() == getItemCount() - 2) {
                tvName.setText("邀请");
                imgHead.setImageResource(R.mipmap.shanghui_tianjia);
                imgDelete.setVisibility(View.GONE);
            } else {
                tvName.setText(object.getUserName());
                imgHead.setImageResource(R.drawable.iconfont_tongxunlutouxiang);
                if (object.getCreateId().equals(SharePrefUtil.getString(context, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId())) &&
                        !object.getUserId().equals(SharePrefUtil.getString(context, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()))) {
                    imgDelete.setVisibility(View.VISIBLE);
                    imgDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            context.showDeletUserDialog(object.getUserId());
                        }
                    });
                } else {
                    imgDelete.setVisibility(View.GONE);
                }
            }

        }

        @Override
        public void onItemViewClick(DiscussUserBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
            if (getLayoutPosition() == getItemCount() - 2) {
                context.showAddUserDialog();
            }
        }
    }
}
