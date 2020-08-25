package cn.xports.entity;

import java.io.Serializable;

public class CampaignInfo implements Serializable {
    private String bmEnrolledNum;
    private String campEndDate;
    private String campId;
    private String campStartDate;
    private String campType;
    private String centerId;
    private String coverImg;
    private String createTime;
    private String description;
    private String enrollEndDate;
    private String enrollStartDate;
    private String enrolledNum;
    private String isCharitable;
    private String place;
    private String status;
    private String title;
    private String updateStaffId;
    private String updateTime;

    public String getCenterId() {
        return this.centerId;
    }

    public void setCenterId(String str) {
        this.centerId = str;
    }

    public String getCoverImg() {
        return this.coverImg;
    }

    public void setCoverImg(String str) {
        this.coverImg = str;
    }

    public String getCampId() {
        return this.campId;
    }

    public void setCampId(String str) {
        this.campId = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getUpdateStaffId() {
        return this.updateStaffId;
    }

    public void setUpdateStaffId(String str) {
        this.updateStaffId = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getEnrolledNum() {
        return this.enrolledNum;
    }

    public void setEnrolledNum(String str) {
        this.enrolledNum = str;
    }

    public String getIsCharitable() {
        return this.isCharitable;
    }

    public void setIsCharitable(String str) {
        this.isCharitable = str;
    }

    public String getCampStartDate() {
        if (this.campStartDate == null) {
            return "";
        }
        return this.campStartDate;
    }

    public void setCampStartDate(String str) {
        this.campStartDate = str;
    }

    public String getBmEnrolledNum() {
        return this.bmEnrolledNum;
    }

    public void setBmEnrolledNum(String str) {
        this.bmEnrolledNum = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getCampEndDate() {
        if (this.campEndDate == null) {
            return "";
        }
        return this.campEndDate;
    }

    public void setCampEndDate(String str) {
        this.campEndDate = str;
    }

    public String getCampType() {
        return this.campType;
    }

    public void setCampType(String str) {
        this.campType = str;
    }

    public String getEnrollEndDate() {
        return this.enrollEndDate;
    }

    public void setEnrollEndDate(String str) {
        this.enrollEndDate = str;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String str) {
        this.place = str;
    }

    public String getEnrollStartDate() {
        return this.enrollStartDate;
    }

    public void setEnrollStartDate(String str) {
        this.enrollStartDate = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
