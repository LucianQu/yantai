package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.ZaiQingTongJiBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class ZaiQingTongJiActivity extends BaseActivity {

    @BindView(R.id.tv_kssj)
    TextView tvKssj;
    @BindView(R.id.tv_jssj)
    TextView tvJssj;
    @BindView(R.id.tv_swrs)
    TextView tvSwrs;
    @BindView(R.id.tv_szrs)
    TextView tvSzrs;
    @BindView(R.id.tv_dtfw)
    TextView tvDtfw;
    @BindView(R.id.tv_zqms)
    TextView tvZqms;
    private ZaiQingTongJiBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_zai_qing_tong_ji;
    }

    @Override
    protected void initView() {
        NetWork.getZqtj(getIntent().getStringExtra("zid"), this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.ZQ_TJ:
                if ("Y".equals(code)) {
                    bean = gson.fromJson(result, ZaiQingTongJiBean.class).getData().get(0);
                    pushDate();
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

    private void pushDate() {
        tvKssj.setText(bean.getQsdt());
        tvJssj.setText(bean.getJsdt());
        tvSwrs.setText(bean.getSwrs());
        tvSzrs.setText(bean.getMissp());
        tvDtfw.setText(bean.getDtfw());
        tvZqms.setText(bean.getZqms());
    }
}
