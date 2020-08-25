package cn.xports.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Field implements Serializable {
    private int bookHour;
    private String centerId;
    private long fieldId;
    private String fieldName;
    private int fieldNo;
    @SerializedName("fieldSgementList")
    private List<FieldSegment> fieldSegmentList;
    private int fieldType;
    private String serviceId;
    private String venueId;

    public int getFieldNo() {
        return this.fieldNo;
    }

    public void setFieldNo(int i) {
        this.fieldNo = i;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public void setCenterId(String str) {
        this.centerId = str;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public Field setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public int getBookHour() {
        return this.bookHour;
    }

    public void setBookHour(int i) {
        this.bookHour = i;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public int getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(int i) {
        this.fieldType = i;
    }

    public long getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(long j) {
        this.fieldId = j;
    }

    public List<FieldSegment> getFieldSegmentList() {
        return this.fieldSegmentList;
    }

    public void setFieldSegmentList(List<FieldSegment> list) {
        this.fieldSegmentList = list;
    }
}
