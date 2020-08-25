package cn.xports.entity;

import java.io.Serializable;

public class AdditionalService implements Serializable {
    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
