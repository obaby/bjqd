package com.alibaba.fastjson.serializer;

import com.alibaba.android.arouter.utils.Consts;

public class SerialContext {
    public final int features;
    public final Object fieldName;
    public final Object object;
    public final SerialContext parent;

    public SerialContext(SerialContext serialContext, Object obj, Object obj2, int i, int i2) {
        this.parent = serialContext;
        this.object = obj;
        this.fieldName = obj2;
        this.features = i;
    }

    public String toString() {
        if (this.parent == null) {
            return "$";
        }
        if (this.fieldName instanceof Integer) {
            return this.parent.toString() + "[" + this.fieldName + "]";
        }
        return this.parent.toString() + Consts.DOT + this.fieldName;
    }

    public SerialContext getParent() {
        return this.parent;
    }

    public Object getObject() {
        return this.object;
    }

    public Object getFieldName() {
        return this.fieldName;
    }

    public String getPath() {
        return toString();
    }
}
