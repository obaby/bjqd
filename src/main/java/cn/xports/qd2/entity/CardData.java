package cn.xports.qd2.entity;

import cn.xports.entity.CardPointInfo;
import java.io.Serializable;
import java.util.List;

public class CardData implements Serializable {
    public List<CardList> cardList;
    public String custFaceType;
    public long error;
    public String message;
    public String sysdate;

    public class CardList implements Serializable {
        public List<CardPointInfo> cardInfo;
        public long centerId;
        public String centerName;

        public CardList() {
        }
    }
}
