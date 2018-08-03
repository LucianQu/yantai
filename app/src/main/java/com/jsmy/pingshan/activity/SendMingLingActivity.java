package com.jsmy.pingshan.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.SendMingLingAdapter;
import com.jsmy.pingshan.bean.PersonBean;
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
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;

public class SendMingLingActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.edit_data)
    EditText editData;
    @BindView(R.id.m_RecyclerView)
    RefreshRecyclerView mRecyclerView;
    private Handler handler;
    private List<PersonBean.DataBean.ListBean> list;
    private SendMingLingAdapter adapter;
    private List<PersonBean.DataBean.ListBean> listBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_send_ming_ling;
    }

    @Override
    protected void initView() {
        tvName.setText(MyApplication.getMyApplication().bean.getXm());
        list = new ArrayList<>();
        handler = new Handler();
        adapter = new SendMingLingAdapter(this);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        NetWork.getUserList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_USER_LIST:
                if ("Y".equals(code)) {
                    listBeen = gson.fromJson(result, PersonBean.class).getData().getList();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.UPDATE_XCML_FS:
                if ("Y".equals(code)) {
                    ToastUtil.showShort(this, "发送成功！！！");
                    closeWaitingDialog();
                    setBackData("Y");
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {
        if (type.equals(API.UPDATE_XCML_FS)){
            closeWaitingDialog();
        }
    }

    @OnClick({R.id.img_back, R.id.tv_send, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                setBackData("N");
                break;
            case R.id.tv_send:
                sendMingLing();
                break;
            case R.id.tv_add:
                showMultiChoiceDialog();
                break;
        }
    }

    private void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(list);
            }
        }, 100);
    }

    public void removePerson(int position) {
        adapter.remove(position);
        list.remove(position);
    }

    private String[] items;
    private boolean initChoiceSets[];
    private String[] yourChoices;

    public void showMultiChoiceDialog() {
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
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(SendMingLingActivity.this);
        multiChoiceDialog.setTitle("请选择要接收的人");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                MyLog.showLog("DDD", "点击了第 - " + which + " - 项");
                if (isChecked) {
//                    yourChoices.add(which, listBeen.get(which).getUserName());
                    yourChoices[which] = listBeen.get(which).getUserName();
                } else {
//                    yourChoices.add(which, "aaa");
                    yourChoices[which] = "aaa";
                }
            }

        });
        multiChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.clear();
                for (int i = 0; i < yourChoices.length; i++) {
                    if (!yourChoices[i].equals("aaa")) {
                        list.add(listBeen.get(i));
                    }
                }
                if (list.size() > 0) {

                    pushData();
                }
            }
        });
        multiChoiceDialog.show();
    }

    private void sendMingLing() {
        String data = editData.getText().toString().trim();
        if (data == null || "".equals(data)) {
            ToastUtil.showShort(this, "请输入内容！！！");
            return;
        }
        if (list.size() == 0) {
            ToastUtil.showShort(this, "请选择接收人！！！");
            return;
        }
        String userId = "";
        for (int i = 0; i < list.size(); i++) {
            userId = userId + list.get(i).getUserid() + ",";
        }

        NetWork.updateXCMLfs(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()),
                SharePrefUtil.getString(this, Constant.SHARE_USERNAME, MyApplication.getMyApplication().bean.getZh()),
                MyApplication.getMyApplication().bean.getXm(),
                data,
                userId,
                this);
        showWaitingDialog();
    }

    private void setBackData(String code) {
        if ("Y".equals(code)) {
            setResult(101);
        } else {
            setResult(100);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        setBackData("N");
        super.onBackPressed();
    }

    private ProgressDialog waitingDialog;

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        waitingDialog = new ProgressDialog(SendMingLingActivity.this);
        waitingDialog.setTitle("发送巡查命令");
        waitingDialog.setMessage("发送中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    private void closeWaitingDialog() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
        }
    }
}
