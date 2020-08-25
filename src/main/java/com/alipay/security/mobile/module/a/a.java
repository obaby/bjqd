package com.alipay.security.mobile.module.a;

import android.os.Environment;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.zip.GZIPOutputStream;

public final class a {
    public static File a() {
        try {
            return (File) Environment.class.getMethod(new String(com.alipay.security.mobile.module.a.a.a.a("Z2V0RXh0ZXJuYWxTdG9yYWdlRGlyZWN0b3J5")), new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r0.get(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.util.Map<java.lang.String, java.lang.String> r0, java.lang.String r1, java.lang.String r2) {
        /*
            if (r0 != 0) goto L_0x0003
            return r2
        L_0x0003:
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x000c
            return r2
        L_0x000c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.a.a.a(java.util.Map, java.lang.String, java.lang.String):java.lang.String");
    }

    public static boolean a(String str) {
        int length;
        if (!(str == null || (length = str.length()) == 0)) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public static String b(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
        } catch (Exception unused) {
            return str2;
        }
    }

    public static boolean b(String str) {
        return !a(str);
    }

    public static boolean c(String str) {
        for (byte b2 : str.getBytes()) {
            if ((b2 >= 0 && b2 <= 31) || b2 >= Byte.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    public static String d(String str) {
        return str == null ? "" : str;
    }

    public static String e(String str) {
        try {
            if (a(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String f(String str) {
        try {
            byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(str.length()).array();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes("UTF-8"));
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            byte[] bArr = new byte[(byteArrayOutputStream.toByteArray().length + 4)];
            System.arraycopy(array, 0, bArr, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr, 4, byteArrayOutputStream.toByteArray().length);
            return Base64.encodeToString(bArr, 8);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String g(String str) {
        if (a(str)) {
            return "";
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    gZIPOutputStream.write(bArr, 0, read);
                } else {
                    gZIPOutputStream.flush();
                    gZIPOutputStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    byteArrayInputStream.close();
                    return new String(Base64.encode(byteArray, 2));
                }
            }
        } catch (Exception unused) {
            return "";
        }
    }
}
