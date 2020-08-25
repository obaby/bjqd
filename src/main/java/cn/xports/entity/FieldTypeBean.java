package cn.xports.entity;

import java.io.Serializable;

public class FieldTypeBean implements Serializable {
    private int bookHour;
    private String endDate;
    private Long fieldCnt;
    private int fieldType;
    private String fieldTypeName;
    private String fullTag;
    private int leftTicketNum;
    private int lowestPrice;
    private Long priceItem;
    private String refundRuleId;
    private String remark;
    private Long serviceId;
    private String startDate;
    private Long venueId;

    public int getFieldType() {
        return this.fieldType;
    }

    public FieldTypeBean setFieldType(int i) {
        this.fieldType = i;
        return this;
    }

    public Long getPriceItem() {
        return this.priceItem;
    }

    public FieldTypeBean setPriceItem(Long l) {
        this.priceItem = l;
        return this;
    }

    public String getFieldTypeName() {
        return this.fieldTypeName;
    }

    public FieldTypeBean setFieldTypeName(String str) {
        this.fieldTypeName = str;
        return this;
    }

    public Long getFieldCnt() {
        return this.fieldCnt;
    }

    public FieldTypeBean setFieldCnt(Long l) {
        this.fieldCnt = l;
        return this;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public FieldTypeBean setStartDate(String str) {
        this.startDate = str;
        return this;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public FieldTypeBean setEndDate(String str) {
        this.endDate = str;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public FieldTypeBean setRemark(String str) {
        this.remark = str;
        return this;
    }

    public Long getVenueId() {
        return this.venueId;
    }

    public FieldTypeBean setVenueId(Long l) {
        this.venueId = l;
        return this;
    }

    public Long getServiceId() {
        return this.serviceId;
    }

    public FieldTypeBean setServiceId(Long l) {
        this.serviceId = l;
        return this;
    }

    public int getBookHour() {
        return this.bookHour;
    }

    public FieldTypeBean setBookHour(int i) {
        this.bookHour = i;
        return this;
    }

    public String getRefundRuleId() {
        return this.refundRuleId;
    }

    public FieldTypeBean setRefundRuleId(String str) {
        this.refundRuleId = str;
        return this;
    }

    public String getFullTag() {
        return this.fullTag;
    }

    public FieldTypeBean setFullTag(String str) {
        this.fullTag = str;
        return this;
    }

    public int getLowestPrice() {
        return this.lowestPrice;
    }

    public FieldTypeBean setLowestPrice(int i) {
        this.lowestPrice = i;
        return this;
    }

    public int getLeftTicketNum() {
        return this.leftTicketNum;
    }

    public FieldTypeBean setLeftTicketNum(int i) {
        this.leftTicketNum = i;
        return this;
    }
}
