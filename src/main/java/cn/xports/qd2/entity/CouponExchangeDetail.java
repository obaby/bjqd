package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class CouponExchangeDetail extends Response implements Serializable {
    public ExchangeDetail exchangeDetail;

    public class ExchangeDetail implements Serializable {
        public long centerId;
        public String couponCat;
        public String couponName;
        public String endDate;
        public String exchangeMode;
        public long exchangeNum;
        public String extra;
        public String hotTag;
        public long id;
        public String objectId;
        public long objectNum;
        public String objectType;
        public String photo;
        public String pointsType;
        public String remark;
        public long sort;
        public String startDate;
        public String state;
        public String useVenueName;
        public long value;
        public String valueType;
        public long venueId;

        public ExchangeDetail() {
        }
    }
}
