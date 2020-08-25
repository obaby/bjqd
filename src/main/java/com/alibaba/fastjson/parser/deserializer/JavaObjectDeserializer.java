package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;

public class JavaObjectDeserializer implements ObjectDeserializer {
    public static final JavaObjectDeserializer instance = new JavaObjectDeserializer();

    public int getFastMatchToken() {
        return 12;
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [T, java.lang.Object[]] */
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (genericComponentType instanceof TypeVariable) {
                genericComponentType = ((TypeVariable) genericComponentType).getBounds()[0];
            }
            ArrayList arrayList = new ArrayList();
            defaultJSONParser.parseArray(genericComponentType, (Collection) arrayList);
            if (!(genericComponentType instanceof Class)) {
                return arrayList.toArray();
            }
            ? r2 = (Object[]) Array.newInstance((Class) genericComponentType, arrayList.size());
            arrayList.toArray(r2);
            return r2;
        } else if (!(type instanceof Class) || type == Object.class || type == Serializable.class) {
            return defaultJSONParser.parse(obj);
        } else {
            return defaultJSONParser.parseObject(type);
        }
    }
}
