package cn.xports.qd2.entity;

import java.io.Serializable;

public class StudentInfo implements Serializable {
    private String addingTime;
    private String birthday;
    private String cardNo;
    private String centerId;
    private String custId;
    private String email;
    private String gender;
    private String height;
    private String instId;
    private String interest;
    private String level;
    private String manageVenueId;
    private String phone;
    private String photo;
    private String selfTag;
    private String stuId;
    private String stuName;

    public String getStuId() {
        return this.stuId;
    }

    public void setStuId(String str) {
        this.stuId = str;
    }

    public String getInstId() {
        return this.instId;
    }

    public void setInstId(String str) {
        this.instId = str;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String str) {
        this.stuName = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getInterest() {
        return this.interest;
    }

    public void setInterest(String str) {
        this.interest = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getAddingTime() {
        return this.addingTime;
    }

    public void setAddingTime(String str) {
        this.addingTime = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getCustId() {
        return this.custId;
    }

    public void setCustId(String str) {
        this.custId = str;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getSelfTag() {
        return this.selfTag;
    }

    public void setSelfTag(String str) {
        this.selfTag = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public void setCenterId(String str) {
        this.centerId = str;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public String getManageVenueId() {
        return this.manageVenueId;
    }

    public void setManageVenueId(String str) {
        this.manageVenueId = str;
    }
}
