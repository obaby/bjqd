package com.alipay.security.mobile.module.a.a;

import java.lang.reflect.Method;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static String f759a = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

    public static String a() {
        String str = new String();
        for (int i = 0; i < f759a.length() - 1; i += 4) {
            str = str + f759a.charAt(i);
        }
        return str;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0068, code lost:
        return b(a(a(r7.getBytes()), r8.getBytes()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0069, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0054 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r7, java.lang.String r8) {
        /*
            javax.crypto.spec.PBEKeySpec r0 = a((java.lang.String) r7)     // Catch:{ Exception -> 0x0054 }
            byte[] r1 = r8.getBytes()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r2 = "PBKDF2WithHmacSHA1"
            javax.crypto.SecretKeyFactory r2 = javax.crypto.SecretKeyFactory.getInstance(r2)     // Catch:{ Exception -> 0x0054 }
            javax.crypto.SecretKey r2 = r2.generateSecret(r0)     // Catch:{ Exception -> 0x0054 }
            byte[] r2 = r2.getEncoded()     // Catch:{ Exception -> 0x0054 }
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch:{ Exception -> 0x0054 }
            java.lang.String r4 = "AES"
            r3.<init>(r2, r4)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r2 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch:{ Exception -> 0x0054 }
            r4 = 1
            javax.crypto.spec.IvParameterSpec r5 = new javax.crypto.spec.IvParameterSpec     // Catch:{ Exception -> 0x0054 }
            int r6 = r2.getBlockSize()     // Catch:{ Exception -> 0x0054 }
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x0054 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0054 }
            r2.init(r4, r3, r5)     // Catch:{ Exception -> 0x0054 }
            byte[] r0 = r0.getSalt()     // Catch:{ Exception -> 0x0054 }
            int r3 = r0.length     // Catch:{ Exception -> 0x0054 }
            int r4 = r1.length     // Catch:{ Exception -> 0x0054 }
            int r4 = r2.getOutputSize(r4)     // Catch:{ Exception -> 0x0054 }
            int r3 = r3 + r4
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ Exception -> 0x0054 }
            r3.put(r0)     // Catch:{ Exception -> 0x0054 }
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r1)     // Catch:{ Exception -> 0x0054 }
            r2.doFinal(r0, r3)     // Catch:{ Exception -> 0x0054 }
            byte[] r0 = r3.array()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r0 = b((byte[]) r0)     // Catch:{ Exception -> 0x0054 }
            return r0
        L_0x0054:
            byte[] r7 = r7.getBytes()     // Catch:{ Exception -> 0x0069 }
            byte[] r7 = a((byte[]) r7)     // Catch:{ Exception -> 0x0069 }
            byte[] r8 = r8.getBytes()     // Catch:{ Exception -> 0x0069 }
            byte[] r7 = a((byte[]) r7, (byte[]) r8)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r7 = b((byte[]) r7)     // Catch:{ Exception -> 0x0069 }
            return r7
        L_0x0069:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.a.c.a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.crypto.spec.PBEKeySpec a(java.lang.String r8) {
        /*
            java.lang.String r0 = new java.lang.String
            java.lang.String r1 = "amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20="
            byte[] r1 = com.alipay.security.mobile.module.a.a.a.a(r1)
            r0.<init>(r1)
            java.lang.Class r0 = java.lang.Class.forName(r0)
            java.lang.Object r1 = r0.newInstance()
            r2 = 16
            byte[] r2 = new byte[r2]
            java.lang.String r3 = "nextBytes"
            r4 = 1
            java.lang.Class[] r5 = new java.lang.Class[r4]
            java.lang.Class r6 = r2.getClass()
            r7 = 0
            r5[r7] = r6
            java.lang.reflect.Method r0 = r0.getMethod(r3, r5)
            r0.setAccessible(r4)
            java.lang.Object[] r3 = new java.lang.Object[r4]
            r3[r7] = r2
            r0.invoke(r1, r3)
            javax.crypto.spec.PBEKeySpec r0 = new javax.crypto.spec.PBEKeySpec
            char[] r8 = r8.toCharArray()
            r1 = 10
            r3 = 128(0x80, float:1.794E-43)
            r0.<init>(r8, r2, r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.a.c.a(java.lang.String):javax.crypto.spec.PBEKeySpec");
    }

    private static byte[] a(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        Class<?> cls = Class.forName(new String(a.a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object invoke = cls.getMethod("getInstance", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{"SHA1PRNG", "Crypto"});
        Method method = cls.getMethod("setSeed", new Class[]{bArr.getClass()});
        method.setAccessible(true);
        method.invoke(invoke, new Object[]{bArr});
        KeyGenerator.class.getMethod("init", new Class[]{Integer.TYPE, cls}).invoke(instance, new Object[]{128, invoke});
        return instance.generateKey().getEncoded();
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return instance.doFinal(bArr2);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:7|(1:9)|12|13|(1:15)(1:16)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0065 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x009b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x009c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r9, java.lang.String r10) {
        /*
            r0 = 2
            r1 = 0
            javax.crypto.spec.PBEKeySpec r2 = a((java.lang.String) r9)     // Catch:{ Exception -> 0x0065 }
            byte[] r3 = b((java.lang.String) r10)     // Catch:{ Exception -> 0x0065 }
            int r4 = r3.length     // Catch:{ Exception -> 0x0065 }
            r5 = 16
            if (r4 > r5) goto L_0x0011
            r2 = r1
            goto L_0x0051
        L_0x0011:
            javax.crypto.spec.PBEKeySpec r4 = new javax.crypto.spec.PBEKeySpec     // Catch:{ Exception -> 0x0065 }
            char[] r2 = r2.getPassword()     // Catch:{ Exception -> 0x0065 }
            byte[] r6 = java.util.Arrays.copyOf(r3, r5)     // Catch:{ Exception -> 0x0065 }
            r7 = 10
            r8 = 128(0x80, float:1.794E-43)
            r4.<init>(r2, r6, r7, r8)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r2 = "PBKDF2WithHmacSHA1"
            javax.crypto.SecretKeyFactory r2 = javax.crypto.SecretKeyFactory.getInstance(r2)     // Catch:{ Exception -> 0x0065 }
            javax.crypto.SecretKey r2 = r2.generateSecret(r4)     // Catch:{ Exception -> 0x0065 }
            byte[] r2 = r2.getEncoded()     // Catch:{ Exception -> 0x0065 }
            javax.crypto.spec.SecretKeySpec r4 = new javax.crypto.spec.SecretKeySpec     // Catch:{ Exception -> 0x0065 }
            java.lang.String r6 = "AES"
            r4.<init>(r2, r6)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r2 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch:{ Exception -> 0x0065 }
            javax.crypto.spec.IvParameterSpec r6 = new javax.crypto.spec.IvParameterSpec     // Catch:{ Exception -> 0x0065 }
            int r7 = r2.getBlockSize()     // Catch:{ Exception -> 0x0065 }
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x0065 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0065 }
            r2.init(r0, r4, r6)     // Catch:{ Exception -> 0x0065 }
            int r4 = r3.length     // Catch:{ Exception -> 0x0065 }
            int r4 = r4 - r5
            byte[] r2 = r2.doFinal(r3, r5, r4)     // Catch:{ Exception -> 0x0065 }
        L_0x0051:
            if (r2 == 0) goto L_0x005f
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0065 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0065 }
            boolean r2 = com.alipay.security.mobile.module.a.a.c(r3)     // Catch:{ Exception -> 0x0065 }
            if (r2 == 0) goto L_0x0065
            return r3
        L_0x005f:
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Exception -> 0x0065 }
            r2.<init>()     // Catch:{ Exception -> 0x0065 }
            throw r2     // Catch:{ Exception -> 0x0065 }
        L_0x0065:
            byte[] r9 = r9.getBytes()     // Catch:{ Exception -> 0x009c }
            byte[] r9 = a((byte[]) r9)     // Catch:{ Exception -> 0x009c }
            byte[] r10 = b((java.lang.String) r10)     // Catch:{ Exception -> 0x009c }
            javax.crypto.spec.SecretKeySpec r2 = new javax.crypto.spec.SecretKeySpec     // Catch:{ Exception -> 0x009c }
            java.lang.String r3 = "AES"
            r2.<init>(r9, r3)     // Catch:{ Exception -> 0x009c }
            java.lang.String r9 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r9 = javax.crypto.Cipher.getInstance(r9)     // Catch:{ Exception -> 0x009c }
            javax.crypto.spec.IvParameterSpec r3 = new javax.crypto.spec.IvParameterSpec     // Catch:{ Exception -> 0x009c }
            int r4 = r9.getBlockSize()     // Catch:{ Exception -> 0x009c }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x009c }
            r3.<init>(r4)     // Catch:{ Exception -> 0x009c }
            r9.init(r0, r2, r3)     // Catch:{ Exception -> 0x009c }
            byte[] r9 = r9.doFinal(r10)     // Catch:{ Exception -> 0x009c }
            java.lang.String r10 = new java.lang.String     // Catch:{ Exception -> 0x009c }
            r10.<init>(r9)     // Catch:{ Exception -> 0x009c }
            boolean r9 = com.alipay.security.mobile.module.a.a.c(r10)     // Catch:{ Exception -> 0x009c }
            if (r9 == 0) goto L_0x009c
            return r10
        L_0x009c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.a.c.b(java.lang.String, java.lang.String):java.lang.String");
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b2 >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(b2 & 15));
        }
        return stringBuffer.toString();
    }

    private static byte[] b(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}
