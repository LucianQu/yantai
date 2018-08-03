package com.test.demo;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class VideoBean {

    /**
     * check :
     * code : Y
     * data : [{"NM":"1","address":"","adnm":"屏山县","url":"http://117.139.109.76:8500"},{"NM":"2","address":"2","adnm":"新市镇","url":"http://117.139.109.76:8501"},{"NM":"3","address":"","adnm":"屏山县","url":"http://117.139.109.76:8502"},{"NM":"4","address":"","adnm":"屏山县","url":"http://117.139.109.76:8503"},{"NM":"5","address":"","adnm":"屏山县","url":"http://117.139.109.76:8504"},{"NM":"6","address":"","adnm":"屏山县","url":"http://117.139.109.76:8505"}]
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
         * NM : 1
         * address :
         * adnm : 屏山县
         * url : http://117.139.109.76:8500
         */

        private String NM;
        private String address;
        private String adnm;
        private String url;

        public String getNM() {
            return NM;
        }

        public void setNM(String NM) {
            this.NM = NM;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAdnm() {
            return adnm;
        }

        public void setAdnm(String adnm) {
            this.adnm = adnm;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
