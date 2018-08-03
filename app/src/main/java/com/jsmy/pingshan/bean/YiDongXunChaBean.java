package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YiDongXunChaBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301811026696","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301811417661","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301811423888","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301811425795","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301811436145","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301811439804","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301812575968","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:3","inStatus":"1","jlid":"1710301813013825","titleDesc":"","username":"张三"},{"beginDt":"2017-10-3018:13","inStatus":"1","jlid":"1710301820100287","titleDesc":"","username":"铁玉"},{"beginDt":"2017-10-3018:13","inStatus":"1","jlid":"1710301821477537","titleDesc":"","username":"铁玉"},{"beginDt":"2017-10-3018:13","inStatus":"1","jlid":"1710301823001923","titleDesc":"","username":"铁玉"},{"beginDt":"2017-10-3018:13","inStatus":"1","jlid":"1710301824029096","titleDesc":"","username":"铁玉"},{"beginDt":"2017-10-3018:19","inStatus":"1","jlid":"1710301825513766","titleDesc":"","username":"经济"},{"beginDt":"2017-10-3018:21","inStatus":"1","jlid":"1710301828103340","titleDesc":"","username":"ii"},{"beginDt":"2017-10-319:25","inStatus":"1","jlid":"1710310931285866","titleDesc":"","username":"看看"},{"beginDt":"2017-10-319:26","inStatus":"1","jlid":"1710310932558707","titleDesc":"","username":"哦哦"},{"beginDt":"2017-10-319:33","inStatus":"1","jlid":"1710310940106857","titleDesc":"","username":"III"},{"beginDt":"2017-10-3110:11","inStatus":"1","jlid":"1710311017597571","titleDesc":"","username":"匹配"},{"beginDt":"2017-10-3110:11","inStatus":"1","jlid":"1710311020238937","titleDesc":"","username":"匹配"},{"beginDt":"2017-10-3110:11","inStatus":"1","jlid":"1710311020467583","titleDesc":"","username":"匹配"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311021358996","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311021468587","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311034232579","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311035167655","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311036015743","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311036124246","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311036243383","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3110:15","inStatus":"1","jlid":"1710311038077924","titleDesc":"","username":"哟哟哟"},{"beginDt":"2017-10-3116:58","inStatus":"1","jlid":"1710311704599426","titleDesc":"","username":"cc"},{"beginDt":"2017-10-3116:59","inStatus":"1","jlid":"1710311706165447","titleDesc":"","username":"vv"},{"beginDt":"2017-10-3116:59","inStatus":"1","jlid":"1710311710136210","titleDesc":"","username":"vv"},{"beginDt":"2017-10-3117:40","inStatus":"1","jlid":"1710311747058535","titleDesc":"","username":"ym"}]}
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
             * beginDt : 2017-10-3018:3
             * inStatus : 1
             * jlid : 1710301811026696
             * titleDesc :
             * username : 张三
             */

            private String beginDt;
            private String inStatus;
            private String jlid;
            private String titleDesc;
            private String username;

            public String getBeginDt() {
                return beginDt;
            }

            public void setBeginDt(String beginDt) {
                this.beginDt = beginDt;
            }

            public String getInStatus() {
                return inStatus;
            }

            public void setInStatus(String inStatus) {
                this.inStatus = inStatus;
            }

            public String getJlid() {
                return jlid;
            }

            public void setJlid(String jlid) {
                this.jlid = jlid;
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
        }
    }
}
