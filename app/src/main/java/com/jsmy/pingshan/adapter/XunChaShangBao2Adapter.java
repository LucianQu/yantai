package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.XunChaShangBao2Activity;
import com.jsmy.pingshan.activity.XunChaShangBao3Activity;
import com.jsmy.pingshan.db.Page;
import com.jsmy.pingshan.db.Point;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/13.
 */

public class XunChaShangBao2Adapter extends RecyclerAdapter<Point> {
    private XunChaShangBao2Activity context;

    public XunChaShangBao2Adapter(XunChaShangBao2Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<Point> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new XunChaShangBao2Holder(parent);
    }

    class XunChaShangBao2Holder extends BaseViewHolder<Point> {
        public XunChaShangBao2Holder(ViewGroup parent) {
            super(parent, R.layout.activity_xun_cha_shang_bao2_item);
        }

        private TextView tvNum;
        private TextView tvName;
        private TextView tvTime;
        private ImageView imgData;
        private ImageView imgDelete;

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvNum = (TextView) findViewById(R.id.tv_num);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvTime = (TextView) findViewById(R.id.tv_time);
            imgData = (ImageView) findViewById(R.id.img_data);
            imgDelete = (ImageView) findViewById(R.id.img_delete);
        }

        @Override
        public void setData(final Point object) {
            super.setData(object);
            tvNum.setText(getLayoutPosition() + 1 + "");
            tvName.setText(object.getDz());
            tvTime.setText("");
            imgData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, XunChaShangBao3Activity.class);
                    intent.putExtra("id", object.getId());
                    context.startActivity(intent);
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    context.showNormalDialog(object.getId(), 1, "确定删除该条记录?");
                    context.showDeleteDialog(object.getId(), object.getTid() + "", object.getMs(), object.getDz(), object.getWd(), object.getJd());
                }
            });
        }
    }
}
