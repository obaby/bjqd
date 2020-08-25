package com.alibaba.fastjson.serializer;

public class ClobSeriliazer implements ObjectSerializer {
    public static final ClobSeriliazer instance = new ClobSeriliazer();

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        throw new com.alibaba.fastjson.JSONException("read string from reader error", r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        throw new java.io.IOException("write clob error", r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0006, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:1:0x0002, B:8:0x0015] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r2, java.lang.Object r3, java.lang.Object r4, java.lang.reflect.Type r5, int r6) throws java.io.IOException {
        /*
            r1 = this;
            if (r3 != 0) goto L_0x0008
            r2.writeNull()     // Catch:{ SQLException -> 0x0006 }
            return
        L_0x0006:
            r2 = move-exception
            goto L_0x0037
        L_0x0008:
            java.sql.Clob r3 = (java.sql.Clob) r3     // Catch:{ SQLException -> 0x0006 }
            java.io.Reader r3 = r3.getCharacterStream()     // Catch:{ SQLException -> 0x0006 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLException -> 0x0006 }
            r4.<init>()     // Catch:{ SQLException -> 0x0006 }
            r5 = 2048(0x800, float:2.87E-42)
            char[] r5 = new char[r5]     // Catch:{ Exception -> 0x002e }
        L_0x0017:
            int r6 = r5.length     // Catch:{ Exception -> 0x002e }
            r0 = 0
            int r6 = r3.read(r5, r0, r6)     // Catch:{ Exception -> 0x002e }
            if (r6 >= 0) goto L_0x002a
            java.lang.String r4 = r4.toString()     // Catch:{ SQLException -> 0x0006 }
            r3.close()     // Catch:{ SQLException -> 0x0006 }
            r2.write((java.lang.String) r4)     // Catch:{ SQLException -> 0x0006 }
            return
        L_0x002a:
            r4.append(r5, r0, r6)     // Catch:{ Exception -> 0x002e }
            goto L_0x0017
        L_0x002e:
            r2 = move-exception
            com.alibaba.fastjson.JSONException r3 = new com.alibaba.fastjson.JSONException     // Catch:{ SQLException -> 0x0006 }
            java.lang.String r4 = "read string from reader error"
            r3.<init>(r4, r2)     // Catch:{ SQLException -> 0x0006 }
            throw r3     // Catch:{ SQLException -> 0x0006 }
        L_0x0037:
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r4 = "write clob error"
            r3.<init>(r4, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ClobSeriliazer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int):void");
    }
}
