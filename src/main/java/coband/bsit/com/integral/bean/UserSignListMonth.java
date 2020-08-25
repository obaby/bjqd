package coband.bsit.com.integral.bean;

import java.io.Serializable;
import java.util.List;

public class UserSignListMonth implements Serializable {
    private int code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return this.result;
    }

    public void setResult(List<ResultBean> list) {
        this.result = list;
    }

    public static class ResultBean {
        private String credate;
        private Object curMonth;
        private int id;
        private String logicdelete;
        private String moddate;
        private Object signEndDate;
        private Object signStartDate;
        private String signTime;
        private int userid;

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public int getUserid() {
            return this.userid;
        }

        public void setUserid(int i) {
            this.userid = i;
        }

        public String getSignTime() {
            return this.signTime;
        }

        public void setSignTime(String str) {
            this.signTime = str;
        }

        public String getCredate() {
            return this.credate;
        }

        public void setCredate(String str) {
            this.credate = str;
        }

        public String getModdate() {
            return this.moddate;
        }

        public void setModdate(String str) {
            this.moddate = str;
        }

        public Object getCurMonth() {
            return this.curMonth;
        }

        public void setCurMonth(Object obj) {
            this.curMonth = obj;
        }

        public Object getSignStartDate() {
            return this.signStartDate;
        }

        public void setSignStartDate(Object obj) {
            this.signStartDate = obj;
        }

        public Object getSignEndDate() {
            return this.signEndDate;
        }

        public void setSignEndDate(Object obj) {
            this.signEndDate = obj;
        }

        public String getLogicdelete() {
            return this.logicdelete;
        }

        public void setLogicdelete(String str) {
            this.logicdelete = str;
        }
    }
}
