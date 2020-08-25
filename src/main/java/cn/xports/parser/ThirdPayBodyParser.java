package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class ThirdPayBodyParser extends Response implements Serializable {
    private String sysdate;
    private String valueName;

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public String getValueName() {
        return this.valueName;
    }

    public void setValueName(String str) {
        this.valueName = str;
    }
}
