package com.jsmy.pingshan.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.YuAnCDataAdapter;
import com.jsmy.pingshan.adapter.YuAnDataAdapter;
import com.jsmy.pingshan.adapter.YuAnGuanLiAdapter;
import com.jsmy.pingshan.bean.YuAnCDataBean;
import com.jsmy.pingshan.bean.YuAnZDataBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.OfficeUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YuAnDataActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.my_recycler)
    RecyclerView myRecycler;
    private YuAnDataAdapter zAdapter;
    private YuAnCDataAdapter cAdapter;
    private List<YuAnZDataBean.DataBean> zList;
    private List<YuAnCDataBean.DataBean> cList;

    private String type;
    private String mc;
    private String id;
    private Context context;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_yu_an_data;
    }

    @Override
    protected void initView() {
        context = this;
        type = getIntent().getStringExtra("type");
        mc = getIntent().getStringExtra("mc");
        id = getIntent().getStringExtra("id");

        tvTitle.setText(mc + "预案详情");
    }

    @Override
    protected void initData() {
        loadData();
    }

    private void loadData() {
        if ("c".equals(type)) {
            NetWork.getcyanList(id, this);
        } else {
            NetWork.getzyanList(id, this);
        }
    }

    private void initZRecycler() {
        zAdapter = new YuAnDataAdapter(this, zList);
        myRecycler.setLayoutManager(new LinearLayoutManager(context));
        myRecycler.setItemAnimator(null);
        myRecycler.setAdapter(zAdapter);
    }

    private void initCRecycler() {
        cAdapter = new YuAnCDataAdapter(this, cList);
        myRecycler.setLayoutManager(new LinearLayoutManager(context));
        myRecycler.setItemAnimator(null);
        myRecycler.setAdapter(cAdapter);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_ZYAN_LIST:
                if ("Y".equals(code)) {
                    zList = gson.fromJson(result, YuAnZDataBean.class).getData();
                    initZRecycler();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_CYAN_LIST:
                if ("Y".equals(code)) {
                    cList = gson.fromJson(result, YuAnCDataBean.class).getData();
                    initCRecycler();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.DOWLOAD_FILE:

                if ("Y".equals(code)) {
                    loadData();
                    if (null != url && !"".equals(url))
                        context.startActivity(OfficeUtil.getFileIntent(context, API.SAVA_DOC_PATH + "/" + url.substring(url.lastIndexOf("/") + 1)));
                }
                ToastUtil.showShort(this, msg);
                closeWaitingDialog();
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {
        if (type.equals(API.DOWLOAD_FILE)) {
            closeWaitingDialog();
        }
    }

    private ProgressDialog waitingDialog;

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        if (waitingDialog == null) {
            waitingDialog = new ProgressDialog(YuAnDataActivity.this);
//            waitingDialog.setTitle("正在保存本次巡查信息");
            waitingDialog.setMessage("下载中...");
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

    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    public void dowLoadFile(String url) {
        NetWork.dowLoadFole(url, this);
        showWaitingDialog();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public void verifyStoragePermissions(String url) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            dowLoadFile(url);
            this.url = url;
        }
    }


    //下载对话框
    public void showProgressDialog(final String url) {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(YuAnDataActivity.this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("下载进度...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL dow_url = new URL(url);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) dow_url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();
                    File file = new File(API.SAVA_DOC_PATH);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File docFile = new File(API.SAVA_DOC_PATH, url.substring(url.lastIndexOf("/") + 1));
                    FileOutputStream fos = new FileOutputStream(docFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    int numread = 0;
                    do {
                        numread = is.read(buf);
                        MyLog.showLog("YYY", " - " + numread);
                        count += numread;
                        // 计算进度条位置
                        int progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        if (progress < MAX_PROGRESS) {
                            progressDialog.setProgress(progress);
                            // 写入文件
//                            fos.write(buf, 0, numread);
                            fos.write(buf);
                        } else {
                            progressDialog.cancel();
                        }

                    } while (numread != -1);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                    loadData();
                } catch (MalformedURLException e) {
                    progressDialog.cancel();
                    ToastUtil.showShort(context, "下载中断");
                    e.printStackTrace();
                } catch (IOException e) {
                    progressDialog.cancel();
                    ToastUtil.showShort(context, "下载中断");
                    e.printStackTrace();
                } catch (Exception e) {
                    progressDialog.cancel();
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
