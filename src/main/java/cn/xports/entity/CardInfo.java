package cn.xports.entity;

import java.io.Serializable;
import java.util.List;

public class CardInfo implements Serializable {
    private List<BalanceInfo> balanceInfoList;
    private String centerId;
    private String centerName;
    private String contactPhone;
    private String createTime;
    private String custName;
    private String ecardNo;
    private String endDate;
    private boolean isExpand;
    private String lastUseTime;
    private String netUserId;
    private String primaryTag;
    private boolean selected;
    private String startDate;
    private String status;
    private String updateTime;
    private String venueCustId;

    public List<BalanceInfo> getBalanceInfoList() {
        return this.balanceInfoList;
    }

    public void setBalanceInfoList(List<BalanceInfo> list) {
        this.balanceInfoList = list;
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    public void setExpand(boolean z) {
        this.isExpand = z;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String str) {
        this.custName = str;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String str) {
        this.contactPhone = str;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String getNetUserId() {
        return this.netUserId;
    }

    public CardInfo setNetUserId(String str) {
        this.netUserId = str;
        return this;
    }

    public String getEcardNo() {
        if (this.ecardNo == null) {
            return "";
        }
        return this.ecardNo;
    }

    public CardInfo setEcardNo(String str) {
        this.ecardNo = str;
        return this;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public CardInfo setStartDate(String str) {
        this.startDate = str;
        return this;
    }

    public String getVenueCustId() {
        return this.venueCustId;
    }

    public CardInfo setVenueCustId(String str) {
        this.venueCustId = str;
        return this;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public CardInfo setCenterId(String str) {
        this.centerId = str;
        return this;
    }

    public String getPrimaryTag() {
        return this.primaryTag;
    }

    public CardInfo setPrimaryTag(String str) {
        this.primaryTag = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public CardInfo setStatus(String str) {
        this.status = str;
        return this;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public CardInfo setEndDate(String str) {
        this.endDate = str;
        return this;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public CardInfo setCreateTime(String str) {
        this.createTime = str;
        return this;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public CardInfo setUpdateTime(String str) {
        this.updateTime = str;
        return this;
    }

    public String getLastUseTime() {
        return this.lastUseTime;
    }

    public CardInfo setLastUseTime(String str) {
        this.lastUseTime = str;
        return this;
    }

    public String getCenterName() {
        return this.centerName;
    }

    public CardInfo setCenterName(String str) {
        this.centerName = str;
        return this;
    }
}
