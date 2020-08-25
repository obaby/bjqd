package cn.xports.entity;

import java.io.Serializable;
import java.util.Objects;

public class FieldSegment implements Serializable {
    private int endSegment;
    private long fieldId;
    private String fieldName;
    private int fieldNo;
    private String fieldSegmentId;
    private int price;
    private int segment;
    private int startSegment;
    private String state;
    private int step;

    public FieldSegment() {
    }

    public FieldSegment(FieldSegment fieldSegment) {
        this.price = fieldSegment.getPrice();
        this.segment = fieldSegment.getSegment();
        this.step = fieldSegment.getStep();
        this.state = fieldSegment.getState();
        this.startSegment = fieldSegment.getStartSegment();
        this.endSegment = fieldSegment.getEndSegment();
        this.fieldNo = fieldSegment.getFieldNo();
        this.fieldId = fieldSegment.getFieldId();
        this.fieldName = fieldSegment.getFieldName();
        this.fieldSegmentId = fieldSegment.getFieldSegmentId();
    }

    public String getFieldSegmentId() {
        return this.fieldSegmentId;
    }

    public FieldSegment setFieldSegmentId(String str) {
        this.fieldSegmentId = str;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public int getSegment() {
        return this.segment;
    }

    public void setSegment(int i) {
        this.segment = i;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public int getStartSegment() {
        return this.startSegment;
    }

    public void setStartSegment(int i) {
        this.startSegment = i;
    }

    public int getEndSegment() {
        return this.endSegment;
    }

    public void setEndSegment(int i) {
        this.endSegment = i;
    }

    public int getFieldNo() {
        return this.fieldNo;
    }

    public void setFieldNo(int i) {
        this.fieldNo = i;
    }

    public long getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(long j) {
        this.fieldId = j;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FieldSegment fieldSegment = (FieldSegment) obj;
        if (this.price == fieldSegment.price && this.segment == fieldSegment.segment && this.step == fieldSegment.step && this.startSegment == fieldSegment.startSegment && this.endSegment == fieldSegment.endSegment && this.fieldNo == fieldSegment.fieldNo && this.fieldId == fieldSegment.fieldId && Objects.equals(this.state, fieldSegment.state) && Objects.equals(this.fieldSegmentId, fieldSegment.fieldSegmentId)) {
            return Objects.equals(this.fieldName, fieldSegment.fieldName);
        }
        return false;
    }
}
