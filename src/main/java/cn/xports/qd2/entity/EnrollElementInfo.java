package cn.xports.qd2.entity;

import java.io.Serializable;

public class EnrollElementInfo implements Serializable {
    private String elementId;
    private String value;

    public String getElementId() {
        return this.elementId;
    }

    public EnrollElementInfo setElementId(String str) {
        this.elementId = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public EnrollElementInfo setValue(String str) {
        this.value = str;
        return this;
    }
}
