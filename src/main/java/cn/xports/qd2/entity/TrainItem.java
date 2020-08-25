package cn.xports.qd2.entity;

import java.io.Serializable;

public class TrainItem implements Serializable {
    private String centerName;
    private String courseEnrolled;
    private String courseId;
    private String courseName;
    private String coursePicUrl;
    private String courseType;
    private String endDate;
    private String latitude;
    private int lessonNum;
    private String longitude;
    private String originalPrice;
    private int price;
    private String privateCourseEnrolled;
    private String privateTag;
    private String startDate;
    private Integer subjectPrice;
    private String suitPerson;
    private String validPeriod;
    private String venueId;
    private String venueName;

    public Integer getSubjectPrice() {
        return this.subjectPrice;
    }

    public TrainItem setSubjectPrice(Integer num) {
        this.subjectPrice = num;
        return this;
    }

    public int getLessonNum() {
        return this.lessonNum;
    }

    public void setLessonNum(int i) {
        this.lessonNum = i;
    }

    public String getCourseType() {
        return this.courseType;
    }

    public void setCourseType(String str) {
        this.courseType = str;
    }

    public String getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(String str) {
        this.originalPrice = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getValidPeriod() {
        return this.validPeriod;
    }

    public void setValidPeriod(String str) {
        this.validPeriod = str;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public void setVenueName(String str) {
        this.venueName = str;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String str) {
        this.courseName = str;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public void setVenueId(String str) {
        this.venueId = str;
    }

    public String getSuitPerson() {
        return this.suitPerson;
    }

    public void setSuitPerson(String str) {
        this.suitPerson = str;
    }

    public String getPrivateTag() {
        return this.privateTag;
    }

    public void setPrivateTag(String str) {
        this.privateTag = str;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String str) {
        this.courseId = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getCenterName() {
        return this.centerName;
    }

    public void setCenterName(String str) {
        this.centerName = str;
    }

    public String getCoursePicUrl() {
        return this.coursePicUrl;
    }

    public void setCoursePicUrl(String str) {
        this.coursePicUrl = str;
    }

    public String getCourseEnrolled() {
        return this.courseEnrolled;
    }

    public void setCourseEnrolled(String str) {
        this.courseEnrolled = str;
    }

    public String getPrivateCourseEnrolled() {
        return this.privateCourseEnrolled;
    }

    public void setPrivateCourseEnrolled(String str) {
        this.privateCourseEnrolled = str;
    }

    public boolean isPrivate() {
        return "1".equals(this.privateTag);
    }
}
