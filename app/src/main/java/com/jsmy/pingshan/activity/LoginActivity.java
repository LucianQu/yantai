package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.LoginBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.CheckNetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.img_ispw)
    ImageView imgIspw;
    @BindView(R.id.ed_login)
    EditText edLogin;
    @BindView(R.id.ed_password)
    EditText edPassword;

    private String username = "";
    private String password = "";
    private String json = "";
    private boolean isPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        json = SharePrefUtil.getString(this, Constant.SHARE_JSON, "");
        if (!"".equals(json)) {
            MyApplication.getMyApplication().bean = gson.fromJson(json, LoginBean.class).getData();
        }
        username = SharePrefUtil.getString(this, Constant.SHARE_USERNAME, "");
        password = SharePrefUtil.getString(this, Constant.SHARE_PASSWORD, "");
        isPasword = SharePrefUtil.getBoolean(this, Constant.SHARE_ISPASSWORD, false);
        // 判断是否记住密码
        if (isPasword) {
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                edLogin.setText("" + username);
                edPassword.setText("" + password);
            }
            imgIspw.setImageResource(R.mipmap.chanpin_fuxaun_yixuan);
        } else {
            imgIspw.setImageResource(R.mipmap.chanpin_fuxaun_weixuan);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.LOG_IN:
                if ("1".equals(code)) {
                    SharePrefUtil.saveString(this, Constant.SHARE_JSON, result);
                    MyApplication.getMyApplication().bean = gson.fromJson(result, LoginBean.class).getData();
                    SharePrefUtil.saveString(this, Constant.SHARE_USERNAME, username);
                    SharePrefUtil.saveString(this, Constant.SHARE_PASSWORD, password);
                    SharePrefUtil.saveString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId());
                    SharePrefUtil.saveString(this, Constant.SHARE_TOKEN, MyApplication.getMyApplication().bean.getToken());
                    loginJMessage("10000" + MyApplication.getMyApplication().bean.getId());
                    ToastUtil.showShort(this, "登录成功！");
                    connect(SharePrefUtil.getString(this, Constant.SHARE_TOKEN, MyApplication.getMyApplication().bean.getToken()));
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_ispw, R.id.bt_login, R.id.tv_net})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_ispw: //shiji记住密码
                if (!isPasword) {
                    isPasword = true;
                    imgIspw.setImageResource(R.mipmap.chanpin_fuxaun_yixuan);
                } else {
                    isPasword = false;
                    imgIspw.setImageResource(R.mipmap.chanpin_fuxaun_weixuan);
                }
                SharePrefUtil.saveBoolean(this, Constant.SHARE_ISPASSWORD, isPasword);
                break;
            case R.id.bt_login:
                if (CheckNetWork.getNetWorkType(this) != CheckNetWork.NETWORKTYPE_INVALID) {
                    checkLogin();
                } else if (!"".equals(SharePrefUtil.getString(this, Constant.SHARE_JSON, ""))) {
                    ToastUtil.showShort(this, "当前为无网络状态下操作！！！");
                    connect(SharePrefUtil.getString(this, Constant.SHARE_TOKEN, MyApplication.getMyApplication().bean.getToken()));
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    ToastUtil.showShort(this, "请检查网络状态后重试");
                }
                break;
            case R.id.tv_net:
                this.startActivity(new Intent(this, RiZhiChaXunActivity.class));
                break;
            default:
                break;
        }
    }

    private void checkLogin() {
        username = edLogin.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.showShort(this, "请输入账号");
            return;
        }
        password = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(this, "请输入密码");
            return;
        }
        NetWork.login(username, password,"123456","" ,this);
    }


    private void connect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                MyLog.showLog("LoginActivity", "--onError--" + "onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                MyLog.showLog("LoginActivity", "--onSuccess-- " + userid);
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("userid", userid);
//                startActivity(intent);
//                finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyLog.showLog("LoginActivity", "--onError" + errorCode.getValue() + " - " + errorCode.getMessage());
            }
        });

    }
}
