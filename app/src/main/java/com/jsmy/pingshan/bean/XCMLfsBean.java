package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public class XCMLfsBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"isRead":"N","jsrmc":"朱广彦","jsrzs":6,"sendContent":"明天是个好天气","sendTime":"2017-10-30 17:41:02","sendid":"15669999","ydrzs":2},{"isRead":"N","jsrmc":"欧阳锋","jsrzs":6,"sendContent":"明天有大雨，加紧巡查","sendTime":"2017-10-30 17:41:02","sendid":"1710301735539673","ydrzs":2},{"isRead":"N","jsrmc":"欧阳锋","jsrzs":6,"sendContent":"明天有领导下来检查，请各位巡查员认真执行!","sendTime":"2017-10-30 17:41:02","sendid":"1710301741022454","ydrzs":2},{"isRead":"N","jsrmc":"欧阳锋","jsrzs":6,"sendContent":"明天有领导下来检查，请各位巡查员认真执行!66666666","sendTime":"2017-10-30 18:45:08","sendid":"1710301845083454","ydrzs":2},{"isRead":"Y","jsrmc":"欧阳锋","jsrzs":6,"sendContent":"明天有领导下来检查，请各位巡查员认真执行!666666668888888","sendTime":"2017-10-30 18:50:03","sendid":"1710301850033401","ydrzs":2},{"isRead":"Y","jsrmc":"黄药师","jsrzs":6,"sendContent":"明天有领导下来检查，请各位巡查员认真执行!666666668888888","sendTime":"2017-10-30 18:50:03","sendid":"1710301850033401","ydrzs":2}]}
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
             * jsrmc : 朱广彦
             * jsrzs : 6
             * sendContent : 明天是个好天气
             * sendTime : 2017-10-30 17:41:02
             * sendid : 15669999
             * ydrzs : 2
             */

            private String isRead;
            private String jsrmc;
            private String jsrzs;
            private String sendContent;
            private String sendTime;
            private String sendid;
            private String ydrzs;

            public String getIsRead() {
                return isRead;
            }

            public void setIsRead(String isRead) {
                this.isRead = isRead;
            }

            public String getJsrmc() {
                return jsrmc;
            }

            public void setJsrmc(String jsrmc) {
                this.jsrmc = jsrmc;
            }

            public String getJsrzs() {
                return jsrzs;
            }

            public void setJsrzs(String jsrzs) {
                this.jsrzs = jsrzs;
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

            public String getSendid() {
                return sendid;
            }

            public void setSendid(String sendid) {
                this.sendid = sendid;
            }

            public String getYdrzs() {
                return ydrzs;
            }

            public void setYdrzs(String ydrzs) {
                this.ydrzs = ydrzs;
            }
        }
    }
}
