package com.jsmy.pingshan.bean;

import java.util.List;

public class PreBean {

    /**
     * code : 1
     * data : {"list":[{"regionCd":"370600000000000","regionNm":"烟台市"},{"regionCd":"370611000000000","regionNm":"福山区"},{"regionCd":"370612000000000","regionNm":"牟平区"},{"regionCd":"370613000000000","regionNm":"莱山区"},{"regionCd":"370684000000000","regionNm":"城区"}]}
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
             * regionCd : 370600000000000
             * regionNm : 烟台市
             */

            private String regionCd;
            private String regionNm;

            public String getRegionCd() {
                return regionCd;
            }

            public void setRegionCd(String regionCd) {
                this.regionCd = regionCd;
            }

            public String getRegionNm() {
                return regionNm;
            }

            public void setRegionNm(String regionNm) {
                this.regionNm = regionNm;
            }
        }
    }
}
