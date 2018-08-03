package com.jsmy.pingshan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ConversationListActivity;
import com.jsmy.pingshan.adapter.PersonFragment3Adapter;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.bean.TaoLunZuBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lemon.view.RefreshRecyclerView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by Administrator on 2017/11/6.
 */

public class PersonFragment3 extends BaseFragment {

    @BindView(R.id.my_recycler)
    RefreshRecyclerView myRecycler;
    Unbinder unbinder;
    private List<PersonBean.DataBean.ListBean> listBeen;
    private Handler handler;
    private PersonFragment3Adapter adapter;
    private List<TaoLunZuBean.DataBean.ListBean> taolunzu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person3, null);
        unbinder = ButterKnife.bind(this, view);
        initRecycler();
        NetWork.getUserList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), this);
        NetWork.discussList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), PersonFragment3.this);
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_USER_LIST:
                if ("Y".equals(code)) {
                    listBeen = gson.fromJson(result, PersonBean.class).getData().getList();
                } else {
                    ToastUtil.showShort(getActivity(), msg);
                }
                break;
            case API.DISCUSS_CREAT:
                if ("Y".equals(code)) {
                    NetWork.discussList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), PersonFragment3.this);
                } else {
                    ToastUtil.showShort(getActivity(), msg);
                }
                break;
            case API.DISCUSS_LIST:
                if ("Y".equals(code)) {
                    taolunzu.clear();
                    taolunzu.addAll(gson.fromJson(result, TaoLunZuBean.class).getData().getList());
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

    @OnClick(R.id.rela_creat)
    public void onViewClicked() {
        showInputDialog();
    }

    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(getActivity());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(getActivity());
        inputDialog.setTitle("输入讨论组标题").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editText.getText().toString().trim();
                        if (null == title || "".equals(title)) {
                            ToastUtil.showShort(getActivity(), "标题不能为空");
                        } else {
                            showMultiChoiceDialog(title);
                        }
                    }
                }).show();
    }

    private String[] items;
    private boolean initChoiceSets[];
    private String[] yourChoices;
    private List<String> list;

    public void showMultiChoiceDialog(final String title) {
        //设置每个选项的名称
        items = new String[listBeen.size()];
        for (int i = 0; i < listBeen.size(); i++) {
            items[i] = listBeen.get(i).getUserName();
        }
        // 设置默认选中的选项，全为false默认均未选中
        initChoiceSets = new boolean[listBeen.size()];
        for (int i = 0; i < listBeen.size(); i++) {
            initChoiceSets[i] = false;
        }
        //选择/取消 改变值
        yourChoices = new String[listBeen.size()];
        for (int i = 0; i < listBeen.size(); i++) {
            yourChoices[i] = "aaa";
        }
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(getActivity());
        multiChoiceDialog.setTitle("请选择讨论组成员");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
//                    yourChoices.add(which, listBeen.get(which).getUserName());
                    yourChoices[which] = listBeen.get(which).getUserName();
                } else {
//                    yourChoices.add(which, "aaa");
                    yourChoices[which] = "aaa";
                }
            }

        });
        list = new ArrayList<>();
        multiChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.clear();
                for (int i = 0; i < yourChoices.length; i++) {
                    if (!yourChoices[i].equals("aaa")) {
                        list.add(listBeen.get(i).getUserid());
                    }
                }
                if (list.size() > 0) {
                    creatDicussion(title);
                }else {
                    ToastUtil.showShort(getActivity(),"创建失败，当前没有选择用户！");
                }
            }
        });
        multiChoiceDialog.show();
    }

    private void creatDicussion(final String title) {
        RongIM.getInstance().createDiscussionChat(getActivity(), list, title, new RongIMClient.CreateDiscussionCallback() {
            @Override
            public void onSuccess(String s) {
                MyLog.showLog("PPP", s);
                String userID = "";
                for (String user : list) {
                    userID = userID + user + ",";
                }
                userID = userID + SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId());
                NetWork.discussCreat(
                        SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()),
                        s,
                        title,
                        userID,
                        PersonFragment3.this);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                ToastUtil.showShort(getActivity(), errorCode.getMessage());
            }
        });
    }

    private void initRecycler() {
        taolunzu = new ArrayList<>();
        handler = new Handler();
        adapter = new PersonFragment3Adapter((ConversationListActivity) getActivity());
        myRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.setAdapter(adapter);
    }

    private void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(taolunzu);
            }
        }, 100);
    }

    public void getDiscussList() {
        NetWork.discussList(SharePrefUtil.getString(getActivity(), Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), PersonFragment3.this);
    }

}
