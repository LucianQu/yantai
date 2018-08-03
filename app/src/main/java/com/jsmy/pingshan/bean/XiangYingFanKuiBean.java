package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class XiangYingFanKuiBean {

    /**
     * check :
     * code : Y
     * data : [{"adnm":"鸭池乡","collapseHous":500,"dead":1,"distractTotal":12345,"distracted":12345,"docName":"","endDt":"2017-11-16 14:31","fksj":"2017-11-16 14:31","missing":13,"rescuePerson":265,"siegeTotal":23,"sieged":9245,"startDt":"2017-11-16 14:20","undistract":0,"unsiege":12,"workGpPerson":12,"xydj":"立即转移","xymc":"测试预警20171116"}]
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
         * adnm : 鸭池乡
         * collapseHous : 500
         * dead : 1
         * distractTotal : 12345
         * distracted : 12345
         * docName :
         * endDt : 2017-11-16 14:31
         * fksj : 2017-11-16 14:31
         * missing : 13
         * rescuePerson : 265
         * siegeTotal : 23
         * sieged : 9245
         * startDt : 2017-11-16 14:20
         * undistract : 0
         * unsiege : 12
         * workGpPerson : 12
         * xydj : 立即转移
         * xymc : 测试预警20171116
         */

        private String adnm;
        private String collapseHous;
        private String dead;
        private String distractTotal;
        private String distracted;
        private String docName;
        private String endDt;
        private String fksj;
        private String missing;
        private String rescuePerson;
        private String siegeTotal;
        private String sieged;
        private String startDt;
        private String undistract;
        private String unsiege;
        private String workGpPerson;
        private String xydj;
        private String xymc;
        private String workGp;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getWorkGp() {
            return workGp;
        }

        public void setWorkGp(String workGp) {
            this.workGp = workGp;
        }

        public String getAdnm() {
            return adnm;
        }

        public void setAdnm(String adnm) {
            this.adnm = adnm;
        }

        public String getCollapseHous() {
            return collapseHous;
        }

        public void setCollapseHous(String collapseHous) {
            this.collapseHous = collapseHous;
        }

        public String getDead() {
            return dead;
        }

        public void setDead(String dead) {
            this.dead = dead;
        }

        public String getDistractTotal() {
            return distractTotal;
        }

        public void setDistractTotal(String distractTotal) {
            this.distractTotal = distractTotal;
        }

        public String getDistracted() {
            return distracted;
        }

        public void setDistracted(String distracted) {
            this.distracted = distracted;
        }

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public String getEndDt() {
            return endDt;
        }

        public void setEndDt(String endDt) {
            this.endDt = endDt;
        }

        public String getFksj() {
            return fksj;
        }

        public void setFksj(String fksj) {
            this.fksj = fksj;
        }

        public String getMissing() {
            return missing;
        }

        public void setMissing(String missing) {
            this.missing = missing;
        }

        public String getRescuePerson() {
            return rescuePerson;
        }

        public void setRescuePerson(String rescuePerson) {
            this.rescuePerson = rescuePerson;
        }

        public String getSiegeTotal() {
            return siegeTotal;
        }

        public void setSiegeTotal(String siegeTotal) {
            this.siegeTotal = siegeTotal;
        }

        public String getSieged() {
            return sieged;
        }

        public void setSieged(String sieged) {
            this.sieged = sieged;
        }

        public String getStartDt() {
            return startDt;
        }

        public void setStartDt(String startDt) {
            this.startDt = startDt;
        }

        public String getUndistract() {
            return undistract;
        }

        public void setUndistract(String undistract) {
            this.undistract = undistract;
        }

        public String getUnsiege() {
            return unsiege;
        }

        public void setUnsiege(String unsiege) {
            this.unsiege = unsiege;
        }

        public String getWorkGpPerson() {
            return workGpPerson;
        }

        public void setWorkGpPerson(String workGpPerson) {
            this.workGpPerson = workGpPerson;
        }

        public String getXydj() {
            return xydj;
        }

        public void setXydj(String xydj) {
            this.xydj = xydj;
        }

        public String getXymc() {
            return xymc;
        }

        public void setXymc(String xymc) {
            this.xymc = xymc;
        }
    }
}
