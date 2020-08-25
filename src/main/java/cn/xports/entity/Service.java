package cn.xports.entity;

import java.io.Serializable;

public class Service implements Serializable {
    private String bookTag;
    private String endDate;
    private int fieldTag;
    private boolean isCheck = false;
    private String picUrl;
    private String serviceId;
    private String serviceName;
    private String serviceType;
    private String startDate;

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public String getBookTag() {
        return this.bookTag;
    }

    public void setBookTag(String str) {
        this.bookTag = str;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public int getFieldTag() {
        return this.fieldTag;
    }

    public void setFieldTag(int i) {
        this.fieldTag = i;
    }
}
