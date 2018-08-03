package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class CheckLineBean {

    /**
     * check :
     * code : Y
     * data : [{"LGTD":"116.31847233016086","LTTD":"40.04079915298939","id":"1712111001380826","logId":"1","mark":"Y","uploadTime":"2017-12-11 10:01:38"},{"LGTD":"116.31847233016086","LTTD":"40.04079915298939","id":"1712111001386293","logId":"1","mark":"Y","uploadTime":"2017-12-11 10:01:38"},{"LGTD":"116.318524","LTTD":"40.041808","id":"1712111000497928","logId":"1","mark":"Y","uploadTime":"2017-12-11 10:00:49"},{"LGTD":"116.318524","LTTD":"40.041808","id":"1712111000480573","logId":"1","mark":"Y","uploadTime":"2017-12-11 10:00:48"}]
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
         * LGTD : 116.31847233016086
         * LTTD : 40.04079915298939
         * id : 1712111001380826
         * logId : 1
         * mark : Y
         * uploadTime : 2017-12-11 10:01:38
         */

        private String LGTD;
        private String LTTD;
        private String id;
        private String logId;
        private String mark;
        private String uploadTime;

        public String getLGTD() {
            return LGTD;
        }

        public void setLGTD(String LGTD) {
            this.LGTD = LGTD;
        }

        public String getLTTD() {
            return LTTD;
        }

        public void setLTTD(String LTTD) {
            this.LTTD = LTTD;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogId() {
            return logId;
        }

        public void setLogId(String logId) {
            this.logId = logId;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(String uploadTime) {
            this.uploadTime = uploadTime;
        }
    }
}
