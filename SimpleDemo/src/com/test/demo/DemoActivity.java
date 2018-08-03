/**
 * <p>DemoActivity Class</p>
 *
 * @author zhuzhenlei 2014-7-17
 * @version V1.0
 * @modificationHistory
 * @modify by user:
 * @modify by reason:
 */
package com.test.demo;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.MediaPlayer.PlayM4.Player;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hikvision.netsdk.ExceptionCallBack;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.NET_DVR_COMPRESSIONCFG_V30;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_PLAYBACK_INFO;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.PTZCommand;
import com.hikvision.netsdk.PlaybackControlCommand;


/**
 * <pre>
 *  ClassName  DemoActivity Class
 * </pre>
 *
 * @author zhuzhenlei
 * @version V1.0
 * @modificationHistory
 */
public class DemoActivity extends Activity {

    private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;

    private int m_iLogID = -1; //return by NET_DVR_Login_v30
    private int m_iPlayID = -1; // return by NET_DVR_RealPlay_V30
    private int m_iPlaybackID = -1; // return by NET_DVR_PlayBackByTime

    private int m_iPort = -1; // play port
    private int m_iStartChan = 0; // start channel no
    private int m_iChanNum = 0; // channel number
    private PlaySurfaceView[] playView = new PlaySurfaceView[4];

    private static final String TAG = "DemoActivity";

    private boolean m_bTalkOn = false;
    private boolean m_bPTZL = false;
    private boolean m_bMultiPlay = false;

    private boolean m_bNeedDecode = true;
    private boolean m_bSaveRealData = false;
    private boolean m_bStopPlayback = false;

    private String IPAddr = "";
    private String Port = "";
    private String User = "admin";
    private String Psd = "admin12345";

    private Spinner mySpinner;
    private TextView m_oLoginBtn = null;
    private TextView m_oPreviewBtn = null;

    private ImageView imgBack;

    private String video;

    private List<VideoBean.DataBean> list;
    private List<String> listVideo;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashUtil crashUtil = CrashUtil.getInstance();
        crashUtil.init(this);

        setContentView(R.layout.main);

        if (!initeSdk()) {
            this.finish();
            return;
        }

        if (!initeActivity()) {
            this.finish();
            return;
        }
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        video = getIntent().getStringExtra("video");
        Gson gson = new Gson();
        try {
            list = gson.fromJson(video, VideoBean.class).getData();
            listVideo = new ArrayList<>();
            for (VideoBean.DataBean dataBean : list) {
                listVideo.add(dataBean.getAdnm() + dataBean.getNM());
            }
            mySpinner = (Spinner) findViewById(R.id.my_spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_shipin, listVideo);
            adapter.setDropDownViewResource(R.layout.spinner_item_dropdown_shipin);
            mySpinner.setAdapter(adapter);
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    IPAddr = list.get(i).getUrl().substring(7, list.get(i).getUrl().lastIndexOf(":"));
                    Port = list.get(i).getUrl().substring(list.get(i).getUrl().lastIndexOf(":") + 1);
                    logOut(true);
                    Log.e("DDD", " -- " + Port + " -- " + IPAddr + " -- " + listVideo.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("m_iPort", m_iPort);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        m_iPort = savedInstanceState.getInt("m_iPort");
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }


    private boolean initeSdk() {
        // init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init()) {
            Log.e(TAG, "HCNetSDK init is failed!");
            return false;
        }
        HCNetSDK.getInstance().NET_DVR_SetLogToFile(3, "/mnt/sdcard/sdklog/",
                true);
        return true;
    }

    // GUI init
    private boolean initeActivity() {
        m_oLoginBtn = (TextView) findViewById(R.id.btn_Login);
        m_oLoginBtn.setOnClickListener(Login_Listener);
        m_oPreviewBtn = (TextView) findViewById(R.id.btn_Preview);
        m_oPreviewBtn.setOnClickListener(Preview_Listener);
        return true;
    }

    // login listener
    private TextView.OnClickListener Login_Listener = new TextView.OnClickListener() {
        public void onClick(View v) {
            try {
                if (m_iLogID < 0) {
                    // login on the device
                    m_iLogID = loginDevice();
                    if (m_iLogID < 0) {
                        Log.e(TAG, "This device logins failed!");
                        Toast.makeText(DemoActivity.this, "This device logins failed!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Log.e(TAG, "m_iLogID=" + m_iLogID);
                    }
                    // get instance of exception callback and set
                    ExceptionCallBack oexceptionCbf = getExceptiongCbf();
                    if (oexceptionCbf == null) {
                        Log.e(TAG, "ExceptionCallBack object is failed!");
                        return;
                    }

                    if (!HCNetSDK.getInstance().NET_DVR_SetExceptionCallBack(
                            oexceptionCbf)) {
                        Log.e(TAG, "NET_DVR_SetExceptionCallBack is failed!");
                        return;
                    }

                    m_oLoginBtn.setText("退出");
                    Log.i(TAG, "Login sucess ****************************1***************************");
                    Toast.makeText(DemoActivity.this, "Login sucess!", Toast.LENGTH_SHORT).show();
                } else {
                    // whether we have logout
                    if (!HCNetSDK.getInstance().NET_DVR_Logout_V30(m_iLogID)) {
                        Log.e(TAG, " NET_DVR_Logout is failed!");
                        Toast.makeText(DemoActivity.this, "NET_DVR_Logout is failed!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    m_oLoginBtn.setText("登录");
                    m_oPreviewBtn.setText("播放");
                    m_iLogID = -1;
                }
            } catch (Exception err) {
                Log.e(TAG, "error: " + err.toString());
            }
        }
    };

    // Preview listener
    private TextView.OnClickListener Preview_Listener = new TextView.OnClickListener() {
        public void onClick(View v) {
            try {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(m_oLoginBtn.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                if (m_iLogID < 0) {
                    Log.e(TAG, "please login on device first");
                    Toast.makeText(DemoActivity.this, "please login on device first!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (m_iPlaybackID >= 0) {
                    Log.i(TAG, "Please stop palyback first");
                    Toast.makeText(DemoActivity.this, "Please stop palyback first!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (m_bNeedDecode) {
                    if (m_iChanNum > 1)// preview more than a channel
                    {
                        if (!m_bMultiPlay) {
                            startMultiPreview();
                            // startMultiPreview();
                            m_bMultiPlay = true;
                            m_oPreviewBtn.setText("停止");
                        } else {
                            stopMultiPreview();
                            m_bMultiPlay = false;
                            m_oPreviewBtn.setText("播放");
                        }
                    } else // preivew a channel
                    {
                        if (m_iPlayID < 0) {
                            startSinglePreview(0);
                            m_oPreviewBtn.setText("停止");
                        } else {
                            stopSinglePreview();
                            m_oPreviewBtn.setText("播放");
                        }
                    }
                } else {

                }
            } catch (Exception err) {
                Log.e(TAG, "error: " + err.toString());
            }
        }
    };

    private void logOut(boolean showToast) {
        try {
            if (m_iLogID < 0) {

            } else {
                // whether we have logout
                if (!HCNetSDK.getInstance().NET_DVR_Logout_V30(m_iLogID)) {
                    Log.e(TAG, " NET_DVR_Logout is failed!");
                    return;
                }
                m_oPreviewBtn.setText("播放");
                m_oLoginBtn.setText("登录");
                m_iLogID = -1;
                if (showToast)
                    Toast.makeText(DemoActivity.this, "更换成功，请重新登录!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception err) {
            Log.e(TAG, "error: " + err.toString());
        }
    }

    private void playOut() {
        try {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(m_oLoginBtn.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            if (m_iLogID < 0) {
                Log.e(TAG, "please login on device first");
                return;
            }

            if (m_iPlaybackID >= 0) {
                Log.i(TAG, "Please stop palyback first");
                return;
            }

            if (m_bNeedDecode) {
                if (m_iChanNum > 1) {// preview more than a channel
                    if (!m_bMultiPlay) {

                    } else {
                        stopMultiPreview();
                        m_bMultiPlay = false;
                        m_oPreviewBtn.setText("播放");
                    }
                } else {// preivew a channel
                    if (m_iPlayID < 0) {

                    } else {
                        stopSinglePreview();
                        m_oPreviewBtn.setText("播放");
                    }
                }
            } else {

            }
        } catch (Exception err) {
            Log.e(TAG, "error: " + err.toString());
        }
    }

    private void ChangeSingleSurFace(boolean bSingle) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        for (int i = 0; i < 4; i++) {
            if (playView[i] == null) {
                playView[i] = new PlaySurfaceView(this);
                playView[i].setParam(metric.widthPixels);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
//                params.bottomMargin = playView[i].getM_iHeight() - (i / 2) * playView[i].getM_iHeight();
//                params.leftMargin = (i % 2) * playView[i].getM_iWidth();
//                params.gravity = Gravity.CENTER | Gravity.LEFT;
                params.gravity = Gravity.CENTER;
                addContentView(playView[i], params);
                playView[i].setVisibility(View.INVISIBLE);

            }
        }

        if (bSingle) {
            // ��·ֻ��ʾ����1
            for (int i = 0; i < 4; ++i) {
                playView[i].setVisibility(View.INVISIBLE);
            }
            playView[0].setParam(metric.widthPixels * 2);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
//            params.bottomMargin = playView[3].getM_iHeight() - (3 / 2) * playView[3].getM_iHeight();
//            params.leftMargin = 0;
//            params.gravity = Gravity.CENTER | Gravity.LEFT;
            params.gravity = Gravity.CENTER;
            playView[0].setLayoutParams(params);
            playView[0].setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < 4; ++i) {
                playView[i].setVisibility(View.VISIBLE);
            }

            playView[0].setParam(metric.widthPixels);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
//            params.bottomMargin = playView[0].getM_iHeight() - (0 / 2) * playView[0].getM_iHeight();
//            params.leftMargin = (0 % 2) * playView[0].getM_iWidth();
//            params.gravity = Gravity.CENTER| Gravity.LEFT;
            params.gravity = Gravity.CENTER;
            playView[0].setLayoutParams(params);
        }

    }

    private void startMultiPreview() {

        for (int i = 0; i < 4; i++) {
            playView[i].startPreview(m_iLogID, m_iStartChan + i);
        }
        m_iPlayID = playView[0].m_iPreviewHandle;
    }

    private void stopMultiPreview() {
        int i = 0;
        for (i = 0; i < 4; i++) {
            playView[i].stopPreview();
        }
        m_iPlayID = -1;
    }

    private void startSinglePreview(int num) {
        if (m_iPlaybackID >= 0) {
            Log.i(TAG, "Please stop palyback first");
            return;
        }

        Log.i(TAG, "m_iStartChan:" + m_iStartChan);

        NET_DVR_PREVIEWINFO previewInfo = new NET_DVR_PREVIEWINFO();
        previewInfo.lChannel = m_iStartChan;
        previewInfo.dwStreamType = 0; // substream
        previewInfo.bBlocked = 1;
        previewInfo.hHwnd = playView[num].getHolder();
        // HCNetSDK start preview
        m_iPlayID = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(m_iLogID, previewInfo, null);
        Log.e(TAG, "start m_iPlayID = " + m_iPlayID);
        if (m_iPlayID < 0) {
            Log.e(TAG, "NET_DVR_RealPlay is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            Log.e(TAG, "NET_DVR_RealPlay is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetErrorMsg(new INT_PTR()));
            if (num < 3) {
                num++;
                startSinglePreview(num);
            }
            return;
        }

        Log.i(TAG, "NetSdk Play sucess ***********************3***************************");

    }

    private void stopSinglePreview() {
        if (m_iPlayID < 0) {
            Log.e(TAG, "m_iPlayID < 0");
            return;
        }
        Log.e(TAG, "stop m_iPlayID = " + m_iPlayID);
        // net sdk stop preview
        if (!HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPlayID)) {
            Log.e(TAG, "StopRealPlay is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            Log.e(TAG, "StopRealPlay is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetErrorMsg(new INT_PTR()));
            return;
        }

        m_iPlayID = -1;
    }


    private int loginNormalDevice() {
        // get instance
        m_oNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
        if (null == m_oNetDvrDeviceInfoV30) {
            Log.e(TAG, "HKNetDvrDeviceInfoV30 new is failed!");
            return -1;
        }
        String strIP = IPAddr;
        int nPort = Integer.parseInt(Port);
        String strUser = User;
        String strPsd = Psd;
        // call NET_DVR_Login_v30 to login on, port 8000 as default
        int iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(strIP, nPort,
                strUser, strPsd, m_oNetDvrDeviceInfoV30);
        if (iLogID < 0) {
            Log.e(TAG, "NET_DVR_Login is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            Log.e(TAG, "NET_DVR_Login is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetErrorMsg(new INT_PTR()));
            return -1;
        }
        if (m_oNetDvrDeviceInfoV30.byChanNum > 0) {
            m_iStartChan = m_oNetDvrDeviceInfoV30.byStartChan;
            m_iChanNum = m_oNetDvrDeviceInfoV30.byChanNum;
        } else if (m_oNetDvrDeviceInfoV30.byIPChanNum > 0) {
            m_iStartChan = m_oNetDvrDeviceInfoV30.byStartDChan;
            m_iChanNum = 1/*m_oNetDvrDeviceInfoV30.byIPChanNum
                    + m_oNetDvrDeviceInfoV30.byHighDChanNum * 256*/;
        }

        if (m_iChanNum > 1) {
            ChangeSingleSurFace(false);
        } else {
            ChangeSingleSurFace(true);
        }
        Log.i(TAG, "NET_DVR_Login is Successful!");

        return iLogID;
    }

    public static void Test_XMLAbility(int iUserID) {
        byte[] arrayOutBuf = new byte[64 * 1024];
        INT_PTR intPtr = new INT_PTR();
        String strInput = new String(
                "<AlarmHostAbility version=\"2.0\"></AlarmHostAbility>");
        byte[] arrayInBuf = new byte[8 * 1024];
        arrayInBuf = strInput.getBytes();
        if (!HCNetSDK.getInstance().NET_DVR_GetXMLAbility(iUserID,
                HCNetSDK.DEVICE_ABILITY_INFO, arrayInBuf, strInput.length(),
                arrayOutBuf, 64 * 1024, intPtr)) {
            Log.e(TAG, "get DEVICE_ABILITY_INFO faild!" + " err: "
                    + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            Log.e(TAG, "get DEVICE_ABILITY_INFO succ!");
        }
    }


    private int loginEzvizDevice() {
        return -1;
    }


    private int loginDevice() {
        int iLogID = -1;

        iLogID = loginNormalDevice();

        return iLogID;
    }


    private void paramCfg(final int iUserID) {
        // whether have logined on
        if (iUserID < 0) {
            Log.e(TAG, "iUserID < 0");
            return;
        }

        NET_DVR_COMPRESSIONCFG_V30 struCompress = new NET_DVR_COMPRESSIONCFG_V30();
        if (!HCNetSDK.getInstance().NET_DVR_GetDVRConfig(iUserID,
                HCNetSDK.NET_DVR_GET_COMPRESSCFG_V30, m_iStartChan,
                struCompress)) {
            Log.e(TAG, "NET_DVR_GET_COMPRESSCFG_V30 failed with error code:"
                    + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            Log.i(TAG, "NET_DVR_GET_COMPRESSCFG_V30 succ");
        }
        // set substream resolution to cif
        struCompress.struNetPara.byResolution = 1;
        if (!HCNetSDK.getInstance().NET_DVR_SetDVRConfig(iUserID,
                HCNetSDK.NET_DVR_SET_COMPRESSCFG_V30, m_iStartChan,
                struCompress)) {
            Log.e(TAG, "NET_DVR_SET_COMPRESSCFG_V30 failed with error code:"
                    + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            Log.i(TAG, "NET_DVR_SET_COMPRESSCFG_V30 succ");
        }
    }


    private ExceptionCallBack getExceptiongCbf() {
        ExceptionCallBack oExceptionCbf = new ExceptionCallBack() {
            public void fExceptionCallBack(int iType, int iUserID, int iHandle) {
                Log.e(TAG, "recv exception, type:" + iType);
            }
        };
        return oExceptionCbf;
    }

    public void Cleanup() {
        // release net SDK resource
        HCNetSDK.getInstance().NET_DVR_Cleanup();
    }

    @Override
    public void finish() {
        stopSinglePreview();
        logOut(false);
        Cleanup();
        super.finish();
    }
}
