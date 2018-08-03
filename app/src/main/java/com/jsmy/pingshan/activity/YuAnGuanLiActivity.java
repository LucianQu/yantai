package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.YuAnGuanLiAdapter;
import com.jsmy.pingshan.bean.XZQBean;
import com.jsmy.pingshan.bean.YuAnGuanLiBean;
import com.jsmy.pingshan.bean.YuanBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YuAnGuanLiActivity extends BaseActivity {

    @BindView(R.id.my_expand)
    ExpandableListView myExpand;
    private YuAnGuanLiAdapter adapter;
//    private List<XZQBean.DataBean.ListBean> list;
    private List<YuanBean.DataBean.ListBean> list;
    private static String ID_KEY="idkey";
    private  String regionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_yu_an_guan_li;
    }

    @Override
    protected void initView() {
        NetWork.getxzList(getIntent().getStringExtra(ID_KEY),this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        Log.e("yuan", "onSuccess: "+result);
        switch (type) {

//            case API.GET_XZ_LIST:
            case API.GET_XZ_LIST:
                if ("1".equals(code)) {
//                    list = gson.fromJson(result, XZQBean.class).getData().getList();
                    list = gson.fromJson(result, YuanBean.class).getData().getList();
                    adapter = new YuAnGuanLiAdapter(this, list);
                    myExpand.setGroupIndicator(null);
                    myExpand.setAdapter(adapter);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    public static void startActivity(Context context, String xid) {
        Intent intent = new Intent(context, YuAnGuanLiActivity.class);
        intent.putExtra(ID_KEY, xid);
        context.startActivity(intent);

    }
}
