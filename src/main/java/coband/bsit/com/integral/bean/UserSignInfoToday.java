package coband.bsit.com.integral.bean;

import java.io.Serializable;

public class UserSignInfoToday implements Serializable {
    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public ResultBean getResult() {
        return this.result;
    }

    public void setResult(ResultBean resultBean) {
        this.result = resultBean;
    }

    public static class ResultBean {
        private String lastSignDate;
        private int signcnt;
        private String todaySignFlg;
        private int userid;

        public int getUserid() {
            return this.userid;
        }

        public void setUserid(int i) {
            this.userid = i;
        }

        public int getSigncnt() {
            return this.signcnt;
        }

        public void setSigncnt(int i) {
            this.signcnt = i;
        }

        public String getLastSignDate() {
            return this.lastSignDate;
        }

        public void setLastSignDate(String str) {
            this.lastSignDate = str;
        }

        public String getTodaySignFlg() {
            return this.todaySignFlg;
        }

        public void setTodaySignFlg(String str) {
            this.todaySignFlg = str;
        }
    }
}
