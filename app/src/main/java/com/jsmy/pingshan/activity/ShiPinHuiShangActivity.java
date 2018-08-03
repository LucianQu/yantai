package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.ShiPinHuiShangAdapter;
import com.jsmy.pingshan.bean.DiscussUserBean;
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
import io.rong.callkit.RongCallKit;
import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ShiPinHuiShangActivity extends BaseActivity {


    @BindView(R.id.m_RecyclerView)
    RefreshRecyclerView mRecyclerView;
    private Handler handler;
    private ShiPinHuiShangAdapter adapter;
    private List<PersonBean.DataBean.ListBean> listPerson;
    private String[] listId;
    private ArrayList<String> ids;
    private ArrayList<String> listAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_shi_pin_hui_shang;
    }

    @Override
    protected void initView() {
        ids = new ArrayList<>();
        handler = new Handler();
        adapter = new ShiPinHuiShangAdapter(this);
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
                    listPerson = gson.fromJson(result, PersonBean.class).getData().getList();
                    pushData();
                    pushID();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }


    @OnClick({R.id.img_back, R.id.tv_begin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_begin:
                chcekListId();
                break;
        }
    }

    private void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(listPerson);
            }
        }, 100);
    }

    private void pushID() {
        listId = new String[listPerson.size()];
        for (int i = 0; i < listPerson.size(); i++) {
            listId[i] = "aaa";
        }
        listAll = new ArrayList<>();
        for (PersonBean.DataBean.ListBean bean : listPerson) {
            listAll.add(bean.getUserid());
        }
        addDiscussUser();
    }

    public void getListId(int position, String id) {
        listId[position] = id;
    }

    private void chcekListId() {
        ids.clear();
        if (listId == null){
            return;
        }
        for (int i = 0; i < listId.length; i++) {
            if (!listId[i].equals("aaa")) {
                MyLog.showLog("SSS", "视频会商 " + listId[i]);
                ids.add(listId[i]);
            }
        }
        if (ids.size() == 0) {
            ToastUtil.showShort(this, "请选择需要会商的人");
        } else {
            try {
                RongCallKit.startMultiCall(getApplicationContext(), Conversation.ConversationType.DISCUSSION, Constant.DISCUSS_ID, RongCallKit.CallMediaType.CALL_MEDIA_TYPE_VIDEO, ids);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //添加用户
    public void addDiscussUser() {
        RongIM.getInstance().addMemberToDiscussion(Constant.DISCUSS_ID, listAll, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                MyLog.showLog("ShiPinHuiShangActivity", "- - 添加成功 - -");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyLog.showLog("ShiPinHuiShangActivity", errorCode.getMessage());
            }
        });
    }

}
