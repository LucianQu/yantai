package com.jsmy.pingshan.bean;

/**
 * Created by Administrator on 2017/12/8.
 */

public class LineBean {

    /**
     * check :
     * code : Y
     * data : {}
     * msg : 新增巡查线路成功!
     * xcId : 1712081732262231
     */

    private String check;
    private String code;
    private DataBean data;
    private String msg;
    private String xcId;

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

    public String getXcId() {
        return xcId;
    }

    public void setXcId(String xcId) {
        this.xcId = xcId;
    }

    public static class DataBean {
    }
}
