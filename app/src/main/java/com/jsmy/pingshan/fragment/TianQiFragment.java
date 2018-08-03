package com.jsmy.pingshan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.QiXiangXinXiActivity;
import com.jsmy.pingshan.adapter.TianQiFragmentAdapter;
import com.jsmy.pingshan.bean.ForecastBean;
import com.jsmy.pingshan.bean.ForecastInfo;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/11/4.
 */

public class TianQiFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_skycon)
    TextView tvSkycon;
    @BindView(R.id.tv_aqi)
    TextView tvAqi;
    @BindView(R.id.m_RecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.img_tianqi)
    ImageView imgTianqi;
    private ForecastBean.ResultBean bean;
    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();
    public List<ForecastInfo> list;
    private TianQiFragmentAdapter adapter;
    private String jd;
    private String wd;
    private String cs;
    private String cq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tianqi, null);
        unbinder = ButterKnife.bind(this, view);
        initLocation();
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.TIANQA_FORECAST:
                if ("ok".equals(code)) {
                    bean = gson.fromJson(result, ForecastBean.class).getResult();
                    pushData();
                    pushList();
                } else {
                    ToastUtil.showShort(getActivity(), "天气信息获取失败！！！");
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    getForecastInfo(jd + "", wd + "");
                    if (null != tvTitle)
                        tvTitle.setText(cs + " " + cq);
                    break;
            }
        }
    };

    private void pushData() {
        if (null == tvTemp || null == tvAqi || null == tvSkycon || null == imgTianqi)
            return;
        tvTemp.setText(bean.getHourly().getTemperature().get(0).getValue() + "°");
        tvAqi.setText("空气质量 " + bean.getHourly().getAqi().get(0).getValue() + "");
        String wind = "";
        double speed = bean.getHourly().getWind().get(0).getSpeed();
        if (speed < 1) {
            wind = "无风";
        } else if (speed < 38) {
            wind = "微风";
        } else if (speed < 74) {
            wind = "大风";
        } else if (speed < 117) {
            wind = "暴风";
        } else {
            wind = "飓风";
        }
        MyLog.showLog("TTT", wind + " - " + bean.getHourly().getSkycon().get(0).getValue());
        switch (bean.getHourly().getSkycon().get(0).getValue()) {
            case Constant.CLEAR_DAY:
                tvSkycon.setText("晴天" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.qing1);
                break;
            case Constant.CLEAR_NIGHT:
                tvSkycon.setText("晴夜" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.ye1);
                break;
            case Constant.PARTLY_CLOUDY_DAY:
                tvSkycon.setText("多云" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.yun2);
                break;
            case Constant.PARTLY_CLOUDY_NIGHT:
                tvSkycon.setText("多云" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.yun2);
                break;
            case Constant.CLOUDY:
                tvSkycon.setText("阴天" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.qing2);
                break;
            case Constant.RAIN:
                tvSkycon.setText("降雨" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.yu3);
                break;
            case Constant.SNOW:
                tvSkycon.setText("降雪" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.xue3);
                break;
            case Constant.WIND:
                tvSkycon.setText("刮风" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.feng1);
                break;
            case Constant.FOG:
                tvSkycon.setText("起雾" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.feng1);
                break;
            case Constant.HAZE:
                tvSkycon.setText("雾霾" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.feng1);
                break;
            case Constant.SLEET:
                tvSkycon.setText("冻雨" + " " + wind);
                imgTianqi.setImageResource(R.mipmap.yu3);
                break;
        }
    }

    private void pushList() {
        list = new ArrayList<>();
        for (int i = 0; i < bean.getDaily().getAstro().size(); i++) {
            String date = bean.getDaily().getAstro().get(i).getDate();
            String skycon = bean.getDaily().getSkycon().get(i).getValue();
            String temp = bean.getDaily().getTemperature().get(i).getMin() + "°-" + " " + bean.getDaily().getTemperature().get(i).getMax() + "°";
            String wind = "";
            double speed = bean.getDaily().getWind().get(i).getAvg().getSpeed();
            if (speed < 1) {
                wind = "无风";
            } else if (speed < 38) {
                wind = "微风";
            } else if (speed < 74) {
                wind = "大风";
            } else if (speed < 117) {
                wind = "暴风";
            } else {
                wind = "飓风";
            }
            list.add(new ForecastInfo(date, skycon, temp, wind));
        }
        initRecycer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRecycer() {
        adapter = new TianQiFragmentAdapter((QiXiangXinXiActivity) getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        setLocation();
        mLocationClient.start();
    }

    private void getForecastInfo(String jd, String wd) {
        String url = jd + "," + wd + API.FORECAST;
        Map<String, String> map = new HashMap<>();
        NetWork.getForecast(url, map, this);
    }

    private void setLocation() {

        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");

        int span = 0;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setScanSpan(span);

        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);

        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);

        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);

        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);

        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);

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
            MyLog.showLog("BBB", "latitude- " + location.getLatitude() + " - longitude- " + location.getLongitude());
            jd = location.getLongitude() + "";
            wd = location.getLatitude() + "";
            cs = location.getCity();
            cq = location.getDistrict();
            handler.sendEmptyMessage(101);
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

}
