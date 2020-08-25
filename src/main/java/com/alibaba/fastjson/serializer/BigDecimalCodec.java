package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalCodec implements ObjectSerializer, ObjectDeserializer {
    public static final BigDecimalCodec instance = new BigDecimalCodec();

    public int getFastMatchToken() {
        return 2;
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        String str;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        if (serializeWriter.isEnabled(SerializerFeature.WriteBigDecimalAsPlain)) {
            str = bigDecimal.toPlainString();
        } else {
            str = bigDecimal.toString();
        }
        serializeWriter.write(str);
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName) && type != BigDecimal.class && bigDecimal.scale() == 0) {
            serializeWriter.write(46);
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            return deserialze(defaultJSONParser);
        } catch (Exception e) {
            throw new JSONException("parseDecimal error, field : " + obj, e);
        }
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            T decimalValue = jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            return decimalValue;
        } else if (jSONLexer.token() == 3) {
            T decimalValue2 = jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            return decimalValue2;
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return TypeUtils.castToBigDecimal(parse);
        }
    }
}
