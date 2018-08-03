package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class QiangianFrament2Bean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"lgtd2":"123.2","lttd2":"123.1","qxrs":10,"zid":"511529000000000","zmc":"屏山县"},{"lgtd2":"125.2","lttd2":"63.2","qxrs":45,"zid":"511529103000000","zmc":"龙华镇"},{"lgtd2":"123.6","lttd2":"23.6","qxrs":5,"zid":"511529105000000","zmc":"福延镇"}]}
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
             * lgtd2 : 123.2
             * lttd2 : 123.1
             * qxrs : 10
             * zid : 511529000000000
             * zmc : 屏山县
             */

            private String lgtd2;
            private String lttd2;
            private String qxrs;
            private String zid;
            private String zmc;

            public String getLgtd2() {
                return lgtd2;
            }

            public void setLgtd2(String lgtd2) {
                this.lgtd2 = lgtd2;
            }

            public String getLttd2() {
                return lttd2;
            }

            public void setLttd2(String lttd2) {
                this.lttd2 = lttd2;
            }

            public String getQxrs() {
                return qxrs;
            }

            public void setQxrs(String qxrs) {
                this.qxrs = qxrs;
            }

            public String getZid() {
                return zid;
            }

            public void setZid(String zid) {
                this.zid = zid;
            }

            public String getZmc() {
                return zmc;
            }

            public void setZmc(String zmc) {
                this.zmc = zmc;
            }
        }
    }
}
