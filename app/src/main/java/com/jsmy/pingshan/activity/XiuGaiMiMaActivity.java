package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class XiuGaiMiMaActivity extends BaseActivity {

    @BindView(R.id.edit_jmm)
    EditText editJmm;
    @BindView(R.id.edit_xmm1)
    EditText editXmm1;
    @BindView(R.id.edit_xmm2)
    EditText editXmm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xiu_gai_mi_ma;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type){
            case API.UPDATE_MM:
                if ("Y".equals(code)){
                    ToastUtil.showShort(this,"密码修改成功！");
                    finish();
                }else {
                    ToastUtil.showShort(this,msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_change:
                checkPassWord();
                break;
        }
    }

    private void checkPassWord(){
        String jmm = editJmm.getText().toString().trim();
        if (jmm == null || "".equals(jmm)){
            ToastUtil.showShort(this,"请输入旧密码！");
            return;
        }

        String xmm1 = editXmm1.getText().toString().trim();
        if (jmm == null || "".equals(xmm1)){
            ToastUtil.showShort(this,"请输入新密码！");
            return;
        }

        String xmm2 = editXmm2.getText().toString().trim();
        if (jmm == null || "".equals(xmm2)){
            ToastUtil.showShort(this,"请再次输入新密码！");
            return;
        }

        if (!xmm1.equals(xmm2)){
            ToastUtil.showShort(this,"新密码不一致，请重新输入！");
            editXmm1.setText("");
            editXmm1.setHint("请输入新密码");
            editXmm2.setText("");
            editXmm2.setHint("请再次输入新密码");
            return;
        }

//        ToastUtil.showShort(this,"未连接网络数据......");
        NetWork.updatemm(SharePrefUtil.getString(this, Constant.SHARE_USERNAME, ""),jmm,xmm2,this);
    }

}
