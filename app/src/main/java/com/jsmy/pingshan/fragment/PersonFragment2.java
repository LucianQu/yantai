package com.jsmy.pingshan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ConversationListActivity;
import com.jsmy.pingshan.adapter.PersonFragment2Adapter;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lemon.view.RefreshRecyclerView;

/**
 * Created by Administrator on 2017/11/6.
 */

public class PersonFragment2 extends BaseFragment {

    @BindView(R.id.m_RecyclerView)
    RefreshRecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<PersonBean.DataBean.ListBean> listBeen;
    private Handler handler;
    private PersonFragment2Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person2, null);
        unbinder = ButterKnife.bind(this, view);
        initRecycler();
        NetWork.getUserList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()),this);
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_USER_LIST:
                if ("Y".equals(code)) {
                    listBeen = gson.fromJson(result, PersonBean.class).getData().getList();
                    pushData();
                } else {
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

    private void initRecycler(){
        handler = new Handler();
        adapter = new PersonFragment2Adapter((ConversationListActivity) getActivity());
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
    }

    private void pushData(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(listBeen);
            }
        },100);
    }
}
