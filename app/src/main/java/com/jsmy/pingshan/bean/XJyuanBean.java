package com.jsmy.pingshan.bean;

import java.util.List;

public class XJyuanBean {

    /**
     * code : 1
     * data : [{"area":"","department":"海阳市人民政府防汛抗旱指挥部办公室","docImg":"","docImgName":"","docName":"海阳市山洪灾害防御预案.doc","dt":"2013-04-10","id":"1304290959420019","levl":1,"morefileName":"","morefileUrl":"","name":"山洪灾害防御预案","regionId":"370600000000000"}]
     * msg : 操作成功！
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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
         * department : 海阳市人民政府防汛抗旱指挥部办公室
         * docImg :
         * docImgName :
         * docName : 海阳市山洪灾害防御预案.doc
         * dt : 2013-04-10
         * id : 1304290959420019
         * levl : 1
         * morefileName :
         * morefileUrl :
         * name : 山洪灾害防御预案
         * regionId : 370600000000000
         */

        private String area;
        private String department;
        private String docImg;
        private String docImgName;
        private String docName;
        private String dt;
        private String id;
        private int levl;
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

        public int getLevl() {
            return levl;
        }

        public void setLevl(int levl) {
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
