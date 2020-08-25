package cn.xports.entity;

import java.io.Serializable;

public class TrainCourse implements Serializable {
    private String address;
    private String centerId;
    private String centerName;
    private String classTag;
    private String courseDesc;
    private String courseEnrolled;
    private String courseId;
    private String courseName;
    private String coursePicUrl;
    private String courseTypeName;
    private String endDate;
    private String instId;
    private String latitude;
    private int lessonNum;
    private String longitude;
    private int maxPurchaseNum;
    private int minPurchaseNum;
    private String originalPrice;
    private int price;
    private String privateCourseEnrolled;
    private String privateTag;
    private int promPrice;
    private String serviceId;
    private String serviceName;
    private String startDate;
    private String suitPerson;
    private String suitPersonName;
    private String trainingType;
    private String validPeriod;
    private String venueId;
    private String venueName;

    public int getMinPurchaseNum() {
        return this.minPurchaseNum;
    }

    public TrainCourse setMinPurchaseNum(int i) {
        this.minPurchaseNum = i;
        return this;
    }

    public int getMaxPurchaseNum() {
        return this.maxPurchaseNum;
    }

    public TrainCourse setMaxPurchaseNum(int i) {
        this.maxPurchaseNum = i;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public int getPromPrice() {
        return this.promPrice;
    }

    public void setPromPrice(int i) {
        this.promPrice = i;
    }

    public String getCoursePicUrl() {
        return this.coursePicUrl;
    }

    public void setCoursePicUrl(String str) {
        this.coursePicUrl = str;
    }

    public boolean isPrivate() {
        return "1".equals(this.privateTag);
    }

    public boolean isNeedClass() {
        return "1".equals(this.classTag);
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String str) {
        this.courseId = str;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String str) {
        this.courseName = str;
    }

    public String getInstId() {
        return this.instId;
    }

    public void setInstId(String str) {
        this.instId = str;
    }

    public String getCourseDesc() {
        return this.courseDesc;
    }

    public void setCourseDesc(String str) {
        this.courseDesc = str;
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

    public int getLessonNum() {
        return this.lessonNum;
    }

    public void setLessonNum(int i) {
        this.lessonNum = i;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public String getPrivateTag() {
        return this.privateTag;
    }

    public void setPrivateTag(String str) {
        this.privateTag = str;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public String getValidPeriod() {
        return this.validPeriod;
    }

    public void setValidPeriod(String str) {
        this.validPeriod = str;
    }

    public String getTrainingType() {
        return this.trainingType;
    }

    public void setTrainingType(String str) {
        this.trainingType = str;
    }

    public String getClassTag() {
        return this.classTag;
    }

    public void setClassTag(String str) {
        this.classTag = str;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public String getCourseTypeName() {
        return this.courseTypeName;
    }

    public void setCourseTypeName(String str) {
        this.courseTypeName = str;
    }

    public String getSuitPerson() {
        return this.suitPerson;
    }

    public void setSuitPerson(String str) {
        this.suitPerson = str;
    }

    public String getSuitPersonName() {
        return this.suitPersonName;
    }

    public void setSuitPersonName(String str) {
        this.suitPersonName = str;
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

    public String getVenueName() {
        return this.venueName;
    }

    public void setVenueName(String str) {
        this.venueName = str;
    }

    public String getCenterName() {
        return this.centerName;
    }

    public void setCenterName(String str) {
        this.centerName = str;
    }

    public String getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(String str) {
        this.originalPrice = str;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public void setCenterId(String str) {
        this.centerId = str;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public void setVenueId(String str) {
        this.venueId = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }
}
