package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LongCodec implements ObjectSerializer, ObjectDeserializer {
    public static LongCodec instance = new LongCodec();

    public int getFastMatchToken() {
        return 2;
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        long longValue = ((Long) obj).longValue();
        serializeWriter.writeLong(longValue);
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName) && longValue <= 2147483647L && longValue >= -2147483648L && type != Long.class && type != Long.TYPE) {
            serializeWriter.write(76);
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        try {
            int i = jSONLexer.token();
            if (i == 2) {
                long longValue = jSONLexer.longValue();
                jSONLexer.nextToken(16);
                t = Long.valueOf(longValue);
            } else {
                if (i == 12) {
                    JSONObject jSONObject = new JSONObject(true);
                    defaultJSONParser.parseObject((Map) jSONObject);
                    t = TypeUtils.castToLong(jSONObject);
                } else {
                    t = TypeUtils.castToLong(defaultJSONParser.parse());
                }
                if (t == null) {
                    return null;
                }
            }
            return type == AtomicLong.class ? new AtomicLong(t.longValue()) : t;
        } catch (Exception e) {
            throw new JSONException("parseLong error, field : " + obj, e);
        }
    }
}
