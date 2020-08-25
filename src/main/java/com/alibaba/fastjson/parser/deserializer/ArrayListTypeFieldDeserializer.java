package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Map;

public class ArrayListTypeFieldDeserializer extends FieldDeserializer {
    private ObjectDeserializer deserializer;
    private int itemFastMatchToken;
    private final Type itemType;

    public int getFastMatchToken() {
        return 14;
    }

    public ArrayListTypeFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        if (fieldInfo.fieldType instanceof ParameterizedType) {
            Type type = ((ParameterizedType) fieldInfo.fieldType).getActualTypeArguments()[0];
            if (type instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                if (upperBounds.length == 1) {
                    type = upperBounds[0];
                }
            }
            this.itemType = type;
            return;
        }
        this.itemType = Object.class;
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        if (i == 8 || (i == 4 && jSONLexer.stringVal().length() == 0)) {
            setValue(obj, (String) null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ParseContext context = defaultJSONParser.getContext();
        defaultJSONParser.setContext(context, obj, this.fieldInfo.name);
        parseArray(defaultJSONParser, type, arrayList);
        defaultJSONParser.setContext(context);
        if (obj == null) {
            map.put(this.fieldInfo.name, arrayList);
        } else {
            setValue(obj, (Object) arrayList);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseArray(com.alibaba.fastjson.parser.DefaultJSONParser r13, java.lang.reflect.Type r14, java.util.Collection r15) {
        /*
            r12 = this;
            java.lang.reflect.Type r0 = r12.itemType
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r12.deserializer
            boolean r2 = r14 instanceof java.lang.reflect.ParameterizedType
            r3 = 0
            if (r2 == 0) goto L_0x00c1
            boolean r2 = r0 instanceof java.lang.reflect.TypeVariable
            r4 = 0
            r5 = -1
            if (r2 == 0) goto L_0x005e
            r2 = r0
            java.lang.reflect.TypeVariable r2 = (java.lang.reflect.TypeVariable) r2
            java.lang.reflect.ParameterizedType r14 = (java.lang.reflect.ParameterizedType) r14
            java.lang.reflect.Type r6 = r14.getRawType()
            boolean r6 = r6 instanceof java.lang.Class
            if (r6 == 0) goto L_0x0022
            java.lang.reflect.Type r4 = r14.getRawType()
            java.lang.Class r4 = (java.lang.Class) r4
        L_0x0022:
            if (r4 == 0) goto L_0x0044
            java.lang.reflect.TypeVariable[] r6 = r4.getTypeParameters()
            int r6 = r6.length
            r7 = 0
        L_0x002a:
            if (r7 >= r6) goto L_0x0044
            java.lang.reflect.TypeVariable[] r8 = r4.getTypeParameters()
            r8 = r8[r7]
            java.lang.String r8 = r8.getName()
            java.lang.String r9 = r2.getName()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0041
            goto L_0x0045
        L_0x0041:
            int r7 = r7 + 1
            goto L_0x002a
        L_0x0044:
            r7 = -1
        L_0x0045:
            if (r7 == r5) goto L_0x00c1
            java.lang.reflect.Type[] r14 = r14.getActualTypeArguments()
            r0 = r14[r7]
            java.lang.reflect.Type r14 = r12.itemType
            boolean r14 = r0.equals(r14)
            if (r14 != 0) goto L_0x00c1
            com.alibaba.fastjson.parser.ParserConfig r14 = r13.getConfig()
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r14.getDeserializer((java.lang.reflect.Type) r0)
            goto L_0x00c1
        L_0x005e:
            boolean r2 = r0 instanceof java.lang.reflect.ParameterizedType
            if (r2 == 0) goto L_0x00c1
            r2 = r0
            java.lang.reflect.ParameterizedType r2 = (java.lang.reflect.ParameterizedType) r2
            java.lang.reflect.Type[] r6 = r2.getActualTypeArguments()
            int r7 = r6.length
            r8 = 1
            if (r7 != r8) goto L_0x00c1
            r7 = r6[r3]
            boolean r7 = r7 instanceof java.lang.reflect.TypeVariable
            if (r7 == 0) goto L_0x00c1
            r7 = r6[r3]
            java.lang.reflect.TypeVariable r7 = (java.lang.reflect.TypeVariable) r7
            java.lang.reflect.ParameterizedType r14 = (java.lang.reflect.ParameterizedType) r14
            java.lang.reflect.Type r8 = r14.getRawType()
            boolean r8 = r8 instanceof java.lang.Class
            if (r8 == 0) goto L_0x0087
            java.lang.reflect.Type r4 = r14.getRawType()
            java.lang.Class r4 = (java.lang.Class) r4
        L_0x0087:
            if (r4 == 0) goto L_0x00a9
            java.lang.reflect.TypeVariable[] r8 = r4.getTypeParameters()
            int r8 = r8.length
            r9 = 0
        L_0x008f:
            if (r9 >= r8) goto L_0x00a9
            java.lang.reflect.TypeVariable[] r10 = r4.getTypeParameters()
            r10 = r10[r9]
            java.lang.String r10 = r10.getName()
            java.lang.String r11 = r7.getName()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x00a6
            goto L_0x00aa
        L_0x00a6:
            int r9 = r9 + 1
            goto L_0x008f
        L_0x00a9:
            r9 = -1
        L_0x00aa:
            if (r9 == r5) goto L_0x00c1
            java.lang.reflect.Type[] r14 = r14.getActualTypeArguments()
            r14 = r14[r9]
            r6[r3] = r14
            com.alibaba.fastjson.util.ParameterizedTypeImpl r0 = new com.alibaba.fastjson.util.ParameterizedTypeImpl
            java.lang.reflect.Type r14 = r2.getOwnerType()
            java.lang.reflect.Type r2 = r2.getRawType()
            r0.<init>(r6, r14, r2)
        L_0x00c1:
            com.alibaba.fastjson.parser.JSONLexer r14 = r13.lexer
            int r2 = r14.token()
            r4 = 14
            if (r2 != r4) goto L_0x0121
            if (r1 != 0) goto L_0x00df
            com.alibaba.fastjson.parser.ParserConfig r1 = r13.getConfig()
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r1.getDeserializer((java.lang.reflect.Type) r0)
            r12.deserializer = r1
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r12.deserializer
            int r2 = r2.getFastMatchToken()
            r12.itemFastMatchToken = r2
        L_0x00df:
            r2 = r1
            int r1 = r12.itemFastMatchToken
            r14.nextToken(r1)
        L_0x00e5:
            com.alibaba.fastjson.parser.Feature r1 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas
            boolean r1 = r14.isEnabled((com.alibaba.fastjson.parser.Feature) r1)
            r4 = 16
            if (r1 == 0) goto L_0x00f9
        L_0x00ef:
            int r1 = r14.token()
            if (r1 != r4) goto L_0x00f9
            r14.nextToken()
            goto L_0x00ef
        L_0x00f9:
            int r1 = r14.token()
            r5 = 15
            if (r1 != r5) goto L_0x0105
            r14.nextToken(r4)
            goto L_0x013b
        L_0x0105:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.Object r1 = r2.deserialze(r13, r0, r1)
            r15.add(r1)
            r13.checkListResolve(r15)
            int r1 = r14.token()
            if (r1 != r4) goto L_0x011e
            int r1 = r12.itemFastMatchToken
            r14.nextToken(r1)
        L_0x011e:
            int r3 = r3 + 1
            goto L_0x00e5
        L_0x0121:
            if (r1 != 0) goto L_0x012d
            com.alibaba.fastjson.parser.ParserConfig r14 = r13.getConfig()
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r14.getDeserializer((java.lang.reflect.Type) r0)
            r12.deserializer = r1
        L_0x012d:
            java.lang.Integer r14 = java.lang.Integer.valueOf(r3)
            java.lang.Object r14 = r1.deserialze(r13, r0, r14)
            r15.add(r14)
            r13.checkListResolve(r15)
        L_0x013b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer.parseArray(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.util.Collection):void");
    }
}
