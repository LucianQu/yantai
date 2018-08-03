package com.jsmy.pingshan;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.hikvision.netsdk.HCNetSDK;
import com.jsmy.pingshan.bean.LoginBean;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.util.MyLog;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017/10/11.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication myApplication;
    public LoginBean.DataBean bean;
    public List<PersonBean.DataBean.ListBean> listUser;
    public String video;
    //是不是市级 1为市级 0 为乡镇级  默认为1
    public static  int isCity=1;

    @Override
    public void onCreate() {
        super.onCreate();
        initBaseWork();
//        int pid = android.os.Process.myPid();
//        MyLog.showLog("xbg", " - " + pid);
//        String processNameString = "";
//        ActivityManager mActivityManager = (ActivityManager) this.getSystemService(getApplicationContext().ACTIVITY_SERVICE);
//        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
//            if (appProcess.pid == pid) {
//                processNameString = appProcess.processName;
//            }
//        }
//        if ("com.jsmy.pingshan".equals(processNameString)) {
//            initBaseWork();
//            MyLog.showLog("xbg", "processName = " + processNameString + "-----work");
//        } else {
//            MyLog.showLog("xbg", "processName = " + processNameString + "-----work");
//        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }

    private void initBaseWork() {
        myApplication = this;
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        //百度
        SDKInitializer.initialize(getApplicationContext());
        //融云
        RongIM.init(this);
        //友盟
        MobclickAgent.enableEncrypt(true);
        MobclickAgent.openActivityDurationTrack(false);
        //极光
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        //海康
//        initeSdk();
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        RongIM.getInstance().disconnect();
        JMessageClient.logout();
//        Cleanup();
    }

    private boolean initeSdk() {
        // init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init()) {
            Log.e("DemoActivity", "HCNetSDK init is failed!");
            return false;
        }
        HCNetSDK.getInstance().NET_DVR_SetLogToFile(3, "/mnt/sdcard/sdklog/",
                true);
        return true;
    }

    public void Cleanup() {
        // release net SDK resource
        HCNetSDK.getInstance().NET_DVR_Cleanup();
    }

}
