package com.jsmy.pingshan.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.CheckLineBean;
import com.jsmy.pingshan.db.DBManager;
import com.jsmy.pingshan.db.Image;
import com.jsmy.pingshan.db.Page;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckLineActivity extends BaseActivity {

    @BindView(R.id.map_view)
    MapView mapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();
    private List<LatLng> listLatLng;
    private String id;
    private List<CheckLineBean.DataBean> listLine;

    private DBManager dbManager;
    private List<com.jsmy.pingshan.db.Point> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_check_line;
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra("id");
        initBaiduMap();
        dbManager = new DBManager(this, Constant.DB_NAME, Constant.DB_VERSION, Page.class, com.jsmy.pingshan.db.Point.class, Image.class);
        list = new ArrayList<>();
    }

    @Override
    protected void initData() {
        NetWork.getXcgjList(id + "", this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_XCGJ_LIST:
                if ("Y".equals(code)) {
                    listLine = gson.fromJson(result, CheckLineBean.class).getData();
                    markerPoit();
                } else {
//                    pushPoit(104.054616, 28.707722);
                    findAll();
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    //百度地图

    private void initBaiduMap() {
        mBaiduMap = mapView.getMap();

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        //开启交通图
//        mBaiduMap.setTrafficEnabled(true);

        //开启热力图
//        mBaiduMap.setBaiduHeatMapEnabled(true);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(10.0f);//初始倍数
        mBaiduMap.setMapStatus(msu);

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        initLocation();

        mLocationClient.start();


    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null)
            mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null)
            mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbManager.closeDataBase();
        if (mapView != null)
            mapView.onDestroy();
    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
//        option.setScanSpan(span);
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

            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                MyLog.showLog("location", "BDLocation.TypeGpsLocation");
                //当前为GPS定位结果，可获取以下信息
                location.getSpeed();    //获取当前速度，单位：公里每小时
                location.getSatelliteNumber();    //获取当前卫星数
                location.getAltitude();    //获取海拔高度信息，单位米
                location.getDirection();    //获取方向信息，单位度

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                //当前为网络定位结果，可获取以下信息
                location.getOperators();    //获取运营商信息
                MyLog.showLog("location", "BDLocation.TypeNetWorkLocation");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                //当前为网络定位结果
                MyLog.showLog("location", "BDLocation.TypeOffLineLocation");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                //当前网络定位失败
                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
                MyLog.showLog("location", "BDLocation.TypeServerError");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                //当前网络不通
                MyLog.showLog("location", "BDLocation.TypeNetWorkException");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                //可进一步参考onLocDiagnosticMessage中的错误返回码
                MyLog.showLog("location", "BDLocation.TypeCriteriaException");
            }
        }

        /**
         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
         * 自动回调，相同的diagnosticType只会回调一次
         *
         * @param locType           当前定位类型
         * @param diagnosticType    诊断类型（1~9）
         * @param diagnosticMessage 具体的诊断信息释义
         */
        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {

            if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS");
                //建议打开GPS

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI");
                //建议打开wifi，不必连接，这样有助于提高网络定位精度！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION");
                //定位权限受限，建议提示用户授予APP定位权限！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET");
                //网络异常造成定位失败，建议用户确认网络状态是否异常！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE");
                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI");
                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH");
                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_SERVER_FAIL");
                //百度定位服务端定位失败
                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {
                MyLog.showLog("LocationClient", "LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN");
                //无法获取有效定位依据，但无法确定具体原因
                //建议检查是否有安全软件屏蔽相关定位权限
                //或调用LocationClient.restart()重新启动后重试！

            }
        }

    }

    private void pushOverlay(LatLng latLng) {
        OverlayOptions overlayOptions = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.xunchajiluxiangqing_dizhi));
        Marker marker = (Marker) mBaiduMap.addOverlay(overlayOptions);
        Bundle bundle = new Bundle();
//        bundle.putString("mc", bean.getJcz());
//        bundle.putString("yl", bean.getYl());
//        bundle.putString("wxyl", bean.getWxyl());
//        bundle.putString("jjyl", bean.getJjyl());
        marker.setExtraInfo(bundle);

    }

    private void pushPoit(Double latitude, Double longitude) {
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        mBaiduMap.setMapStatus(u);
    }

    private void markerPoit() {
        mBaiduMap.clear();
        listLatLng = new ArrayList<>();
//        pushPoit(104.054616, 28.707722);
        pushPoit(28.707722, 104.054616);
        if (listLine == null) {
            return;
        }

        for (int i = 0; i < listLine.size(); i++) {
            if ("".equals(listLine.get(i).getLGTD()) || "".equals(listLine.get(i).getLTTD())) {
                continue;
            }
            if ("Y".equals(listLine.get(i).getMark())) {
                listLatLng.add(new LatLng(Double.parseDouble(listLine.get(i).getLTTD()), Double.parseDouble(listLine.get(i).getLGTD())));
                pushOverlay(new LatLng(Double.parseDouble(listLine.get(i).getLTTD()), Double.parseDouble(listLine.get(i).getLGTD())));
            }
        }

        if (listLatLng.size() > 0) {
            pushPoit(listLatLng.get(listLatLng.size() - 1).latitude, listLatLng.get(listLatLng.size() - 1).longitude);
        }

        if (listLatLng.size() >= 2)
            mBaiduMap.addOverlay(new PolylineOptions().width(10).color(0xAAFF0000).points(listLatLng));

    }

    private void markerPoit2() {
        mBaiduMap.clear();
        listLatLng = new ArrayList<>();
        pushPoit(28.707722, 104.054616);
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if ("".equals(list.get(i).getJd()) || "".equals(list.get(i).getWd())) {
                continue;
            }
            listLatLng.add(new LatLng(Double.parseDouble(list.get(i).getWd()), Double.parseDouble(list.get(i).getJd())));
            pushOverlay(new LatLng(Double.parseDouble(list.get(i).getWd()), Double.parseDouble(list.get(i).getJd())));
        }

        if (listLatLng.size() > 0) {
            pushPoit(listLatLng.get(listLatLng.size() - 1).latitude, listLatLng.get(listLatLng.size() - 1).longitude);
        }

        if (listLatLng.size() >= 2)
            mBaiduMap.addOverlay(new PolylineOptions().width(10).color(0xAAFF0000).points(listLatLng));

    }

    public void findAll() {
        list.clear();
        list.addAll(dbManager.findByArgs(com.jsmy.pingshan.db.Point.class, "tid=" + id, null));
        if (list.size() >= 0) {
            markerPoit2();
        }
    }

}
