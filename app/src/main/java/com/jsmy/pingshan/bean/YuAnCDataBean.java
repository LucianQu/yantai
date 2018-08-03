package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class YuAnCDataBean {

    /**
     * check :
     * code : Y
     * data : [{"area":"","department":"","docImg":"","docImgName":"","docName":"推流曲线数据模板 (1).xlsx","docUrl":"","dt":"","fileSaveName":"","id":"1712191111350006","levl":3,"morefileName":"","name":"14","ruralId":"511529100201000"}]
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
         * area :
         * department :
         * docImg :
         * docImgName :
         * docName : 推流曲线数据模板 (1).xlsx
         * docUrl :
         * dt :
         * fileSaveName :
         * id : 1712191111350006
         * levl : 3
         * morefileName :
         * name : 14
         * ruralId : 511529100201000
         */

        private String area;
        private String department;
        private String docImg;
        private String docImgName;
        private String docName;
        private String docUrl;
        private String dt;
        private String fileSaveName;
        private String id;
        private String levl;
        private String morefileName;
        private String name;
        private String ruralId;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDocImg() {
            return docImg;
        }

        public void setDocImg(String docImg) {
            this.docImg = docImg;
        }

        public String getDocImgName() {
            return docImgName;
        }

        public void setDocImgName(String docImgName) {
            this.docImgName = docImgName;
        }

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public String getDocUrl() {
            return docUrl;
        }

        public void setDocUrl(String docUrl) {
            this.docUrl = docUrl;
        }

        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
        }

        public String getFileSaveName() {
            return fileSaveName;
        }

        public void setFileSaveName(String fileSaveName) {
            this.fileSaveName = fileSaveName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevl() {
            return levl;
        }

        public void setLevl(String levl) {
            this.levl = levl;
        }

        public String getMorefileName() {
            return morefileName;
        }

        public void setMorefileName(String morefileName) {
            this.morefileName = morefileName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRuralId() {
            return ruralId;
        }

        public void setRuralId(String ruralId) {
            this.ruralId = ruralId;
        }
    }
}
