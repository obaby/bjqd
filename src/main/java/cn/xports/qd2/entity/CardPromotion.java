package cn.xports.qd2.entity;

import java.io.Serializable;
import java.util.List;

public class CardPromotion implements Serializable {
    private BuyGift buyGifts;
    private List<Coupon> coupons;
    private String discountMoney;
    private String discountType;
    private String endDate;
    private List<Gift> gifts;
    private boolean isSelect;
    private String promId;
    private String promName;
    private String promType;
    private String startDate;

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public List<Gift> getGifts() {
        return this.gifts;
    }

    public void setGifts(List<Gift> list) {
        this.gifts = list;
    }

    public List<Coupon> getCoupons() {
        return this.coupons;
    }

    public void setCoupons(List<Coupon> list) {
        this.coupons = list;
    }

    public BuyGift getBuyGifts() {
        return this.buyGifts;
    }

    public void setBuyGifts(BuyGift buyGift) {
        this.buyGifts = buyGift;
    }

    public String getPromId() {
        return this.promId;
    }

    public void setPromId(String str) {
        this.promId = str;
    }

    public String getPromName() {
        return this.promName;
    }

    public void setPromName(String str) {
        this.promName = str;
    }

    public String getPromType() {
        return this.promType;
    }

    public void setPromType(String str) {
        this.promType = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getDiscountMoney() {
        return this.discountMoney;
    }

    public void setDiscountMoney(String str) {
        this.discountMoney = str;
    }

    public String getDiscountType() {
        return this.discountType;
    }

    public void setDiscountType(String str) {
        this.discountType = str;
    }

    public static class Gift {
        private String productId;
        private String productName;

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String str) {
            this.productId = str;
        }

        public String getProductName() {
            return this.productName;
        }

        public void setProductName(String str) {
            this.productName = str;
        }
    }

    public static class Coupon {
        private int balance;
        private String couponChecked;
        private String couponId;
        private String couponName;
        private String couponNo;
        private String limitTag;

        public String getCouponId() {
            return this.couponId;
        }

        public void setCouponId(String str) {
            this.couponId = str;
        }

        public String getCouponName() {
            return this.couponName;
        }

        public void setCouponName(String str) {
            this.couponName = str;
        }

        public int getBalance() {
            return this.balance;
        }

        public void setBalance(int i) {
            this.balance = i;
        }

        public String getCouponNo() {
            return this.couponNo;
        }

        public void setCouponNo(String str) {
            this.couponNo = str;
        }

        public String getCouponChecked() {
            return this.couponChecked;
        }

        public void setCouponChecked(String str) {
            this.couponChecked = str;
        }

        public String getLimitTag() {
            return this.limitTag;
        }

        public void setLimitTag(String str) {
            this.limitTag = str;
        }
    }

    public static class BuyGift {
        private String endDate;
        private String giftId;
        private String giftType;
        private String giftValue;
        private String limitTag;
        private String promId;
        private String startDate;
        private String unit;

        public String getPromId() {
            return this.promId;
        }

        public void setPromId(String str) {
            this.promId = str;
        }

        public String getGiftId() {
            return this.giftId;
        }

        public void setGiftId(String str) {
            this.giftId = str;
        }

        public String getGiftType() {
            return this.giftType;
        }

        public void setGiftType(String str) {
            this.giftType = str;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public void setEndDate(String str) {
            this.endDate = str;
        }

        public String getGiftValue() {
            return this.giftValue;
        }

        public void setGiftValue(String str) {
            this.giftValue = str;
        }

        public String getUnit() {
            return this.unit;
        }

        public void setUnit(String str) {
            this.unit = str;
        }

        public String getLimitTag() {
            return this.limitTag;
        }

        public void setLimitTag(String str) {
            this.limitTag = str;
        }
    }
}
