package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class KuCunFramentBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"list":[{"adnm":"城关镇","cid":"621027100203000","cmc":"高庄村","cwzx":"125.36","cwzy":"35.62","located":"东边仓库","person":"王三","sdt":14,"tc":15,"tel":"13645894587","yx":13,"yy":12},{"adnm":"城关镇","cid":"621027100207000","cmc":"金龙村","cwzx":"123.52","cwzy":"32.68","located":"西边仓库","person":"王一","sdt":140,"tc":150,"tel":"13645894688","yx":130,"yy":120}],"sdt":154,"tc":165,"yx":143,"yy":132,"zid":"621027100000000","zmc":"城关镇"},{"list":[{"adnm":"临泾镇","cid":"621027200200107","cmc":"耿塬","cwzx":"122.48","cwzy":"31.70","located":"南边仓库","person":"王二","sdt":21,"tc":8,"tel":"13645894599","yx":13,"yy":9}],"sdt":21,"tc":8,"yx":13,"yy":9,"zid":"621027200000000","zmc":"临泾镇"}]}
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
             * list : [{"adnm":"城关镇","cid":"621027100203000","cmc":"高庄村","cwzx":"125.36","cwzy":"35.62","located":"东边仓库","person":"王三","sdt":14,"tc":15,"tel":"13645894587","yx":13,"yy":12},{"adnm":"城关镇","cid":"621027100207000","cmc":"金龙村","cwzx":"123.52","cwzy":"32.68","located":"西边仓库","person":"王一","sdt":140,"tc":150,"tel":"13645894688","yx":130,"yy":120}]
             * sdt : 154
             * tc : 165
             * yx : 143
             * yy : 132
             * zid : 621027100000000
             * zmc : 城关镇
             */

            private String sdt;
            private String tc;
            private String yx;
            private String yy;
            private String zid;
            private String zmc;
            private List<ListBean> list;

            public String getSdt() {
                return sdt;
            }

            public void setSdt(String sdt) {
                this.sdt = sdt;
            }

            public String getTc() {
                return tc;
            }

            public void setTc(String tc) {
                this.tc = tc;
            }

            public String getYx() {
                return yx;
            }

            public void setYx(String yx) {
                this.yx = yx;
            }

            public String getYy() {
                return yy;
            }

            public void setYy(String yy) {
                this.yy = yy;
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
                 * adnm : 城关镇
                 * cid : 621027100203000
                 * cmc : 高庄村
                 * cwzx : 125.36
                 * cwzy : 35.62
                 * located : 东边仓库
                 * person : 王三
                 * sdt : 14
                 * tc : 15
                 * tel : 13645894587
                 * yx : 13
                 * yy : 12
                 */

                private String adnm;
                private String cid;
                private String cmc;
                private String cwzx;
                private String cwzy;
                private String located;
                private String person;
                private String sdt;
                private String tc;
                private String tel;
                private String yx;
                private String yy;

                public String getAdnm() {
                    return adnm;
                }

                public void setAdnm(String adnm) {
                    this.adnm = adnm;
                }

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

                public String getLocated() {
                    return located;
                }

                public void setLocated(String located) {
                    this.located = located;
                }

                public String getPerson() {
                    return person;
                }

                public void setPerson(String person) {
                    this.person = person;
                }

                public String getSdt() {
                    return sdt;
                }

                public void setSdt(String sdt) {
                    this.sdt = sdt;
                }

                public String getTc() {
                    return tc;
                }

                public void setTc(String tc) {
                    this.tc = tc;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public String getYx() {
                    return yx;
                }

                public void setYx(String yx) {
                    this.yx = yx;
                }

                public String getYy() {
                    return yy;
                }

                public void setYy(String yy) {
                    this.yy = yy;
                }
            }

        }

    }

}
