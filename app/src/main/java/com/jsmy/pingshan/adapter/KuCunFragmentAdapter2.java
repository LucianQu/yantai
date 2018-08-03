package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.DingWeiTongJiActivity;
import com.jsmy.pingshan.activity.KuCunBianJiActivity;
import com.jsmy.pingshan.bean.KunCunFrament2Bean;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/3.
 */

public class KuCunFragmentAdapter2 extends RecyclerAdapter<KunCunFrament2Bean.DataBean> {
    private DingWeiTongJiActivity contect;

    public KuCunFragmentAdapter2(DingWeiTongJiActivity context) {
        super(context);
        this.contect = context;
    }

    @Override
    public BaseViewHolder<KunCunFrament2Bean.DataBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new KuCunFramentHolder(parent);
    }

    class KuCunFramentHolder extends BaseViewHolder<KunCunFrament2Bean.DataBean> {
        public KuCunFramentHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_ku_cun_child);
        }

        private TextView tvXzq;
        private TextView tvWz;
        private TextView tvNum;
        private ImageView imgEdit;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvXzq = (TextView) findViewById(R.id.tv_xzq);
            tvWz = (TextView) findViewById(R.id.tv_wz);
            tvNum = (TextView) findViewById(R.id.tv_num);
            imgEdit = (ImageView) findViewById(R.id.img_edit);
        }

        @Override
        public void setData(final KunCunFrament2Bean.DataBean object) {
            super.setData(object);
            tvXzq.setText(object.getADNM());
            tvWz.setText(object.getWzmc());
            tvNum.setText(object.getNumber());
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contect, KuCunBianJiActivity.class);
                    intent.putExtra("xzq", object.getADNM());
                    intent.putExtra("wz", object.getWzmc());
                    intent.putExtra("num", object.getNumber());
                    intent.putExtra("wzid", object.getWzid());
                    contect.startActivity(intent);
                }
            });
        }

        @Override
        public void onItemViewClick(KunCunFrament2Bean.DataBean object) {
            super.onItemViewClick(object);
        }
    }
}
