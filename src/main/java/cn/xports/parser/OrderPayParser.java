package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CampaignInfo;
import cn.xports.entity.DepositInfo;
import cn.xports.entity.OrderInfo;
import cn.xports.entity.ProductRes;
import cn.xports.entity.TradeTicket;
import cn.xports.entity.TrainCourse;
import cn.xports.entity.ValidProduct;
import java.io.Serializable;
import java.util.List;

public class OrderPayParser extends Response implements Serializable {
    private String acceptDate;
    private BalanceInfo balanceInfo;
    private TrainCourse course;
    private List<DepositInfo> depositList;
    private DiscountInfo discountInfo;
    private int discountedFee;
    private String isVerifyCodeSend;
    private String margin;
    private List<TradeTicket> orderInfo;
    private List<DepositInfo> periodDepositList;
    private ValidProduct product;
    private ProductRes productRes;
    private CampaignInfo publicCampaign;
    private int realTotalPay;
    private String sysdate;
    private List<TradeTicket> ticketInfo;
    private String ticketName;
    private List<DepositInfo> timesDepositList;
    private int totalPayMoney;
    private OrderInfo trade;
    private String venueName;

    public TrainCourse getCourse() {
        return this.course;
    }

    public void setCourse(TrainCourse trainCourse) {
        this.course = trainCourse;
    }

    public CampaignInfo getPublicCampaign() {
        return this.publicCampaign;
    }

    public OrderPayParser setPublicCampaign(CampaignInfo campaignInfo) {
        this.publicCampaign = campaignInfo;
        return this;
    }

    public List<DepositInfo> getDepositList() {
        return this.depositList;
    }

    public OrderPayParser setDepositList(List<DepositInfo> list) {
        this.depositList = list;
        return this;
    }

    public List<DepositInfo> getPeriodDepositList() {
        return this.periodDepositList;
    }

    public OrderPayParser setPeriodDepositList(List<DepositInfo> list) {
        this.periodDepositList = list;
        return this;
    }

    public List<DepositInfo> getTimesDepositList() {
        return this.timesDepositList;
    }

    public OrderPayParser setTimesDepositList(List<DepositInfo> list) {
        this.timesDepositList = list;
        return this;
    }

    public BalanceInfo getBalanceInfo() {
        return this.balanceInfo;
    }

    public void setBalanceInfo(BalanceInfo balanceInfo2) {
        this.balanceInfo = balanceInfo2;
    }

    public DiscountInfo getDiscountInfo() {
        return this.discountInfo;
    }

    public void setDiscountInfo(DiscountInfo discountInfo2) {
        this.discountInfo = discountInfo2;
    }

    public ValidProduct getProduct() {
        return this.product;
    }

    public void setProduct(ValidProduct validProduct) {
        this.product = validProduct;
    }

    public ProductRes getProductRes() {
        return this.productRes;
    }

    public void setProductRes(ProductRes productRes2) {
        this.productRes = productRes2;
    }

    public String getSysdate() {
        return this.sysdate;
    }

    public OrderPayParser setSysdate(String str) {
        this.sysdate = str;
        return this;
    }

    public String getAcceptDate() {
        return this.acceptDate;
    }

    public OrderPayParser setAcceptDate(String str) {
        this.acceptDate = str;
        return this;
    }

    public String getIsVerifyCodeSend() {
        return this.isVerifyCodeSend;
    }

    public OrderPayParser setIsVerifyCodeSend(String str) {
        this.isVerifyCodeSend = str;
        return this;
    }

    public int getTotalPayMoney() {
        return this.totalPayMoney;
    }

    public OrderPayParser setTotalPayMoney(int i) {
        this.totalPayMoney = i;
        return this;
    }

    public int getDiscountedFee() {
        return this.discountedFee;
    }

    public OrderPayParser setDiscountedFee(int i) {
        this.discountedFee = i;
        return this;
    }

    public int getRealTotalPay() {
        return this.realTotalPay;
    }

    public OrderPayParser setRealTotalPay(int i) {
        this.realTotalPay = i;
        return this;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public OrderPayParser setVenueName(String str) {
        this.venueName = str;
        return this;
    }

    public String getMargin() {
        return this.margin;
    }

    public OrderPayParser setMargin(String str) {
        this.margin = str;
        return this;
    }

    public String getTicketName() {
        return this.ticketName;
    }

    public OrderPayParser setTicketName(String str) {
        this.ticketName = str;
        return this;
    }

    public OrderInfo getTrade() {
        return this.trade;
    }

    public OrderPayParser setTrade(OrderInfo orderInfo2) {
        this.trade = orderInfo2;
        return this;
    }

    public List<TradeTicket> getOrderInfo() {
        return this.orderInfo;
    }

    public OrderPayParser setOrderInfo(List<TradeTicket> list) {
        this.orderInfo = list;
        return this;
    }

    public List<TradeTicket> getTicketInfo() {
        return this.ticketInfo;
    }

    public OrderPayParser setTicketInfo(List<TradeTicket> list) {
        this.ticketInfo = list;
        return this;
    }

    public static class DiscountInfo {
        private String discountTag;
        private long ecardDiscount;

        public long getEcardDiscount() {
            return this.ecardDiscount;
        }

        public void setEcardDiscount(long j) {
            this.ecardDiscount = j;
        }

        public String getDiscountTag() {
            return this.discountTag;
        }

        public void setDiscountTag(String str) {
            this.discountTag = str;
        }
    }
}
