package cn.xports.entity;

import java.io.Serializable;

public class TradePayLog implements Serializable {
    private String custId;
    private int drawbackMoney;
    private String ecardCustId;
    private String ecardNo;
    private String payLogId;
    private String payModeCode;
    private String payModeCodeName;
    private int realPay;
    private int shouldPay;
    private String tradeDate;
    private String tradeId;
    private String tradeStaffId;
    private String tradeVenueId;

    public int getRealPay() {
        return this.realPay;
    }

    public TradePayLog setRealPay(int i) {
        this.realPay = i;
        return this;
    }

    public String getPayModeCode() {
        return this.payModeCode;
    }

    public TradePayLog setPayModeCode(String str) {
        this.payModeCode = str;
        return this;
    }

    public int getShouldPay() {
        return this.shouldPay;
    }

    public TradePayLog setShouldPay(int i) {
        this.shouldPay = i;
        return this;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public TradePayLog setEcardNo(String str) {
        this.ecardNo = str;
        return this;
    }

    public String getPayLogId() {
        return this.payLogId;
    }

    public TradePayLog setPayLogId(String str) {
        this.payLogId = str;
        return this;
    }

    public String getTradeDate() {
        return this.tradeDate;
    }

    public TradePayLog setTradeDate(String str) {
        this.tradeDate = str;
        return this;
    }

    public String getTradeStaffId() {
        return this.tradeStaffId;
    }

    public TradePayLog setTradeStaffId(String str) {
        this.tradeStaffId = str;
        return this;
    }

    public String getTradeVenueId() {
        return this.tradeVenueId;
    }

    public TradePayLog setTradeVenueId(String str) {
        this.tradeVenueId = str;
        return this;
    }

    public String getCustId() {
        return this.custId;
    }

    public TradePayLog setCustId(String str) {
        this.custId = str;
        return this;
    }

    public String getPayModeCodeName() {
        return this.payModeCodeName;
    }

    public TradePayLog setPayModeCodeName(String str) {
        this.payModeCodeName = str;
        return this;
    }

    public String getEcardCustId() {
        return this.ecardCustId;
    }

    public TradePayLog setEcardCustId(String str) {
        this.ecardCustId = str;
        return this;
    }

    public String getTradeId() {
        return this.tradeId;
    }

    public TradePayLog setTradeId(String str) {
        this.tradeId = str;
        return this;
    }

    public int getDrawbackMoney() {
        return this.drawbackMoney;
    }

    public TradePayLog setDrawbackMoney(int i) {
        this.drawbackMoney = i;
        return this;
    }
}
