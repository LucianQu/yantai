package com.jsmy.pingshan.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.XunChaShangBao3Activity;
import com.jsmy.pingshan.activity.XunChaShangBaoActivity;
import com.jsmy.pingshan.util.ImageUtil;
import com.jsmy.pingshan.util.MyLog;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/19.
 */

public class ShangBaoVido3Adapter extends RecyclerAdapter<String> {
    private XunChaShangBao3Activity context;

    public ShangBaoVido3Adapter(XunChaShangBao3Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<String> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        mStatusView.setVisibility(View.GONE);
        return new ShangBaoVidoHolder(parent);
    }

    class ShangBaoVidoHolder extends BaseViewHolder<String> {
        private ImageView img;
        private ImageView imgPlay;
        private ImageView imgClose;
        private ImageView imgChioce;

        public ShangBaoVidoHolder(ViewGroup parent) {
            super(parent, R.layout.activity_xun_cha_shang_bao_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            img = (ImageView) findViewById(R.id.img);
            imgPlay = (ImageView) findViewById(R.id.img_play);
            imgClose = (ImageView) findViewById(R.id.img_close);
            imgChioce = (ImageView) findViewById(R.id.img_chioce);
        }

        @Override
        public void setData(final String object) {
            super.setData(object);
            img.setVisibility(View.VISIBLE);
            if (object.contains("http")){
                img.setImageBitmap(ImageUtil.getNetVideoBitmap(object));
            }else {
                Glide.with(context).load(object).diskCacheStrategy(DiskCacheStrategy.RESULT).into(img);
            }

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.showVideo(object);
                }
            });
            imgPlay.setVisibility(View.VISIBLE);
            imgClose.setVisibility(View.GONE);
            imgChioce.setVisibility(View.GONE);
        }

        @Override
        public void onItemViewClick(String object) {
            super.onItemViewClick(object);
        }

    }
}
