package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.AgreementInfo;
import cn.xports.entity.CardInfo;
import cn.xports.entity.ValidProduct;
import java.io.Serializable;
import java.util.List;

public class CardProductResult extends Response implements Serializable {
    private AgreementInfo agreementInfo;
    private List<CardInfo> cardInfoList;
    private List<CardPromotion> limitedDiscountPriceList;
    private CardInfo memberCard;
    private ValidProduct product;
    private List<CardPromotion> specialCardPromotionList;
    private Detail useDetails;

    public AgreementInfo getAgreementInfo() {
        return this.agreementInfo;
    }

    public void setAgreementInfo(AgreementInfo agreementInfo2) {
        this.agreementInfo = agreementInfo2;
    }

    public Detail getUseDetails() {
        return this.useDetails;
    }

    public void setUseDetails(Detail detail) {
        this.useDetails = detail;
    }

    public CardInfo getMemberCard() {
        return this.memberCard;
    }

    public void setMemberCard(CardInfo cardInfo) {
        this.memberCard = cardInfo;
    }

    public List<CardPromotion> getSpecialCardPromotionList() {
        return this.specialCardPromotionList;
    }

    public void setSpecialCardPromotionList(List<CardPromotion> list) {
        this.specialCardPromotionList = list;
    }

    public List<CardInfo> getCardInfoList() {
        return this.cardInfoList;
    }

    public void setCardInfoList(List<CardInfo> list) {
        this.cardInfoList = list;
    }

    public ValidProduct getProduct() {
        return this.product;
    }

    public void setProduct(ValidProduct validProduct) {
        this.product = validProduct;
    }

    public List<CardPromotion> getLimitedDiscountPriceList() {
        return this.limitedDiscountPriceList;
    }

    public void setLimitedDiscountPriceList(List<CardPromotion> list) {
        this.limitedDiscountPriceList = list;
    }

    public static class Detail implements Serializable {
        private String value;

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }
}
