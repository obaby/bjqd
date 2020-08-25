package cn.xports.qd2.entity;

import cn.xports.qd2.entity.RechargePromotion;
import java.io.Serializable;

public class RechargeValue implements Serializable {
    private boolean isCanInput;
    private boolean isSelected;
    private String promInfo;
    private RechargePromotion.PromoPresent promoPresent;
    private String rechargeCampInfo;
    private RechargePromotion.RechargeCampRule rechargeCampRule;
    private String value;

    public RechargePromotion.RechargeCampRule getRechargeCampRule() {
        return this.rechargeCampRule;
    }

    public void setRechargeCampRule(RechargePromotion.RechargeCampRule rechargeCampRule2) {
        this.rechargeCampRule = rechargeCampRule2;
    }

    public RechargePromotion.PromoPresent getPromoPresent() {
        return this.promoPresent;
    }

    public void setPromoPresent(RechargePromotion.PromoPresent promoPresent2) {
        this.promoPresent = promoPresent2;
    }

    public String getRechargeCampInfo() {
        return this.rechargeCampInfo;
    }

    public RechargeValue setRechargeCampInfo(String str) {
        this.rechargeCampInfo = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public RechargeValue setValue(String str) {
        this.value = str;
        return this;
    }

    public String getPromInfo() {
        if (this.promInfo == null) {
            return "";
        }
        return this.promInfo;
    }

    public RechargeValue setPromInfo(String str) {
        this.promInfo = str;
        return this;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public RechargeValue setSelected(boolean z) {
        this.isSelected = z;
        return this;
    }

    public boolean isCanInput() {
        return this.isCanInput;
    }

    public RechargeValue setCanInput(boolean z) {
        this.isCanInput = z;
        return this;
    }
}
