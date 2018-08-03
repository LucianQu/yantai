package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class QiangianFramentBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"list":[{"cid":"621027100200100","cmc":"五里沟","cwzx":"120.36","cwzy":"35.62","qxrs":13},{"cid":"621027100201100","cmc":"水荫","cwzx":"115.36","cwzy":"28.36","qxrs":16}],"qxrs":29,"zid":"621027100000000","zmc":"城关镇"},{"list":[{"cid":"621027105200000","cmc":"陈坪村","cwzx":"120.25","cwzy":"44.36","qxrs":20},{"cid":"621027105200100","cmc":"下洼","cwzx":"125.55","cwzy":"51.6","qxrs":30},{"cid":"621027105200101","cmc":"陈沟","cwzx":"120.56","cwzy":"32.56","qxrs":12},{"cid":"621027105200102","cmc":"王沟","cwzx":"119.52","cwzy":"29.25","qxrs":8}],"qxrs":70,"zid":"621027105000000","zmc":"开边镇"},{"list":[{"cid":"621027102211100","cmc":"赵咀","cwzx":"125.25","cwzy":"32.36","qxrs":10},{"cid":"621027102211101","cmc":"董咀","cwzx":"126.32","cwzy":"31.32","qxrs":13},{"cid":"621027102211102","cmc":"北山","cwzx":"125.32","cwzy":"30.23","qxrs":15},{"cid":"621027102211103","cmc":"南庄","cwzx":"125.35","cwzy":"29.23","qxrs":16},{"cid":"621027102211104","cmc":"何范","cwzx":"125.56","cwzy":"35.25","qxrs":20},{"cid":"621027102211105","cmc":"周洼","cwzx":"130.54","cwzy":"34.3","qxrs":30}],"qxrs":104,"zid":"621027102000000","zmc":"孟坝镇"},{"list":[{"cid":"621027104215108","cmc":"陈洼","cwzx":"140.25","cwzy":"34.2","qxrs":40},{"cid":"621027104215109","cmc":"田峁","cwzx":"130.5","cwzy":"31.5","qxrs":35}],"qxrs":75,"zid":"621027104000000","zmc":"平泉镇"}]}
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
        private List<ListBeanX> list;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * list : [{"cid":"621027100200100","cmc":"五里沟","cwzx":"120.36","cwzy":"35.62","qxrs":13},{"cid":"621027100201100","cmc":"水荫","cwzx":"115.36","cwzy":"28.36","qxrs":16}]
             * qxrs : 29
             * zid : 621027100000000
             * zmc : 城关镇
             */

            private String qxrs;
            private String zid;
            private String zmc;
            private List<ListBean> list;

            public String getQxrs() {
                return qxrs;
            }

            public void setQxrs(String qxrs) {
                this.qxrs = qxrs;
            }

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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * cid : 621027100200100
                 * cmc : 五里沟
                 * cwzx : 120.36
                 * cwzy : 35.62
                 * qxrs : 13
                 */

                private String cid;
                private String cmc;
                private String cwzx;
                private String cwzy;
                private String qxrs;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getCmc() {
                    return cmc;
                }

                public void setCmc(String cmc) {
                    this.cmc = cmc;
                }

                public String getCwzx() {
                    return cwzx;
                }

                public void setCwzx(String cwzx) {
                    this.cwzx = cwzx;
                }

                public String getCwzy() {
                    return cwzy;
                }

                public void setCwzy(String cwzy) {
                    this.cwzy = cwzy;
                }

                public String getQxrs() {
                    return qxrs;
                }

                public void setQxrs(String qxrs) {
                    this.qxrs = qxrs;
                }
            }
        }
    }
}
