package cn.xports.entity;

import android.text.TextUtils;
import java.io.Serializable;

public class TradeTicket implements Serializable {
    private int backFee;
    private String centerName;
    private int couponAmount;
    private String createTime;
    private long custId;
    private String custName;
    private int discount;
    private String ecardNo;
    private String effectDate;
    private String effect_date;
    private int endSegment;
    private String expireDate;
    private long fieldId;
    private String fieldName;
    private String groupTag;
    private int num = 1;
    private int payMoney;
    private String serviceId;
    private String serviceName;
    private int startSegment;
    private int state;
    private Long ticketId;
    private String ticketNo;
    private String ticketSourceType;
    private Long ticketTimeId;
    private int ticketType;
    private Long ticketTypeId;
    private String ticketTypeName;
    private String tradeId;
    private Long venueId;
    private String venueName;

    public TradeTicket() {
    }

    public TradeTicket(TradeTicket tradeTicket) {
        this.ticketId = tradeTicket.getTicketId();
        this.tradeId = tradeTicket.getTradeId();
        this.serviceId = tradeTicket.getServiceId();
        this.venueId = tradeTicket.getVenueId();
        this.fieldId = tradeTicket.getFieldId();
        this.fieldName = tradeTicket.getFieldName();
        this.startSegment = tradeTicket.getStartSegment();
        this.endSegment = tradeTicket.getEndSegment();
        this.payMoney = tradeTicket.getPayMoney();
        this.discount = tradeTicket.getDiscount();
        this.state = tradeTicket.getState();
        this.createTime = tradeTicket.getCreateTime();
        this.ticketSourceType = tradeTicket.getTicketSourceType();
        this.effectDate = tradeTicket.getEffectDate();
        this.ecardNo = tradeTicket.getEcardNo();
        this.custId = tradeTicket.getCustId();
        this.custName = tradeTicket.getCustName();
        this.ticketType = tradeTicket.getTicketType();
        this.couponAmount = tradeTicket.getCouponAmount();
        this.groupTag = tradeTicket.getGroupTag();
        this.ticketNo = tradeTicket.getTicketNo();
        this.backFee = tradeTicket.getBackFee();
        this.serviceName = tradeTicket.getServiceName();
        this.ticketTypeName = tradeTicket.getTicketTypeName();
        this.num = 1;
        this.ticketTypeId = tradeTicket.getTicketTypeId();
        this.ticketTimeId = tradeTicket.getTicketTimeId();
        this.venueName = tradeTicket.getVenueName();
        this.expireDate = tradeTicket.getExpireDate();
    }

    public String getCenterName() {
        return this.centerName;
    }

    public TradeTicket setCenterName(String str) {
        this.centerName = str;
        return this;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public TradeTicket setVenueName(String str) {
        this.venueName = str;
        return this;
    }

    public String getExpireDate() {
        return this.expireDate;
    }

    public TradeTicket setExpireDate(String str) {
        this.expireDate = str;
        return this;
    }

    public Long getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Long l) {
        this.ticketId = l;
    }

    public String getTradeId() {
        return this.tradeId;
    }

    public void setTradeId(String str) {
        this.tradeId = str;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public Long getVenueId() {
        return this.venueId;
    }

    public void setVenueId(Long l) {
        this.venueId = l;
    }

    public int getStartSegment() {
        return this.startSegment;
    }

    public void setStartSegment(int i) {
        this.startSegment = i;
    }

    public int getEndSegment() {
        return this.endSegment;
    }

    public void setEndSegment(int i) {
        this.endSegment = i;
    }

    public int getPayMoney() {
        return this.payMoney;
    }

    public void setPayMoney(int i) {
        this.payMoney = i;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int i) {
        this.discount = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getTicketSourceType() {
        return this.ticketSourceType;
    }

    public void setTicketSourceType(String str) {
        this.ticketSourceType = str;
    }

    public String getEffectDate() {
        return TextUtils.isEmpty(this.effectDate) ? this.effect_date : this.effectDate;
    }

    public void setEffectDate(String str) {
        this.effectDate = str;
    }

    public String getEffect_date() {
        return this.effect_date;
    }

    public TradeTicket setEffect_date(String str) {
        this.effect_date = str;
        return this;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public void setEcardNo(String str) {
        this.ecardNo = str;
    }

    public long getCustId() {
        return this.custId;
    }

    public void setCustId(long j) {
        this.custId = j;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String str) {
        this.custName = str;
    }

    public int getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(int i) {
        this.ticketType = i;
    }

    public int getCouponAmount() {
        return this.couponAmount;
    }

    public void setCouponAmount(int i) {
        this.couponAmount = i;
    }

    public String getGroupTag() {
        return this.groupTag;
    }

    public void setGroupTag(String str) {
        this.groupTag = str;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }

    public long getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(long j) {
        this.fieldId = j;
    }

    public String getTicketNo() {
        return this.ticketNo;
    }

    public void setTicketNo(String str) {
        this.ticketNo = str;
    }

    public int getBackFee() {
        return this.backFee;
    }

    public void setBackFee(int i) {
        this.backFee = i;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TradeTicket tradeTicket = (TradeTicket) obj;
        if (this.serviceId != tradeTicket.serviceId || this.fieldId != tradeTicket.fieldId || this.startSegment != tradeTicket.startSegment || this.endSegment != tradeTicket.endSegment || this.payMoney != tradeTicket.payMoney || this.discount != tradeTicket.discount || this.state != tradeTicket.state || this.custId != tradeTicket.custId || this.ticketType != tradeTicket.ticketType || this.couponAmount != tradeTicket.couponAmount || this.backFee != tradeTicket.backFee || this.num != tradeTicket.num) {
            return false;
        }
        if (this.ticketId == null ? tradeTicket.ticketId != null : !this.ticketId.equals(tradeTicket.ticketId)) {
            return false;
        }
        if (this.tradeId == null ? tradeTicket.tradeId != null : !this.tradeId.equals(tradeTicket.tradeId)) {
            return false;
        }
        if (this.venueId == null ? tradeTicket.venueId != null : !this.venueId.equals(tradeTicket.venueId)) {
            return false;
        }
        if (this.fieldName == null ? tradeTicket.fieldName != null : !this.fieldName.equals(tradeTicket.fieldName)) {
            return false;
        }
        if (this.createTime == null ? tradeTicket.createTime != null : !this.createTime.equals(tradeTicket.createTime)) {
            return false;
        }
        if (this.ticketSourceType == null ? tradeTicket.ticketSourceType != null : !this.ticketSourceType.equals(tradeTicket.ticketSourceType)) {
            return false;
        }
        if (this.effectDate == null ? tradeTicket.effectDate != null : !this.effectDate.equals(tradeTicket.effectDate)) {
            return false;
        }
        if (this.ecardNo == null ? tradeTicket.ecardNo != null : !this.ecardNo.equals(tradeTicket.ecardNo)) {
            return false;
        }
        if (this.custName == null ? tradeTicket.custName != null : !this.custName.equals(tradeTicket.custName)) {
            return false;
        }
        if (this.groupTag == null ? tradeTicket.groupTag != null : !this.groupTag.equals(tradeTicket.groupTag)) {
            return false;
        }
        if (this.ticketNo == null ? tradeTicket.ticketNo != null : !this.ticketNo.equals(tradeTicket.ticketNo)) {
            return false;
        }
        if (this.serviceName == null ? tradeTicket.serviceName != null : !this.serviceName.equals(tradeTicket.serviceName)) {
            return false;
        }
        if (this.ticketTypeName == null ? tradeTicket.ticketTypeName != null : !this.ticketTypeName.equals(tradeTicket.ticketTypeName)) {
            return false;
        }
        if (this.ticketTypeId == null ? tradeTicket.ticketTypeId != null : !this.ticketTypeId.equals(tradeTicket.ticketTypeId)) {
            return false;
        }
        if (this.ticketTimeId == null ? tradeTicket.ticketTimeId != null : !this.ticketTimeId.equals(tradeTicket.ticketTimeId)) {
            return false;
        }
        if (this.venueName == null ? tradeTicket.venueName != null : !this.venueName.equals(tradeTicket.venueName)) {
            return false;
        }
        if (this.expireDate != null) {
            return this.expireDate.equals(tradeTicket.expireDate);
        }
        if (tradeTicket.expireDate == null) {
            return true;
        }
        return false;
    }

    public String getTicketTypeName() {
        return this.ticketTypeName;
    }

    public void setTicketTypeName(String str) {
        this.ticketTypeName = str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public Long getTicketTypeId() {
        return this.ticketTypeId;
    }

    public void setTicketTypeId(Long l) {
        this.ticketTypeId = l;
    }

    public Long getTicketTimeId() {
        return this.ticketTimeId;
    }

    public void setTicketTimeId(Long l) {
        this.ticketTimeId = l;
    }

    public String getTicketTypeIdAndTimeId() {
        return String.valueOf(this.ticketTypeId) + String.valueOf(this.ticketTimeId);
    }
}
