package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14.
 */

public class ShuiQingBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"STTP":"ZZ","adnm":"大乘镇","jcz":"河道水位2","jczwz":"C地址","jczwzx":28.621186,"jczwzy":103.962864,"jxsw":"","ll":"","localTm":"","stationId":"1711021050060075","sw":"","trend":"","wxsw":"","yjjb":""},{"STTP":"ZZ","adnm":"新市镇","jcz":"河道水位1","jczwz":"A地址","jczwzx":28.78036,"jczwzy":104.030704,"jxsw":1220,"ll":"","localTm":"2017-12-27 14:00:01","stationId":"1711021045260037","sw":1211.13,"trend":"5","wxsw":1225,"yjjb":""},{"STTP":"PR","adnm":"中都镇","jcz":"中都水库","jczwz":"B地址","jczwzx":28.7717633,"jczwzy":104.384482,"jxsw":50.85,"ll":100.35,"localTm":"2017-12-27 9:56","stationId":"1712131041340023","sw":50.36,"trend":"","wxsw":51.25,"yjjb":""}]}
     * msg : 操作成功！
     */

    private String check;
    private String code;
    private DataBean data;
    private String msg;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * STTP : ZZ
             * adnm : 大乘镇
             * jcz : 河道水位2
             * jczwz : C地址
             * jczwzx : 28.621186
             * jczwzy : 103.962864
             * jxsw :
             * ll :
             * localTm :
             * stationId : 1711021050060075
             * sw :
             * trend :
             * wxsw :
             * yjjb :
             */

            private String STTP;
            private String adnm;
            private String jcz;
            private String jczwz;
            private String jczwzx;
            private String jczwzy;
            private String jxsw;
            private String ll;
            private String localTm;
            private String stationId;
            private String sw;
            private String trend;
            private String wxsw;
            private String yjjb;

            public String getSTTP() {
                return STTP;
            }

            public void setSTTP(String STTP) {
                this.STTP = STTP;
            }

            public String getAdnm() {
                return adnm;
            }

            public void setAdnm(String adnm) {
                this.adnm = adnm;
            }

            public String getJcz() {
                return jcz;
            }

            public void setJcz(String jcz) {
                this.jcz = jcz;
            }

            public String getJczwz() {
                return jczwz;
            }

            public void setJczwz(String jczwz) {
                this.jczwz = jczwz;
            }

            public String getJczwzx() {
                return jczwzx;
            }

            public void setJczwzx(String jczwzx) {
                this.jczwzx = jczwzx;
            }

            public String getJczwzy() {
                return jczwzy;
            }

            public void setJczwzy(String jczwzy) {
                this.jczwzy = jczwzy;
            }

            public String getJxsw() {
                return jxsw;
            }

            public void setJxsw(String jxsw) {
                this.jxsw = jxsw;
            }

            public String getLl() {
                return ll;
            }

            public void setLl(String ll) {
                this.ll = ll;
            }

            public String getLocalTm() {
                return localTm;
            }

            public void setLocalTm(String localTm) {
                this.localTm = localTm;
            }

            public String getStationId() {
                return stationId;
            }

            public void setStationId(String stationId) {
                this.stationId = stationId;
            }

            public String getSw() {
                return sw;
            }

            public void setSw(String sw) {
                this.sw = sw;
            }

            public String getTrend() {
                return trend;
            }

            public void setTrend(String trend) {
                this.trend = trend;
            }

            public String getWxsw() {
                return wxsw;
            }

            public void setWxsw(String wxsw) {
                this.wxsw = wxsw;
            }

            public String getYjjb() {
                return yjjb;
            }

            public void setYjjb(String yjjb) {
                this.yjjb = yjjb;
            }
        }
    }
}
