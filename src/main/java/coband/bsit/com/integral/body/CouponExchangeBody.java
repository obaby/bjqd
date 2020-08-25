package coband.bsit.com.integral.body;

import com.convenient.qd.core.base.AppInfo;
import java.io.Serializable;

public class CouponExchangeBody implements Serializable {
    private AppInfo appInfo = new AppInfo();
    private String couponcnt;
    private String couponid;
    private int creditid;

    public int getCreditid() {
        return this.creditid;
    }

    public void setCreditid(int i) {
        this.creditid = i;
    }

    public String getCouponid() {
        return this.couponid;
    }

    public void setCouponid(String str) {
        this.couponid = str;
    }

    public String getCouponcnt() {
        return this.couponcnt;
    }

    public void setCouponcnt(String str) {
        this.couponcnt = str;
    }
}
