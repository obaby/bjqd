package cn.xports.qd2.entity;

import java.io.Serializable;
import java.util.List;

public class CoursePromInfo extends Option implements Serializable {
    private String endDate;
    private String limitNum;
    private List<PromDetail> promDetail;
    private String promId;
    private String promName;
    private String startData;

    public String getPromId() {
        return this.promId;
    }

    public void setPromId(String str) {
        this.promId = str;
    }

    public String getPromName() {
        return this.promName;
    }

    public void setPromName(String str) {
        this.promName = str;
    }

    public String getStartData() {
        return this.startData;
    }

    public void setStartData(String str) {
        this.startData = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getLimitNum() {
        return this.limitNum;
    }

    public void setLimitNum(String str) {
        this.limitNum = str;
    }

    public List<PromDetail> getPromDetail() {
        return this.promDetail;
    }

    public void setPromDetail(List<PromDetail> list) {
        this.promDetail = list;
    }

    public static class PromDetail implements Serializable {
        private String elementDesc;
        private String elementName;
        private String elementNum;
        private String elementType;
        private String elementValue;

        public String getElementNum() {
            return this.elementNum;
        }

        public void setElementNum(String str) {
            this.elementNum = str;
        }

        public String getElementValue() {
            return this.elementValue;
        }

        public void setElementValue(String str) {
            this.elementValue = str;
        }

        public String getElementType() {
            return this.elementType;
        }

        public void setElementType(String str) {
            this.elementType = str;
        }

        public String getElementDesc() {
            return this.elementDesc;
        }

        public void setElementDesc(String str) {
            this.elementDesc = str;
        }

        public String getElementName() {
            return this.elementName;
        }

        public void setElementName(String str) {
            this.elementName = str;
        }
    }
}
