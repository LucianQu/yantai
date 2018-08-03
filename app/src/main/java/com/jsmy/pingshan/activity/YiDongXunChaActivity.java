package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.YiDongXunChaAdapter;
import com.jsmy.pingshan.bean.YiDongXunChaBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;

public class YiDongXunChaActivity extends BaseActivity {

    @BindView(R.id.my_spinner)
    Spinner mySpinner;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.m_RecyclerView)
    RefreshRecyclerView mRecyclerView;
    private String[] states;
    private Handler handler;
    private YiDongXunChaAdapter mAdapter;
    private List<YiDongXunChaBean.DataBean.ListBean> list;
    private String mh = "";
    private String rq = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_yi_dong_xun_cha;
    }

    @Override
    protected void initView() {
        states = getResources().getStringArray(R.array.spingarr);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, states);
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        rq = "" + 3;
                        break;
                    case 1:
                        rq = "" + 6;
                        break;
                    case 2:
                        rq = "" + 30;
                        break;
                    case 3:
                        rq = "";
                        break;
                }
                NetWork.getYdxclist("", rq, YiDongXunChaActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        list = new ArrayList<>();
        handler = new Handler();
        mAdapter = new YiDongXunChaAdapter(this);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_YDXC_LIST:
                if ("Y".equals(code)) {
                    list.clear();
                    list.addAll(gson.fromJson(result, YiDongXunChaBean.class).getData().getList());
                    pushData();
                } else {
                    list.clear();
                    pushData();
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_update, R.id.img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_update:
//                startActivityForResult(new Intent(this, XunChaShangBaoActivity.class),101);
                startActivityForResult(new Intent(this, XunChaShangBao2Activity.class),101);
                break;
            case R.id.img_search:
                searchPerson();
                break;
        }
    }

    private void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.clear();
                mAdapter.addAll(list);
            }
        }, 100);
    }

    private void changeInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
    }

    private void searchPerson() {
        mh = editSearch.getText().toString().trim();
        NetWork.getYdxclist(mh, rq, YiDongXunChaActivity.this);
        changeInput();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101){
            NetWork.getYdxclist("", rq, YiDongXunChaActivity.this);
        }
    }
}
