package com.jsmy.pingshan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YuJingBean implements Serializable {


    /**
     * check :
     * code : Y
     * data : [{"ADNM":"楼东乡","effects":"1711101313290005,1711101313290007,1711101313300009,1711101313300011,1711101313300013,1711101313300015,1711101313300017,1711101313300019,1711101313300021,1711101313300023,1711101313300025,1711101313300027,","latestContent":"楼东乡发生立即转移雨量预警：桂花村发生立即转移雨量预警。石岗村发生立即转移雨量预警。西村发生立即转移雨量预警。书楼村发生立即转移雨量预警。田坝村发生立即转移雨量预警。莲花村发生立即转移雨量预警。芭蕉村发生立即转移雨量预警。沙坝村发生立即转移雨量预警。场镇居委会发生立即转移雨量预警。高田村发生立即转移雨量预警。月坡村发生立即转移雨量预警。宝宁村发生立即转移雨量预警。","latestDt":"2017-11-10 13:51:25","latestLevl":1,"list":[{"adcd":"511529202208000","cmc":"桂花村","dt":"2017-11-10 13:13:30","id":"1711101313290005","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202207000","cmc":"石岗村","dt":"2017-11-10 13:13:30","id":"1711101313290007","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202205000","cmc":"西村","dt":"2017-11-10 13:13:30","id":"1711101313300009","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202206000","cmc":"书楼村","dt":"2017-11-10 13:13:30","id":"1711101313300011","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202201000","cmc":"田坝村","dt":"2017-11-10 13:13:30","id":"1711101313300013","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202210000","cmc":"莲花村","dt":"2017-11-10 13:13:30","id":"1711101313300015","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202211000","cmc":"芭蕉村","dt":"2017-11-10 13:13:30","id":"1711101313300017","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202203000","cmc":"沙坝村","dt":"2017-11-10 13:13:30","id":"1711101313300019","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202001000","cmc":"场镇居委会","dt":"2017-11-10 13:13:30","id":"1711101313300021","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202209000","cmc":"高田村","dt":"2017-11-10 13:13:30","id":"1711101313300023","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202204000","cmc":"月坡村","dt":"2017-11-10 13:13:30","id":"1711101313300025","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202202000","cmc":"宝宁村","dt":"2017-11-10 13:13:30","id":"1711101313300027","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"}],"zid":"511529202000000"}]
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
         * ADNM : 楼东乡
         * effects : 1711101313290005,1711101313290007,1711101313300009,1711101313300011,1711101313300013,1711101313300015,1711101313300017,1711101313300019,1711101313300021,1711101313300023,1711101313300025,1711101313300027,
         * latestContent : 楼东乡发生立即转移雨量预警：桂花村发生立即转移雨量预警。石岗村发生立即转移雨量预警。西村发生立即转移雨量预警。书楼村发生立即转移雨量预警。田坝村发生立即转移雨量预警。莲花村发生立即转移雨量预警。芭蕉村发生立即转移雨量预警。沙坝村发生立即转移雨量预警。场镇居委会发生立即转移雨量预警。高田村发生立即转移雨量预警。月坡村发生立即转移雨量预警。宝宁村发生立即转移雨量预警。
         * latestDt : 2017-11-10 13:51:25
         * latestLevl : 1
         * list : [{"adcd":"511529202208000","cmc":"桂花村","dt":"2017-11-10 13:13:30","id":"1711101313290005","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202207000","cmc":"石岗村","dt":"2017-11-10 13:13:30","id":"1711101313290007","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202205000","cmc":"西村","dt":"2017-11-10 13:13:30","id":"1711101313300009","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202206000","cmc":"书楼村","dt":"2017-11-10 13:13:30","id":"1711101313300011","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202201000","cmc":"田坝村","dt":"2017-11-10 13:13:30","id":"1711101313300013","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202210000","cmc":"莲花村","dt":"2017-11-10 13:13:30","id":"1711101313300015","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202211000","cmc":"芭蕉村","dt":"2017-11-10 13:13:30","id":"1711101313300017","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202203000","cmc":"沙坝村","dt":"2017-11-10 13:13:30","id":"1711101313300019","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202001000","cmc":"场镇居委会","dt":"2017-11-10 13:13:30","id":"1711101313300021","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202209000","cmc":"高田村","dt":"2017-11-10 13:13:30","id":"1711101313300023","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202204000","cmc":"月坡村","dt":"2017-11-10 13:13:30","id":"1711101313300025","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"},{"adcd":"511529202202000","cmc":"宝宁村","dt":"2017-11-10 13:13:30","id":"1711101313300027","latestLevl":"立即转移","list":[{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}],"stationIds":"1711091042400002,","type":"1"}]
         * zid : 511529202000000
         */

        private String ADNM;
        private String effects;
        private String latestContent;
        private String latestDt;
        private String latestLevl;
        private String zid;
        private List<ListBeanX> list;

        public String getADNM() {
            return ADNM;
        }

        public void setADNM(String ADNM) {
            this.ADNM = ADNM;
        }

        public String getEffects() {
            return effects;
        }

        public void setEffects(String effects) {
            this.effects = effects;
        }

        public String getLatestContent() {
            return latestContent;
        }

        public void setLatestContent(String latestContent) {
            this.latestContent = latestContent;
        }

        public String getLatestDt() {
            return latestDt;
        }

        public void setLatestDt(String latestDt) {
            this.latestDt = latestDt;
        }

        public String getLatestLevl() {
            return latestLevl;
        }

        public void setLatestLevl(String latestLevl) {
            this.latestLevl = latestLevl;
        }

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * adcd : 511529202208000
             * cmc : 桂花村
             * dt : 2017-11-10 13:13:30
             * id : 1711101313290005
             * latestLevl : 立即转移
             * list : [{"LGTD":"","LTTD":"","STNM":"测试10","latestyl":"1h雨量41.5mm"}]
             * stationIds : 1711091042400002,
             * type : 1
             */

            private String adcd;
            private String cmc;
            private String dt;
            private String id;
            private String latestLevl;
            private String stationIds;
            private String type;
            private List<ListBean> list;

            public String getAdcd() {
                return adcd;
            }

            public void setAdcd(String adcd) {
                this.adcd = adcd;
            }

            public String getCmc() {
                return cmc;
            }

            public void setCmc(String cmc) {
                this.cmc = cmc;
            }

            public String getDt() {
                return dt;
            }

            public void setDt(String dt) {
                this.dt = dt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLatestLevl() {
                return latestLevl;
            }

            public void setLatestLevl(String latestLevl) {
                this.latestLevl = latestLevl;
            }

            public String getStationIds() {
                return stationIds;
            }

            public void setStationIds(String stationIds) {
                this.stationIds = stationIds;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * LGTD :
                 * LTTD :
                 * STNM : 测试10
                 * latestyl : 1h雨量41.5mm
                 */

                private String LGTD;
                private String LTTD;
                private String STNM;
                private String latestyl;

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

                public String getSTNM() {
                    return STNM;
                }

                public void setSTNM(String STNM) {
                    this.STNM = STNM;
                }

                public String getLatestyl() {
                    return latestyl;
                }

                public void setLatestyl(String latestyl) {
                    this.latestyl = latestyl;
                }
            }
        }
    }
}
