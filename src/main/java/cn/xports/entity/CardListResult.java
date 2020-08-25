package cn.xports.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CardListResult extends Response implements Serializable {
    private List<CardCenterInfo> cardList;

    public List<CardCenterInfo> getCardList() {
        return this.cardList;
    }

    public CardListResult setCardList(List<CardCenterInfo> list) {
        this.cardList = list;
        return this;
    }

    public static class CardCenterInfo implements Serializable {
        private List<CardInfo> cardInfo;
        private String centerId;
        private String centerName;

        public String getCenterId() {
            return this.centerId;
        }

        public CardCenterInfo setCenterId(String str) {
            this.centerId = str;
            return this;
        }

        public String getCenterName() {
            return this.centerName;
        }

        public CardCenterInfo setCenterName(String str) {
            this.centerName = str;
            return this;
        }

        public List<CardInfo> getCardInfo() {
            return this.cardInfo;
        }

        public CardCenterInfo setCardInfo(List<CardInfo> list) {
            this.cardInfo = list;
            return this;
        }
    }
}
