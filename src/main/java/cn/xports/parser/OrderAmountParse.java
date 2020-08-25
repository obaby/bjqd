package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class OrderAmountParse extends Response implements Serializable {
    private int orderAmount;

    public int getOrderAmount() {
        return this.orderAmount;
    }

    public OrderAmountParse setOrderAmount(int i) {
        this.orderAmount = i;
        return this;
    }
}
