package com.adffice.library.dbhelper.table;

public class KeyValue {
    private String key;
    private Object value;

    public KeyValue(String str, Object obj) {
        this.key = str;
        this.value = obj;
    }

    public KeyValue() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }
}
