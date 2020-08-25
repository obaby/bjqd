package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class RechargePromotion extends Response implements Serializable {
    private List<Promo> promoList;
    private List<PromoPresent> promoPresentList;
    private List<RechargeCampRule> rechargeCampRuleList;
    private RechargeCampaign rechargeCampaign;

    public List<RechargeCampRule> getRechargeCampRuleList() {
        return this.rechargeCampRuleList;
    }

    public void setRechargeCampRuleList(List<RechargeCampRule> list) {
        this.rechargeCampRuleList = list;
    }

    public List<Promo> getPromoList() {
        return this.promoList;
    }

    public void setPromoList(List<Promo> list) {
        this.promoList = list;
    }

    public RechargeCampaign getRechargeCampaign() {
        return this.rechargeCampaign;
    }

    public void setRechargeCampaign(RechargeCampaign rechargeCampaign2) {
        this.rechargeCampaign = rechargeCampaign2;
    }

    public List<PromoPresent> getPromoPresentList() {
        return this.promoPresentList;
    }

    public void setPromoPresentList(List<PromoPresent> list) {
        this.promoPresentList = list;
    }

    public static class RechargeCampRule implements Serializable {
        private int buyNum;
        private int presentNum;
        private String presentType;
        private String presentValue;
        private String startDate;
        private String valueName;

        public int getPresentNum() {
            return this.presentNum;
        }

        public void setPresentNum(int i) {
            this.presentNum = i;
        }

        public String getPresentValue() {
            return this.presentValue;
        }

        public void setPresentValue(String str) {
            this.presentValue = str;
        }

        public int getBuyNum() {
            return this.buyNum;
        }

        public void setBuyNum(int i) {
            this.buyNum = i;
        }

        public String getValueName() {
            return this.valueName;
        }

        public void setValueName(String str) {
            this.valueName = str;
        }

        public String getPresentType() {
            return this.presentType;
        }

        public void setPresentType(String str) {
            this.presentType = str;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }
    }

    public static class Promo implements Serializable {
        private String centerId;
        private String endDate;
        private String promId;
        private String promName;
        private String promType;
        private String startDate;

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

        public String getCenterId() {
            return this.centerId;
        }

        public void setCenterId(String str) {
            this.centerId = str;
        }
    }

    public static class RechargeCampaign implements Serializable {
        private String campDesc;
        private String campId;
        private String campName;
        private String endDate;
        private int minNum;
        private String promType;
        private String startDate;

        public String getPromType() {
            return this.promType;
        }

        public void setPromType(String str) {
            this.promType = str;
        }

        public String getCampId() {
            return this.campId;
        }

        public void setCampId(String str) {
            this.campId = str;
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

        public int getMinNum() {
            return this.minNum;
        }

        public void setMinNum(int i) {
            this.minNum = i;
        }

        public String getCampDesc() {
            return this.campDesc;
        }

        public void setCampDesc(String str) {
            this.campDesc = str;
        }

        public String getCampName() {
            return this.campName;
        }

        public void setCampName(String str) {
            this.campName = str;
        }
    }

    public static class PromoPresent implements Serializable {
        private String endDate;
        private int presentMoney;
        private String presentType;
        private String promId;
        private int rechargeLevel;
        private String startDate;

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

        public String getPromId() {
            return this.promId;
        }

        public void setPromId(String str) {
            this.promId = str;
        }

        public int getRechargeLevel() {
            return this.rechargeLevel;
        }

        public void setRechargeLevel(int i) {
            this.rechargeLevel = i;
        }

        public int getPresentMoney() {
            return this.presentMoney;
        }

        public void setPresentMoney(int i) {
            this.presentMoney = i;
        }

        public String getPresentType() {
            return this.presentType;
        }

        public void setPresentType(String str) {
            this.presentType = str;
        }
    }
}
