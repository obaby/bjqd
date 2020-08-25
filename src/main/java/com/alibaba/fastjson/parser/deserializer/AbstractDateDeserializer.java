package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractDateDeserializer extends ContextObjectDeserializer implements ObjectDeserializer {
    /* access modifiers changed from: protected */
    public abstract <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, (String) null, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i) {
        SimpleDateFormat simpleDateFormat;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        Object obj2 = null;
        if (jSONLexer.token() == 2) {
            obj2 = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
        } else if (jSONLexer.token() == 4) {
            String stringVal = jSONLexer.stringVal();
            if (str != null) {
                try {
                    simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                } catch (IllegalArgumentException unused) {
                    if (str.equals("yyyy-MM-ddTHH:mm:ss.SSS")) {
                        str = "yyyy-MM-dd'T'HH:mm:ss.SSS";
                        simpleDateFormat = new SimpleDateFormat(str);
                    } else if (str.equals("yyyy-MM-ddTHH:mm:ss")) {
                        str = "yyyy-MM-dd'T'HH:mm:ss";
                        simpleDateFormat = new SimpleDateFormat(str);
                    } else {
                        simpleDateFormat = null;
                    }
                }
                try {
                    obj2 = simpleDateFormat.parse(stringVal);
                } catch (ParseException unused2) {
                    if (str.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && stringVal.length() == 19) {
                        try {
                            obj2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(stringVal);
                        } catch (ParseException unused3) {
                        }
                    }
                }
            }
            if (obj2 == null) {
                jSONLexer.nextToken(16);
                String str2 = stringVal;
                if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner jSONScanner = new JSONScanner(stringVal);
                    Date date = stringVal;
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        date = jSONScanner.getCalendar().getTime();
                    }
                    jSONScanner.close();
                    str2 = date;
                }
                obj2 = str2;
            }
        } else if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
        } else if (jSONLexer.token() == 12) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 4) {
                if (JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Class<?> checkAutoType = defaultJSONParser.getConfig().checkAutoType(jSONLexer.stringVal(), (Class<?>) null, jSONLexer.getFeatures());
                    if (checkAutoType != null) {
                        type = checkAutoType;
                    }
                    defaultJSONParser.accept(4);
                    defaultJSONParser.accept(16);
                }
                jSONLexer.nextTokenWithColon(2);
                if (jSONLexer.token() == 2) {
                    long longValue = jSONLexer.longValue();
                    jSONLexer.nextToken();
                    obj2 = Long.valueOf(longValue);
                    defaultJSONParser.accept(13);
                } else {
                    throw new JSONException("syntax error : " + jSONLexer.tokenName());
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else if (defaultJSONParser.getResolveStatus() == 2) {
            defaultJSONParser.setResolveStatus(0);
            defaultJSONParser.accept(16);
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            } else if ("val".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                obj2 = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            obj2 = defaultJSONParser.parse();
        }
        return cast(defaultJSONParser, type, obj, obj2);
    }
}
