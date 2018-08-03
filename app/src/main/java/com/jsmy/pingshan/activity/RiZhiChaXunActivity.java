package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ActivityTack;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class RiZhiChaXunActivity extends BaseActivity {

    @BindView(R.id.radio)
    RadioGroup radio;
    @BindView(R.id.radio_nei)
    RadioButton radioNei;
    @BindView(R.id.radio_wai)
    RadioButton radioWai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_ri_zhi_cha_xun;
    }

    @Override
    protected void initView() {
        if ("nei".equals(SharePrefUtil.getString(this,"net",""))){
            radioNei.setChecked(true);
        }else {
            radioWai.setChecked(true);
        }

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioNei.getId() == i) {
                    SharePrefUtil.saveString(RiZhiChaXunActivity.this, "net", "nei");
                } else if (radioWai.getId() == i) {
                    SharePrefUtil.saveString(RiZhiChaXunActivity.this, "net", "wai");
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {

        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_check:
                NetWork.resetRetrofit();
                ToastUtil.showShort(this, "网络切换,请重新登录！");
                finish();
                ActivityTack.getInstanse().removeAllActivity();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }

}
