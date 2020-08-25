package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.Field;
import java.io.Serializable;
import java.util.List;

public class FieldParser extends Response implements Serializable {
    private int bookHour;
    private Object discountInfo;
    private List<Field> fieldList;
    private boolean refundTag;
    private String serviceBookingManual;
    private String sysdate;

    public String getServiceBookingManual() {
        return this.serviceBookingManual;
    }

    public FieldParser setServiceBookingManual(String str) {
        this.serviceBookingManual = str;
        return this;
    }

    public int getBookHour() {
        return this.bookHour;
    }

    public FieldParser setBookHour(int i) {
        this.bookHour = i;
        return this;
    }

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public Object getDiscountInfo() {
        return this.discountInfo;
    }

    public void setDiscountInfo(Object obj) {
        this.discountInfo = obj;
    }

    public boolean isRefundTag() {
        return this.refundTag;
    }

    public void setRefundTag(boolean z) {
        this.refundTag = z;
    }

    public List<Field> getFieldList() {
        return this.fieldList;
    }

    public void setFieldList(List<Field> list) {
        this.fieldList = list;
    }
}
