package com.jsmy.pingshan.db;

/**
 * Created by Administrator on 2017/11/7.
 */

public class Point {
    public int id;
    public long tid;
    public String dz;
    public String jd;
    public String wd;
    public String ms;
    public String issb;
    public Point() {

    }

    public Point(long tid, String dz, String jd, String wd,String ms) {
        this.tid = tid;
        this.dz = dz;
        this.jd = jd;
        this.wd = wd;
        this.ms = ms;
    }

    public Point(long tid, String dz, String jd, String wd,String ms,String issb) {
        this.tid = tid;
        this.dz = dz;
        this.jd = jd;
        this.wd = wd;
        this.ms = ms;
        this.issb = issb;
    }

    public String getIssb() {
        return issb;
    }

    public void setIssb(String issb) {
        this.issb = issb;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", tid=" + tid +
                ", dz='" + dz + '\'' +
                ", jd='" + jd + '\'' +
                ", wd='" + wd + '\'' +
                ", ms='" + ms + '\'' +
                ", issb='" + issb + '\'' +
                '}';
    }
}
