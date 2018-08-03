package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.ShangBaoImg3Adapter;
import com.jsmy.pingshan.adapter.ShangBaoVido3Adapter;
import com.jsmy.pingshan.db.DBManager;
import com.jsmy.pingshan.db.Image;
import com.jsmy.pingshan.db.Page;
import com.jsmy.pingshan.db.Point;
import com.jsmy.pingshan.util.Constant;
import com.liji.imagezoom.util.ImageZoom;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;
import permissions.dispatcher.RuntimePermissions;

public class XunChaShangBao3Activity extends BaseActivity {

    @BindView(R.id.tv_dizhi)
    EditText tvDizhi;
    @BindView(R.id.tv_data)
    EditText tvData;
    @BindView(R.id.img_recycler)
    RefreshRecyclerView imgRecycler;
    @BindView(R.id.vido_recycler)
    RefreshRecyclerView vidoRecycler;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    private Handler handler;
    private List<String> listImg;
    private List<String> listVido;
    private ShangBaoImg3Adapter imgAdapter;
    private ShangBaoVido3Adapter vidoAdapter;
    private DBManager dbManager;
    private int id;
    private Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_shang_bao;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", 1);
        dbManager = new DBManager(this, Constant.DB_NAME, Constant.DB_VERSION, Page.class, Point.class, Image.class);
        handler = new Handler();
        tvData.setEnabled(false);
        tvDizhi.setEnabled(false);
        tvCheck.setVisibility(View.GONE);
        initImaRecycler();
        initVidoRecycler();
    }

    @Override
    protected void initData() {
        findPoint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDataBase();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.img_dizhi, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_dizhi:
                Intent intent = new Intent(this, PushLocationActivity.class);
                intent.putExtra("latitude", point.getJd());
                intent.putExtra("longitude", point.getWd());
                startActivity(intent);
                break;
        }
    }


    private void initImaRecycler() {
        listImg = new ArrayList<>();
        imgAdapter = new ShangBaoImg3Adapter(this);
        imgRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        imgRecycler.setLayoutManager(layoutManager);
        imgRecycler.setAdapter(imgAdapter);
    }

    private void pushImgData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAdapter.clear();
                imgAdapter.addAll(listImg);
            }
        }, 100);
    }

    public void removeImg(int position) {
        imgAdapter.remove(position);
        listImg.remove(position);
    }

    private void initVidoRecycler() {
        listVido = new ArrayList<>();
        vidoAdapter = new ShangBaoVido3Adapter(this);
        vidoRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vidoRecycler.setLayoutManager(layoutManager);
        vidoRecycler.setAdapter(vidoAdapter);
    }

    private void pushVidoData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vidoAdapter.clear();
                vidoAdapter.addAll(listVido);
            }
        }, 100);
    }

    public void removeVido(int position) {
        vidoAdapter.remove(position);
        listVido.remove(position);
    }

    public void findPoint() {
        point = dbManager.findById(Point.class, id);
        tvDizhi.setText(point.getDz());
        tvDizhi.setTextColor(Color.parseColor("#333333"));
        tvData.setText(point.getMs());
        tvData.setTextColor(Color.parseColor("#333333"));
        findImg();
    }

    public void findImg() {
        List<Image> images = new ArrayList<>();
        images.addAll(dbManager.findByArgs(Image.class, "did=" + point.getId(), null));
        if (images.size() > 0) {
            for (Image image : images) {
                if ("1".equals(image.getType())) {
                    listVido.add(image.getUrl());
                } else {
                    listImg.add(image.getUrl());
                }
            }
        }
        pushImgData();
        pushVidoData();
    }


    public void showImage(String url) {
        List<String> list = new ArrayList<>();
        list.addAll(listImg);
        ImageZoom.show(this, url, list);
    }

    public void showVideo(String url) {
        Intent intent = new Intent(this, PlayVideoActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

}
