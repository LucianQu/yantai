package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */

public class KunCunFrament2Bean {

    /**
     * check :
     * code : Y
     * data : [{"ADNM":"屏山县","lgtd2":"123.6","located":"gg","lttd2":"25.321","number":10,"person":"小明111","tel":"13701284925","wzid":"1710271619510002","wzmc":"雨衣"},{"ADNM":"鸭池乡","lgtd2":"121.6","located":"","lttd2":"24.321","number":16,"person":"","tel":"","wzid":"1710311328260007","wzmc":"我爱方便面"},{"ADNM":"锦屏镇","lgtd2":"120.6","located":"","lttd2":"25.365","number":69,"person":"","tel":"","wzid":"1710271622120011","wzmc":"雨伞"}]
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
         * ADNM : 屏山县
         * lgtd2 : 123.6
         * located : gg
         * lttd2 : 25.321
         * number : 10
         * person : 小明111
         * tel : 13701284925
         * wzid : 1710271619510002
         * wzmc : 雨衣
         */

        private String ADNM;
        private String lgtd2;
        private String located;
        private String lttd2;
        private String number;
        private String person;
        private String tel;
        private String wzid;
        private String wzmc;

        public String getADNM() {
            return ADNM;
        }

        public void setADNM(String ADNM) {
            this.ADNM = ADNM;
        }

        public String getLgtd2() {
            return lgtd2;
        }

        public void setLgtd2(String lgtd2) {
            this.lgtd2 = lgtd2;
        }

        public String getLocated() {
            return located;
        }

        public void setLocated(String located) {
            this.located = located;
        }

        public String getLttd2() {
            return lttd2;
        }

        public void setLttd2(String lttd2) {
            this.lttd2 = lttd2;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getWzid() {
            return wzid;
        }

        public void setWzid(String wzid) {
            this.wzid = wzid;
        }

        public String getWzmc() {
            return wzmc;
        }

        public void setWzmc(String wzmc) {
            this.wzmc = wzmc;
        }
    }
}
