package com.alibaba.fastjson.serializer;

import com.google.common.collect.Multimap;
import java.io.IOException;
import java.lang.reflect.Type;

public class GuavaCodec implements ObjectSerializer {
    public static GuavaCodec instance = new GuavaCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj instanceof Multimap) {
            jSONSerializer.write((Object) ((Multimap) obj).asMap());
        }
    }
}
