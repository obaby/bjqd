package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.WechatPayInfo;
import java.io.Serializable;

public class WeChatPayParser extends Response implements Serializable {
    private WechatPayInfo wechatPayInfo;

    public WechatPayInfo getWechatPayInfo() {
        return this.wechatPayInfo;
    }

    public void setWechatPayInfo(WechatPayInfo wechatPayInfo2) {
        this.wechatPayInfo = wechatPayInfo2;
    }
}
