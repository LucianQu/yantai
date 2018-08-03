package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.ZhiHuiRenYuanAdapter;
import com.jsmy.pingshan.bean.ZhiHuiRenYuanBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;

public class ZhiHuiRenYuanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.my_recycler)
    RefreshRecyclerView myRecycler;
    private ZhiHuiRenYuanAdapter adapter;
    private List<ZhiHuiRenYuanBean.DataBean.ListBean> list;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_zhi_hui_ren_yuan;
    }

    @Override
    protected void initView() {
        tvTitle.setText(getIntent().getStringExtra("zmc") + getIntent().getStringExtra("cmc") + "指挥人员");
        handler = new Handler();
        adapter = new ZhiHuiRenYuanAdapter(this);
        myRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        NetWork.getZhrylist(getIntent().getStringExtra("cid"), this);
//        pushDateeee();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_ZHRY_LIST:
                if ("Y".equals(code)) {
                    list = gson.fromJson(result,ZhiHuiRenYuanBean.class).getData().getList();
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

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
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
    //无接口，构造假数据
    private void pushDateeee(){
        list = new ArrayList<>();
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        list.add(new ZhiHuiRenYuanBean.DataBean.ListBean());
        pushData();
    }

}
