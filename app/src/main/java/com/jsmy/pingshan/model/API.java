package com.jsmy.pingshan.model;

import android.os.Environment;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.util.SharePrefUtil;

/**
 * Created by Administrator on 2017/10/11.
 */

public class API {
    //文档路径
    public static final String DOC_URL = "http://192.168.1.116:8080/";
    //文档路径
    public static final String SAVA_DOC_PATH = Environment.getExternalStorageDirectory() + "/pingshanwdoc";
    //天气
    public static final String TIANQI_URL = "http://m.nmc.cn/publish/forecast/ASC/pingshan.html";
    //卫星
    public static final String WEIXING_URL = "http://m.nmc.cn/publish/satellite/fy2.htm";
    //雷达
    public static final String LEIDA_URL = "http://m.nmc.cn/publish/radar/chinaall.html";

    //实时天气状况接口 https://api.caiyunapp.com/v2/TAkhjf8d1nlSlspN/121.6544,25.1552/realtime.json
    public static final String TIANQI_REAL_TIEM = "https://api.caiyunapp.com/v2/j0YjlSf41iaMTGWk/";
    public static final String REAL_TIEM = "/realtime.json";
    //天气预报接口
    public static final String TIANQA_FORECAST = "https://api.caiyunapp.com/v2/DCY=b3ucRso2nZZ5/";
    public static final String FORECAST = "/forecast.json";


    //内网
//    public static final String BASE_URL_NEI = "http://192.168.3.170:8080/pingshan/";
//    public static final String BASE_URL_NEI = "http://172.16.60.125:9002/pingshan/";
//    public static final String BASE_URL_NEI = "http://172.16.60.125:9002/yantai/";
    public static final String BASE_URL_NEI = "http://202.85.210.70:9002/yantai/";

    //外网
//    public static final String BASE_URL_WAI = "http://117.139.109.76:28900/pingshan/";
    public static final String BASE_URL_WAI = "http://202.85.210.70:9002/yantai/";
    public static String getBaseUrl() {
        if ("nei".equals(SharePrefUtil.getString(MyApplication.getMyApplication().getApplicationContext(), "net", ""))) {
            return BASE_URL_NEI;
        } else {
            return BASE_URL_WAI;
        }
    }

    //1登录 (OK)
    public static final String LOG_IN = "sys/login.do";
    //2修改密码 (OK)
    public static final String UPDATE_MM = "sys/updatemm.do";
    //3版本更新 (OK)
    public static final String APP_VERSION = "sys/version.do";
    //4预警响应 (OK)
    public static final String GET_YJXY_LIST = "sys/getyjlist3.do";
    //5响应反馈 (OK)
    public static final String XY_FK = "yjxy/xyfk.do";
    //6灾情统计 (OK)
    public static final String ZQ_TJ = "yjxy/zqtj.do";
    //7水情列表 (OK)
    public static final String GET_SQ_LIST = "gzt/getsqlist.do";
    //8雨情列表 (OK)
    public static final String GET_YQ_LIST = "gzt/getyqlist.do";
    //9移动巡查 (OK)
    public static final String GET_YDXC_LIST = "gzt/getydxclist.do";
    //10巡查上报 (OK)
    public static final String UPDATE_XCSB = "gzt/updatexcsb.do";
    //11移动巡查详情 (OK)
    public static final String GET_YDXC_INFO = "gzt/getydxcinfo2.do";
    //12指挥人员数列表 (OK)
    public static final String GET_ZHRYS_LIST = "gzt/getzhryslist.do";
    //13指挥人员列表 (OK)
    public static final String GET_ZHRY_LIST = "gzt/getzhrylist.do";
    //14抢险力量列表 (OK)
    public static final String GET_QXLL_LIST = "gzt/getqxlllist.do";
    //15抢险力量编辑 (OK)
    public static final String UPDATE_QXLL = "gzt/updateqxll.do";
    //16库存物资列表 (OK)
    public static final String GET_KCWZ_LIST = "gzt/getfxwzlist2.do";
    //17库存物资编辑 (OK)
    public static final String UPDATE_KCWZ = "gzt/updatefxwz2.do";
    //18预案管理 (OK)
    public static final String GET_YAGL_LIST = "gzt/getyagllist.do";
    //19日志查询
    public static final String GET_RZ_LIST = "gzt/getrzlist.do";
    //20通讯录 (OK)
    public static final String GET_PERSON_LIST = "sys/GetPersonList2.do";
    //雨情
    public static final String GET_YUQINF_LIST = "gzt/findTotalRain.do";
    //前置界面 获取市的县列表
    public static final String GET_PRE_LIST = "sys/GetCountyList.do";
    //21历史水情列表 (OK)
    public static final String GET_LSSQ_LIST = "gzt/getLssqlist.do";
    //22历史雨情列表 (OK)
    public static final String GET_YQLS_LIST = "gzt/getyqLslist.do";
    //23巡查命令发送列表 (OK)
    public static final String GET_XCML_FS_LIST = "gzt/xcmlfslist.do";
    //24巡查命令接收列表 (OK)
    public static final String GET_XCML_JS_LIST = "gzt/getxcmljslist.do";
    //25获取登陆用户列表 (OK)
    public static final String GET_USER_LIST = "sys/getUserlist.do";
    //26巡查命令发送 (OK)
    public static final String UPDATE_XCML_FS = "gzt/insertXcmlfs.do";
    //27巡查命令详情 (OK)
    public static final String GET_XCML_INFO = "gzt/getxcmlinfo.do";
    //28回复巡查命令 (OK)
    public static final String UPDATE_XCML = "gzt/updateXcml.do";
    //29创建讨论组及讨论组用户
    public static final String DISCUSS_CREAT = "sys/insertDiscuss.do";
    //30创建人新增论组用户
    public static final String DISCUSS_ADD = "sys/adddisUser.do";
    //31创建人删除论组用户
    public static final String DISCUSS_DELETE = "sys/deletedisUser.do";
    //32讨论组列表
    public static final String DISCUSS_LIST = "sys/getdisculist.do";
    //32讨论组成员列表
    public static final String DISCUSS_USER_LIST = "sys/getdisuserlist.do";
    //33上传线路巡查图片视频
    public static final String SCXL_TPSP = "gzt/scXltpsp.do";
    //33查询临时巡查图片视频列表
    public static final String GET_XL_TPSP_LIST = "gzt/getXltpsplist.do";
    //34巡查记录上报
    public static final String UPDATA_XCSB_LIST = "gzt/insertxcsblist.do";
    //35获取摄像头列表
    public static final String GET_VIDEO_LIST = "gzt/getVideoList.do";
    //36更新巡查命令阅读状态
    public static final String UPDATE_XCML_IS_READ = "gzt/updateXcmlIsRead.do";
    //37用户等级功能权限
    public static final String GET_FUNCTION_LIST = "sys/getFunctionList.do";
    //38新增巡查线路
    public static final String INSERT_XCRY = "gzt/insertXcry.do";
    //39插入巡查点
    public static final String INSERT_XCSB_LIST3 = "gzt/insertxcsblist3.do";
    //40新增巡查记录轨迹
    public static final String INSERT_XCGJ = "gzt/insertXcgj.do";
    //41查询巡查路线轨迹
    public static final String GET_XCGJ_LIST = "gzt/getXcgjList.do";
    //42查询行政区域
    public static final String GET_XZ_LIST = "gzt/getxzList.do";
    //县级预案
    public static final String GET_XJYAN = " gzt/getxyanList.do";
    //43查询镇预案
    public static final String GET_ZYAN_LIST = "gzt/getzyanList.do";
    //44查询村预案
    public static final String GET_CYAN_LIST = "gzt/getcyanList.do";
    //45上传坐标间隔
    public static final String GET_SJJG_LIST = "gzt/getsjjgList.do";
    //46查询巡查类型
    public static final String GET_XCLX_LIST = "gzt/getxclxList.do";
    //47发送巡查命令详情
    public static final String GET_FSXCML_INFO = "gzt/getfsxcmlinfo.do";
    //48删除巡查点
    public static final String DELETE_XCD = "gzt/deleteXcd.do";
    //49删除巡查路线轨迹
    public static final String DELETE_XCLSGJ = "gzt/deleteXclsgj.do";

    //50下载
    public static final String DOWLOAD_FILE = "gzt/dowloadfile.do";

}
