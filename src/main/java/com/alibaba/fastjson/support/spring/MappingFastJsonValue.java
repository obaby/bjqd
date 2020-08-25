package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

@Deprecated
public class MappingFastJsonValue implements JSONSerializable {
    private static final int BrowserSecureMask = SerializerFeature.BrowserSecure.mask;
    private static final String SECURITY_PREFIX = "/**/";
    private String jsonpFunction;
    private Object value;

    public MappingFastJsonValue(Object obj) {
        this.value = obj;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public void setJsonpFunction(String str) {
        this.jsonpFunction = str;
    }

    public String getJsonpFunction() {
        return this.jsonpFunction;
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (this.jsonpFunction == null) {
            jSONSerializer.write(this.value);
            return;
        }
        if ((BrowserSecureMask & i) != 0 || serializeWriter.isEnabled(BrowserSecureMask)) {
            serializeWriter.write(SECURITY_PREFIX);
        }
        serializeWriter.write(this.jsonpFunction);
        serializeWriter.write(40);
        jSONSerializer.write(this.value);
        serializeWriter.write(41);
    }
}
