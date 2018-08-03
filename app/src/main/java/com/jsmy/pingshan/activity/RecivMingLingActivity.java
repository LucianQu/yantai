package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.MingLingInfoBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class RecivMingLingActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.edit_data)
    TextView editData;
    @BindView(R.id.edit_reciv)
    EditText editReciv;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private MingLingInfoBean.DataBean bean;
    private String jsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_reciv_ming_ling;
    }

    @Override
    protected void initView() {
        jsid = getIntent().getStringExtra("id");
        NetWork.update_xmml_is_read(jsid, this);
        NetWork.getXCMLinfo(getIntent().getStringExtra("id"), this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_XCML_INFO:
                if ("Y".equals(code)) {
                    bean = gson.fromJson(result, MingLingInfoBean.class).getData();
                    tvName.setText(bean.getSendName());
                    tvTime.setText(bean.getSendTime());
                    editData.setText(bean.getSendContent());
                    editReciv.setText(bean.getReplyContent());
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.UPDATE_XCML:
                if ("Y".equals(code)) {
                    ToastUtil.showShort(this, msg);
                    setBackData("Y");
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.UPDATE_XCML_IS_READ:

                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                setBackData("N");
                break;
            case R.id.tv_send:
                if (!"".equals(bean.getReplyContent())) {
                    ToastUtil.showShort(this, "已回复，无需再次回复！");
                } else {
                    sendBack();
                }
                break;
        }
    }

    private void setBackData(String code) {
//        if ("Y".equals(code)) {
//            setResult(101);
//        } else {
//            setResult(100);
//        }
        setResult(101);
        finish();
    }

    @Override
    public void onBackPressed() {
        setBackData("N");
    }

    private void sendBack() {
        String str = editReciv.getText().toString().trim();
        if (str == null || "".equals(str)) {
            ToastUtil.showShort(this, "请输入回复内容！");
            return;
        }
        NetWork.getUpdataXcml(getIntent().getStringExtra("id"), str, bean.getUserid(), SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), this);
    }
}
