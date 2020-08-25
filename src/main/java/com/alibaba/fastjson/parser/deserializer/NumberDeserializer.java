package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class NumberDeserializer implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    public int getFastMatchToken() {
        return 2;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String numberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return Double.valueOf(Double.parseDouble(numberString));
            }
            long longValue = jSONLexer.longValue();
            jSONLexer.nextToken(16);
            if (type == Short.TYPE || type == Short.class) {
                if (longValue <= 32767 && longValue >= -32768) {
                    return Short.valueOf((short) ((int) longValue));
                }
                throw new JSONException("short overflow : " + longValue);
            } else if (type == Byte.TYPE || type == Byte.class) {
                if (longValue <= 127 && longValue >= -128) {
                    return Byte.valueOf((byte) ((int) longValue));
                }
                throw new JSONException("short overflow : " + longValue);
            } else if (longValue < -2147483648L || longValue > 2147483647L) {
                return Long.valueOf(longValue);
            } else {
                return Integer.valueOf((int) longValue);
            }
        } else if (jSONLexer.token() == 3) {
            if (type == Double.TYPE || type == Double.class) {
                String numberString2 = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return Double.valueOf(Double.parseDouble(numberString2));
            }
            T decimalValue = jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            if (type == Short.TYPE || type == Short.class) {
                if (decimalValue.compareTo(BigDecimal.valueOf(32767)) <= 0 && decimalValue.compareTo(BigDecimal.valueOf(-32768)) >= 0) {
                    return Short.valueOf(decimalValue.shortValue());
                }
                throw new JSONException("short overflow : " + decimalValue);
            } else if (type == Byte.TYPE || type == Byte.class) {
                return Byte.valueOf(decimalValue.byteValue());
            } else {
                return decimalValue;
            }
        } else if (jSONLexer.token() != 18 || !"NaN".equals(jSONLexer.stringVal())) {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            if (type == Double.TYPE || type == Double.class) {
                try {
                    return TypeUtils.castToDouble(parse);
                } catch (Exception e) {
                    throw new JSONException("parseDouble error, field : " + obj, e);
                }
            } else if (type == Short.TYPE || type == Short.class) {
                try {
                    return TypeUtils.castToShort(parse);
                } catch (Exception e2) {
                    throw new JSONException("parseShort error, field : " + obj, e2);
                }
            } else if (type != Byte.TYPE && type != Byte.class) {
                return TypeUtils.castToBigDecimal(parse);
            } else {
                try {
                    return TypeUtils.castToByte(parse);
                } catch (Exception e3) {
                    throw new JSONException("parseByte error, field : " + obj, e3);
                }
            }
        } else {
            jSONLexer.nextToken();
            if (type == Double.class) {
                return Double.valueOf(Double.NaN);
            }
            if (type == Float.class) {
                return Float.valueOf(Float.NaN);
            }
            return null;
        }
    }
}
