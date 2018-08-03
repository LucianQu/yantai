package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class LineChartBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"adnm":"大乘镇","jcz":"河道水位2","levl":1213.33,"stationTm":"2017-12-27 14:00:00"}]}
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
             * adnm : 大乘镇
             * jcz : 河道水位2
             * levl : 1213.33
             * stationTm : 2017-12-27 14:00:00
             */

            private String adnm;
            private String jcz;
            private String levl;
            private String localTm;

            public String getAdnm() {
                return adnm;
            }

            public void setAdnm(String adnm) {
                this.adnm = adnm;
            }

            public String getJcz() {
                return jcz;
            }

            public void setJcz(String jcz) {
                this.jcz = jcz;
            }

            public String getLevl() {
                return levl;
            }

            public void setLevl(String levl) {
                this.levl = levl;
            }

            public String getLocalTm() {
                return localTm;
            }

            public void setLocalTm(String localTm) {
                this.localTm = localTm;
            }
        }
    }
}
