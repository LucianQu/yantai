package com.jsmy.pingshan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.adapter.TongXunLuAdapter;
import com.jsmy.pingshan.bean.TongXunLuBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

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

public class TongXunLuFragment extends BaseFragment {
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.my_list)
    ExpandableListView myExpand;
    private TongXunLuAdapter adapter;
    private List<TongXunLuBean.DataBean.ListBeanX> list;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tongxunlu, container, false);
        unbinder = ButterKnife.bind(this, view);
//        pushData();
        NetWork.getPersonList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), "", this);
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_PERSON_LIST:
                if ("1".equals(code)) {
                    if (gson.fromJson(result, TongXunLuBean.class).getData().getList().size() > 0) {
                        list = gson.fromJson(result, TongXunLuBean.class).getData().getList();
                        adapter = new TongXunLuAdapter((MainActivity) getActivity(), list);
                        myExpand.setAdapter(adapter);
                    }
                } else {
                    ToastUtil.showShort(getContext(), msg);
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

    @OnClick(R.id.img_search)
    public void onViewClicked() {
        changeInput();
//        ToastUtil.showShort(getActivity(),"未连接网络数据......");
        searchPerson();
    }

    private void searchPerson() {
        String word = editSearch.getText().toString().trim();
        if (word != null && !"".equals(word)) {
            NetWork.getPersonList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), word, this);
        } else {
            NetWork.getPersonList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), "", this);
        }
        changeInput();
    }

    private void changeInput() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
    }
}
