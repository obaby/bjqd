package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FloatCodec implements ObjectSerializer, ObjectDeserializer {
    public static FloatCodec instance = new FloatCodec();
    private NumberFormat decimalFormat;

    public int getFastMatchToken() {
        return 2;
    }

    public FloatCodec() {
    }

    public FloatCodec(DecimalFormat decimalFormat2) {
        this.decimalFormat = decimalFormat2;
    }

    public FloatCodec(String str) {
        this(new DecimalFormat(str));
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        float floatValue = ((Float) obj).floatValue();
        if (this.decimalFormat != null) {
            serializeWriter.write(this.decimalFormat.format((double) floatValue));
        } else {
            serializeWriter.writeFloat(floatValue, true);
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            return deserialze(defaultJSONParser);
        } catch (Exception e) {
            throw new JSONException("parseLong error, field : " + obj, e);
        }
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            String numberString = jSONLexer.numberString();
            jSONLexer.nextToken(16);
            return Float.valueOf(Float.parseFloat(numberString));
        } else if (jSONLexer.token() == 3) {
            float floatValue = jSONLexer.floatValue();
            jSONLexer.nextToken(16);
            return Float.valueOf(floatValue);
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return TypeUtils.castToFloat(parse);
        }
    }
}
