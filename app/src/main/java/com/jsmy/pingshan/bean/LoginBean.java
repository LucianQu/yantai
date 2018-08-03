package com.jsmy.pingshan.bean;

/**
 * Created by Administrator on 2017/10/13.
 */

public class LoginBean {

    /**
     * check :
     * code : Y
     * data : {"dw":"","id":"1710310950270235","img":"","level":"1","tel":"13656895686","token":"qDBtd0LA44+8ombiULqsEqgE7ObsYxdvUCLbAeX8MgGjxnkdzSjCwKiXaUNMorX98yC3Kpiu/lfz+aWfir7yl1zCSHjyCBV8LGzFNJxvM5Q=","xm":"咖啡豆","zh":"user1","zw":""}
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
        /**
         * dw :
         * id : 1710310950270235
         * img :
         * level : 1
         * tel : 13656895686
         * token : qDBtd0LA44+8ombiULqsEqgE7ObsYxdvUCLbAeX8MgGjxnkdzSjCwKiXaUNMorX98yC3Kpiu/lfz+aWfir7yl1zCSHjyCBV8LGzFNJxvM5Q=
         * xm : 咖啡豆
         * zh : user1
         * zw :
         */

        private String dw;
        private String id;
        private String img;
        private String level;
        private String tel;
        private String token;
        private String xm;
        private String zh;
        private String zw;

        public String getDw() {
            return dw;
        }

        public void setDw(String dw) {
            this.dw = dw;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }

        public String getZw() {
            return zw;
        }

        public void setZw(String zw) {
            this.zw = zw;
        }
    }
}
