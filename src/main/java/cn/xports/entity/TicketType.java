package cn.xports.entity;

import java.io.Serializable;

public class TicketType implements Serializable {
    private String beginDay;
    private int buyLimit;
    private String cashPledge;
    private long centerId;
    private String chargeMode;
    private int count;
    private int dayAmount;
    private int dayRemain;
    private String endDate;
    private String endDay;
    private int endSegment;
    private String groupTag;
    private String picUrl;
    private int price;
    private String priceItem;
    private String priceType;
    private int priceUnit;
    private long refundRuleId;
    private String remark;
    private String serviceId;
    private String startDate;
    private int startSegment;
    private String ticketImageUrl;
    private String ticketKind;
    private int ticketNum;
    private long ticketTypeId;
    private String ticketTypeName;
    private int timeId;
    private String timeName;
    private int timeout;
    private int timeoutMoney;
    private int timeoutUnit;
    private int validDays;
    private String venueId;

    public long getCenterId() {
        return this.centerId;
    }

    public TicketType setCenterId(long j) {
        this.centerId = j;
        return this;
    }

    public String getCashPledge() {
        return this.cashPledge;
    }

    public TicketType setCashPledge(String str) {
        this.cashPledge = str;
        return this;
    }

    public long getRefundRuleId() {
        return this.refundRuleId;
    }

    public TicketType setRefundRuleId(long j) {
        this.refundRuleId = j;
        return this;
    }

    public String getChargeMode() {
        return this.chargeMode;
    }

    public TicketType setChargeMode(String str) {
        this.chargeMode = str;
        return this;
    }

    public String getGroupTag() {
        return this.groupTag;
    }

    public TicketType setGroupTag(String str) {
        this.groupTag = str;
        return this;
    }

    public TicketType setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public TicketType setRemark(String str) {
        this.remark = str;
        return this;
    }

    public int getTicketNum() {
        return this.ticketNum;
    }

    public TicketType setTicketNum(int i) {
        this.ticketNum = i;
        return this;
    }

    public int getBuyLimit() {
        return this.buyLimit;
    }

    public TicketType setBuyLimit(int i) {
        this.buyLimit = i;
        return this;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public TicketType setPicUrl(String str) {
        this.picUrl = str;
        return this;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public TicketType setTimeout(int i) {
        this.timeout = i;
        return this;
    }

    public int getTimeoutMoney() {
        return this.timeoutMoney;
    }

    public TicketType setTimeoutMoney(int i) {
        this.timeoutMoney = i;
        return this;
    }

    public int getTimeoutUnit() {
        return this.timeoutUnit;
    }

    public TicketType setTimeoutUnit(int i) {
        this.timeoutUnit = i;
        return this;
    }

    public String getBeginDay() {
        return this.beginDay;
    }

    public void setBeginDay(String str) {
        this.beginDay = str;
    }

    public int getDayAmount() {
        return this.dayAmount;
    }

    public void setDayAmount(int i) {
        this.dayAmount = i;
    }

    public int getDayRemain() {
        return this.dayRemain;
    }

    public void setDayRemain(int i) {
        this.dayRemain = i;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getEndDay() {
        return this.endDay;
    }

    public void setEndDay(String str) {
        this.endDay = str;
    }

    public int getEndSegment() {
        return this.endSegment;
    }

    public void setEndSegment(int i) {
        this.endSegment = i;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public String getPriceItem() {
        return this.priceItem;
    }

    public void setPriceItem(String str) {
        this.priceItem = str;
    }

    public String getPriceType() {
        return this.priceType;
    }

    public void setPriceType(String str) {
        this.priceType = str;
    }

    public int getPriceUnit() {
        return this.priceUnit;
    }

    public void setPriceUnit(int i) {
        this.priceUnit = i;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public int getStartSegment() {
        return this.startSegment;
    }

    public void setStartSegment(int i) {
        this.startSegment = i;
    }

    public String getTicketKind() {
        return this.ticketKind;
    }

    public void setTicketKind(String str) {
        this.ticketKind = str;
    }

    public long getTicketTypeId() {
        return this.ticketTypeId;
    }

    public void setTicketTypeId(long j) {
        this.ticketTypeId = j;
    }

    public String getTicketTypeName() {
        return this.ticketTypeName;
    }

    public void setTicketTypeName(String str) {
        this.ticketTypeName = str;
    }

    public int getTimeId() {
        return this.timeId;
    }

    public void setTimeId(int i) {
        this.timeId = i;
    }

    public String getTimeName() {
        return this.timeName;
    }

    public void setTimeName(String str) {
        this.timeName = str;
    }

    public int getValidDays() {
        return this.validDays;
    }

    public void setValidDays(int i) {
        this.validDays = i;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public Long getId() {
        return Long.valueOf(this.ticketTypeId + "" + this.timeId);
    }

    public String getTicketImageUrl() {
        return this.ticketImageUrl;
    }

    public void setTicketImageUrl(String str) {
        this.ticketImageUrl = str;
    }
}
