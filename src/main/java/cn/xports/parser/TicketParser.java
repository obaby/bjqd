package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.TicketType;
import java.io.Serializable;
import java.util.List;

public class TicketParser extends Response implements Serializable {
    private String sysdate;
    private List<TicketType> ticketTypeList;

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public List<TicketType> getTicketTypeList() {
        return this.ticketTypeList;
    }

    public void setTicketTypeList(List<TicketType> list) {
        this.ticketTypeList = list;
    }
}
