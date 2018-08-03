package com.jsmy.pingshan.model;

import android.content.Context;
import android.util.Log;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.util.CheckNetWork;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/10/11.
 */

public class NetWork {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API.getBaseUrl())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static GitApi gitapi = retrofit.create(GitApi.class);

    public static void resetRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API.getBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitapi = retrofit.create(GitApi.class);
    }

    ;
    private static Call<String> call;
    private static Call<ResponseBody> callFile;

    private static void getNetVolue(final String url, final Map<String, String> map, final CallListener callListener) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        call = gitapi.getNetWork(url, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() == null) {
                    ToastUtil.showShort(MyApplication.getMyApplication(), "404 - 请检查后台服务开启状态或切换服务器在尝试");
                    return;
                }
                try {
                    MyLog.showLog("NetWork", "*****请求成功****" + response.toString());
                    MyLog.showLog("NetWork", "*****返回数据****" + response.body());
                    String result = new String(response.body());
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("code");
                    String msg = jsonObject.optString("msg");
                    String check = jsonObject.optString("check");
                    if (callListener != null) {
                        callListener.onSuccess(url, check, code, result, msg);
                    } else {
                        MyLog.showLog("NetWork", "*****监听器为空****");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("NetWork", "*****请求失败****" + t.toString());
                if (callListener != null) {
                    callListener.onFailure(url, t.toString());
                } else {
                    MyLog.showLog("NetWork", "*****监听器为空****" + t.toString());
                }
                ToastUtil.showShort(MyApplication.getMyApplication(), "404 - 请检查后台服务开启状态或切换服务器在尝试");
            }
        });

    }

    private static void postFiles(final String url, final Map<String, String> options, final Map<String, RequestBody> maps, final CallListener callListener) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        call = gitapi.updataFiles(url, options, maps);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    MyLog.showLog("NetWork", "*****请求成功****" + response.toString());
                    MyLog.showLog("NetWork", "*****返回数据****" + response.body());
                    String result = new String(response.body());
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("code");
                    String msg = jsonObject.optString("msg");
                    String check = jsonObject.optString("check");
                    if (callListener != null) {
                        callListener.onSuccess(url, check, code, result, msg);
                    } else {
                        MyLog.showLog("NetWork", "*****监听器为空****");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("NetWork", "*****请求失败****" + t.toString());
                if (callListener != null) {
                    callListener.onFailure(url, t.toString());
                } else {
                    MyLog.showLog("NetWork", "*****监听器为空****" + t.toString());
                }
                ToastUtil.showShort(MyApplication.getMyApplication(), "*****请求失败****" + t.toString());
            }
        });
    }

    private static void upLoad(final String url, RequestBody body, final CallListener callListener) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        call = gitapi.upLoad(url, body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    MyLog.showLog("NetWork", "*****请求成功****" + response.toString());
                    MyLog.showLog("NetWork", "*****返回数据****" + response.body());
                    String result = new String(response.body());
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("code");
                    String msg = jsonObject.optString("msg");
                    String check = jsonObject.optString("check");
                    if (callListener != null) {
                        callListener.onSuccess(url, check, code, result, msg);
                    } else {
                        MyLog.showLog("NetWork", "*****监听器为空****");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("NetWork", "*****请求失败****" + t.toString());
                if (callListener != null) {
                    callListener.onFailure(url, t.toString());
                } else {
                    MyLog.showLog("NetWork", "*****监听器为空****" + t.toString());
                }
                ToastUtil.showShort(MyApplication.getMyApplication(), "*****请求失败****" + t.toString());
            }
        });
    }

    private static void getDowloadFile(final String url, final String type, final CallListener callListener) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        callFile = gitapi.downloadFiles(url);
        callFile.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body(), url);
                            if (writtenToDisk) {
                                if (callListener != null) {
                                    try {
                                        callListener.onSuccess(type, "N", "Y", "Y", "下载成功！");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    callListener.onSuccess(type, "N", "N", "Y", "下载失败，请重试！");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }).start();

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callListener != null) {
                    callListener.onFailure(type, t.toString());
                }
            }
        });
    }

    public static boolean writeResponseBodyToDisk(ResponseBody body, String url) {
        try {
            File file = new File(API.SAVA_DOC_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            File futureStudioIconFile = new File(API.SAVA_DOC_PATH, url.substring(url.lastIndexOf("/") + 1));

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[8 * 1024];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public interface CallListener {
        public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException;

        public void onFailure(String type, String arg1);
    }

    //巡查上报
//    public static void updataXCSB(Map<String, String> options, Map<String, RequestBody> maps, CallListener callListener) {
//        postFiles(API.UPDATE_XCSB, options, maps, callListener);
//    }

    //上传线路巡查图片视频
    public static void updataTPSP(RequestBody body, CallListener callListener) {
        upLoad(API.SCXL_TPSP, body, callListener);
    }


    //登录
    public static void login(String zh, String mm, String modid,String regionid,CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("zh", zh);
        map.put("mm", mm);
        map.put("modId", modid);
        map.put("regionid", regionid);
        getNetVolue(API.LOG_IN, map, callListener);
    }

    //修改密码
    public static void updatemm(String zh, String jmm, String xmm, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("zh", zh);
        map.put("jmm", jmm);
        map.put("xmm", xmm);
        getNetVolue(API.UPDATE_MM, map, callListener);
    }

    //预警列表
    public static void getYjxyList(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.GET_YJXY_LIST, map, callListener);
    }

    //响应反馈
    public static void getXyfk(String cid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("zid", cid);
        getNetVolue(API.XY_FK, map, callListener);
    }

    //灾情统计
    public static void getZqtj(String cid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yjjlId", cid);
        getNetVolue(API.ZQ_TJ, map, callListener);
    }

    //水情列表
    public static void getSqlist(String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_SQ_LIST, map, callListener);
    }

    //雨情列表
    public static void getYqlist(String pageindex, String time, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pageindex", pageindex);
        map.put("time", time);
        getNetVolue(API.GET_YQ_LIST, map, callListener);
    }

    //移动巡查
    public static void getYdxclist(String mh, String rq, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("mh", mh);
        map.put("rq", rq);
        getNetVolue(API.GET_YDXC_LIST, map, callListener);
    }

    //巡查上报
    public static void updatexcsb(String cjr, String ksdt, String dz, String ms, String jd, String wd, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("cjr", cjr);
        map.put("ksdt", ksdt);
        map.put("dz", dz);
        map.put("ms", ms);
        map.put("jd", jd);
        map.put("wd", wd);
        getNetVolue(API.UPDATE_XCSB, map, callListener);
    }

    //移动巡查详情
    public static void getYdxcinfo(String id, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("jlid", id);
        getNetVolue(API.GET_YDXC_INFO, map, callListener);
    }

    //指挥人员数列表
    public static void getZhryslist(String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_ZHRYS_LIST, map, callListener);
    }

    //指挥人员数列表
    public static void getZhrylist(String cid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        getNetVolue(API.GET_ZHRY_LIST, map, callListener);
    }

    //抢险力量列表
    public static void getQxlllist(String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_QXLL_LIST, map, callListener);
    }

    //抢险力量编辑
    public static void updateQxll(String cid, String qxrs, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        map.put("qxrs", qxrs);
        getNetVolue(API.UPDATE_QXLL, map, callListener);
    }

    //库存物资列表
    public static void getKcwzlist(String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_KCWZ_LIST, map, callListener);
    }

    //库存物资编辑
    public static void updateKcwz(Map<String, String> map, CallListener callListener) {
        getNetVolue(API.UPDATE_KCWZ, map, callListener);
    }

    //预案管理
    public static void getYagllist(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.GET_YAGL_LIST, map, callListener);
    }

    //日志查询
    public static void getRzlist(String pageindex, String yhid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("pageindex", pageindex);
        map.put("yhid", yhid);
        getNetVolue(API.GET_RZ_LIST, map, callListener);
    }

    //版本更新
    public static void getVersion(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.APP_VERSION, map, callListener);
    }

    //前置界面
    public static void getPreList ( CallListener callListener) {
        Map<String, String> map = new HashMap<>();

        getNetVolue(API.GET_PRE_LIST, map, callListener);
    }
    //通讯录
    public static void getPersonList(String uid, String keyword, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("keyword", keyword);
        getNetVolue(API.GET_PERSON_LIST, map, callListener);
    }
    //新雨情
    public static void getYuqingList(String cityid, String st,String et, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("cityid", cityid);
        map.put("st", st);
        map.put("et", et);
        getNetVolue(API.GET_YUQINF_LIST, map, callListener);
    }

    //历史雨情
    public static void getLSYQlist(String rq, String stationId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("rq", rq);
        map.put("stationId", stationId);
        getNetVolue(API.GET_YQLS_LIST, map, callListener);
    }

    //历史水情
    public static void getLSSQlist(String rq, String stationId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("rq", rq);
        map.put("stationId", stationId);
        getNetVolue(API.GET_LSSQ_LIST, map, callListener);
    }

    //巡查命令发送列表
    public static void getXCMLfsList(String userid, String jsrmc, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("jsrmc", jsrmc);
        getNetVolue(API.GET_XCML_FS_LIST, map, callListener);
    }

    //巡查命令接收列表
    public static void getXCMLjsList(String userid, String sendName, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("sendName", sendName);
        getNetVolue(API.GET_XCML_JS_LIST, map, callListener);
    }

    //巡获取登陆用户列表
    public static void getUserList(String userid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        getNetVolue(API.GET_USER_LIST, map, callListener);
    }

    //巡查命令发送
    public static void updateXCMLfs(String userid, String userNm, String username, String sendContent, String userid2, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("userNm", userNm);
        map.put("username", username);
        map.put("sendContent", sendContent);
        map.put("userid2", userid2);
        getNetVolue(API.UPDATE_XCML_FS, map, callListener);
    }

    //巡查命令详情
    public static void getXCMLinfo(String jsid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("jsid", jsid);
        getNetVolue(API.GET_XCML_INFO, map, callListener);
    }

    //回复巡查命令
    public static void getUpdataXcml(String jsid, String replyContent, String userid, String userid2, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("jsid", jsid);
        map.put("replyContent", replyContent);
        map.put("userid", userid);
        map.put("userid2", userid2);
        getNetVolue(API.UPDATE_XCML, map, callListener);
    }

    //获取天气预报
    public static void getForecast(final String url, final Map<String, String> map, final CallListener callListener) {

        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.TIANQA_FORECAST)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitApi gitapi = retrofit.create(GitApi.class);
        call = gitapi.getNetWork(url, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    MyLog.showLog("NetWork", "*****请求成功****" + response.toString());
                    MyLog.showLog("NetWork", "*****返回数据****" + response.body());
                    String result = new String(response.body());
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("status");
                    String msg = jsonObject.optString("server_time");
                    String check = jsonObject.optString("unit");
                    if (callListener != null) {
                        callListener.onSuccess(API.TIANQA_FORECAST, check, code, result, msg);
                    } else {
                        MyLog.showLog("NetWork", "*****监听器为空****");
                    }
                } catch (JSONException e) {
                    MyLog.showLog("NetWork", "*****请求失败****" + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("NetWork", "*****请求失败****" + t.toString());
                if (callListener != null) {
                    callListener.onFailure(API.TIANQA_FORECAST, t.toString());
                } else {
                    MyLog.showLog("NetWork", "*****监听器为空****" + t.toString());
                }
                ToastUtil.showShort(MyApplication.getMyApplication(), "*****请求失败****" + t.toString());
            }
        });
    }

    //创建讨论组
    public static void discussCreat(String createId, String discussId, String discussName, String userId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("createId", createId);
        map.put("discussId", discussId);
        map.put("discussName", discussName);
        map.put("userId", userId);
        getNetVolue(API.DISCUSS_CREAT, map, callListener);
    }

    //增加讨论组成员
    public static void discussAdd(String createId, String discussId, String userId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("createId", createId);
        map.put("discussId", discussId);
        map.put("userId", userId);
        getNetVolue(API.DISCUSS_ADD, map, callListener);
    }

    //删除讨论组成员
    public static void discussDelete(String createId, String discussId, String userId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("createId", createId);
        map.put("discussId", discussId);
        map.put("userId", userId);
        getNetVolue(API.DISCUSS_DELETE, map, callListener);
    }

    //讨论组列表
    public static void discussList(String userId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        getNetVolue(API.DISCUSS_LIST, map, callListener);
    }

    //讨论组成员列表
    public static void discussPersonList(String discussId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("discussId", discussId);
        getNetVolue(API.DISCUSS_USER_LIST, map, callListener);
    }

    //查询临时巡查图片视频列表
    public static void getXLtpspList(String acquId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("acquId", acquId);
        getNetVolue(API.GET_XL_TPSP_LIST, map, callListener);
    }

    //巡查记录上报
    public static void updataXCSBList(String json, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("json", json);
        getNetVolue(API.UPDATA_XCSB_LIST, map, callListener);
    }

    //巡查记录上报
    public static void getVideoList(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.GET_VIDEO_LIST, map, callListener);
    }

    //更新巡查命令阅读状态
    public static void update_xmml_is_read(String jsid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("jsid", jsid);
        getNetVolue(API.UPDATE_XCML_IS_READ, map, callListener);
    }

    //用户等级功能权限
    public static void getFunctionList(String level, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("level", level);
        NetWork.getNetVolue(API.GET_FUNCTION_LIST, map, callListener);
    }

    //
    public static void insertXcry(String fid, String userNm, String username, String titleDesc, String beginDt, String endDt, String inTypeCode, String inTypeName, String userId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("fid", fid);
        map.put("userNm", userNm);
        map.put("username", username);
        map.put("titleDesc", titleDesc);
        map.put("beginDt", beginDt);
        map.put("endDt", endDt);
        map.put("inTypeCode", inTypeCode);
        map.put("inTypeName", inTypeName);
        map.put("userId", userId);
        NetWork.getNetVolue(API.INSERT_XCRY, map, callListener);
    }

    //插入巡查点
    public static void insertxcsblist3(String json, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("json", json);
        getNetVolue(API.INSERT_XCSB_LIST3, map, callListener);
    }

    //新增巡查轨迹
    public static void insertXcgj(String logId, String LGTD, String LTTD, String mark, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("logId", logId);
        map.put("LGTD", LGTD);
        map.put("LTTD", LTTD);
        map.put("mark", mark);
        getNetVolue(API.INSERT_XCGJ, map, callListener);
    }

    //查询巡查路线轨迹
    public static void getXcgjList(String logId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("logId", logId);
        getNetVolue(API.GET_XCGJ_LIST, map, callListener);
    }
//
//    //查询行政区域
//    public static void getxzList(CallListener callListener) {
//        Map<String, String> map = new HashMap<>();
//        getNetVolue(API.GET_XZ_LIST, map, callListener);
//    }
    //（新 行政区划（前置页面）县乡镇村）
    public static void getxzList(String regionId,CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("regionId",regionId);
        getNetVolue(API.GET_XZ_LIST, map, callListener);
    }
    //查询县级预案
    public static void getxianjiList(String xid,CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("xid",xid);
        getNetVolue(API.GET_XJYAN, map, callListener);
    }

    //查询镇预案
    public static void getzyanList(String zid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("zid", zid);
        getNetVolue(API.GET_ZYAN_LIST, map, callListener);
    }

    //查询村预案
    public static void getcyanList(String cid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        getNetVolue(API.GET_CYAN_LIST, map, callListener);
    }

    //查询行政区域
    public static void getsjjgList(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.GET_SJJG_LIST, map, callListener);
    }

    //查询巡查类型
    public static void getXclxList(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.GET_XCLX_LIST, map, callListener);
    }

    //发送巡查命令详情
    public static void getfsxcmlinfo(String fsid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("fsid", fsid);
        getNetVolue(API.GET_FSXCML_INFO, map, callListener);
    }

    //删除巡查点
    public static void deleteXcd(String logId, String contentDesc, String address, String LGTD, String LTTD, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("logId", logId);
        map.put("contentDesc", contentDesc);
        map.put("address", address);
        map.put("LGTD", LGTD);
        map.put("LTTD", LTTD);
        getNetVolue(API.DELETE_XCD, map, callListener);
    }

    //删除巡查点
    public static void deleteXclsgj(String logId, String mark, String LGTD, String LTTD, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("logId", logId);
        map.put("mark", mark);
        map.put("LGTD", LGTD);
        map.put("LTTD", LTTD);
        getNetVolue(API.DELETE_XCLSGJ, map, callListener);
    }

    public static void dowLoadFole(String url, CallListener callListener) {
        getDowloadFile(url, API.DOWLOAD_FILE, callListener);
    }
}
