package com.jsmy.pingshan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.jsmy.pingshan.activity.ShiPinJianKongActivity;
import com.jsmy.pingshan.activity.ShuiQingActivity;
import com.jsmy.pingshan.activity.XunChaMingLingActivity;
import com.jsmy.pingshan.activity.YiDongBanGongActivity;
import com.jsmy.pingshan.activity.YiDongXunChaActivity;
import com.jsmy.pingshan.activity.YuAnGuanLiActivity;
import com.jsmy.pingshan.activity.YuQingActivity;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;
import com.test.demo.DemoActivity;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/11.
 */

public class GongZuoTaiFragment extends BaseFragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gongzuotai, container, false);
        unbinder = ButterKnife.bind(this, view);
        NetWork.getVideoList(this);
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_VIDEO_LIST:
                if ("Y".equals(code)) {
                    MyApplication.getMyApplication().video = result;
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
}
