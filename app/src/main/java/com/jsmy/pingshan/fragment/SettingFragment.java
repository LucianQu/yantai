package com.jsmy.pingshan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.BanBenGuanLiActivity;
import com.jsmy.pingshan.activity.LoginActivity;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.activity.PersonActivity;
import com.jsmy.pingshan.activity.RiZhiChaXunActivity;
import com.jsmy.pingshan.activity.XiuGaiMiMaActivity;
import com.jsmy.pingshan.util.ActivityTack;
import com.jsmy.pingshan.util.SharePrefUtil;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/11.
 */

public class SettingFragment extends BaseFragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
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

    @OnClick({R.id.ll_gerenxinxi, R.id.ll_rizhichaxun, R.id.ll_banbenguanli, R.id.ll_xiugaimima, R.id.ll_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_gerenxinxi:
                getActivity().startActivity(new Intent(getActivity(), PersonActivity.class));
                break;
            case R.id.ll_rizhichaxun:
                getActivity().startActivity(new Intent(getActivity(), RiZhiChaXunActivity.class));
                break;
            case R.id.ll_banbenguanli:
                getActivity().startActivity(new Intent(getActivity(), BanBenGuanLiActivity.class));
                break;
            case R.id.ll_xiugaimima:
                getActivity().startActivity(new Intent(getActivity(), XiuGaiMiMaActivity.class));
                break;
            case R.id.ll_exit:
                getActivity().finish();
                ActivityTack.getInstanse().removeAllActivity();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                SharePrefUtil.clear(getContext());
                break;
        }
    }
}
