package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.OrderPageInfo;
import java.io.Serializable;

public class OrderListParser extends Response implements Serializable {
    private OrderPageInfo pageInfo;
    private String sysdate;

    public String getSysdate() {
        return this.sysdate;
    }

    public OrderListParser setSysdate(String str) {
        this.sysdate = str;
        return this;
    }

    public OrderPageInfo getPageInfo() {
        return this.pageInfo;
    }

    public OrderListParser setPageInfo(OrderPageInfo orderPageInfo) {
        this.pageInfo = orderPageInfo;
        return this;
    }
}
