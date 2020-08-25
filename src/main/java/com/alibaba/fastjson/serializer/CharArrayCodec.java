package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;

public class CharArrayCodec implements ObjectDeserializer {
    public int getFastMatchToken() {
        return 4;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser);
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [char[], T] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r5) {
        /*
            com.alibaba.fastjson.parser.JSONLexer r0 = r5.lexer
            int r1 = r0.token()
            r2 = 16
            r3 = 4
            if (r1 != r3) goto L_0x0017
            java.lang.String r5 = r0.stringVal()
            r0.nextToken(r2)
            char[] r5 = r5.toCharArray()
            return r5
        L_0x0017:
            int r1 = r0.token()
            r3 = 2
            if (r1 != r3) goto L_0x002e
            java.lang.Number r5 = r0.integerValue()
            r0.nextToken(r2)
            java.lang.String r5 = r5.toString()
            char[] r5 = r5.toCharArray()
            return r5
        L_0x002e:
            java.lang.Object r5 = r5.parse()
            boolean r0 = r5 instanceof java.lang.String
            if (r0 == 0) goto L_0x003d
            java.lang.String r5 = (java.lang.String) r5
            char[] r5 = r5.toCharArray()
            return r5
        L_0x003d:
            boolean r0 = r5 instanceof java.util.Collection
            if (r0 == 0) goto L_0x008c
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r0 = r5.iterator()
        L_0x0047:
            boolean r1 = r0.hasNext()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0060
            java.lang.Object r1 = r0.next()
            boolean r4 = r1 instanceof java.lang.String
            if (r4 == 0) goto L_0x0047
            java.lang.String r1 = (java.lang.String) r1
            int r1 = r1.length()
            if (r1 == r3) goto L_0x0047
            r3 = 0
        L_0x0060:
            if (r3 == 0) goto L_0x0084
            int r0 = r5.size()
            char[] r0 = new char[r0]
            java.util.Iterator r5 = r5.iterator()
            r1 = 0
        L_0x006d:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x0083
            java.lang.Object r3 = r5.next()
            int r4 = r1 + 1
            java.lang.String r3 = (java.lang.String) r3
            char r3 = r3.charAt(r2)
            r0[r1] = r3
            r1 = r4
            goto L_0x006d
        L_0x0083:
            return r0
        L_0x0084:
            com.alibaba.fastjson.JSONException r5 = new com.alibaba.fastjson.JSONException
            java.lang.String r0 = "can not cast to char[]"
            r5.<init>(r0)
            throw r5
        L_0x008c:
            if (r5 != 0) goto L_0x0090
            r5 = 0
            goto L_0x0098
        L_0x0090:
            java.lang.String r5 = com.alibaba.fastjson.JSON.toJSONString(r5)
            char[] r5 = r5.toCharArray()
        L_0x0098:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.CharArrayCodec.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser):java.lang.Object");
    }
}
