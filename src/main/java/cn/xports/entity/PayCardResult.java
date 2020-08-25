package cn.xports.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class PayCardResult extends Response implements Serializable {
    private List<CardInfo> cardInfoList;
    private String chooseCard;
    private CardInfo primaryCard;

    public CardInfo getPrimaryCard() {
        return this.primaryCard;
    }

    public void setPrimaryCard(CardInfo cardInfo) {
        this.primaryCard = cardInfo;
    }

    public List<CardInfo> getCardInfoList() {
        return this.cardInfoList;
    }

    public void setCardInfoList(List<CardInfo> list) {
        this.cardInfoList = list;
    }

    public String getChooseCard() {
        return this.chooseCard;
    }

    public void setChooseCard(String str) {
        this.chooseCard = str;
    }
}
