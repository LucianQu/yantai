package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.XunChaImg3Adapter;
import com.jsmy.pingshan.adapter.XunChaVido3Adapter;
import com.jsmy.pingshan.bean.XunChaBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;
import com.liji.imagezoom.util.ImageZoom;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;

public class XunChaJiLu3Activity extends BaseActivity {

    @BindView(R.id.tv_dizhi)
    EditText tvDizhi;
    @BindView(R.id.tv_data)
    EditText tvData;
    @BindView(R.id.img_recycler)
    RefreshRecyclerView imgRecycler;
    @BindView(R.id.vido_recycler)
    RefreshRecyclerView vidoRecycler;

    private String pId;
    private int position;

    private Handler handler;
    private List<String> listImg;
    private List<String> listVido;
    private XunChaImg3Adapter imgAdapter;
    private XunChaVido3Adapter vidoAdapter;
    private List<XunChaBean.DataBean.ListBean.PointBean> pointBeen;
    private XunChaBean.DataBean.ListBean.PointBean point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_ji_lu3;
    }

    @Override
    protected void initView() {
        pId = getIntent().getStringExtra("pId");
        position = getIntent().getIntExtra("position", 0);

        handler = new Handler();
        tvData.setEnabled(false);
        tvDizhi.setEnabled(false);

        initImaRecycler();
        initVidoRecycler();
    }

    @Override
    protected void initData() {
        NetWork.getYdxcinfo(pId, this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_YDXC_INFO:
                if ("Y".equals(code)) {
                    pointBeen = gson.fromJson(result, XunChaBean.class).getData().getList().get(0).getPoint();
                    point = pointBeen.get(position);
                    pushPoint();
                    pushImg();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.img_dizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_dizhi:
                Intent intent = new Intent(this, PushLocationActivity.class);
                intent.putExtra("latitude", point.getLGTD());
                intent.putExtra("longitude", point.getLTTD());
                MyLog.showLog("TTT", point.getLTTD() + " - - " + point.getLGTD());
                startActivity(intent);
                break;
        }
    }

    private void pushPoint() {
        tvDizhi.setText(point.getAddress());
        tvData.setText(point.getContentDesc());
    }

    private void pushImg() {
        List<XunChaBean.DataBean.ListBean.PointBean.ImgBean> imagBeen = point.getImg();
        for (XunChaBean.DataBean.ListBean.PointBean.ImgBean bean : imagBeen) {
            if ("1".equals(bean.getWordType())) {
                listVido.add(bean.getWordPath());
            } else {
                listImg.add(bean.getWordPath());
            }
        }
        pushImgData();
        pushVidoData();
    }

    private void initImaRecycler() {
        listImg = new ArrayList<>();
        imgAdapter = new XunChaImg3Adapter(this);
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

    private void initVidoRecycler() {
        listVido = new ArrayList<>();
        vidoAdapter = new XunChaVido3Adapter(this);
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
