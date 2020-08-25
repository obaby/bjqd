package cn.xports.entity;

import java.io.Serializable;

public class AgreementInfo implements Serializable {
    private String agreementCont;
    private String agreementId;
    private String agreementName;
    private String agreementType;
    private String tradeTypeCode;

    public String getAgreementId() {
        return this.agreementId;
    }

    public void setAgreementId(String str) {
        this.agreementId = str;
    }

    public String getAgreementType() {
        return this.agreementType;
    }

    public void setAgreementType(String str) {
        this.agreementType = str;
    }

    public String getTradeTypeCode() {
        return this.tradeTypeCode;
    }

    public void setTradeTypeCode(String str) {
        this.tradeTypeCode = str;
    }

    public String getAgreementName() {
        return this.agreementName;
    }

    public AgreementInfo setAgreementName(String str) {
        this.agreementName = str;
        return this;
    }

    public String getAgreementCont() {
        return this.agreementCont;
    }

    public AgreementInfo setAgreementCont(String str) {
        this.agreementCont = str;
        return this;
    }
}
