package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class KuCunBianJiActivity extends BaseActivity {


    @BindView(R.id.edit_xzq)
    EditText editXzq;
    @BindView(R.id.edit_wz)
    EditText editWz;
    @BindView(R.id.edit_num)
    EditText editNum;
    private String xzq = "";
    private String wz = "";
    private String num = "";
    private String wzid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_ku_cun_bian_ji;
    }

    @Override
    protected void initView() {
        xzq = getIntent().getStringExtra("xzq");
        wz = getIntent().getStringExtra("wz");
        num = getIntent().getStringExtra("num");
        wzid = getIntent().getStringExtra("wzid");
    }

    @Override
    protected void initData() {
        editXzq.setText(xzq);
        editWz.setText(wz);
        editNum.setText(num);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.UPDATE_KCWZ:
                if ("Y".equals(code)) {
                    ToastUtil.showShort(this, "库存物资修改成功！");
                    finish();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
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
                updataEdit();
                break;
        }
    }

    private void updataEdit() {
        if ("".equals(editNum.getText().toString().trim())) {
            ToastUtil.showShort(this,"请输入物资数量");
        }else {
            Map<String, String> map = new HashMap<>();
            map.put("wzid", wzid);
            map.put("number",editNum.getText().toString().trim());
            NetWork.updateKcwz(map, this);
        }


    }

}
