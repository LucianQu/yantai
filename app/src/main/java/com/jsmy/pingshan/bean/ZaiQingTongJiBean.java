package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class ZaiQingTongJiBean {

    /**
     * check :
     * code : Y
     * data : [{"dtfw":2,"id":"1801101304270012","jsdt":"2018-01-10 13:03","missp":3,"qsdt":"2018-01-09 13:04","swrs":1,"zqms":"龙华镇测试","zqtjmc":"龙华镇灾情"}]
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
         * dtfw : 2
         * id : 1801101304270012
         * jsdt : 2018-01-10 13:03
         * missp : 3
         * qsdt : 2018-01-09 13:04
         * swrs : 1
         * zqms : 龙华镇测试
         * zqtjmc : 龙华镇灾情
         */

        private String dtfw;
        private String id;
        private String jsdt;
        private String missp;
        private String qsdt;
        private String swrs;
        private String zqms;
        private String zqtjmc;

        public String getDtfw() {
            return dtfw;
        }

        public void setDtfw(String dtfw) {
            this.dtfw = dtfw;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJsdt() {
            return jsdt;
        }

        public void setJsdt(String jsdt) {
            this.jsdt = jsdt;
        }

        public String getMissp() {
            return missp;
        }

        public void setMissp(String missp) {
            this.missp = missp;
        }

        public String getQsdt() {
            return qsdt;
        }

        public void setQsdt(String qsdt) {
            this.qsdt = qsdt;
        }

        public String getSwrs() {
            return swrs;
        }

        public void setSwrs(String swrs) {
            this.swrs = swrs;
        }

        public String getZqms() {
            return zqms;
        }

        public void setZqms(String zqms) {
            this.zqms = zqms;
        }

        public String getZqtjmc() {
            return zqtjmc;
        }

        public void setZqtjmc(String zqtjmc) {
            this.zqtjmc = zqtjmc;
        }
    }
}
