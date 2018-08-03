package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.gson.Gson;
import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ActivityTack;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.LoginStateChangeEvent;
import cn.jpush.im.api.BasicCallback;
import io.rong.imlib.model.UserInfo;


public abstract class BaseActivity extends FragmentActivity implements NetWork.CallListener {
    public Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContenView() != 0) {
            setContentView(getContenView());
        }
        ButterKnife.bind(this);
        ActivityTack.getInstanse().addActivity(this);
        JMessageClient.registerEventReceiver(this);
        gson = new Gson();
        initView();
        initData();
        MyLog.showLog("TTT", getClass().getName());
    }

    public void onEventMainThread(LoginStateChangeEvent event) {
        LoginStateChangeEvent.Reason reason = event.getReason();//获取变更的原因
        cn.jpush.im.android.api.model.UserInfo myInfo = event.getMyInfo();//获取当前被登出账号的信息
        MyLog.showLog("vvv", "" + reason + "  " + myInfo);
        switch (reason) {
            case user_password_change:
                //用户密码在服务器端被修改
                break;
            case user_logout:
                //用户换设备登录
                showNormalDialog(this);
                break;
            case user_deleted:
                //用户被删除
                break;
        }
    }

    private void showNormalDialog(final Context context) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
        normalDialog.setTitle("下线提示:");
        normalDialog.setMessage("您的账号已经在另一台设备上登录，您被迫下线!");
        normalDialog.setCancelable(false);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                        startActivity(intent);
                        SharePrefUtil.clear(context);
                        ActivityTack.getInstanse().removeAllActivity();
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected abstract int getContenView();

    protected abstract void initView();

    protected abstract void initData();

    private long lastClickTime;// 上次点击时间点

    /**
     * 判断事件出发时间间隔是否超过预定值
     *
     * @return
     * @Description
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 3000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防止连续点击
     *
     * @Description
     */
    public void startActivity(Intent intent) {
        if (isFastDoubleClick()) {
            MyLog.showLog("MMM", "startActivity() 重复调用");
            return;
        }
        super.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 删除集合存储记录
        ActivityTack.getInstanse().removeActivity(this);
        JMessageClient.unRegisterEventReceiver(this);
    }

    public Uri getUriForFile(File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(getApplicationContext(), "com.jsmy.pingshan.FileProvider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    private String img = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1328742420,2854750637&fm=27&gp=0.jpg";

    public UserInfo finUserById(String userId) {
        for (PersonBean.DataBean.ListBean bean : MyApplication.getMyApplication().listUser) {
            if (userId.equals(bean.getUserid())) {
                return new UserInfo(userId, bean.getUserName(), Uri.parse(img));
            }
        }
        return new UserInfo(userId, "网友", Uri.parse(img));
    }

    public void loginJMessage(final String yhid) {
        //极光登录
        JMessageClient.login(yhid, "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    MyLog.showLog("vvv", "登录成功");
                    //设置别名
                    setAliasByYhid(yhid);
                } else if (i == 800005) {
                    MyLog.showLog("vvv", "用户ID未注册（appkey无该UID）");
                } else if (i == 800006) {
                    MyLog.showLog("vvv", "用户ID不存在（数据库中无该UID）");
                } else if (i == 800012) {
                    MyLog.showLog("vvv", "发起的用户处于登出状态，账号注册以后从未登录过，需要先登录");
                } else if (i == 800013) {
                    MyLog.showLog("vvv", "发起的用户处于登出状态，请求的用户已经登出，需要先登录");
                } else if (i == 800014) {
                    MyLog.showLog("vvv", "发起的用户appkey与目标不匹配");
                } else if (i == 801003) {
                    MyLog.showLog("vvv", "登录的用户名未注册，登录失败");
                    registerJMessage(yhid);
                } else if (i == 801004) {
                    MyLog.showLog("vvv", "登录的用户密码错误，登录失败");
                } else if (i == 801005) {
                    MyLog.showLog("vvv", "登录的用户设备有误，登录失败");
                } else if (i == 802002) {
                    MyLog.showLog("vvv", "登出用户名和登录用户名不匹配，登出失败");
                } else if (i == 871201) {
                    MyLog.showLog("vvv", "登录失败，响应超时！");
                    loginJMessage(yhid);
                } else {
                    MyLog.showLog("vvv", "错误码 " + i + " 登录失败，请联系管理员！");
                }
            }
        });
    }

    public void registerJMessage(final String yhid) {
        //极光注册
        JMessageClient.register(yhid, "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    MyLog.showLog("vvv", "注册成功！");
                    loginJMessage(yhid);
                } else if (i == 871504) {
                    MyLog.showLog("vvv", "Push 注册未完成，请稍后重试。如果持续出现这个问题，可能你的 JPush 配置不正确。");
                    registerJMessage(yhid);
                } else if (i == 871505) {
                    MyLog.showLog("vvv", "Push 注册失败,对应包名在控制台上不存在。");
                } else if (i == 871506) {
                    MyLog.showLog("vvv", "Push 注册失败，设备IMEI不合法");
                } else {
                    MyLog.showLog("vvv", "错误码 " + i + " 注册失败，请联系管理员！");
                }
            }
        });
    }

    public void setAliasByYhid(final String yhid) {
        //这是别名
        JPushInterface.setAlias(this, yhid, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                MyLog.showLog("vvv", " --- " + yhid + " ---" + i);
                switch (i) {
                    case 0:
                        break;
                    case 6002:
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
