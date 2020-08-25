package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class RechargeRule extends Response implements Serializable {
    private String electronicCardRecharge;
    private int minMoney;
    private List<String> priceList;
    private String wechatCanEcardRecharge;

    public String getElectronicCardRecharge() {
        return this.electronicCardRecharge;
    }

    public void setElectronicCardRecharge(String str) {
        this.electronicCardRecharge = str;
    }

    public int getMinMoney() {
        return this.minMoney;
    }

    public void setMinMoney(int i) {
        this.minMoney = i;
    }

    public String getWechatCanEcardRecharge() {
        return this.wechatCanEcardRecharge;
    }

    public void setWechatCanEcardRecharge(String str) {
        this.wechatCanEcardRecharge = str;
    }

    public List<String> getPriceList() {
        return this.priceList;
    }

    public void setPriceList(List<String> list) {
        this.priceList = list;
    }
}
