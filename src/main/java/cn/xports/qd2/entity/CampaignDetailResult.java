package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CampaignDetailResult extends Response implements Serializable {
    private List<CampItem> campItemList;
    private CampaignDetail campaign;
    private String couponCampaignId;
    private int enrollNum;
    private int margin;

    public String getCouponCampaignId() {
        return this.couponCampaignId;
    }

    public void setCouponCampaignId(String str) {
        this.couponCampaignId = str;
    }

    public CampaignDetail getCampaign() {
        return this.campaign;
    }

    public void setCampaign(CampaignDetail campaignDetail) {
        this.campaign = campaignDetail;
    }

    public int getMargin() {
        return this.margin;
    }

    public void setMargin(int i) {
        this.margin = i;
    }

    public int getEnrollNum() {
        return this.enrollNum;
    }

    public void setEnrollNum(int i) {
        this.enrollNum = i;
    }

    public List<CampItem> getCampItemList() {
        return this.campItemList;
    }

    public CampaignDetailResult setCampItemList(List<CampItem> list) {
        this.campItemList = list;
        return this;
    }
}
