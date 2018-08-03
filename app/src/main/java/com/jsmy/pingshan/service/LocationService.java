package com.jsmy.pingshan.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jsmy.pingshan.db.DBManager;
import com.jsmy.pingshan.db.Image;
import com.jsmy.pingshan.db.Page;
import com.jsmy.pingshan.db.Point;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class LocationService extends Service{

    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();
    public double latitude;
    public double longitude;

    private long timetick;

    public Timer timer;

    @Override
    public void onCreate() {
        MyLog.showLog("SSS", "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            timetick = Long.parseLong(intent.getStringExtra("time"));
        }catch (Exception e){
            timetick = 30;
        }

        initBaiduMap();
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new Work(), 0, timetick * 1000);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //百度地图
    private void initBaiduMap() {

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        initLocation();

        mLocationClient.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            MyLog.showLog("SSS", "onReceiveLocation()");
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            findRecordLast();
            if (page != null && !"Y".equals(page.getIssb())) {
                NetWork.insertXcgj(page.getFid() + "", longitude + "", latitude + "", "N", null);
            }
            mLocationClient.stop();
        }

        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
            MyLog.showLog("SSS", "onLocDiagnosticMessage()");
            Log.e("SSS", "onLocDiagnosticMessage()");

        }

    }

    private Page page;

    public void findRecordLast() {
        DBManager dbManager = new DBManager(this, Constant.DB_NAME, Constant.DB_VERSION, Page.class, Point.class, Image.class);
        List<Page> pages = dbManager.findAll(Page.class);
        if (pages.size() > 0) {
            page = pages.get(pages.size() - 1);
        }
    }

    class Work extends TimerTask {

        @Override
        public void run() {
            MyLog.showLog("SSS", "Work.run()");
            initBaiduMap();
        }
    }

}
