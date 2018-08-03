package com.jsmy.pingshan.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.ShangBaoImgAdapter;
import com.jsmy.pingshan.adapter.ShangBaoVidoAdapter;
import com.jsmy.pingshan.bean.ImagBean;
import com.jsmy.pingshan.db.DBManager;
import com.jsmy.pingshan.db.Image;
import com.jsmy.pingshan.db.Page;
import com.jsmy.pingshan.db.Point;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.CheckNetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;
import com.liji.imagezoom.util.ImageZoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import vn.tungdx.mediapicker.MediaItem;
import vn.tungdx.mediapicker.MediaOptions;
import vn.tungdx.mediapicker.activities.MediaPickerActivity;

@RuntimePermissions
public class XunChaShangBaoActivity extends BaseActivity {

    @BindView(R.id.tv_dizhi)
    EditText tvDizhi;
    @BindView(R.id.tv_data)
    EditText tvData;
    @BindView(R.id.img_recycler)
    RefreshRecyclerView imgRecycler;
    @BindView(R.id.vido_recycler)
    RefreshRecyclerView vidoRecycler;
    private Handler handler;
    private List<String> listImg;
    private List<String> listVido;
    private ShangBaoImgAdapter imgAdapter;
    private ShangBaoVidoAdapter vidoAdapter;
    private String latitude;
    private String longitude;
    private DBManager dbManager;
    private long tid;
    private Point point;
    private List<ImagBean.DataBean.ListBean> listImag;

    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_shang_bao;
    }

    @Override
    protected void initView() {
        XunChaShangBaoActivityPermissionsDispatcher.permisNeedWithPermissionCheck(this);
        tid = Long.parseLong(getIntent().getStringExtra("tid"));
        dbManager = new DBManager(this, Constant.DB_NAME, Constant.DB_VERSION, Page.class, Point.class, Image.class);
        handler = new Handler();
        initImaRecycler();
        initVidoRecycler();
        initLocation();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDataBase();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.SCXL_TPSP:
                if ("Y".equals(code)) {
                    JSONObject jsonObject = new JSONObject(result);
                    String acquId = jsonObject.optString("acquId");
                    NetWork.getXLtpspList(acquId, XunChaShangBaoActivity.this);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_XL_TPSP_LIST:
                if ("Y".equals(code)) {
                    listImag = gson.fromJson(result, ImagBean.class).getData().getList();
                    insertDB(true);
                } else {
                    ToastUtil.showShort(this, msg);
                }

                break;
            case API.INSERT_XCSB_LIST3:
                if ("Y".equals(code)) {
                    updataPoint();
                    NetWork.insertXcgj(tid + "", longitude + "", latitude + "", "Y", this);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.INSERT_XCGJ:
                if ("Y".equals(code)) {
                    closeWaitingDialog();
                    ToastUtil.showShort(this, "添加成功");
                    finish();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {
        switch (type) {
            case API.SCXL_TPSP:
                closeWaitingDialog();
                ToastUtil.showShort(this, "上报失败！请重试！" + arg1);
                break;
            case API.UPDATE_XCSB:
                closeWaitingDialog();
                ToastUtil.showShort(this, "上报失败！请重试！" + arg1);
                break;
        }
    }

    @OnClick({R.id.img_back, R.id.img_dizhi, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_dizhi:
                startActivityForResult(new Intent(this, GetLocationActivity.class), REQUEST_DATA);
                break;
            case R.id.tv_check:
                updateFiles();
                break;
        }
    }

    private void initImaRecycler() {
        imgAdapter = new ShangBaoImgAdapter(this);
        imgRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        imgRecycler.setLayoutManager(layoutManager);
        imgRecycler.setAdapter(imgAdapter);
        listImg = new ArrayList<>();
        listImg.add("");
        pushImgData();
    }

    private void pushImgData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAdapter.clear();
                imgAdapter.addAll(listImg);
            }
        }, 100);
    }

    public void removeImg(int position) {
        imgAdapter.remove(position);
        listImg.remove(position);
    }

    private void initVidoRecycler() {
        vidoAdapter = new ShangBaoVidoAdapter(this);
        vidoRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vidoRecycler.setLayoutManager(layoutManager);
        vidoRecycler.setAdapter(vidoAdapter);
        listVido = new ArrayList<>();
        listVido.add("");
        pushVidoData();
    }

    private void pushVidoData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vidoAdapter.clear();
                vidoAdapter.addAll(listVido);
            }
        }, 100);
    }

    public void removeVido(int position) {
        vidoAdapter.remove(position);
        listVido.remove(position);
    }

    private void updateFiles() {

        if (TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude)) {
            ToastUtil.showShort(this, "请获取经纬度");
            return;
        }


        if (listImg.size() <= 1 && listVido.size() <= 1) {
            ToastUtil.showShort(this, "请选择图片或者视频");
            return;
        }

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (int i = 0; i < listVido.size() - 1; i++) {
            File file = new File(listVido.get(i));
            builder.addFormDataPart("video", file.getName(), RequestBody.create(MediaType.parse("video/*"), file));
        }
        //图片
        for (int i = 0; i < listImg.size() - 1; i++) {
            File file = new File(listImg.get(i).substring(7));
            builder.addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        RequestBody body = builder.build();
        if (CheckNetWork.getNetWorkType(this) != CheckNetWork.NETWORKTYPE_INVALID) {
            NetWork.updataTPSP(body, this);
            showWaitingDialog();
        } else {
            insertDB(false);
        }
    }

    private String beJSONPoint() {
        String json = "";
        try {
            JSONObject objectP = new JSONObject();
            JSONArray arrayImag = new JSONArray();
            objectP.put("tid", point.getTid());
            objectP.put("dz", point.getDz());
            objectP.put("jd", point.getJd());
            objectP.put("wd", point.getWd());
            objectP.put("ms", point.getMs());
            List<Image> images = dbManager.findByArgs(Image.class, "did=" + point.getId(), null);
            for (Image image : images) {
                JSONObject objectI = new JSONObject();
                objectI.put("url", image.getUrl());
                objectI.put("type", image.getType());
                arrayImag.put(objectI);
                objectI = null;
            }
            objectP.put("imag", String.valueOf(arrayImag));
            json = objectP.toString();
            json = json.replace("\\", "");
            json = json.replace("\"[", "[");
            json = json.replace("]\"", "]");
            MyLog.showLog("JJJ", json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void insertDB(boolean isUp) {
        if (isUp) {
            if (insert() != -1) {
                findRecordLast();
                for (int i = 0; i < listImag.size(); i++) {
                    Image image = new Image(point.getId(), listImag.get(i).getUrl(), listImag.get(i).getWordType());
                    dbManager.insert(image);
                }
                ToastUtil.showShort(this, "添加成功");
                NetWork.insertxcsblist3(beJSONPoint(), this);
//                closeWaitingDialog();
//                ToastUtil.showShort(this, "添加成功");
//                finish();
            } else {
                ToastUtil.showShort(this, "添加失败");
            }
        } else {
            if (insert() != -1) {
                findRecordLast();
                for (int i = 0; i < listImg.size() - 1; i++) {
                    Image image = new Image(point.getId(), listImg.get(i), "2");
                    dbManager.insert(image);
                }
                for (int i = 0; i < listVido.size() - 1; i++) {
                    Image image = new Image(point.getId(), listVido.get(i), "1");
                    dbManager.insert(image);
                }
                closeWaitingDialog();
                ToastUtil.showShort(this, "添加成功");
                finish();
            } else {
                ToastUtil.showShort(this, "添加失败");
            }
        }

    }

    public long insert() {
        String dz = tvDizhi.getText().toString().trim();
        String jd = latitude;
        String wd = longitude;
        String ms = tvData.getText().toString().trim();
        Point point = new Point(tid, dz, jd, wd, ms, "N");
        return dbManager.insert(point);
    }

    public void updataPoint() {
        ContentValues values = new ContentValues();
        values.put("issb", "Y");
        dbManager.updateById(Point.class, values, point.getId());
    }

    public void findRecordLast() {
        List<Point> points = dbManager.findAll(Point.class);
        if (points.size() > 0) {
            point = points.get(points.size() - 1);
        }
    }

    private ProgressDialog waitingDialog;

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        if (waitingDialog == null) {
            waitingDialog = new ProgressDialog(XunChaShangBaoActivity.this);
            waitingDialog.setTitle("正在保存本次巡查信息");
            waitingDialog.setMessage("保存中...");
            waitingDialog.setIndeterminate(true);
            waitingDialog.setCancelable(false);
            waitingDialog.show();
        } else if (waitingDialog != null && !waitingDialog.isShowing()) {
            waitingDialog.show();
        }
    }

    private void closeWaitingDialog() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
        }
    }

    private static final int REQUEST_DATA = 101;
    private static final int REQUEST_IMG = 1001;
    private static final int REQUEST_VIDEO = 1002;

    public void takeVideoList() {
        MediaOptions.Builder builder = new MediaOptions.Builder();
        MediaOptions options = builder.selectVideo().canSelectMultiVideo(true).build();
        MediaPickerActivity.open(this, REQUEST_VIDEO, options);
    }

    public void takePictureList() {
        MediaOptions.Builder builder = new MediaOptions.Builder();
        MediaOptions options = builder.selectPhoto().canSelectMultiPhoto(true).build();
        MediaPickerActivity.open(this, REQUEST_IMG, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLog.showLog("AAA", "requestCode -- " + requestCode);
        switch (requestCode) {
            case REQUEST_IMG:
                if (resultCode == RESULT_OK) {
                    listImg.remove("");
                    List<MediaItem> mediaSelectedList = MediaPickerActivity.getMediaItemSelected(data);
                    for (int i = 0; i < mediaSelectedList.size(); i++) {
                        String furl = "file://" + mediaSelectedList.get(i).getPathOrigin(this);
                        if (!listImg.contains(furl)) {
                            listImg.add(furl);
                        }
                    }
                    listImg.add("");
                    pushImgData();
                } else {
                    ToastUtil.showShort(this, "未成功获取图片路径，请重新尝试");
                }
                break;
            case REQUEST_VIDEO:
                if (resultCode == RESULT_OK) {
                    listVido.remove("");
                    List<MediaItem> mediaSelectedList = MediaPickerActivity.getMediaItemSelected(data);
                    for (int i = 0; i < mediaSelectedList.size(); i++) {
                        String furl = mediaSelectedList.get(i).getPathOrigin(this);
                        if (!listVido.contains(furl)) {
                            listVido.add(furl);
                        }
                    }
                    listVido.add("");
                    pushVidoData();
                } else {
                    ToastUtil.showShort(this, "未成功获取视频路径，请重新尝试");
                }
                break;
            case REQUEST_DATA:
                if (REQUEST_DATA == resultCode) {
                    latitude = data.getStringExtra("latitude");
                    longitude = data.getStringExtra("longitude");
//                    tvDizhi.setText(latitude + "," + longitude);
                }
                break;
        }
    }

    //申请权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        XunChaShangBaoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void permisNeed() {
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void permisWhy(final PermissionRequest request) {
        showRationaleDialog("上传图片视频需要取得相机权限", request);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void permisDenied() {
        AskForPermission();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void permisNever() {
        AskForPermission();
    }

    /**
     * 告知用户具体需要权限的原因
     *
     * @param messageResId
     * @param request
     */
    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();//请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    /**
     * 被拒绝并且不再提醒,提示用户去设置界面重新打开权限
     */
    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("上传图片视频需要相机权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    public void showImage(String url) {
        List<String> list = new ArrayList<>();
        list.addAll(listImg);
        list.remove("");
        ImageZoom.show(this, url, list);
    }

    public void showVideo(String url) {
        Intent intent = new Intent(this, PlayVideoActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

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

        mLocationClient.start();
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            MyLog.showLog("SSS", "onReceiveLocation()");
            latitude = location.getLatitude() + "";
            longitude = location.getLongitude() + "";

            mLocationClient.stop();
        }

        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
            MyLog.showLog("SSS", "onLocDiagnosticMessage()");
            Log.e("SSS", "onLocDiagnosticMessage()");

        }

    }

    private String getRunnable() {
        return String.format("%04d", (int) (Math.random() * 10000));
    }

}
