package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.SaleField;
import java.io.Serializable;
import java.util.List;

public class FieldSaleParser extends Response implements Serializable {
    private List<SaleField.FieldCal> fieldCal;
    private String fieldTypeId;
    private List<SaleField.FieldType> fieldTypeList;
    private String serviceId;
    private String venueId;
    private String wechatFieldOpenTime;

    public String getWechatFieldOpenTime() {
        return this.wechatFieldOpenTime;
    }

    public FieldSaleParser setWechatFieldOpenTime(String str) {
        this.wechatFieldOpenTime = str;
        return this;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public FieldSaleParser setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public FieldSaleParser setServiceId(String str) {
        this.serviceId = str;
        return this;
    }

    public List<SaleField.FieldCal> getFieldCal() {
        return this.fieldCal;
    }

    public FieldSaleParser setFieldCal(List<SaleField.FieldCal> list) {
        this.fieldCal = list;
        return this;
    }

    public List<SaleField.FieldType> getFieldTypeList() {
        return this.fieldTypeList;
    }

    public FieldSaleParser setFieldTypeList(List<SaleField.FieldType> list) {
        this.fieldTypeList = list;
        return this;
    }

    public String getFieldTypeId() {
        return this.fieldTypeId;
    }

    public FieldSaleParser setFieldTypeId(String str) {
        this.fieldTypeId = str;
        return this;
    }

    public boolean isFieldDay() {
        return this.fieldCal != null && this.fieldCal.size() > 0;
    }
}
