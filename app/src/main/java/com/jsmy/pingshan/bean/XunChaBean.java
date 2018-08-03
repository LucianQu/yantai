package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class XunChaBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"beginDt":"2017-11-15 15:11:08","id":"1711152002174506","point":[{"LGTD":39.9145451,"LTTD":116.404018,"address":"雅美","cjid":"1711152002172990","contentDesc":"好好好","img":[{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/514957530f9e43ad8e0b1d1602797d8a.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/061d4f29fdac4ba3b81be51bbc4724f1.mp4","wordType":"1"}]},{"LGTD":39.9099309,"LTTD":116.3970651,"address":"上地","cjid":"1711152002187249","contentDesc":"好好好","img":[{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/061d4f29fdac4ba3b81be51bbc4724f1.mp4","wordType":"1"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/42c6e101c7b64a8ea89ca9ab4adecca7.mp4","wordType":"1"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/514957530f9e43ad8e0b1d1602797d8a.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/36cadf283680449cb803b5d0f713f733.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/6854d759e7d6427ca713e69b5b10b2db.mp4","wordType":"1"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/31141bde587243059537a1ab6e17c397.jpg","wordType":"2"}]}],"titleDesc":"好好好","username":"咖啡豆"}]}
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
             * beginDt : 2017-11-15 15:11:08
             * id : 1711152002174506
             * point : [{"LGTD":39.9145451,"LTTD":116.404018,"address":"雅美","cjid":"1711152002172990","contentDesc":"好好好","img":[{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/514957530f9e43ad8e0b1d1602797d8a.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/061d4f29fdac4ba3b81be51bbc4724f1.mp4","wordType":"1"}]},{"LGTD":39.9099309,"LTTD":116.3970651,"address":"上地","cjid":"1711152002187249","contentDesc":"好好好","img":[{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/061d4f29fdac4ba3b81be51bbc4724f1.mp4","wordType":"1"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/42c6e101c7b64a8ea89ca9ab4adecca7.mp4","wordType":"1"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/514957530f9e43ad8e0b1d1602797d8a.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/36cadf283680449cb803b5d0f713f733.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/6854d759e7d6427ca713e69b5b10b2db.mp4","wordType":"1"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/31141bde587243059537a1ab6e17c397.jpg","wordType":"2"}]}]
             * titleDesc : 好好好
             * username : 咖啡豆
             */

            private String beginDt;
            private String endDt;
            private String id;
            private String titleDesc;
            private String username;
            private List<PointBean> point;

            public String getBeginDt() {
                return beginDt;
            }

            public void setBeginDt(String beginDt) {
                this.beginDt = beginDt;
            }

            public String getEndDt() {
                return endDt;
            }

            public void setEndDt(String endDt) {
                this.endDt = endDt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitleDesc() {
                return titleDesc;
            }

            public void setTitleDesc(String titleDesc) {
                this.titleDesc = titleDesc;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public List<PointBean> getPoint() {
                return point;
            }

            public void setPoint(List<PointBean> point) {
                this.point = point;
            }

            public static class PointBean {
                /**
                 * LGTD : 39.9145451
                 * LTTD : 116.404018
                 * address : 雅美
                 * cjid : 1711152002172990
                 * contentDesc : 好好好
                 * img : [{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/514957530f9e43ad8e0b1d1602797d8a.jpg","wordType":"2"},{"wordPath":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/061d4f29fdac4ba3b81be51bbc4724f1.mp4","wordType":"1"}]
                 */

                private String LGTD;
                private String LTTD;
                private String address;
                private String cjid;
                private String contentDesc;
                private List<ImgBean> img;

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

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCjid() {
                    return cjid;
                }

                public void setCjid(String cjid) {
                    this.cjid = cjid;
                }

                public String getContentDesc() {
                    return contentDesc;
                }

                public void setContentDesc(String contentDesc) {
                    this.contentDesc = contentDesc;
                }

                public List<ImgBean> getImg() {
                    return img;
                }

                public void setImg(List<ImgBean> img) {
                    this.img = img;
                }

                public static class ImgBean {
                    /**
                     * wordPath : http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/514957530f9e43ad8e0b1d1602797d8a.jpg
                     * wordType : 2
                     */

                    private String wordPath;
                    private String wordType;

                    public String getWordPath() {
                        return wordPath;
                    }

                    public void setWordPath(String wordPath) {
                        this.wordPath = wordPath;
                    }

                    public String getWordType() {
                        return wordType;
                    }

                    public void setWordType(String wordType) {
                        this.wordType = wordType;
                    }
                }
            }
        }
    }
}
