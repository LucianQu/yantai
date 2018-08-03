package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/22.
 */

public class PersonBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"dh":"","userName":"欧阳锋","userid":"1603311643290006","zh":"oyf"},{"dh":"15689562366","userName":"黄药师","userid":"1604021423090026","zh":"hys"},{"dh":"15294412364","userName":"朱广彦","userid":"1605101654390004","zh":"aaa"},{"dh":"15985486532","userName":"夜观天象","userid":"1606061511570005","zh":"admin"}]}
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
             * dh :
             * userName : 欧阳锋
             * userid : 1603311643290006
             * zh : oyf
             */

            private String dh;
            private String userName;
            private String userid;
            private String zh;

            public ListBean(String dh, String userName) {
                this.dh = dh;
                this.userName = userName;
            }

            public String getDh() {
                return dh;
            }

            public void setDh(String dh) {
                this.dh = dh;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getZh() {
                return zh;
            }

            public void setZh(String zh) {
                this.zh = zh;
            }
        }
    }
}
