package cn.xports.qd2.entity;

import java.io.Serializable;

public class CampaignDetail implements Serializable {
    private String agreementName;
    private String campEndDate;
    private String campId;
    private String campStartDate;
    private String campType;
    private String content;
    private String coverImg;
    private String description;
    private String enrollEndDate;
    private int enrollFee;
    private int enrollNum;
    private String enrollStartDate;
    private String isCharitable;
    private String margin;
    private float maxGroupNum;
    private String place;
    private String status;
    private String title;
    private String type;

    public int getEnrollFee() {
        return this.enrollFee;
    }

    public CampaignDetail setEnrollFee(int i) {
        this.enrollFee = i;
        return this;
    }

    public int getEnrollNum() {
        return this.enrollNum;
    }

    public CampaignDetail setEnrollNum(int i) {
        this.enrollNum = i;
        return this;
    }

    public String getMargin() {
        return this.margin;
    }

    public void setMargin(String str) {
        this.margin = str;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getCampStartDate() {
        return this.campStartDate;
    }

    public void setCampStartDate(String str) {
        this.campStartDate = str;
    }

    public String getIsCharitable() {
        return this.isCharitable;
    }

    public void setIsCharitable(String str) {
        this.isCharitable = str;
    }

    public String getAgreementName() {
        return this.agreementName;
    }

    public void setAgreementName(String str) {
        this.agreementName = str;
    }

    public String getCampEndDate() {
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

    public float getMaxGroupNum() {
        return this.maxGroupNum;
    }

    public void setMaxGroupNum(float f) {
        this.maxGroupNum = f;
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
