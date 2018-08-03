package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */

public class FuntionBean {

    /**
     * check :
     * code : Y
     * data : [{"function":"雨情","id":"10001","number":"1"},{"function":"水情","id":"10002","number":"2"},{"function":"移动巡查","id":"10003","number":"3"},{"function":"定位","id":"10004","number":"4"},{"function":"气象信息","id":"10005","number":"5"},{"function":"预案管理","id":"10006","number":"6"},{"function":"移动办公","id":"10007","number":"7"},{"function":"视频监控","id":"10008","number":"8"},{"function":"视频会商","id":"10009","number":"9"},{"function":"巡查命令","id":"10010","number":"10"}]
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
         * function : 雨情
         * id : 10001
         * number : 1
         */

        private String function;
        private String id;
        private String number;

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
