package cn.xports.qd2.entity;

import java.io.Serializable;

public class Option<T> implements Serializable {
    protected T data;
    protected boolean isSelect;
    protected String name;
    protected String value;

    public boolean isSelect() {
        return this.isSelect;
    }

    public Option<T> setSelect(boolean z) {
        this.isSelect = z;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Option<T> setName(String str) {
        this.name = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Option<T> setValue(String str) {
        this.value = str;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Option<T> setData(T t) {
        this.data = t;
        return this;
    }
}
