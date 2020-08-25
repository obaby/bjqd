package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.Venue;
import java.io.Serializable;

public class VenueDetailParser extends Response implements Serializable {
    private String experienceCouponNum;
    private String showPromTag;
    private String valueCouponNum;
    private Venue venue;

    public String getShowPromTag() {
        return this.showPromTag;
    }

    public VenueDetailParser setShowPromTag(String str) {
        this.showPromTag = str;
        return this;
    }

    public String getValueCouponNum() {
        return this.valueCouponNum;
    }

    public VenueDetailParser setValueCouponNum(String str) {
        this.valueCouponNum = str;
        return this;
    }

    public String getExperienceCouponNum() {
        return this.experienceCouponNum;
    }

    public VenueDetailParser setExperienceCouponNum(String str) {
        this.experienceCouponNum = str;
        return this;
    }

    public Venue getVenue() {
        return this.venue;
    }

    public VenueDetailParser setVenue(Venue venue2) {
        this.venue = venue2;
        return this;
    }
}
