package com.jsmy.pingshan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ConversationListActivity;
import com.jsmy.pingshan.activity.DingWeiTongJiActivity;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.activity.QiXiangXinXiActivity;
import com.jsmy.pingshan.activity.ShiPinHuiShangActivity;
import com.jsmy.pingshan.activity.ShuiQingActivity;
import com.jsmy.pingshan.activity.XunChaMingLingActivity;
import com.jsmy.pingshan.activity.YiDongXunChaActivity;
import com.jsmy.pingshan.activity.YuAnGuanLiActivity;
import com.jsmy.pingshan.activity.YuQingActivity;
import com.jsmy.pingshan.adapter.DividerGridItemDecoration;
import com.jsmy.pingshan.adapter.GongZuoTaiFragment2Adapter;
import com.jsmy.pingshan.bean.FuntionBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;
import com.test.demo.DemoActivity;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/11.
 */

public class GongZuoTaiFragment2 extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.my_recycler)
    RecyclerView myRecycler;
    private GongZuoTaiFragment2Adapter adapter;
    private List<FuntionBean.DataBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gongzuotai2, container, false);
        unbinder = ButterKnife.bind(this, view);
        NetWork.getVideoList(this);
        NetWork.getFunctionList(MyApplication.getMyApplication().bean.getLevel(),this);
        initRecycler();
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_VIDEO_LIST:
                if ("1".equals(code)) {
                    MyApplication.getMyApplication().video = result;
                } else {
                    ToastUtil.showShort(getActivity(), msg);
                }
                break;
            case API.GET_FUNCTION_LIST:
                if ("1".equals(code)){
                    list.clear();
                    list.addAll(gson.fromJson(result,FuntionBean.class).getData());
                    adapter.notifyDataSetChanged();
                }else {
                    ToastUtil.showShort(getActivity(), msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRecycler() {
        list = new ArrayList<>();
        adapter = new GongZuoTaiFragment2Adapter((MainActivity) getActivity(), list);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        myRecycler.setLayoutManager(layoutManager);
        myRecycler.setAdapter(adapter);
        myRecycler.setItemAnimator(null);
        myRecycler.addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

}
