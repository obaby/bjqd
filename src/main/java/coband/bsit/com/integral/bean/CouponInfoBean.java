package coband.bsit.com.integral.bean;

import java.io.Serializable;

public class CouponInfoBean implements Serializable {
    private Object couponcnt;
    private String couponid;
    private String couponname;
    private int couponprice;
    private String coupontype;
    private int credit;
    private int creditid;
    private int type;

    public int getCreditid() {
        return this.creditid;
    }

    public void setCreditid(int i) {
        this.creditid = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int i) {
        this.credit = i;
    }

    public String getCouponid() {
        return this.couponid;
    }

    public void setCouponid(String str) {
        this.couponid = str;
    }

    public String getCouponname() {
        return this.couponname;
    }

    public void setCouponname(String str) {
        this.couponname = str;
    }

    public String getCoupontype() {
        return this.coupontype;
    }

    public void setCoupontype(String str) {
        this.coupontype = str;
    }

    public Object getCouponcnt() {
        return this.couponcnt;
    }

    public void setCouponcnt(Object obj) {
        this.couponcnt = obj;
    }

    public int getCouponprice() {
        return this.couponprice;
    }

    public void setCouponprice(int i) {
        this.couponprice = i;
    }
}
