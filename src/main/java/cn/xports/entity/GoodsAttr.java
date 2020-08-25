package cn.xports.entity;

import java.io.Serializable;

public class GoodsAttr implements Serializable {
    private int attrId;
    private String code;
    private int goodsId;
    private int id;
    private String value;
    private String valueType;

    public int getAttrId() {
        return this.attrId;
    }

    public void setAttrId(int i) {
        this.attrId = i;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(int i) {
        this.goodsId = i;
    }

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String str) {
        this.valueType = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
