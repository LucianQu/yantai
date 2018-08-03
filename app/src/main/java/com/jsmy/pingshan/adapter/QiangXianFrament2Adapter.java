package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.DingWeiTongJiActivity;
import com.jsmy.pingshan.activity.QiangXianBianJiActivity;
import com.jsmy.pingshan.bean.QiangianFrament2Bean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/3.
 */

public class QiangXianFrament2Adapter extends RecyclerAdapter<QiangianFrament2Bean.DataBean.ListBean> {
    private DingWeiTongJiActivity context;
    public QiangXianFrament2Adapter(DingWeiTongJiActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<QiangianFrament2Bean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new QiangXianFramentHolder(parent);
    }
    class QiangXianFramentHolder extends BaseViewHolder<QiangianFrament2Bean.DataBean.ListBean> {
        public QiangXianFramentHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_zhi_hui_child);
        }
        private TextView tvName;
        private TextView tvNum;
        private ImageView imgZhihui;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            tvNum = (TextView) findViewById(R.id.tv_num);
            imgZhihui = (ImageView) findViewById(R.id.img_zhihui);
        }

        @Override
        public void setData(final QiangianFrament2Bean.DataBean.ListBean object) {
            super.setData(object);
            tvName.setText(object.getZmc());
            tvNum.setText(object.getQxrs());
            imgZhihui.setImageResource(R.mipmap.dingweitongji_bianji);
            imgZhihui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QiangXianBianJiActivity.class);
                    intent.putExtra("cid",object.getZid());
                    intent.putExtra("zmc",object.getZmc());
                    intent.putExtra("rs",object.getQxrs());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onItemViewClick(QiangianFrament2Bean.DataBean.ListBean object) {
            super.onItemViewClick(object);
        }
    }
}
