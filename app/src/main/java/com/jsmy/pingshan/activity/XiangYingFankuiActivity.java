package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.XiangYingFanKuiBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class XiangYingFankuiActivity extends BaseActivity {

    @BindView(R.id.tv_mc)
    TextView tvMc;
    @BindView(R.id.tv_dj)
    TextView tvDj;
    @BindView(R.id.tv_kssj)
    TextView tvKssj;
    @BindView(R.id.tv_jzdj)
    TextView tvJzdj;
    @BindView(R.id.tv_fksj)
    TextView tvFksj;
    @BindView(R.id.tv_gzz)
    TextView tvGzz;
    @BindView(R.id.tv_ren)
    TextView tvRen;
    @BindView(R.id.tv_trqxrs)
    TextView tvTrqxrs;
    @BindView(R.id.tv_xyzyqz)
    TextView tvXyzyqz;
    @BindView(R.id.tv_yzyqz)
    TextView tvYzyqz;
    @BindView(R.id.tv_wzyqz)
    TextView tvWzyqz;
    @BindView(R.id.tv_hswkqz)
    TextView tvHswkqz;
    @BindView(R.id.tv_yzyskqz)
    TextView tvYzyskqz;
    @BindView(R.id.tv_wzyskqz)
    TextView tvWzyskqz;
    @BindView(R.id.tv_swrs)
    TextView tvSwrs;
    @BindView(R.id.tv_szrs)
    TextView tvSzrs;
    @BindView(R.id.tv_dtfw)
    TextView tvDtfw;
    @BindView(R.id.tv_xyms)
    TextView tvXyms;

    private XiangYingFanKuiBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xiang_ying_fankui;
    }

    @Override
    protected void initView() {
        NetWork.getXyfk(getIntent().getStringExtra("zid"), this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.XY_FK:
                if ("Y".equals(code)) {
                    bean = gson.fromJson(result, XiangYingFanKuiBean.class).getData().get(0);
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
        tvMc.setText(bean.getXymc());
        tvDj.setText(bean.getXydj());
        tvKssj.setText(bean.getStartDt());
        tvJzdj.setText(bean.getEndDt());
        tvFksj.setText(bean.getFksj());
        tvGzz.setText(bean.getWorkGp());
        tvRen.setText(bean.getWorkGpPerson());
        tvTrqxrs.setText(bean.getRescuePerson());
        tvXyzyqz.setText(bean.getDistractTotal());
        tvYzyqz.setText(bean.getDistracted());
        tvWzyqz.setText(bean.getUndistract());
        tvHswkqz.setText(bean.getSiegeTotal());
        tvYzyskqz.setText(bean.getSieged());
        tvWzyskqz.setText(bean.getUnsiege());
        tvSwrs.setText(bean.getDead());
        tvSzrs.setText(bean.getMissing());
        tvDtfw.setText(bean.getCollapseHous());
        tvXyms.setText(bean.getContent());
    }
}
