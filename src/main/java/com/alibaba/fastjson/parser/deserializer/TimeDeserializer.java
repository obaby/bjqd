package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.sql.Time;

public class TimeDeserializer implements ObjectDeserializer {
    public static final TimeDeserializer instance = new TimeDeserializer();

    public int getFastMatchToken() {
        return 2;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        long j;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 16) {
            jSONLexer.nextToken(4);
            if (jSONLexer.token() == 4) {
                jSONLexer.nextTokenWithColon(2);
                if (jSONLexer.token() == 2) {
                    long longValue = jSONLexer.longValue();
                    jSONLexer.nextToken(13);
                    if (jSONLexer.token() == 13) {
                        jSONLexer.nextToken(16);
                        return new Time(longValue);
                    }
                    throw new JSONException("syntax error");
                }
                throw new JSONException("syntax error");
            }
            throw new JSONException("syntax error");
        }
        T parse = defaultJSONParser.parse();
        if (parse == null) {
            return null;
        }
        if (parse instanceof Time) {
            return parse;
        }
        if (parse instanceof Number) {
            return new Time(((Number) parse).longValue());
        }
        if (parse instanceof String) {
            String str = (String) parse;
            if (str.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(str);
            if (jSONScanner.scanISO8601DateIfMatch()) {
                j = jSONScanner.getCalendar().getTimeInMillis();
            } else {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= str.length()) {
                        z = true;
                        break;
                    }
                    char charAt = str.charAt(i);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    }
                    i++;
                }
                if (!z) {
                    jSONScanner.close();
                    return Time.valueOf(str);
                }
                j = Long.parseLong(str);
            }
            jSONScanner.close();
            return new Time(j);
        }
        throw new JSONException("parse error");
    }
}
