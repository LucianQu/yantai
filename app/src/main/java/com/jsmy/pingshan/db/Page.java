package com.jsmy.pingshan.db;

/**
 * Created by Administrator on 2017/11/7.
 */

public class Page {
    public int id;
    public long fid;
    public String user;
    public String beginDt;
    public String endDt;
    public String inTypeCode;
    public String inTypeName;
    public String ms;
    public String issb;

    public Page() {
    }

    public Page(long fid, String user, String beginDt, String endDt, String inTypeCode, String inTypeName, String ms, String issb) {
        this.fid = fid;
        this.user = user;
        this.beginDt = beginDt;
        this.endDt = endDt;
        this.inTypeCode = inTypeCode;
        this.inTypeName = inTypeName;
        this.ms = ms;
        this.issb = issb;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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

    public String getInTypeCode() {
        return inTypeCode;
    }

    public void setInTypeCode(String inTypeCode) {
        this.inTypeCode = inTypeCode;
    }

    public String getInTypeName() {
        return inTypeName;
    }

    public void setInTypeName(String inTypeName) {
        this.inTypeName = inTypeName;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getIssb() {
        return issb;
    }

    public void setIssb(String issb) {
        this.issb = issb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", fid=" + fid +
                ", user='" + user + '\'' +
                ", beginDt='" + beginDt + '\'' +
                ", endDt='" + endDt + '\'' +
                ", inTypeCode='" + inTypeCode + '\'' +
                ", inTypeName='" + inTypeName + '\'' +
                ", ms='" + ms + '\'' +
                ", issb='" + issb + '\'' +
                '}';
    }
}
