package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class QiangXianBianJiActivity extends BaseActivity {

    @BindView(R.id.edit_jmm)
    EditText editJmm;
    @BindView(R.id.edit_xmm2)
    EditText editXmm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_qiang_xian_bian_ji;
    }

    @Override
    protected void initView() {
        editJmm.setText(getIntent().getStringExtra("zmc"));
        editXmm2.setText(getIntent().getStringExtra("rs"));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.UPDATE_QXLL:
                if ("Y".equals(code)) {
                    ToastUtil.showShort(this, "抢险人数修改成功！");
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
        String num = editXmm2.getText().toString().trim();
        if (num == null || "".equals(num)) {
            ToastUtil.showShort(this, "请输入抢险人数！");
            return;
        }
//        ToastUtil.showShort(this,"未连接网络数据......");
        NetWork.updateQxll(getIntent().getStringExtra("cid"), num, this);
    }
}
