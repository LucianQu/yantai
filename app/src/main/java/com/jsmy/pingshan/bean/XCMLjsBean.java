package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public class XCMLjsBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"isRead":"N","jsid":"1711011227173806","sendContent":"明天水库大坝见","sendName":"黄药师","sendTime":"2017-11-01 12:27:03"}]}
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
             * isRead : N
             * jsid : 1711011227173806
             * sendContent : 明天水库大坝见
             * sendName : 黄药师
             * sendTime : 2017-11-01 12:27:03
             */

            private String isRead;
            private String jsid;
            private String sendContent;
            private String sendName;
            private String sendTime;

            public String getIsRead() {
                return isRead;
            }

            public void setIsRead(String isRead) {
                this.isRead = isRead;
            }

            public String getJsid() {
                return jsid;
            }

            public void setJsid(String jsid) {
                this.jsid = jsid;
            }

            public String getSendContent() {
                return sendContent;
            }

            public void setSendContent(String sendContent) {
                this.sendContent = sendContent;
            }

            public String getSendName() {
                return sendName;
            }

            public void setSendName(String sendName) {
                this.sendName = sendName;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }
        }
    }
}
