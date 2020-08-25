package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

public class EnumDeserializer implements ObjectDeserializer {
    protected final Class<?> enumClass;
    protected long[] enumNameHashCodes;
    protected final Enum[] enums;
    protected final Enum[] ordinalEnums;

    public int getFastMatchToken() {
        return 2;
    }

    public EnumDeserializer(Class<?> cls) {
        JSONField jSONField;
        Class<?> cls2 = cls;
        this.enumClass = cls2;
        this.ordinalEnums = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < this.ordinalEnums.length) {
            Enum enumR = this.ordinalEnums[i];
            String name = enumR.name();
            try {
                jSONField = (JSONField) cls2.getField(name).getAnnotation(JSONField.class);
                if (jSONField != null) {
                    try {
                        String name2 = jSONField.name();
                        if (name2 != null && name2.length() > 0) {
                            name = name2;
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception unused2) {
                jSONField = null;
            }
            int i2 = 0;
            long j = -3750763034362895579L;
            long j2 = -3750763034362895579L;
            while (i2 < name.length()) {
                int charAt = name.charAt(i2);
                long j3 = ((long) charAt) ^ j;
                if (charAt >= 65 && charAt <= 90) {
                    charAt += 32;
                }
                j2 = (((long) charAt) ^ j2) * 1099511628211L;
                i2++;
                j = j3 * 1099511628211L;
            }
            hashMap.put(Long.valueOf(j), enumR);
            if (j != j2) {
                hashMap.put(Long.valueOf(j2), enumR);
            }
            if (jSONField != null) {
                String[] alternateNames = jSONField.alternateNames();
                int length = alternateNames.length;
                int i3 = 0;
                while (i3 < length) {
                    String str = alternateNames[i3];
                    int i4 = 0;
                    long j4 = -3750763034362895579L;
                    while (i4 < str.length()) {
                        j4 = (j4 ^ ((long) str.charAt(i4))) * 1099511628211L;
                        i4++;
                        i = i;
                    }
                    int i5 = i;
                    if (!(j4 == j || j4 == j2)) {
                        hashMap.put(Long.valueOf(j4), enumR);
                    }
                    i3++;
                    i = i5;
                }
            }
            i++;
        }
        this.enumNameHashCodes = new long[hashMap.size()];
        int i6 = 0;
        for (Long longValue : hashMap.keySet()) {
            this.enumNameHashCodes[i6] = longValue.longValue();
            i6++;
        }
        Arrays.sort(this.enumNameHashCodes);
        this.enums = new Enum[this.enumNameHashCodes.length];
        for (int i7 = 0; i7 < this.enumNameHashCodes.length; i7++) {
            this.enums[i7] = (Enum) hashMap.get(Long.valueOf(this.enumNameHashCodes[i7]));
        }
    }

    public Enum getEnumByHashCode(long j) {
        int binarySearch;
        if (this.enums != null && (binarySearch = Arrays.binarySearch(this.enumNameHashCodes, j)) >= 0) {
            return this.enums[binarySearch];
        }
        return null;
    }

    public Enum<?> valueOf(int i) {
        return this.ordinalEnums[i];
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            JSONLexer jSONLexer = defaultJSONParser.lexer;
            int i = jSONLexer.token();
            if (i == 2) {
                int intValue = jSONLexer.intValue();
                jSONLexer.nextToken(16);
                if (intValue >= 0 && intValue <= this.ordinalEnums.length) {
                    return this.ordinalEnums[intValue];
                }
                throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + intValue);
            } else if (i == 4) {
                String stringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (stringVal.length() == 0) {
                    return null;
                }
                long j = -3750763034362895579L;
                for (int i2 = 0; i2 < stringVal.length(); i2++) {
                    j = (j ^ ((long) stringVal.charAt(i2))) * 1099511628211L;
                }
                return getEnumByHashCode(j);
            } else if (i == 8) {
                jSONLexer.nextToken(16);
                return null;
            } else {
                Object parse = defaultJSONParser.parse();
                throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + parse);
            }
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }
}
