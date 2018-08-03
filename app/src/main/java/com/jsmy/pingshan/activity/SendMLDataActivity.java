package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.SendMLDataBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class SendMLDataActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.edit_data)
    TextView editData;
    @BindView(R.id.edit_reciv)
    TextView editReciv;

    private String sendId = "";
    private SendMLDataBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_send_mldata;
    }

    @Override
    protected void initView() {
        sendId = getIntent().getStringExtra("id");
    }

    @Override
    protected void initData() {
        NetWork.getfsxcmlinfo(sendId, this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

        if ("Y".equals(code)) {
            switch (type) {
                case API.GET_FSXCML_INFO:
                    bean = gson.fromJson(result,SendMLDataBean.class).getData();
                    setDataView();
                    break;
            }
        } else {
            ToastUtil.showShort(this, msg);
        }

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    private void setDataView(){
        tvName.setText(bean.getJsName());
        tvTime.setText(bean.getSendTime());
        editData.setText(bean.getSendContent());
        editReciv.setText(bean.getReplyContent());
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        this.finish();
    }
}
