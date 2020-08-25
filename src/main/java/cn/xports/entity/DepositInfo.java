package cn.xports.entity;

import java.io.Serializable;

public class DepositInfo implements Serializable {
    private String acctId;
    private String activeTag;
    private int balance;
    private String depositId;
    private String depositName;
    private String depositStatus;
    private int discount;
    private String endDate;
    private String limitType;
    private String productId;
    private String productName;
    private String resCnt;
    private String resType;
    private String sourceType;
    private String startDate;
    private String updateTime;
    private String validTag;

    public int getDiscount() {
        return this.discount;
    }

    public DepositInfo setDiscount(int i) {
        this.discount = i;
        return this;
    }

    public String getProductName() {
        return this.productName;
    }

    public DepositInfo setProductName(String str) {
        this.productName = str;
        return this;
    }

    public String getValidTag() {
        return this.validTag;
    }

    public DepositInfo setValidTag(String str) {
        this.validTag = str;
        return this;
    }

    public String getDepositId() {
        return this.depositId;
    }

    public DepositInfo setDepositId(String str) {
        this.depositId = str;
        return this;
    }

    public String getProductId() {
        return this.productId;
    }

    public DepositInfo setProductId(String str) {
        this.productId = str;
        return this;
    }

    public String getActiveTag() {
        return this.activeTag;
    }

    public DepositInfo setActiveTag(String str) {
        this.activeTag = str;
        return this;
    }

    public String getResCnt() {
        return this.resCnt;
    }

    public DepositInfo setResCnt(String str) {
        this.resCnt = str;
        return this;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public DepositInfo setEndDate(String str) {
        this.endDate = str;
        return this;
    }

    public String getResType() {
        return this.resType;
    }

    public DepositInfo setResType(String str) {
        this.resType = str;
        return this;
    }

    public String getAcctId() {
        return this.acctId;
    }

    public DepositInfo setAcctId(String str) {
        this.acctId = str;
        return this;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public DepositInfo setUpdateTime(String str) {
        this.updateTime = str;
        return this;
    }

    public String getDepositName() {
        return this.depositName;
    }

    public DepositInfo setDepositName(String str) {
        this.depositName = str;
        return this;
    }

    public String getLimitType() {
        return this.limitType;
    }

    public DepositInfo setLimitType(String str) {
        this.limitType = str;
        return this;
    }

    public int getBalance() {
        return this.balance;
    }

    public DepositInfo setBalance(int i) {
        this.balance = i;
        return this;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public DepositInfo setSourceType(String str) {
        this.sourceType = str;
        return this;
    }

    public String getDepositStatus() {
        return this.depositStatus;
    }

    public DepositInfo setDepositStatus(String str) {
        this.depositStatus = str;
        return this;
    }

    public String getStartDate() {
        return this.startDate == null ? "" : this.startDate;
    }

    public DepositInfo setStartDate(String str) {
        this.startDate = str;
        return this;
    }
}
