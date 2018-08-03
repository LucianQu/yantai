package com.jsmy.pingshan.model;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */

public class PointBean {

    /**
     * tid : 1789565
     * dz : ooo
     * jd : 40.042128
     * wd : 116.318166
     * ms : ooooo
     * imag : [{"url":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201712/20171208/fbc8053b1ec842599f3e8ec1cae6a7e9.jpg","type":"2"},{"url":"http://192.168.1.170:8080/pingshan/pictureUpload/2017/201712/20171208/29fe41effc7544ba995cecb04179c390.jpg","type":"2"}]
     */

    private String tid;
    private String dz;
    private String jd;
    private String wd;
    private String ms;
    private List<ImagBean> imag;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public List<ImagBean> getImag() {
        return imag;
    }

    public void setImag(List<ImagBean> imag) {
        this.imag = imag;
    }

    public static class ImagBean {
        /**
         * url : http://192.168.1.170:8080/pingshan/pictureUpload/2017/201712/20171208/fbc8053b1ec842599f3e8ec1cae6a7e9.jpg
         * type : 2
         */

        private String url;
        private String type;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
