package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class YuJingBean3 {

    /**
     * check :
     * code : Y
     * data : [{"ADNM":"锦屏镇","id":"1711160956370119","jb":1,"jd":"","nr":"锦屏镇发生立即转移雨量预警：东城社区发生立即转移雨量预警。南城社区发生立即转移雨量预警。","regionId":"511529100000000","sj":"2017-11-16 09:56:37","type":"1","wd":""},{"ADNM":"屏山县","id":"1712121632250023","jb":2,"jd":"","nr":"准备转移","regionId":"511529000000000","sj":"2017-12-12 16:32:14","type":"2","wd":""}]
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
         * ADNM : 锦屏镇
         * id : 1711160956370119
         * jb : 1
         * jd :
         * nr : 锦屏镇发生立即转移雨量预警：东城社区发生立即转移雨量预警。南城社区发生立即转移雨量预警。
         * regionId : 511529100000000
         * sj : 2017-11-16 09:56:37
         * type : 1
         * wd :
         */

        private String ADNM;
        private String id;
        private String jb;
        private String jd;
        private String nr;
        private String regionId;
        private String sj;
        private String type;
        private String wd;

        public String getADNM() {
            return ADNM;
        }

        public void setADNM(String ADNM) {
            this.ADNM = ADNM;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJb() {
            return jb;
        }

        public void setJb(String jb) {
            this.jb = jb;
        }

        public String getJd() {
            return jd;
        }

        public void setJd(String jd) {
            this.jd = jd;
        }

        public String getNr() {
            return nr;
        }

        public void setNr(String nr) {
            this.nr = nr;
        }

        public String getRegionId() {
            return regionId;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        public String getSj() {
            return sj;
        }

        public void setSj(String sj) {
            this.sj = sj;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }
    }
}
