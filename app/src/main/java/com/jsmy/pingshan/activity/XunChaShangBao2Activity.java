package com.jsmy.pingshan.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.XunChaShangBao2Adapter;
import com.jsmy.pingshan.bean.ImagBean;
import com.jsmy.pingshan.bean.LineBean;
import com.jsmy.pingshan.bean.TimeTickBean;
import com.jsmy.pingshan.bean.XCLXBean;
import com.jsmy.pingshan.db.DBManager;
import com.jsmy.pingshan.db.Image;
import com.jsmy.pingshan.db.Page;
import com.jsmy.pingshan.db.Point;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.service.LocationService;
import com.jsmy.pingshan.util.CheckNetWork;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.RefreshRecyclerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class XunChaShangBao2Activity extends BaseActivity {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_data)
    EditText tvData;
    @BindView(R.id.img_recycler)
    RefreshRecyclerView myRecycler;
    @BindView(R.id.tv_map)
    TextView tvMap;
    @BindView(R.id.tv_enddate)
    TextView tvEnddate;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.my_spinner)
    Spinner mySpinner;
    private Handler handler;
    private List<Point> list;
    private XunChaShangBao2Adapter adapter;
    private DBManager dbManager;
    private Page page;
    private int xcState;
    private List<ImagBean.DataBean.ListBean> listImag;
    private LineBean lineBean;
    private String username;
    private String titleDesc;
    private String beginDt;
    private String endDt;
    private String userId;
    private String inTypeCode;
    private String inTypeName;
    private List<XCLXBean.DataBean> xclxList;

    private String timetick;
    private List<Page> pages;
    private long fid;

    private long poitId;

    private boolean issb = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_xun_cha_shang_bao2;
    }

    @Override
    protected void initView() {
        dbManager = new DBManager(this, Constant.DB_NAME, Constant.DB_VERSION, Page.class, Point.class, Image.class);
        initRecycler();
        findRecordLast();
        if (page != null && !"Y".equals(page.getIssb())) {
            xcState = 2;
            tvMap.setText("巡查路线");
//            username = page.getUser();
            tvName.setText(page.getUser());
//            beginDt = page.getBeginDt();
            tvDate.setText(page.getBeginDt().substring(0, 10));
            tvTime.setText(page.getBeginDt().substring(11));
//            endDt = page.getEndDt();
            if (page.getEndDt().length() > 10) {
                tvEnddate.setText(page.getEndDt().substring(0, 10));
                tvEndtime.setText(page.getEndDt().substring(11));
            }
            inTypeCode = page.getInTypeCode();
            inTypeName = page.getInTypeName();
//            titleDesc = page.getMs();
            if (!page.getMs().equals(""))
                tvData.setText(page.getMs());
            findAll();
        } else {
            xcState = 1;
            tvMap.setText("开始巡查");
            tvName.setText(MyApplication.getMyApplication().bean.getXm());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = format.format(date);
            tvDate.setText(time.substring(0, 10));
            tvTime.setText(time.substring(11));
        }
    }

    @Override
    protected void initData() {
        NetWork.getsjjgList(this);
        NetWork.getXclxList(this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.UPDATA_XCSB_LIST:
                if (issb) {
                    update2();
                    closeWaitingDialog();
                    ToastUtil.showShort(this, "上报成功！！！");
                    stopLocationservice();
                    finish();
                } else {
                    update();
                    ToastUtil.showShort(getApplicationContext(), "保存成功！！！");
                }
                break;
            case API.SCXL_TPSP:
                if ("Y".equals(code)) {
                    MyLog.showLog("XXX", result);
                    JSONObject jsonObject = new JSONObject(result);
                    String acquId = jsonObject.optString("acquId");
                    NetWork.getXLtpspList(acquId, XunChaShangBao2Activity.this);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_XL_TPSP_LIST:
                if ("Y".equals(code)) {
                    listImag = gson.fromJson(result, ImagBean.class).getData().getList();
                    updataImagDB();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.INSERT_XCRY:
                if ("Y".equals(code)) {
                    lineBean = gson.fromJson(result, LineBean.class);
                    fid = Long.parseLong(lineBean.getXcId());
                    ler.sendEmptyMessage(101);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_SJJG_LIST:
                if ("Y".equals(code)) {
                    timetick = gson.fromJson(result, TimeTickBean.class).getData();
                } else {
                    ToastUtil.showShort(this, msg);
                }

                break;
            case API.GET_XCLX_LIST:
                if ("Y".equals(code)) {
                    xclxList = gson.fromJson(result, XCLXBean.class).getData();
                    if (xclxList != null && xclxList.size() > 0) {
                        initSpanner();
                    }
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.DELETE_XCD:
                if ("Y".equals(code)) {
                    delete(poitId);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.DELETE_XCLSGJ:
                if ("Y".equals(code)) {
//                    delete(poitId);
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {
        if (type.equals(API.SCXL_TPSP)) {
            closeWaitingDialog();
        }
    }

    @OnClick({R.id.img_back, R.id.tv_add, R.id.tv_map, R.id.tv_update, R.id.tv_date, R.id.tv_time, R.id.tv_keep, R.id.tv_enddate, R.id.tv_endtime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add:
                if (xcState == 1) {
                    ToastUtil.showShort(this, "请先开始巡查！！！");
                } else {
                    Intent intent = new Intent(this, XunChaShangBaoActivity.class);
                    intent.putExtra("tid", page.getFid() + "");
                    startActivityForResult(intent, 101);
                }
                break;
            case R.id.tv_map:
                if (xcState == 1) {
                    showNormalDialog(0, 3, "确定开始一段巡查?");
                } else {
                    Intent line = new Intent(this, CheckLineActivity.class);
                    line.putExtra("id", page.getFid() + "");
                    startActivity(line);
                }
                break;
            case R.id.tv_update:
                if (xcState == 1) {
                    ToastUtil.showShort(this, "请先开始巡查！！！");
                } else {
                    showNormalDialog(0, 4, "确定提交本次巡查?");
                }
                break;
            case R.id.tv_date:
                getDate();
                break;
            case R.id.tv_time:
                getTime();
                break;
            case R.id.tv_keep:
                if (xcState == 1) {
                    ToastUtil.showShort(this, "请先开始巡查！！！");
                } else {
                    showNormalDialog(0, 2, "确定保存修改内容?");
                }
                break;
            case R.id.tv_enddate:
                getEndDate();
                break;
            case R.id.tv_endtime:
                getEndTime();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDataBase();
    }

    private void initSpanner() {
        List<String> listSpan = new ArrayList<>();
        for (XCLXBean.DataBean bean : xclxList) {
            listSpan.add(bean.getTp());
        }
        mySpinner = (Spinner) findViewById(com.test.demo.R.id.my_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.test.demo.R.layout.spinner_item_shipin, listSpan);
        adapter.setDropDownViewResource(com.test.demo.R.layout.spinner_item_dropdown_shipin);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inTypeCode = xclxList.get(i).getId();
                inTypeName = xclxList.get(i).getTp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (null == inTypeCode || "".equals(inTypeCode) || null == inTypeName || "".equals(inTypeName)) {
            inTypeCode = xclxList.get(0).getId();
            inTypeName = xclxList.get(0).getTp();
        } else {
            for (int i = 0; i < listSpan.size(); i++) {
                if (inTypeName.equals(listSpan.get(i))) {
                    mySpinner.setSelection(i);
                }
            }
        }

    }

    private void initRecycler() {
        list = new ArrayList<>();
        handler = new Handler();
        adapter = new XunChaShangBao2Adapter(this);
        myRecycler.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(adapter);
    }

    private void pushData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(list);
            }
        }, 100);
    }

    private void getDate() {
        final Calendar c = Calendar.getInstance();
        String sdata = tvDate.getText().toString().trim();
        if (null != sdata && !"".equals(sdata)) {
            c.set(Calendar.YEAR, Integer.parseInt(sdata.substring(0, 4)));
            c.set(Calendar.MONTH, Integer.parseInt(sdata.substring(5, 7)) - 1);
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sdata.substring(8)));
        }
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(DateFormat.format("yyyy-MM-dd", c));
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void getTime() {
        final Calendar c = Calendar.getInstance();
        String stime = tvTime.getText().toString().trim();
        if (null != stime && !"".equals(stime)) {
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(stime.substring(0, 2)));
            c.set(Calendar.MINUTE, Integer.parseInt(stime.substring(3, 5)));
            c.set(Calendar.SECOND, Integer.parseInt(stime.substring(6)));
        }
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                c.set(0, 0, 0, hourOfDay, minute, 0);
                tvTime.setText(DateFormat.format("HH:mm:ss", c));
            }
        }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true);
        dialog.show();

    }

    private void getEndDate() {
        final Calendar c = Calendar.getInstance();
        String sdata = tvEnddate.getText().toString().trim();
        if (null != sdata && !"".equals(sdata)) {
            c.set(Calendar.YEAR, Integer.parseInt(sdata.substring(0, 4)));
            c.set(Calendar.MONTH, Integer.parseInt(sdata.substring(5, 7)) - 1);
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sdata.substring(8)));
        }
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                tvEnddate.setText(DateFormat.format("yyyy-MM-dd", c));
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void getEndTime() {
        final Calendar c = Calendar.getInstance();
        String stime = tvEndtime.getText().toString().trim();
        if (null != stime && !"".equals(stime)) {
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(stime.substring(0, 2)));
            c.set(Calendar.MINUTE, Integer.parseInt(stime.substring(3, 5)));
            c.set(Calendar.SECOND, Integer.parseInt(stime.substring(6)));
        }
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                c.set(0, 0, 0, hourOfDay, minute, 0);
                tvEndtime.setText(DateFormat.format("HH:mm:ss", c));
            }
        }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true);
        dialog.show();

    }

    public long insert() {
        Page page = new Page(Long.parseLong(lineBean.getXcId()), username, beginDt, endDt, inTypeCode, inTypeName, titleDesc, "N");
        return dbManager.insert(page);
    }

    public long insert2() {
        Page page = new Page(fid, username, beginDt, endDt, inTypeCode, inTypeName, titleDesc, "N");
        return dbManager.insert(page);
    }

    public void update() {
        ContentValues values = new ContentValues();
        values.put("user", username);
        values.put("beginDt", beginDt);
        values.put("endDt", endDt);
        values.put("inTypeCode", inTypeCode);
        values.put("inTypeName", inTypeName);
        values.put("ms", titleDesc);
        dbManager.updateByFId(Page.class, values, page.getFid());
    }

    public void update2() {
        ContentValues values = new ContentValues();
        values.put("issb", "Y");
        dbManager.updateByFId(Page.class, values, page.getFid());
    }

    public void findAll() {
        list.clear();
        list.addAll(dbManager.findByArgs(Point.class, "tid=" + page.getFid(), null));
        MyLog.showLog("SSS", " - " + list.toString());
        if (list.size() >= 0) {
            pushData();
        }
    }

    public void findRecordLast() {
        pages = dbManager.findAll(Page.class);
        MyLog.showLog("XXX", " - " + pages.size());
        if (pages.size() > 0) {
            page = pages.get(pages.size() - 1);
        }
    }

    public void delete(long id) {
        dbManager.deleteById(Point.class, id);
        findAll();
    }

    private String getJson() {
        getPageInfo();
        String json = "";
        try {
            JSONObject jsonPage = new JSONObject();
            JSONArray arrayPoint = new JSONArray();
            JSONArray arrayImag = new JSONArray();
            if (list != null && list.size() != 0) {
                for (Point point : list) {
                    JSONObject objectP = new JSONObject();
                    List<Image> images = dbManager.findByArgs(Image.class, "did=" + point.getId(), null);
                    for (Image image : images) {
                        JSONObject objectI = new JSONObject();
                        objectI.put("url", image.getUrl());
                        objectI.put("type", image.getType());
                        arrayImag.put(objectI);
                        objectI = null;
                    }
                    objectP.put("dz", point.getDz());
                    objectP.put("jd", point.getJd());
                    objectP.put("wd", point.getWd());
                    objectP.put("ms", point.getMs());
                    objectP.put("issc", point.getIssb());
                    objectP.put("imag", String.valueOf(arrayImag));
                    String str = objectP.toString();
                    str = str.replace("\\", "");
                    str = str.replace("\"[", "[");
                    str = str.replace("]\"", "]");
                    MyLog.showLog("JJJ", str);
                    arrayPoint.put(objectP);
                    objectP = null;
                }
            }
            jsonPage.put("fid", page.getFid());
            jsonPage.put("userNm", SharePrefUtil.getString(this, Constant.SHARE_USERNAME, MyApplication.getMyApplication().bean.getZh()));
            jsonPage.put("user", username);
            jsonPage.put("beginDt", beginDt);
            if (" ".equals(endDt)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                endDt = format.format(new Date());
            }
            jsonPage.put("endDt", endDt);
            jsonPage.put("inTypeCode", inTypeCode);
            jsonPage.put("inTypeName", inTypeName);
            jsonPage.put("ms", titleDesc);
            jsonPage.put("point", String.valueOf(arrayPoint));
            json = jsonPage.toString();
            json = json.replace("\\", "");
            json = json.replace("\"[", "[");
            json = json.replace("]\"", "]");
            MyLog.showLog("JJJ", json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private String getJson2() {
        getPageInfo();
        String json = "";
        try {
            JSONObject jsonPage = new JSONObject();
            JSONArray arrayPoint = new JSONArray();
            JSONArray arrayImag = new JSONArray();
            if (list != null && list.size() != 0) {
                for (Point point : list) {
                    JSONObject objectP = new JSONObject();
                    List<Image> images = dbManager.findByArgs(Image.class, "did=" + point.getId(), null);
                    for (Image image : images) {
                        JSONObject objectI = new JSONObject();
                        objectI.put("url", image.getUrl());
                        objectI.put("type", image.getType());
                        arrayImag.put(objectI);
                        objectI = null;
                    }
                    objectP.put("dz", point.getDz());
                    objectP.put("jd", point.getJd());
                    objectP.put("wd", point.getWd());
                    objectP.put("ms", point.getMs());
                    objectP.put("issc", point.getIssb());
                    objectP.put("imag", String.valueOf(arrayImag));
                    String str = objectP.toString();
                    str = str.replace("\\", "");
                    str = str.replace("\"[", "[");
                    str = str.replace("]\"", "]");
                    MyLog.showLog("JJJ", str);
                    arrayPoint.put(objectP);
                    objectP = null;
                }
            }
            jsonPage.put("fid", page.getFid());
            jsonPage.put("userNm", SharePrefUtil.getString(this, Constant.SHARE_USERNAME, MyApplication.getMyApplication().bean.getZh()));
            jsonPage.put("user", username);
            jsonPage.put("beginDt", beginDt);
            jsonPage.put("endDt", endDt);
            jsonPage.put("inTypeCode", inTypeCode);
            jsonPage.put("inTypeName", inTypeName);
            jsonPage.put("ms", titleDesc);
            jsonPage.put("point", String.valueOf(arrayPoint));
            json = jsonPage.toString();
            json = json.replace("\\", "");
            json = json.replace("\"[", "[");
            json = json.replace("]\"", "]");
            MyLog.showLog("JJJ", json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void getPageInfo() {
        username = tvName.getText().toString().trim();
        beginDt = tvDate.getText().toString().trim() + " " + tvTime.getText().toString().trim();
        titleDesc = tvData.getText().toString().trim();
        endDt = tvEnddate.getText().toString().trim() + " " + tvEndtime.getText().toString().trim();
        userId = MyApplication.getMyApplication().bean.getId();
    }

    private int num = 0;

    private void checkImag() {
        if (list != null && list.size() != 0) {
            if (num < list.size()) {
                List<Image> images = dbManager.findByArgs(Image.class, "did=" + list.get(num).getId(), null);
                if (!images.get(0).getUrl().contains("http")) {
                    updataImag();
                } else {
                    num++;
                    checkImag();
                }
            } else {
                if (issb) {
                    NetWork.updataXCSBList(getJson(), XunChaShangBao2Activity.this);
                } else {
                    NetWork.updataXCSBList(getJson2(), XunChaShangBao2Activity.this);
                }
            }
        } else {
            if (issb) {
                NetWork.updataXCSBList(getJson(), XunChaShangBao2Activity.this);
            } else {
                NetWork.updataXCSBList(getJson2(), XunChaShangBao2Activity.this);
            }
        }
    }

    private void updataImag() {
        List<Image> images = dbManager.findByArgs(Image.class, "did=" + list.get(num).getId(), null);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Image image : images) {
            MyLog.showLog("NNN", image.getUrl());
            if (image.getType().equals("2")) {
                File file = new File(image.getUrl().substring(7));
                builder.addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
            } else {
                File file = new File(image.getUrl());
                builder.addFormDataPart("video", file.getName(), RequestBody.create(MediaType.parse("video/*"), file));
            }
        }
        RequestBody body = builder.build();
        NetWork.updataTPSP(body, this);
        showWaitingDialog();
    }

    private void updataImagDB() {
        List<Image> images = dbManager.findByArgs(Image.class, "did=" + list.get(num).getId(), null);
        for (int i = 0; i < images.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("url", listImag.get(i).getUrl());
            values.put("type", listImag.get(i).getWordType());
            dbManager.updateById(Image.class, values, images.get(i).getId());
        }
        num++;
        checkImag();
    }

    private ProgressDialog waitingDialog;

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        if (waitingDialog != null && waitingDialog.isShowing()) {

        } else {
            waitingDialog = new ProgressDialog(XunChaShangBao2Activity.this);
            waitingDialog.setTitle("正在上传本次巡查...");
            waitingDialog.setMessage("上传中...");
            waitingDialog.setIndeterminate(true);
            waitingDialog.setCancelable(false);
            waitingDialog.show();
        }
    }

    private void closeWaitingDialog() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            findAll();
        }
    }

    public void showNormalDialog(final long id, final int num, String title) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(XunChaShangBao2Activity.this);
        normalDialog.setMessage(title);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (num) {
                            case 1:
                                delete(id);
                                break;
                            case 2:
                                issb = false;
                                if (CheckNetWork.getNetWorkType(getApplicationContext()) != CheckNetWork.NETWORKTYPE_INVALID) {
                                    checkImag();
                                } else {
                                    ToastUtil.showShort(getApplicationContext(), "当前网络不可用，请检查后重试！");
                                }
//                                update();
//                                ToastUtil.showShort(getApplicationContext(), "保存成功！！！");
                                break;
                            case 3:
                                creatNewLine();
                                break;
                            case 4:
//                                update2();
//                                closeWaitingDialog();
//                                ToastUtil.showShort(XunChaShangBao2Activity.this, "上报成功！！！");
//                                finish();
                                issb = true;
                                if (CheckNetWork.getNetWorkType(getApplicationContext()) != CheckNetWork.NETWORKTYPE_INVALID) {
                                    checkImag();
                                } else {
                                    ToastUtil.showShort(getApplicationContext(), "当前网络不可用，请检查后重试！");
                                }
                                break;
                        }
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    public void showDeleteDialog(long poitId, final String logId, final String contentDesc, final String address, final String LGTD, final String LTTD) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        this.poitId = poitId;
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(XunChaShangBao2Activity.this);
        normalDialog.setMessage("确定删除该条记录?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NetWork.deleteXcd(logId, contentDesc, address, LTTD, LGTD, XunChaShangBao2Activity.this);
                        NetWork.deleteXclsgj(logId, "Y", LGTD, LTTD, XunChaShangBao2Activity.this);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    public void creatNewLine() {
        username = tvName.getText().toString().trim();
        if (null == username || "".equals(username)) {
            ToastUtil.showShort(this, "请输入巡查人");
            return;
        }
        titleDesc = tvData.getText().toString().trim();
        if (null == titleDesc || "".equals(titleDesc)) {
            ToastUtil.showShort(this, "请输入巡查标题");
            return;
        }
        beginDt = tvDate.getText().toString().trim() + " " + tvTime.getText().toString().trim();

        if (null == beginDt || "".equals(beginDt)) {
            ToastUtil.showShort(this, "请开始时间");
            return;
        }

        endDt = tvEnddate.getText().toString().trim() + " " + tvEndtime.getText().toString().trim();

        userId = MyApplication.getMyApplication().bean.getId();
//        ler.sendEmptyMessage(102);
        fid = Long.parseLong(new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + String.format("%04d", (int) (Math.random() * 10000)));
        if (CheckNetWork.getNetWorkType(this) == CheckNetWork.NETWORKTYPE_INVALID) {
            ler.sendEmptyMessage(102);
        } else {
            String userNm = SharePrefUtil.getString(this, Constant.SHARE_USERNAME, MyApplication.getMyApplication().bean.getZh());
            NetWork.insertXcry(fid + "", userNm, username, titleDesc, beginDt, endDt, inTypeCode, inTypeName, userId, this);
        }
    }

    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 101) {
                if (insert() != -1) {
                    findRecordLast();
                    xcState = 2;
                    tvMap.setText("巡查路线");
                } else {
                    ToastUtil.showShort(XunChaShangBao2Activity.this, "新建巡查路线失败");
                }
                startLocationservice();
            } else if (msg.what == 102) {
                if (insert2() != -1) {
                    findRecordLast();
                    xcState = 2;
                    tvMap.setText("巡查路线");
                } else {
                    ToastUtil.showShort(XunChaShangBao2Activity.this, "新建巡查路线失败");
                }
            }
        }
    };

    public void startLocationservice() {
        Intent intent = new Intent(this, LocationService.class);
        intent.putExtra("time", timetick);
        startService(intent);
    }

    public void stopLocationservice() {
        Intent intent = new Intent(this, LocationService.class);
        stopService(intent);
    }
}
