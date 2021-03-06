package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class TaoLunZuBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"discussId":"101","discussName":"BBC","zrs":5}]}
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
             * discussId : 101
             * discussName : BBC
             * zrs : 5
             */

            private String discussId;
            private String discussName;
            private String zrs;

            public String getDiscussId() {
                return discussId;
            }

            public void setDiscussId(String discussId) {
                this.discussId = discussId;
            }

            public String getDiscussName() {
                return discussName;
            }

            public void setDiscussName(String discussName) {
                this.discussName = discussName;
            }

            public String getZrs() {
                return zrs;
            }

            public void setZrs(String zrs) {
                this.zrs = zrs;
            }
        }
    }
}
