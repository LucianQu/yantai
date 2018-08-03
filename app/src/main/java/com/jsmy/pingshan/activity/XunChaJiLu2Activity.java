package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.XunChaJiLu2Adapter;
import com.jsmy.pingshan.bean.XunChaBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;

public class XunChaJiLu2Activity extends BaseActivity {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_data)
    EditText tvData;
    @BindView(R.id.m_recycler)
    RefreshRecyclerView mRecycler;
    @BindView(R.id.tv_enddate)
    TextView tvEnddate;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    private Handler handler;
    private XunChaBean.DataBean dataBean;
    private List<XunChaBean.DataBean.ListBean.PointBean> pointBeen;
    private XunChaJiLu2Adapter adapter;
    private String pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_ji_lu2;
    }

    @Override
    protected void initView() {
        pId = getIntent().getStringExtra("id");
        initRecycler();
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
                    dataBean = gson.fromJson(result, XunChaBean.class).getData();
                    pushPage();
                    pointBeen.clear();
                    pointBeen.addAll(dataBean.getList().get(0).getPoint());
                    pushPoint();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_map:
                Intent intent = new Intent(this, CheckLineActivity.class);
                intent.putExtra("id", pId);
                startActivity(intent);
                break;
        }
    }

    private void pushPage() {
        tvName.setText(dataBean.getList().get(0).getUsername());
        tvDate.setText(dataBean.getList().get(0).getBeginDt().substring(0, 10));
        tvTime.setText(dataBean.getList().get(0).getBeginDt().substring(11));
        if (dataBean.getList().get(0).getEndDt() != null && dataBean.getList().get(0).getEndDt().length() > 10) {
            tvEnddate.setText(dataBean.getList().get(0).getEndDt().substring(0, 10));
            tvEndtime.setText(dataBean.getList().get(0).getEndDt().substring(11));
        }
        tvData.setText(dataBean.getList().get(0).getTitleDesc());
    }

    private void initRecycler() {
        pointBeen = new ArrayList<>();
        handler = new Handler();
        adapter = new XunChaJiLu2Adapter(this);
        mRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(adapter);
    }

    private void pushPoint() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(pointBeen);
            }
        }, 100);
    }

    public void goToImag(int position) {
        Intent intent = new Intent(this, XunChaJiLu3Activity.class);
        intent.putExtra("pId", pId);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
