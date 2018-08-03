package com.jsmy.pingshan.db;

/**
 * Created by Administrator on 2017/11/7.
 */

public class Image {
    public int id;
    public long did;
    public String url;
    public String type;

    public Image() {

    }

    public Image(int did, String url, String type) {
        this.did = did;
        this.url = url;
        this.type = type;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", did=" + did +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
