package com.jsmy.pingshan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.PreAdapter;
import com.jsmy.pingshan.bean.PreBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PreFragment extends BaseFragment {

    Unbinder unbinder;

    List<PreBean.DataBean.ListBean> list;
    PreAdapter adapter;
    @BindView(R.id.recyc_reciv)
    RecyclerView recycReciv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre, container,false);
        unbinder = ButterKnife.bind(this, view);
        NetWork.getPreList(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_PRE_LIST:
                if ("1".equals(code)) {
                    if (gson.fromJson(result, PreBean.class).getData().getList().size() > 0) {
//                        list = gson.fromJson(result, PreBean.class).getData().getList();
//                        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                        recycReciv.setLayoutManager(layoutManager);
//                        recycReciv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
//                        adapter = new PreAdapter(list, this.getContext(),from);
//                        recycReciv.setAdapter(adapter);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {
    }
}
