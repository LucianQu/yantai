package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ImagBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"id":"1711151431274405","url":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/52021c45a6ea491bb475d218a0c6f77d.jpg","wordType":"2"},{"id":"1711151431274757","url":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/82a7b7f315b44fab9b75ca339f0ee6c6.mp4","wordType":"1"},{"id":"1711151431277788","url":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/c4d00ae2813d45b087f1146da8d564c2.jpg","wordType":"2"},{"id":"1711151431277923","url":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/bef6d5fbcf1d40b6befed0cbdffd519f.mp4","wordType":"1"}]}
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
             * id : 1711151431274405
             * url : http://192.168.1.170:8080/pingshan/pictureUpload/2017/201711/20171115/52021c45a6ea491bb475d218a0c6f77d.jpg
             * wordType : 2
             */

            private String id;
            private String url;
            private String wordType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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
