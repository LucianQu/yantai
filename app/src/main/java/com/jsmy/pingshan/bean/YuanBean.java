package com.jsmy.pingshan.bean;

import java.util.List;

public class YuanBean {

    /**
     * code : 1
     * data : {"list":[{"list2":[],"zid":"370600000000000","zmc":"烟台市"}]}
     * msg : 操作成功！
     */

    private String code;
    private DataBean data;
    private String msg;

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
             * list2 : []
             * zid : 370600000000000
             * zmc : 烟台市
             */

            private String zid;
            private String zmc;
            private List<?> list2;

            public String getZid() {
                return zid;
            }

            public void setZid(String zid) {
                this.zid = zid;
            }

            public String getZmc() {
                return zmc;
            }

            public void setZmc(String zmc) {
                this.zmc = zmc;
            }

            public List<?> getList2() {
                return list2;
            }

            public void setList2(List<?> list2) {
                this.list2 = list2;
            }
        }
    }
}

