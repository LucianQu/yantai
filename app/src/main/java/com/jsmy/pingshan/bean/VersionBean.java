package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class VersionBean {

    /**
     * check :
     * code : Y
     * data : [{"Uptime":"201-11-02","versionCode":"1","versionName":"1.0.0","versionUrl":"www.baidu.com"}]
     * msg : 操作成功！
     */

    private String check;
    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Uptime : 201-11-02
         * versionCode : 1
         * versionName : 1.0.0
         * versionUrl : www.baidu.com
         */

        private String Uptime;
        private String versionCode;
        private String versionName;
        private String versionUrl;

        public String getUptime() {
            return Uptime;
        }

        public void setUptime(String Uptime) {
            this.Uptime = Uptime;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionUrl() {
            return versionUrl;
        }

        public void setVersionUrl(String versionUrl) {
            this.versionUrl = versionUrl;
        }
    }
}
