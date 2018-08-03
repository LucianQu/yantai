package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.XunChaJiLuAdapter;
import com.jsmy.pingshan.bean.XunChaJiLuBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;

public class XunChaJiLuActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.my_recycler)
    RefreshRecyclerView myRecycler;
    private List<XunChaJiLuBean.DataBean.ListBean> list;
    private Handler handler;
    private XunChaJiLuAdapter adapter;
    private String lttD;
    private String lgtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_ji_lu;
    }

    @Override
    protected void initView() {
        handler = new Handler();
        adapter = new XunChaJiLuAdapter(this);
        myRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecycler.setLayoutManager(new GridLayoutManager(this,4));
        myRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        NetWork.getYdxcinfo(getIntent().getStringExtra("id"), this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_YDXC_INFO:
                if ("Y".equals(code)) {
                    list = gson.fromJson(result, XunChaJiLuBean.class).getData().getList();
                    pushInit(list.get(0));
                    pushData();
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
                intent.putExtra("latitude", lttD);
                intent.putExtra("longitude", lgtd);
                startActivity(intent);
                break;
        }
    }

    private void pushInit(XunChaJiLuBean.DataBean.ListBean bean) {
        tvName.setText(bean.getUsername());
        tvTime.setText(bean.getBeginDt());
        lttD = bean.getLTTD();
        lgtd = bean.getLGTD();
        tvDizhi.setText(bean.getAddress());
        tvData.setText(bean.getContentDesc());
    }

    private void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(list);
            }
        }, 100);
    }

}
