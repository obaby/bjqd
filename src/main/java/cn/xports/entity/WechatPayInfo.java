package cn.xports.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class WechatPayInfo implements Serializable {
    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public String getNoncestr() {
        return this.noncestr;
    }

    public void setNoncestr(String str) {
        this.noncestr = str;
    }

    public String getPackageX() {
        return this.packageX;
    }

    public void setPackageX(String str) {
        this.packageX = str;
    }

    public String getPartnerid() {
        return this.partnerid;
    }

    public void setPartnerid(String str) {
        this.partnerid = str;
    }

    public String getPrepayid() {
        return this.prepayid;
    }

    public void setPrepayid(String str) {
        this.prepayid = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }
}
