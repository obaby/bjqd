package cn.xports.entity;

import java.io.Serializable;

public class App implements Serializable {
    private String appId;
    private String appName;
    private String bundleId;
    private String centerId;
    private String createTime;
    private String ossUrl;
    private String payModes;
    private String platform;
    private String status;
    private String updateTime;

    public String getPayModes() {
        return this.payModes;
    }

    public App setPayModes(String str) {
        this.payModes = str;
        return this;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public void setCenterId(String str) {
        this.centerId = str;
    }

    public String getOssUrl() {
        return this.ossUrl;
    }

    public void setOssUrl(String str) {
        this.ossUrl = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public void setBundleId(String str) {
        this.bundleId = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
