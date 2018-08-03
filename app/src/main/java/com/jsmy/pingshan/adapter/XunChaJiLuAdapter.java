package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.PlayVideoActivity;
import com.jsmy.pingshan.activity.XunChaJiLuActivity;
import com.jsmy.pingshan.bean.XunChaJiLuBean;
import com.jsmy.pingshan.util.ImageUtil;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.view.RoundImageView;
import com.liji.imagezoom.util.ImageZoom;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/19.
 */

public class XunChaJiLuAdapter extends RecyclerAdapter<XunChaJiLuBean.DataBean.ListBean> {
    private XunChaJiLuActivity context;

    public XunChaJiLuAdapter(XunChaJiLuActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<XunChaJiLuBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new XunChaJiLuHolder(parent);
    }

    class XunChaJiLuHolder extends BaseViewHolder<XunChaJiLuBean.DataBean.ListBean> {
        private ImageView img;
        private ImageView imgPlay;


        public XunChaJiLuHolder(ViewGroup parent) {
            super(parent, R.layout.activity_xun_cha_ji_lu_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            img = (ImageView) findViewById(R.id.img);
            imgPlay = (ImageView) findViewById(R.id.img_play);
        }

        @Override
        public void setData(final XunChaJiLuBean.DataBean.ListBean object) {
            super.setData(object);
            if ("1".equals(object.getWordType())) {
                img.setImageBitmap(ImageUtil.getNetVideoBitmap(object.getWordPath()));
                imgPlay.setVisibility(View.VISIBLE);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PlayVideoActivity.class);
                        intent.putExtra("url", object.getWordPath());
                        context.startActivity(intent);
                    }
                });
            } else {
                Glide.with(context).load(object.getWordPath()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(img);
                imgPlay.setVisibility(View.GONE);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageZoom.show(context, object.getWordPath());
                    }
                });
            }
            MyLog.showLog("FFF", object.getWordType() + " - " + object.getWordPath());
        }

        @Override
        public void onItemViewClick(XunChaJiLuBean.DataBean.ListBean object) {
            super.onItemViewClick(object);
        }
    }
}
