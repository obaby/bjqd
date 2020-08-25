package cn.xports.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class AgreementResult extends Response implements Serializable {
    private AgreementInfo agreement;

    public AgreementInfo getAgreement() {
        return this.agreement;
    }

    public void setAgreement(AgreementInfo agreementInfo) {
        this.agreement = agreementInfo;
    }
}
