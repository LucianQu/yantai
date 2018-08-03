package com.jsmy.pingshan.bean;

/**
 * Created by Administrator on 2017/11/4.
 */

public class ForecastInfo {
    private String date;
    private String skycon;
    private String temper;
    private String wind;

    public ForecastInfo(String date, String skycon, String temper, String wind) {
        this.date = date;
        this.skycon = skycon;
        this.temper = temper;
        this.wind = wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSkycon() {
        return skycon;
    }

    public void setSkycon(String skycon) {
        this.skycon = skycon;
    }

    public String getTemper() {
        return temper;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
