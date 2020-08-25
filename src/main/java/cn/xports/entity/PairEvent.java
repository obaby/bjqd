package cn.xports.entity;

import java.io.Serializable;

public class PairEvent implements Serializable {
    private Object key1;
    private Object key2;

    public PairEvent(Object obj, Object obj2) {
        this.key1 = obj;
        this.key2 = obj2;
    }

    public PairEvent() {
    }

    public Object getKey1() {
        return this.key1;
    }

    public PairEvent setKey1(Object obj) {
        this.key1 = obj;
        return this;
    }

    public Object getKey2() {
        return this.key2;
    }

    public PairEvent setKey2(Object obj) {
        this.key2 = obj;
        return this;
    }
}
