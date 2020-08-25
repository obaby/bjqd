package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.CardInfo;
import cn.xports.entity.Customer;
import cn.xports.entity.DepositInfo;
import java.io.Serializable;
import java.util.List;

public class CardDetailResult extends Response implements Serializable {
    private String balanceEndDate;
    private CardInfo cardInfo;
    private Customer customer;
    private List<DepositInfo> depositInfo;
    private long ecardBalance;
    private String ecardNo;
    private String wechatCanEcardRecharge;

    public long getEcardBalance() {
        return this.ecardBalance;
    }

    public CardDetailResult setEcardBalance(long j) {
        this.ecardBalance = j;
        return this;
    }

    public CardInfo getCardInfo() {
        return this.cardInfo;
    }

    public CardDetailResult setCardInfo(CardInfo cardInfo2) {
        this.cardInfo = cardInfo2;
        return this;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public CardDetailResult setEcardNo(String str) {
        this.ecardNo = str;
        return this;
    }

    public String getBalanceEndDate() {
        return this.balanceEndDate;
    }

    public CardDetailResult setBalanceEndDate(String str) {
        this.balanceEndDate = str;
        return this;
    }

    public String getWechatCanEcardRecharge() {
        return this.wechatCanEcardRecharge;
    }

    public CardDetailResult setWechatCanEcardRecharge(String str) {
        this.wechatCanEcardRecharge = str;
        return this;
    }

    public List<DepositInfo> getDepositInfo() {
        return this.depositInfo;
    }

    public CardDetailResult setDepositInfo(List<DepositInfo> list) {
        this.depositInfo = list;
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public CardDetailResult setCustomer(Customer customer2) {
        this.customer = customer2;
        return this;
    }
}
