package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.MingLingRecivAdapter;
import com.jsmy.pingshan.adapter.MingLingSendAdapter;
import com.jsmy.pingshan.bean.XCMLfsBean;
import com.jsmy.pingshan.bean.XCMLjsBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;

public class XunChaMingLingActivity extends BaseActivity {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.line_send)
    TextView lineSend;
    @BindView(R.id.tv_reciv)
    TextView tvReciv;
    @BindView(R.id.line_reciv)
    TextView lineReciv;
    @BindView(R.id.recyc_send)
    RefreshRecyclerView recycSend;
    @BindView(R.id.recyc_reciv)
    RefreshRecyclerView recycReciv;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    private Handler handler;

    private List<XCMLfsBean.DataBean.ListBean> listFS;
    private MingLingSendAdapter sendAdapter;

    private List<XCMLjsBean.DataBean.ListBean> listJS;
    private MingLingRecivAdapter recivAdapter;

    private String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_ming_ling;
    }

    @Override
    protected void initView() {
        if ("1".equals(MyApplication.getMyApplication().bean.getLevel())) {
            imgAdd.setVisibility(View.VISIBLE);
        } else {
            imgAdd.setVisibility(View.GONE);
        }
        checkToosbar(2);
        handler = new Handler();

        listFS = new ArrayList<>();
        sendAdapter = new MingLingSendAdapter(this);
        recycSend.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        recycSend.setLayoutManager(new LinearLayoutManager(this));
        recycSend.setAdapter(sendAdapter);

        listJS = new ArrayList<>();
        recivAdapter = new MingLingRecivAdapter(this);
        recycReciv.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        recycReciv.setLayoutManager(new LinearLayoutManager(this));
        recycReciv.setAdapter(recivAdapter);

    }

    @Override
    protected void initData() {
        NetWork.getXCMLfsList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), str, this);
        NetWork.getXCMLjsList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), str, this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_XCML_FS_LIST:
                if ("Y".equals(code)) {
                    listFS.clear();
                    listFS.addAll(gson.fromJson(result, XCMLfsBean.class).getData().getList());
                    pushData(API.GET_XCML_FS_LIST);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_XCML_JS_LIST:
                if ("Y".equals(code)) {
                    listJS.clear();
                    listJS.addAll(gson.fromJson(result, XCMLjsBean.class).getData().getList());
                    pushData(API.GET_XCML_JS_LIST);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.img_add, R.id.img_search, R.id.rela_send, R.id.rela_reciv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                startActivityForResult(new Intent(this, SendMingLingActivity.class), 101);
                break;
            case R.id.img_search:
                str = editSearch.getText().toString().trim();
                NetWork.getXCMLfsList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), str, this);
                NetWork.getXCMLjsList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), str, this);
                changeInput();
                break;
            case R.id.rela_send:
                checkToosbar(1);
                break;
            case R.id.rela_reciv:
                checkToosbar(2);
                break;
        }
    }

    private void checkToosbar(int num) {
        switch (num) {
            case 1:
                tvSend.setTextColor(Color.parseColor("#379AFF"));
                lineSend.setVisibility(View.VISIBLE);
                tvReciv.setTextColor(Color.parseColor("#333333"));
                lineReciv.setVisibility(View.GONE);
                recycSend.setVisibility(View.VISIBLE);
                recycReciv.setVisibility(View.GONE);
                break;
            case 2:
                tvSend.setTextColor(Color.parseColor("#333333"));
                lineSend.setVisibility(View.GONE);
                tvReciv.setTextColor(Color.parseColor("#379AFF"));
                lineReciv.setVisibility(View.VISIBLE);
                recycSend.setVisibility(View.GONE);
                recycReciv.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void pushData(final String type) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case API.GET_XCML_FS_LIST:
                        sendAdapter.clear();
                        sendAdapter.addAll(listFS);
                        break;
                    case API.GET_XCML_JS_LIST:
                        recivAdapter.clear();
                        recivAdapter.addAll(listJS);
                        break;
                }
            }
        }, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            NetWork.getXCMLfsList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), str, this);
            NetWork.getXCMLjsList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), str, this);
        } else {

        }
    }

    private void changeInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
    }
}
