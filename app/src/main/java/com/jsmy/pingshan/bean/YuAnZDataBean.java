package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class YuAnZDataBean {

    /**
     * check :
     * code : Y
     * data : [{"area":"","department":"","docImg":"","docImgName":"","docName":"关于提交2017年总结2018年计划的通知.pdf","docUrl":"D:\\pinshan\\fldBase\\WebRoot\\upload","dt":"","fileSaveName":"20180111155148.pdf","id":"1801111551490004","levl":3,"morefileName":"","morefileUrl":"http://117.139.109.76:8899/fldBase/upload/20180111175525.docx","name":"asfdew","regionId":"511529100000000"}]
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
         * docName : 关于提交2017年总结2018年计划的通知.pdf
         * docUrl :
         * dt :
         * fileSaveName : 20180111155148.pdf
         * id : 1801111551490004
         * levl : 3
         * morefileName :
         * morefileUrl : http://117.139.109.76:8899/fldBase/upload/20180111175525.docx
         * name : asfdew
         * regionId : 511529100000000
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
        private String morefileUrl;
        private String name;
        private String regionId;

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

        public String getMorefileUrl() {
            return morefileUrl;
        }

        public void setMorefileUrl(String morefileUrl) {
            this.morefileUrl = morefileUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegionId() {
            return regionId;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }
    }
}
