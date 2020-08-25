package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.AgreementInfo;
import cn.xports.entity.TicketType;
import java.io.Serializable;

public class TicketDetailParser extends Response implements Serializable {
    private AgreementInfo agreementInfo;
    private DiscountInfo discountInfo;
    private TicketType ticketDetails;

    public TicketType getTicketDetails() {
        return this.ticketDetails;
    }

    public TicketDetailParser setTicketDetails(TicketType ticketType) {
        this.ticketDetails = ticketType;
        return this;
    }

    public DiscountInfo getDiscountInfo() {
        return this.discountInfo;
    }

    public TicketDetailParser setDiscountInfo(DiscountInfo discountInfo2) {
        this.discountInfo = discountInfo2;
        return this;
    }

    public AgreementInfo getAgreementInfo() {
        return this.agreementInfo;
    }

    public TicketDetailParser setAgreementInfo(AgreementInfo agreementInfo2) {
        this.agreementInfo = agreementInfo2;
        return this;
    }

    public static class DiscountInfo implements Serializable {
        String discountTag;

        public String getDiscountTag() {
            return this.discountTag;
        }

        public DiscountInfo setDiscountTag(String str) {
            this.discountTag = str;
            return this;
        }
    }
}
