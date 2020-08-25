package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class ThirdPayAccountParser extends Response implements Serializable {
    private int alipayAccountId;
    private int wechatAccountId;

    public int getWechatAccountId() {
        return this.wechatAccountId;
    }

    public int getAlipayAccountId() {
        return this.alipayAccountId;
    }
}
