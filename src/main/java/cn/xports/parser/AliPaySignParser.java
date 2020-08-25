package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class AliPaySignParser extends Response implements Serializable {
    private String signString;

    public String getSignString() {
        return this.signString;
    }

    public void setSignString(String str) {
        this.signString = str;
    }
}
