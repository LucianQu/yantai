package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.YiDongBanGongActivity;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/17.
 */

public class HuiHuaGouTongAdapter extends RecyclerAdapter<String> {
    private YiDongBanGongActivity context;
    private TextView tvName;
    private ImageView imgDelete;

    public HuiHuaGouTongAdapter(YiDongBanGongActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<String> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new HuiHuaGouTongHolder(parent);
    }

    public void removeItem(int position) {
        remove(position);
    }

    class HuiHuaGouTongHolder extends BaseViewHolder<String> {
        public HuiHuaGouTongHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_hui_hua_gou_tong_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvName = (TextView) findViewById(R.id.tv_name);
            imgDelete = (ImageView) findViewById(R.id.img_delete);
        }

        @Override
        public void setData(String object) {
            super.setData(object);
            tvName.setText(object);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(getLayoutPosition());
                }
            });
        }

        @Override
        public void onItemViewClick(String object) {
            super.onItemViewClick(object);
        }

    }
}
