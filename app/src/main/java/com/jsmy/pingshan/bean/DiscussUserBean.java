package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class DiscussUserBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"createId":"1710310950270235","userId":"1710310950270058","userName":"言语"},{"createId":"1710310950270235","userId":"1710310950270011","userName":"23"},{"createId":"1710310950270235","userId":"1710310950270235","userName":"咖啡豆"},{"createId":"1710310950270235","userId":"1710310950270185","userName":"卡卡"}]}
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
             * createId : 1710310950270235
             * userId : 1710310950270058
             * userName : 言语
             */

            private String createId;
            private String userId;
            private String userName;

            public String getCreateId() {
                return createId;
            }

            public void setCreateId(String createId) {
                this.createId = createId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
