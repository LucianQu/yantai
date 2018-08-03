package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.view.CircleImageView;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    @BindView(R.id.img_tx)
    CircleImageView imgTx;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_dh)
    TextView tvDh;
    @BindView(R.id.tv_dw)
    TextView tvDw;
    @BindView(R.id.tv_zw)
    TextView tvZw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person;
    }

    @Override
    protected void initView() {
        if (MyApplication.getMyApplication().bean != null) {
            tvName.setText(MyApplication.getMyApplication().bean.getXm());
            tvDh.setText(MyApplication.getMyApplication().bean.getTel());
            tvDw.setText(MyApplication.getMyApplication().bean.getDw());
            tvZw.setText(MyApplication.getMyApplication().bean.getZw());
        }else {

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.rela_img, R.id.rela_name, R.id.rela_sex, R.id.rela_phone, R.id.rela_dan, R.id.rela_zhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rela_img:
                break;
            case R.id.rela_name:
                break;
            case R.id.rela_sex:
                break;
            case R.id.rela_phone:
                break;
            case R.id.rela_dan:
                break;
            case R.id.rela_zhi:
                break;
        }
    }
}
