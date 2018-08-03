package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ZhiHuiRenYuanBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"dh":"15856891245","dw":"水荫","xm":"攻城狮B","zw":"2541"},{"dh":"15945866788","dw":"水荫","xm":"桂雪玉","zw":"2541"}]}
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
             * dh : 15856891245
             * dw : 水荫
             * xm : 攻城狮B
             * zw : 2541
             */

            private String dh;
            private String dw;
            private String xm;
            private String zw;

            public String getDh() {
                return dh;
            }

            public void setDh(String dh) {
                this.dh = dh;
            }

            public String getDw() {
                return dw;
            }

            public void setDw(String dw) {
                this.dw = dw;
            }

            public String getXm() {
                return xm;
            }

            public void setXm(String xm) {
                this.xm = xm;
            }

            public String getZw() {
                return zw;
            }

            public void setZw(String zw) {
                this.zw = zw;
            }
        }
    }
}
