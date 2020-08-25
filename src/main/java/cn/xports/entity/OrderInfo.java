package cn.xports.entity;

import android.text.TextUtils;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.util.DateUtil;
import java.io.Serializable;
import java.util.List;

public class OrderInfo extends Response implements Serializable {
    private String acceptDate;
    private String acceptMonth;
    private String acctId;
    private String businessType;
    private boolean cancelAllTicketsTag;
    private String cancelTag;
    private String centerId;
    private String channelId;
    private String createTime;
    private String custId;
    private String custName;
    private String ecardNo;
    private String ecardTradeId;
    private String effect_date;
    private String expireTime;
    private String finishDate;
    private String netUserId;
    private String orderState;
    private String payState;
    private int payTfee;
    private String place;
    private int priority;
    private int realPay;
    private int refundCashPledge;
    private String serviceId;
    private String serviceName;
    private String subscribeId;
    private String subscribeState;
    private String sysdate;
    private String title;
    private int totalCashPledge;
    private String tradeDesc;
    private String tradeId;
    private List<TradePayLog> tradePayLogList;
    private String tradeStaffId;
    private String tradeTypeCode;
    private String tradeTypeCodeName;
    private String userId;
    private String venueId;
    private String venueName;

    public String getEffect_date() {
        return this.effect_date;
    }

    public OrderInfo setEffect_date(String str) {
        this.effect_date = str;
        return this;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public OrderInfo setBusinessType(String str) {
        this.businessType = str;
        return this;
    }

    public int getRealPay() {
        return this.realPay;
    }

    public OrderInfo setRealPay(int i) {
        this.realPay = i;
        return this;
    }

    public List<TradePayLog> getTradePayLogList() {
        return this.tradePayLogList;
    }

    public OrderInfo setTradePayLogList(List<TradePayLog> list) {
        this.tradePayLogList = list;
        return this;
    }

    public int getRefundCashPledge() {
        return this.refundCashPledge;
    }

    public OrderInfo setRefundCashPledge(int i) {
        this.refundCashPledge = i;
        return this;
    }

    public String getSysdate() {
        return this.sysdate;
    }

    public OrderInfo setSysdate(String str) {
        this.sysdate = str;
        return this;
    }

    public String getAcceptDate() {
        return this.acceptDate;
    }

    public OrderInfo setAcceptDate(String str) {
        this.acceptDate = str;
        return this;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public OrderInfo setEcardNo(String str) {
        this.ecardNo = str;
        return this;
    }

    public String getEcardTradeId() {
        return this.ecardTradeId;
    }

    public OrderInfo setEcardTradeId(String str) {
        this.ecardTradeId = str;
        return this;
    }

    public int getPayTfee() {
        return this.payTfee;
    }

    public OrderInfo setPayTfee(int i) {
        this.payTfee = i;
        return this;
    }

    public String getPayState() {
        return this.payState;
    }

    public OrderInfo setPayState(String str) {
        this.payState = str;
        return this;
    }

    public String getTradeDesc() {
        return this.tradeDesc;
    }

    public OrderInfo setTradeDesc(String str) {
        this.tradeDesc = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public OrderInfo setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getTradeStaffId() {
        return this.tradeStaffId;
    }

    public OrderInfo setTradeStaffId(String str) {
        this.tradeStaffId = str;
        return this;
    }

    public String getOrderState() {
        return this.orderState;
    }

    public OrderInfo setOrderState(String str) {
        this.orderState = str;
        return this;
    }

    public boolean isCancelAllTicketsTag() {
        return this.cancelAllTicketsTag;
    }

    public OrderInfo setCancelAllTicketsTag(boolean z) {
        this.cancelAllTicketsTag = z;
        return this;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public OrderInfo setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public String getCustId() {
        return this.custId;
    }

    public OrderInfo setCustId(String str) {
        this.custId = str;
        return this;
    }

    public String getPlace() {
        return this.place;
    }

    public OrderInfo setPlace(String str) {
        this.place = str;
        return this;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public OrderInfo setServiceId(String str) {
        this.serviceId = str;
        return this;
    }

    public String getTradeTypeCode() {
        return this.tradeTypeCode;
    }

    public OrderInfo setTradeTypeCode(String str) {
        this.tradeTypeCode = str;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public OrderInfo setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public String getAcceptMonth() {
        return this.acceptMonth;
    }

    public OrderInfo setAcceptMonth(String str) {
        this.acceptMonth = str;
        return this;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public OrderInfo setCenterId(String str) {
        this.centerId = str;
        return this;
    }

    public String getNetUserId() {
        return this.netUserId;
    }

    public OrderInfo setNetUserId(String str) {
        this.netUserId = str;
        return this;
    }

    public String getSubscribeState() {
        return this.subscribeState;
    }

    public OrderInfo setSubscribeState(String str) {
        this.subscribeState = str;
        return this;
    }

    public String getCancelTag() {
        return this.cancelTag;
    }

    public OrderInfo setCancelTag(String str) {
        this.cancelTag = str;
        return this;
    }

    public String getSubscribeId() {
        return this.subscribeId;
    }

    public OrderInfo setSubscribeId(String str) {
        this.subscribeId = str;
        return this;
    }

    public String getAcctId() {
        return this.acctId;
    }

    public OrderInfo setAcctId(String str) {
        this.acctId = str;
        return this;
    }

    public int getPriority() {
        return this.priority;
    }

    public OrderInfo setPriority(int i) {
        this.priority = i;
        return this;
    }

    public String getCustName() {
        return this.custName;
    }

    public OrderInfo setCustName(String str) {
        this.custName = str;
        return this;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public OrderInfo setServiceName(String str) {
        this.serviceName = str;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public OrderInfo setUserId(String str) {
        this.userId = str;
        return this;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public OrderInfo setVenueName(String str) {
        this.venueName = str;
        return this;
    }

    public String getTradeTypeCodeName() {
        return this.tradeTypeCodeName;
    }

    public OrderInfo setTradeTypeCodeName(String str) {
        this.tradeTypeCodeName = str;
        return this;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public OrderInfo setExpireTime(String str) {
        this.expireTime = str;
        return this;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public OrderInfo setCreateTime(String str) {
        this.createTime = str;
        return this;
    }

    public String getFinishDate() {
        return this.finishDate;
    }

    public OrderInfo setFinishDate(String str) {
        this.finishDate = str;
        return this;
    }

    public String getTradeId() {
        return this.tradeId;
    }

    public OrderInfo setTradeId(String str) {
        this.tradeId = str;
        return this;
    }

    public int getTotalCashPledge() {
        return this.totalCashPledge;
    }

    public OrderInfo setTotalCashPledge(int i) {
        this.totalCashPledge = i;
        return this;
    }

    public boolean isExpired() {
        if (!TextUtils.isEmpty(this.sysdate) && DateUtil.parse2Time(this.sysdate, "yyyy-MM-dd HH:mm:ss") - DateUtil.parse2Time(this.expireTime, "yyyy-MM-dd HH:mm:ss") > 0) {
            return true;
        }
        return false;
    }
}
