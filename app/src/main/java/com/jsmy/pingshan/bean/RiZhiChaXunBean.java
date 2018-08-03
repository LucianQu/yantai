package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14.
 */

public class RiZhiChaXunBean {

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

            private String yhm;
            private String ip;
            private String dldt;

            public ListBean(String yhm, String ip, String dldt) {
                this.yhm = yhm;
                this.ip = ip;
                this.dldt = dldt;
            }

            public String getYhm() {
                return yhm;
            }

            public void setYhm(String yhm) {
                this.yhm = yhm;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getDldt() {
                return dldt;
            }

            public void setDldt(String dldt) {
                this.dldt = dldt;
            }
        }
    }
}
