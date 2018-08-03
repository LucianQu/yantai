package com.jsmy.pingshan.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.DiscussInfoAdapter;
import com.jsmy.pingshan.bean.DiscussUserBean;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class DiscussInfoActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.m_RecyclerView)
    RefreshRecyclerView mRecyclerView;
    private Handler handler;
    private DiscussInfoAdapter adapter;
    private List<DiscussUserBean.DataBean.ListBean> listUser;
    private String discussID;
    private List<PersonBean.DataBean.ListBean> listBeen;

    private boolean isQuit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_discuss_info;
    }

    @Override
    protected void initView() {
        tvName.setText(getIntent().getStringExtra("discussName"));
        discussID = getIntent().getStringExtra("discussID");
        listUser = new ArrayList<>();
        handler = new Handler();
        adapter = new DiscussInfoAdapter(this);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        NetWork.discussPersonList(discussID, this);
        NetWork.getUserList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.DISCUSS_USER_LIST:
                if ("Y".equals(code)) {
                    listUser.clear();
                    listUser.addAll(gson.fromJson(result, DiscussUserBean.class).getData().getList());
                    listUser.add(new DiscussUserBean.DataBean.ListBean());
                    pushData();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.DISCUSS_DELETE:
                if ("Y".equals(code)) {
                    if (!isQuit) {
                        NetWork.discussPersonList(discussID, this);
                    } else {
                        finish();
                    }
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.DISCUSS_ADD:
                if ("Y".equals(code)) {
                    NetWork.discussPersonList(discussID, this);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_USER_LIST:
                if ("Y".equals(code)) {
                    listBeen = gson.fromJson(result, PersonBean.class).getData().getList();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_check:
                showQuitDiscuss();
                break;
        }
    }

    public void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(listUser);
            }
        }, 100);
    }

    //添加用户
    public void addDiscussUser() {
        RongIM.getInstance().addMemberToDiscussion(discussID, list, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                String userId = "";
                for (String user : list) {
                    boolean isInput = false;
                    for (DiscussUserBean.DataBean.ListBean bean : listUser) {
                        if (user.equals(bean.getUserId())) {
                            isInput = true;
                            break;
                        } else {
                            isInput = false;
                        }
                    }
                    if (!isInput) {
                        userId = userId + user + ",";
                    }
                }
                NetWork.discussAdd(listUser.get(0).getCreateId(),
                        discussID,
                        userId,
                        DiscussInfoActivity.this);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
//                ToastUtil.showShort(DiscussInfoActivity.this, errorCode.getMessage());
            }
        });
    }

    private String[] items;
    private boolean initChoiceSets[];
    private String[] yourChoices;
    private List<String> list;

    public void showAddUserDialog() {
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
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(this);
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
                    addDiscussUser();
                }
            }
        });
        multiChoiceDialog.show();
    }

    //踢出用户
    public void deleteDiscussUser(final String userId) {
        RongIM.getInstance().removeMemberFromDiscussion(discussID, userId, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                NetWork.discussDelete(SharePrefUtil.getString(DiscussInfoActivity.this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()),
                        discussID,
                        userId,
                        DiscussInfoActivity.this);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                ToastUtil.showShort(DiscussInfoActivity.this, errorCode.getMessage());
            }
        });
    }

    public void showDeletUserDialog(final String userId) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DiscussInfoActivity.this);
        normalDialog.setMessage("将该用户踢出讨论组？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        deleteDiscussUser(userId);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    //退出讨论组
    public void quitDiscuss() {
        RongIM.getInstance().quitDiscussion(discussID, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                isQuit = true;
                NetWork.discussDelete(listUser.get(0).getCreateId(),
                        discussID,
                        SharePrefUtil.getString(DiscussInfoActivity.this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()),
                        DiscussInfoActivity.this);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                ToastUtil.showShort(DiscussInfoActivity.this, errorCode.getMessage());
            }
        });
    }

    public void showQuitDiscuss() {
                /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DiscussInfoActivity.this);
        normalDialog.setMessage("确定退出该讨论组？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        quitDiscuss();
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

}
