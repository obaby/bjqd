package cn.xports.entity;

import cn.xports.baselib.util.MoneyUtil;
import cn.xports.parser.OrderPayParser;
import cn.xports.qd2.entity.K;
import java.io.Serializable;

public class BalanceInfo implements Serializable {
    private int balance;
    private String balanceInfo;
    private int canPayBalance;
    private int cardPay;
    private int discount;
    private int discountedFee;
    private String ecardNo;
    private String id;
    private String limitType;
    private String name;
    private int orderDiscount;
    private String payMode;
    private int payMoney;
    private int realTotalPay;
    private String resType;
    private boolean select;
    private int totalPayMoney;
    private String validTag;

    public static int shouldPayLeft(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i != 0) {
            i3 = i;
        } else {
            i = i2;
        }
        return i4 != 0 ? i - i4 : i6 == 1 ? i3 : i;
    }

    public int getPayMoney() {
        if (this.payMoney == 0) {
            return this.totalPayMoney;
        }
        return this.payMoney;
    }

    public BalanceInfo setPayMoney(int i) {
        this.payMoney = i;
        return this;
    }

    public boolean isSelect() {
        return this.select;
    }

    public BalanceInfo setSelect(boolean z) {
        this.select = z;
        return this;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public BalanceInfo setEcardNo(String str) {
        this.ecardNo = str;
        return this;
    }

    public static int shouldPayParse(OrderPayParser orderPayParser) {
        if (orderPayParser.getDiscountedFee() != 0) {
            orderPayParser.setTotalPayMoney(orderPayParser.getDiscountedFee());
            orderPayParser.setRealTotalPay(orderPayParser.getDiscountedFee());
        }
        return orderPayParser.getTotalPayMoney();
    }

    public int shouldPayLeft() {
        if (this.discountedFee != 0) {
            this.totalPayMoney = this.discountedFee;
            this.realTotalPay = this.discountedFee;
        }
        if (this.orderDiscount != 0) {
            this.payMoney = this.totalPayMoney - this.orderDiscount;
            return this.payMoney;
        } else if (this.canPayBalance > 0) {
            return this.realTotalPay;
        } else {
            if (this.discount != 0) {
                return this.totalPayMoney - this.discount;
            }
            return this.totalPayMoney;
        }
    }

    public int shouldCouponPay() {
        if (this.discountedFee != 0) {
            this.totalPayMoney = this.discountedFee;
            this.realTotalPay = this.discountedFee;
        }
        if (this.orderDiscount == 0) {
            return this.totalPayMoney;
        }
        this.payMoney = this.totalPayMoney - this.orderDiscount;
        return this.payMoney;
    }

    public int getDiscount() {
        return this.discount;
    }

    public BalanceInfo setDiscount(int i) {
        this.discount = i;
        return this;
    }

    public int getDiscountedFee() {
        return this.discountedFee;
    }

    public BalanceInfo setDiscountedFee(int i) {
        this.discountedFee = i;
        return this;
    }

    public int getTotalPayMoney() {
        return this.totalPayMoney;
    }

    public BalanceInfo setTotalPayMoney(int i) {
        this.totalPayMoney = i;
        return this;
    }

    public int getRealTotalPay() {
        return this.realTotalPay;
    }

    public BalanceInfo setRealTotalPay(int i) {
        this.realTotalPay = i;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public BalanceInfo setId(String str) {
        this.id = str;
        return this;
    }

    public int getCanPayBalance() {
        return this.canPayBalance;
    }

    public BalanceInfo setCanPayBalance(int i) {
        this.canPayBalance = i;
        return this;
    }

    public String getValidTag() {
        return this.validTag;
    }

    public BalanceInfo setValidTag(String str) {
        this.validTag = str;
        return this;
    }

    public String getResType() {
        return this.resType;
    }

    public BalanceInfo setResType(String str) {
        this.resType = str;
        return this;
    }

    public int getOrderDiscount() {
        return this.orderDiscount;
    }

    public BalanceInfo setOrderDiscount(int i) {
        this.orderDiscount = i;
        return this;
    }

    public int getCardPay() {
        return this.cardPay;
    }

    public BalanceInfo setCardPay(int i) {
        this.cardPay = i;
        return this;
    }

    public String getBalanceInfo() {
        if (K.k0.equals(this.limitType) || "2".equals(this.resType)) {
            return this.balance + "天";
        } else if ("1".equals(this.limitType) || K.k0.equals(this.resType)) {
            return this.balance + "次";
        } else {
            return MoneyUtil.cent2Yuan(this.balance) + "元";
        }
    }

    public void setBalanceInfo(String str) {
        this.balanceInfo = str;
    }

    public String getLimitType() {
        return this.limitType;
    }

    public BalanceInfo setLimitType(String str) {
        this.limitType = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public BalanceInfo setName(String str) {
        this.name = str;
        return this;
    }

    public int getBalance() {
        return this.balance;
    }

    public BalanceInfo setBalance(int i) {
        this.balance = i;
        return this;
    }

    public String getPayMode() {
        return this.payMode;
    }

    public BalanceInfo setPayMode(String str) {
        this.payMode = str;
        return this;
    }

    public String toString() {
        return "BalanceInfo{name='" + this.name + '\'' + ", balance=" + this.balance + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BalanceInfo balanceInfo2 = (BalanceInfo) obj;
        if (this.balance != balanceInfo2.balance) {
            return false;
        }
        if (this.name == null ? balanceInfo2.name != null : !this.name.equals(balanceInfo2.name)) {
            return false;
        }
        if (this.id == null ? balanceInfo2.id != null : !this.id.equals(balanceInfo2.id)) {
            return false;
        }
        if (this.limitType == null ? balanceInfo2.limitType != null : !this.limitType.equals(balanceInfo2.limitType)) {
            return false;
        }
        if (this.resType == null ? balanceInfo2.resType != null : !this.resType.equals(balanceInfo2.resType)) {
            return false;
        }
        if (this.validTag != null) {
            return this.validTag.equals(balanceInfo2.validTag);
        }
        if (balanceInfo2.validTag == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((this.name != null ? this.name.hashCode() : 0) * 31) + (this.id != null ? this.id.hashCode() : 0)) * 31) + this.balance) * 31) + (this.limitType != null ? this.limitType.hashCode() : 0)) * 31) + (this.resType != null ? this.resType.hashCode() : 0)) * 31;
        if (this.validTag != null) {
            i = this.validTag.hashCode();
        }
        return hashCode + i;
    }
}
