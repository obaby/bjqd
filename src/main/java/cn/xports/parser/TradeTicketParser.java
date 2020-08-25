package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.TradeTicket;
import java.io.Serializable;
import java.util.List;

public class TradeTicketParser extends Response implements Serializable {
    private String sysdate;
    private List<TradeTicket> tradeTicketList;
    private List<TradeTicket> tradeTickets;

    public List<TradeTicket> getTradeTickets() {
        return this.tradeTickets;
    }

    public TradeTicketParser setTradeTickets(List<TradeTicket> list) {
        this.tradeTickets = list;
        return this;
    }

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public List<TradeTicket> getTradeTicketList() {
        return this.tradeTicketList;
    }

    public void setTradeTicketList(List<TradeTicket> list) {
        this.tradeTicketList = list;
    }
}
