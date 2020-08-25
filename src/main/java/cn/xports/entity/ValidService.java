package cn.xports.entity;

import java.io.Serializable;

public class ValidService implements Serializable {
    private String code;
    private String dataType;
    private String format;
    private String id;
    private String isSearchable;
    private String name;
    private String value;
    private String valueName;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getValueName() {
        return this.valueName;
    }

    public void setValueName(String str) {
        this.valueName = str;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String str) {
        this.dataType = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getIsSearchable() {
        return this.isSearchable;
    }

    public void setIsSearchable(String str) {
        this.isSearchable = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
