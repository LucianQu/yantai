package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.VersionBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;
import com.jsmy.pingshan.util.UpdateManage;
import com.jsmy.pingshan.util.VersionUtil;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BanBenGuanLiActivity extends BaseActivity {

    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.old_version)
    TextView oldVersion;
    @BindView(R.id.new_version)
    TextView newVersion;
    private VersionBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_ban_ben_guan_li;
    }

    @Override
    protected void initView() {
        oldVersion.setText("v" + VersionUtil.getVersionName(this));
    }

    @Override
    protected void initData() {
        NetWork.getVersion(this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.APP_VERSION:
                if ("Y".equals(code)) {
                    bean = gson.fromJson(result, VersionBean.class).getData().get(0);
                    newVersion.setText("v" + bean.getVersionName());
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_update:
                if (!VersionUtil.getVersionName(this).equals(bean.getVersionName())){
                    new UpdateManage(this,bean.getVersionName(),bean.getVersionUrl(),"N");
                }else {
                    ToastUtil.showShort(this,"当前已经是最新版本！");
                }
//                ToastUtil.showShort(this, "无外网安装包......");
                break;
        }
    }
}
