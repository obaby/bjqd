package cn.xports.entity;

import java.io.Serializable;

public class BookingRule implements Serializable {
    private int fieldType;
    private int maxField;
    private int maxHour;
    private int minSerialNum;
    private int piecesUnit;
    private int totalHour;

    public int getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(int i) {
        this.fieldType = i;
    }

    public int getMaxField() {
        return this.maxField;
    }

    public void setMaxField(int i) {
        this.maxField = i;
    }

    public int getMaxHour() {
        return this.maxHour;
    }

    public void setMaxHour(int i) {
        this.maxHour = i;
    }

    public int getPiecesUnit() {
        return this.piecesUnit;
    }

    public void setPiecesUnit(int i) {
        this.piecesUnit = i;
    }

    public int getMinSerialNum() {
        return this.minSerialNum;
    }

    public void setMinSerialNum(int i) {
        this.minSerialNum = i;
    }

    public int getTotalHour() {
        return this.totalHour;
    }

    public void setTotalHour(int i) {
        this.totalHour = i;
    }
}
