package com.alibaba.fastjson.asm;

import com.alipay.sdk.util.h;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class TypeCollector {
    private static final Map<String, String> primitives = new HashMap<String, String>() {
        {
            put("int", "I");
            put("boolean", "Z");
            put("byte", "B");
            put("char", "C");
            put("short", "S");
            put("float", "F");
            put("long", "J");
            put("double", "D");
        }
    };
    protected MethodCollector collector = null;
    private final String methodName;
    private final Class<?>[] parameterTypes;

    public TypeCollector(String str, Class<?>[] clsArr) {
        this.methodName = str;
        this.parameterTypes = clsArr;
    }

    /* access modifiers changed from: protected */
    public MethodCollector visitMethod(int i, String str, String str2) {
        if (this.collector != null || !str.equals(this.methodName)) {
            return null;
        }
        Type[] argumentTypes = Type.getArgumentTypes(str2);
        int i2 = 0;
        for (Type className : argumentTypes) {
            String className2 = className.getClassName();
            if (className2.equals("long") || className2.equals("double")) {
                i2++;
            }
        }
        if (argumentTypes.length != this.parameterTypes.length) {
            return null;
        }
        for (int i3 = 0; i3 < argumentTypes.length; i3++) {
            if (!correctTypeName(argumentTypes[i3], this.parameterTypes[i3].getName())) {
                return null;
            }
        }
        MethodCollector methodCollector = new MethodCollector(Modifier.isStatic(i) ^ true ? 1 : 0, argumentTypes.length + i2);
        this.collector = methodCollector;
        return methodCollector;
    }

    private boolean correctTypeName(Type type, String str) {
        String className = type.getClassName();
        String str2 = "";
        while (className.endsWith("[]")) {
            str2 = str2 + "[";
            className = className.substring(0, className.length() - 2);
        }
        if (!str2.equals("")) {
            if (primitives.containsKey(className)) {
                className = str2 + primitives.get(className);
            } else {
                className = str2 + "L" + className + h.f735b;
            }
        }
        return className.equals(str);
    }

    public String[] getParameterNamesForMethod() {
        if (this.collector == null || !this.collector.debugInfoPresent) {
            return new String[0];
        }
        return this.collector.getResult().split(",");
    }
}
