package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class XCLXBean {

    /**
     * check :
     * code : Y
     * data : [{"id":"1000000000000001","sortNo":"001","tp":"灾前巡查"},{"id":"1000000000000002","sortNo":"002","tp":"灾中巡查"},{"id":"1000000000000003","sortNo":"003","tp":"灾后巡查"},{"id":"1000000000000004","sortNo":"004","tp":"日常巡查"}]
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
         * id : 1000000000000001
         * sortNo : 001
         * tp : 灾前巡查
         */

        private String id;
        private String sortNo;
        private String tp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSortNo() {
            return sortNo;
        }

        public void setSortNo(String sortNo) {
            this.sortNo = sortNo;
        }

        public String getTp() {
            return tp;
        }

        public void setTp(String tp) {
            this.tp = tp;
        }
    }
}
