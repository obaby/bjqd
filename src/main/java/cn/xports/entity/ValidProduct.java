package cn.xports.entity;

import cn.xports.qd2.entity.K;
import java.io.Serializable;
import java.util.List;

public class ValidProduct implements Serializable {
    private int buyNum;
    private String companyTag;
    private long continuePrice;
    private int discount;
    private int discountMoney;
    private String endDate;
    private int enterTimes;
    private List<GoodsAttr> goodsAttrList;
    private String goodsId;
    private String gradeId;
    private String isActive;
    private String limitTag;
    private String limitedDiscountPriceTag;
    private int personNum;
    private String picUrl;
    private long price;
    private String productId;
    private long productLevel;
    private String productMode;
    private String productName;
    private String productType;
    private String saleLimitNum;
    private String serviceId;
    private String specialCardPromotionTag;
    private String startDate;
    private String status;
    private long venueId;

    public int getDiscountMoney() {
        return this.discountMoney;
    }

    public ValidProduct setDiscountMoney(int i) {
        this.discountMoney = i;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getGradeId() {
        return this.gradeId;
    }

    public void setGradeId(String str) {
        this.gradeId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String str) {
        this.goodsId = str;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public long getContinuePrice() {
        return this.continuePrice;
    }

    public void setContinuePrice(long j) {
        this.continuePrice = j;
    }

    public int getBuyNum() {
        return this.buyNum;
    }

    public void setBuyNum(int i) {
        this.buyNum = i;
    }

    public String getCompanyTag() {
        return this.companyTag;
    }

    public void setCompanyTag(String str) {
        this.companyTag = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getProductMode() {
        return this.productMode;
    }

    public void setProductMode(String str) {
        this.productMode = str;
    }

    public String getSaleLimitNum() {
        return this.saleLimitNum;
    }

    public void setSaleLimitNum(String str) {
        this.saleLimitNum = str;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int i) {
        this.discount = i;
    }

    public int getPersonNum() {
        return this.personNum;
    }

    public void setPersonNum(int i) {
        this.personNum = i;
    }

    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String str) {
        this.isActive = str;
    }

    public String getLimitedDiscountPriceTag() {
        return this.limitedDiscountPriceTag;
    }

    public void setLimitedDiscountPriceTag(String str) {
        this.limitedDiscountPriceTag = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public long getProductLevel() {
        return this.productLevel;
    }

    public void setProductLevel(long j) {
        this.productLevel = j;
    }

    public String getLimitTag() {
        return this.limitTag;
    }

    public void setLimitTag(String str) {
        this.limitTag = str;
    }

    public String getSpecialCardPromotionTag() {
        return this.specialCardPromotionTag;
    }

    public void setSpecialCardPromotionTag(String str) {
        this.specialCardPromotionTag = str;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(long j) {
        this.price = j;
    }

    public int getEnterTimes() {
        return this.enterTimes;
    }

    public void setEnterTimes(int i) {
        this.enterTimes = i;
    }

    public long getVenueId() {
        return this.venueId;
    }

    public void setVenueId(long j) {
        this.venueId = j;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public List<GoodsAttr> getGoodsAttrList() {
        return this.goodsAttrList;
    }

    public void setGoodsAttrList(List<GoodsAttr> list) {
        this.goodsAttrList = list;
    }

    public String getCardTypeName() {
        if (K.k0.equals(this.limitTag)) {
            return "期间卡";
        }
        if ("1".equals(this.limitTag)) {
            return "次卡";
        }
        return "2".equals(this.limitTag) ? "储值卡" : "";
    }

    public static String getCardTypeName(String str) {
        if (K.k0.equals(str)) {
            return "期间卡";
        }
        if ("1".equals(str)) {
            return "次卡";
        }
        return "2".equals(str) ? "储值卡" : "";
    }
}
