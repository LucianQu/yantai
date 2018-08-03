package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YuQingBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"STTP":"PP","adnm":"中都镇","jcz":"测试1","jczwz":"","jczwzx":28.662777,"jczwzy":103.847881,"jjyl":20,"stationDt":"2017-12-27 15:54:00","stationId":"1711012015270009","wxyl":30,"yjjb":"","yjyl":"","yl":16.6},{"STTP":"PP","adnm":"新市镇","jcz":"测试6","jczwz":"","jczwzx":28.682046,"jczwzy":104.147987,"jjyl":20,"stationDt":"2017-12-27 15:53:00","stationId":"1711021047040042","wxyl":40,"yjjb":"","yjyl":"","yl":16.6},{"STTP":"PP","adnm":"龙华镇","jcz":"测试4","jczwz":"","jczwzx":28.766176,"jczwzy":104.296315,"jjyl":"","stationDt":"","stationId":"1711021042110023","wxyl":"","yjjb":"","yjyl":"","yl":""},{"STTP":"PP","adnm":"大乘镇","jcz":"测试3","jczwz":"","jczwzx":28.661763,"jczwzy":104.121541,"jjyl":"","stationDt":"","stationId":"1711020937280006","wxyl":"","yjjb":"","yjyl":"","yl":""},{"STTP":"PP","adnm":"锦屏镇","jcz":"测试8","jczwz":"","jczwzx":28.72083,"jczwzy":103.962864,"jjyl":"","stationDt":"","stationId":"1711021059250082","wxyl":"","yjjb":"","yjyl":"","yl":""},{"STTP":"PP","adnm":"锦屏镇","jcz":"测试2","jczwz":"","jczwzx":28.661763,"jczwzy":104.182482,"jjyl":"","stationDt":"","stationId":"1711020900430002","wxyl":"","yjjb":"","yjyl":"","yl":""},{"STTP":"PP","adnm":"新市镇","jcz":"新市","jczwz":"新市镇镇政府楼顶","jczwzx":28.661215,"jczwzy":103.836612,"jjyl":"","stationDt":"","stationId":"","wxyl":"","yjjb":"","yjyl":"","yl":""},{"STTP":"PR","adnm":"中都镇","jcz":"中都水库","jczwz":"B地址","jczwzx":28.7717633,"jczwzy":104.384482,"jjyl":"","stationDt":"","stationId":"1712131041340023","wxyl":"","yjjb":"","yjyl":"","yl":""},{"STTP":"PP","adnm":"夏溪乡","jcz":"测试12","jczwz":"","jczwzx":"","jczwzy":"","jjyl":"","stationDt":"","stationId":"1711101131370006","wxyl":"","yjjb":"","yjyl":"","yl":""}]}
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
             * STTP : PP
             * adnm : 中都镇
             * jcz : 测试1
             * jczwz :
             * jczwzx : 28.662777
             * jczwzy : 103.847881
             * jjyl : 20
             * stationDt : 2017-12-27 15:54:00
             * stationId : 1711012015270009
             * wxyl : 30
             * yjjb :
             * yjyl :
             * yl : 16.6
             */

            private String STTP;
            private String adnm;
            private String jcz;
            private String jczwz;
            private String jczwzx;
            private String jczwzy;
            private String jjyl;
            private String localDt  ;
            private String stationId;
            private String wxyl;
            private String yjjb;
            private String yjyl;
            private String yl;

            public String getSTTP() {
                return STTP;
            }

            public void setSTTP(String STTP) {
                this.STTP = STTP;
            }

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

            public String getJczwz() {
                return jczwz;
            }

            public void setJczwz(String jczwz) {
                this.jczwz = jczwz;
            }

            public String getJczwzx() {
                return jczwzx;
            }

            public void setJczwzx(String jczwzx) {
                this.jczwzx = jczwzx;
            }

            public String getJczwzy() {
                return jczwzy;
            }

            public void setJczwzy(String jczwzy) {
                this.jczwzy = jczwzy;
            }

            public String getJjyl() {
                return jjyl;
            }

            public void setJjyl(String jjyl) {
                this.jjyl = jjyl;
            }

            public String getLocalDt() {
                return localDt;
            }

            public void setLocalDt(String localDt) {
                this.localDt = localDt;
            }

            public String getStationId() {
                return stationId;
            }

            public void setStationId(String stationId) {
                this.stationId = stationId;
            }

            public String getWxyl() {
                return wxyl;
            }

            public void setWxyl(String wxyl) {
                this.wxyl = wxyl;
            }

            public String getYjjb() {
                return yjjb;
            }

            public void setYjjb(String yjjb) {
                this.yjjb = yjjb;
            }

            public String getYjyl() {
                return yjyl;
            }

            public void setYjyl(String yjyl) {
                this.yjyl = yjyl;
            }

            public String getYl() {
                return yl;
            }

            public void setYl(String yl) {
                this.yl = yl;
            }
        }
    }
}
