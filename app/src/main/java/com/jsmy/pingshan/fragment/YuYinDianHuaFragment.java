package com.jsmy.pingshan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.YiDongBanGongActivity;
import com.jsmy.pingshan.adapter.HuiHuaGouTongAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lemon.view.RefreshRecyclerView;

/**
 * Created by Administrator on 2017/10/17.
 */

public class YuYinDianHuaFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_nr)
    public EditText editNr;
    @BindView(R.id.my_recycler)
    RefreshRecyclerView myRecycler;
    private Handler handler;
    private HuiHuaGouTongAdapter mAdapter;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hui_hua_gou_tong, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_check)
    public void onViewClicked() {
        ((YiDongBanGongActivity)getActivity()).showMultiChoiceDialog(3);
    }

    private void initView() {
        tvTitle.setText("语音电话");
        handler = new Handler();
        mAdapter = new HuiHuaGouTongAdapter((YiDongBanGongActivity) getActivity());
        myRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.setAdapter(mAdapter);
    }

    public void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.clear();
                mAdapter.addAll(((YiDongBanGongActivity)getActivity()).choices);
            }
        }, 100);

    }

}
