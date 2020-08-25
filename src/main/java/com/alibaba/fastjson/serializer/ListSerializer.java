package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    /* JADX INFO: finally extract failed */
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        boolean z;
        int i2;
        Object obj3;
        JSONSerializer jSONSerializer2 = jSONSerializer;
        Object obj4 = obj;
        int i3 = i;
        boolean z2 = jSONSerializer2.out.isEnabled(SerializerFeature.WriteClassName) || SerializerFeature.isEnabled(i3, SerializerFeature.WriteClassName);
        SerializeWriter serializeWriter = jSONSerializer2.out;
        Type type2 = null;
        if (z2) {
            type2 = TypeUtils.getCollectionItemType(type);
        }
        Type type3 = type2;
        if (obj4 == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List) obj4;
        if (list.size() == 0) {
            serializeWriter.append((CharSequence) "[]");
            return;
        }
        SerialContext serialContext = jSONSerializer2.context;
        jSONSerializer2.setContext(serialContext, obj4, obj2, 0);
        try {
            char c2 = ',';
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                serializeWriter.append('[');
                jSONSerializer.incrementIndent();
                int i4 = 0;
                for (Object next : list) {
                    if (i4 != 0) {
                        serializeWriter.append(c2);
                    }
                    jSONSerializer.println();
                    if (next == null) {
                        jSONSerializer2.out.writeNull();
                    } else if (jSONSerializer2.containsReference(next)) {
                        jSONSerializer2.writeReference(next);
                    } else {
                        ObjectSerializer objectWriter = jSONSerializer2.getObjectWriter(next.getClass());
                        SerialContext serialContext2 = r1;
                        SerialContext serialContext3 = new SerialContext(serialContext, obj, obj2, 0, 0);
                        jSONSerializer2.context = serialContext2;
                        objectWriter.write(jSONSerializer, next, Integer.valueOf(i4), type3, i);
                    }
                    i4++;
                    Object obj5 = obj;
                    c2 = ',';
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.append(']');
                jSONSerializer2.context = serialContext;
                return;
            }
            char c3 = ']';
            serializeWriter.append('[');
            int size = list.size();
            int i5 = 0;
            while (i5 < size) {
                Object obj6 = list.get(i5);
                if (i5 != 0) {
                    serializeWriter.append(',');
                }
                if (obj6 == null) {
                    serializeWriter.append((CharSequence) "null");
                } else {
                    Class<?> cls = obj6.getClass();
                    if (cls == Integer.class) {
                        serializeWriter.writeInt(((Integer) obj6).intValue());
                    } else if (cls == Long.class) {
                        long longValue = ((Long) obj6).longValue();
                        if (z2) {
                            serializeWriter.writeLong(longValue);
                            serializeWriter.write(76);
                        } else {
                            serializeWriter.writeLong(longValue);
                        }
                    } else {
                        if ((SerializerFeature.DisableCircularReferenceDetect.mask & i3) != 0) {
                            i2 = i5;
                            jSONSerializer2.getObjectWriter(obj6.getClass()).write(jSONSerializer, obj6, Integer.valueOf(i5), type3, i);
                            z = z2;
                        } else {
                            i2 = i5;
                            if (!serializeWriter.disableCircularReferenceDetect) {
                                obj3 = obj6;
                                z = z2;
                                SerialContext serialContext4 = r1;
                                SerialContext serialContext5 = new SerialContext(serialContext, obj, obj2, 0, 0);
                                jSONSerializer2.context = serialContext4;
                            } else {
                                obj3 = obj6;
                                z = z2;
                            }
                            if (jSONSerializer2.containsReference(obj3)) {
                                jSONSerializer2.writeReference(obj3);
                            } else {
                                ObjectSerializer objectWriter2 = jSONSerializer2.getObjectWriter(obj3.getClass());
                                if ((SerializerFeature.WriteClassName.mask & i3) == 0 || !(objectWriter2 instanceof JavaBeanSerializer)) {
                                    objectWriter2.write(jSONSerializer, obj3, Integer.valueOf(i2), type3, i);
                                } else {
                                    ((JavaBeanSerializer) objectWriter2).writeNoneASM(jSONSerializer, obj3, Integer.valueOf(i2), type3, i);
                                }
                            }
                        }
                        i5 = i2 + 1;
                        z2 = z;
                        c3 = ']';
                    }
                }
                i2 = i5;
                z = z2;
                i5 = i2 + 1;
                z2 = z;
                c3 = ']';
            }
            serializeWriter.append(c3);
            jSONSerializer2.context = serialContext;
        } catch (Throwable th) {
            jSONSerializer2.context = serialContext;
            throw th;
        }
    }
}
