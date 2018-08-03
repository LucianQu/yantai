package com.jsmy.pingshan.bean;

/**
 * Created by Administrator on 2018/1/5.
 */

public class SendMLDataBean {

    /**
     * check :
     * code : Y
     * data : {"fsid":"1801051552126238","jsName":"言语","replyContent":"","sendContent":"手机睡觉睡觉","sendTime":"2018-01-05 15:52:12","userid":"1710310950270058"}
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
         * fsid : 1801051552126238
         * jsName : 言语
         * replyContent :
         * sendContent : 手机睡觉睡觉
         * sendTime : 2018-01-05 15:52:12
         * userid : 1710310950270058
         */

        private String fsid;
        private String jsName;
        private String replyContent;
        private String sendContent;
        private String sendTime;
        private String userid;

        public String getFsid() {
            return fsid;
        }

        public void setFsid(String fsid) {
            this.fsid = fsid;
        }

        public String getJsName() {
            return jsName;
        }

        public void setJsName(String jsName) {
            this.jsName = jsName;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getSendContent() {
            return sendContent;
        }

        public void setSendContent(String sendContent) {
            this.sendContent = sendContent;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
