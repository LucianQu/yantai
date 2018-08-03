package com.jsmy.pingshan.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.util.CheckNetWork;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GetLocationActivity extends BaseActivity implements LocationListener {
    public static final String TAG = "GetLocationActivity";
    private static final long MIN_TIME = 1000l;
    private static final float MIN_DISTANCE = 10f;

    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.tv_jwd)
    TextView tvJwd;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();

    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_get_location;
    }

    @Override
    protected void initView() {
        if (CheckNetWork.getNetWorkType(this) == CheckNetWork.NETWORKTYPE_INVALID) {
            initOffLineLocation();
        } else {
            initBaiduMap();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvJwd.setText(latitude + "，" + longitude);
            }
        }
    };


    @OnClick({R.id.img_back, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_check:
                setBackData();
                break;
        }
    }

    //离线地图
    private void initOffLineLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null || locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
                MyLog.showLog(TAG, "正在定位");
                try {
                    List<String> providerList = locationManager.getProviders(true);
                    String addressLine;
                    if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        latitude = location.getLatitude() + "";
                        longitude = location.getLongitude() + "";
                        handler.sendEmptyMessage(0);
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                    } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        latitude = location.getLatitude() + "";
                        longitude = location.getLongitude() + "";
                        handler.sendEmptyMessage(0);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(getApplicationContext(), "无法定位，请打开GPS服务后重试！");
                    }
                });
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude() + "";
        longitude = location.getLongitude() + "";
        handler.sendEmptyMessage(0);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    //百度地图

    private void initBaiduMap() {
        mBaiduMap = mapView.getMap();

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(19.0f);//初始倍数
        mBaiduMap.setMapStatus(msu);

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        initLocation();

        mLocationClient.start();

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MyLog.showLog("TTT", "GetLocation" + "latitude- " + latitude + " - longitude- " + longitude);
                latitude = latLng.latitude + "";
                longitude = latLng.longitude + "";
                handler.sendEmptyMessage(0);
                MyLocationData locData = new MyLocationData.Builder()
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(0).latitude(latLng.latitude)
                        .longitude(latLng.longitude).build();
                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);
                // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.xunchajiluxiangqing_dizhi);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfiguration(config);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MyLog.showLog("TTT", "GetLocation" + "latitude- " + latitude + " - longitude- " + longitude);
                latitude = latLng.latitude + "";
                longitude = latLng.longitude + "";
                handler.sendEmptyMessage(0);
                MyLocationData locData = new MyLocationData.Builder()
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(0).latitude(latLng.latitude)
                        .longitude(latLng.longitude).build();
                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);
                // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.xunchajiluxiangqing_dizhi);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfiguration(config);
            }
        });

        mBaiduMap.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            @Override
            public void onMapDoubleClick(LatLng latLng) {
                MyLog.showLog("TTT", "GetLocation" + "latitude- " + latitude + " - longitude- " + longitude);
                latitude = latLng.latitude + "";
                longitude = latLng.longitude + "";
                handler.sendEmptyMessage(0);
                MyLocationData locData = new MyLocationData.Builder()
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(0).latitude(latLng.latitude)
                        .longitude(latLng.longitude).build();
                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);
                // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.xunchajiluxiangqing_dizhi);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfiguration(config);
            }
        });
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
        if (mapView != null)
            mapView.onDestroy();
    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 0;
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
            MyLog.showLog("BBB", "latitude- " + latitude + " - longitude- " + longitude);
            latitude = location.getLatitude() + "";
            longitude = location.getLongitude() + "";
            handler.sendEmptyMessage(0);
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.xunchajiluxiangqing_dizhi);
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
            mBaiduMap.setMyLocationConfiguration(config);

            mLocationClient.stop();

            //获取定位结果
            location.getTime();    //获取定位时间
            MyLog.showLog("BBB", "获取定位时间- " + location.getTime());
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            MyLog.showLog("BBB", "获取定位唯一ID- " + location.getLocationID());
            location.getLocType();    //获取定位类型
            MyLog.showLog("BBB", "获取定位类型- " + location.getLocType());
            location.getLatitude();    //获取纬度信息
            MyLog.showLog("BBB", "获取纬度信息- " + " - latitude-" + +location.getLatitude());
            location.getLongitude();    //获取经度信息
            MyLog.showLog("BBB", "获取经度信息- " + "" + " - longitude- " + location.getLongitude());
            location.getRadius();    //获取定位精准度
            MyLog.showLog("BBB", "获取定位精准度- " + location.getRadius());
            location.getAddrStr();    //获取地址信息
            MyLog.showLog("BBB", "获取地址信息- " + location.getAddrStr());
            location.getCountry();    //获取国家信息
            MyLog.showLog("BBB", "获取国家信息- " + location.getCountry());
            location.getCountryCode();    //获取国家码
            MyLog.showLog("BBB", "获取国家码- " + location.getCountryCode());
            location.getCity();    //获取城市信息
            MyLog.showLog("BBB", "获取城市信息- " + location.getCity());
            location.getCityCode();    //获取城市码
            MyLog.showLog("BBB", "获取城市码- " + location.getCityCode());
            location.getDistrict();    //获取区县信息
            MyLog.showLog("BBB", "获取区县信息- " + location.getDistrict());
            location.getStreet();    //获取街道信息
            MyLog.showLog("BBB", "获取街道信息- " + location.getStreet());
            location.getStreetNumber();    //获取街道码
            MyLog.showLog("BBB", "获取街道码- " + location.getStreetNumber());
            location.getLocationDescribe();    //获取当前位置描述信息
            MyLog.showLog("BBB", "当前位置描述信息- " + location.getLocationDescribe());
            location.getPoiList();    //获取当前位置周边POI信息
            MyLog.showLog("BBB", "周边POI信息- " + location.getPoiList().toString());
            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            MyLog.showLog("BBB", "楼宇ID- " + location.getBuildingID());
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            MyLog.showLog("BBB", "楼宇名称- " + location.getBuildingName());
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息
            MyLog.showLog("BBB", "楼层信息- " + location.getFloor());

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

    private void setBackData() {
        Intent intent = new Intent();
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        setResult(101, intent);
        finish();
    }


}
