package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.OrderInfo;
import cn.xports.entity.ProductRes;
import cn.xports.entity.TradeTicket;
import java.io.Serializable;
import java.util.List;

public class OrderInfoParser extends Response implements Serializable {
    private boolean cancelAllTicketsTag;
    private boolean cancelTicketTag;
    private ProductRes productRes;
    private String sysdate;
    private String title;
    private int totalPayMoney;
    private OrderInfo trade;
    private List<TradeTicket> tradeTickets;

    public ProductRes getProductRes() {
        return this.productRes;
    }

    public OrderInfoParser setProductRes(ProductRes productRes2) {
        this.productRes = productRes2;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public OrderInfoParser setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getSysdate() {
        return this.sysdate;
    }

    public OrderInfoParser setSysdate(String str) {
        this.sysdate = str;
        return this;
    }

    public OrderInfo getTrade() {
        return this.trade;
    }

    public OrderInfoParser setTrade(OrderInfo orderInfo) {
        this.trade = orderInfo;
        return this;
    }

    public int getTotalPayMoney() {
        return this.totalPayMoney;
    }

    public boolean isCancelAllTicketsTag() {
        return this.cancelAllTicketsTag;
    }

    public List<TradeTicket> getTradeTickets() {
        return this.tradeTickets;
    }

    public boolean isCancelTicketTag() {
        return this.cancelTicketTag;
    }

    public void setTotalPayMoney(int i) {
        this.totalPayMoney = i;
    }

    public void setCancelAllTicketsTag(boolean z) {
        this.cancelAllTicketsTag = z;
    }

    public void setTradeTickets(List<TradeTicket> list) {
        this.tradeTickets = list;
    }

    public void setCancelTicketTag(boolean z) {
        this.cancelTicketTag = z;
    }
}
