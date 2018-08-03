package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class ConversationActivity extends BaseActivity {
    private String mTargetId;
    private String mTitleName;

    private TextView tvTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initDatas();
    }

    private Conversation.ConversationType mConversationType;//会话类型
    private Intent intent;

    private void initDatas() {
        intent = getIntent();
        tvTitle = findViewById(R.id.tv_title);

        mTargetId = intent.getData().getQueryParameter("targetId");
        mTitleName = intent.getData().getQueryParameter("title");
        MyLog.showLog("CCC", mTargetId + " - " + mTitleName);
        tvTitle.setText("与 " + mTitleName + " 会话");

        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
        enterFragment(mConversationType, mTargetId);

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {

                return finUserById(userId);
            }
        }, true);

    }

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation")
                .appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }


}
