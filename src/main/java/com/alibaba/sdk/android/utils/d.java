package com.alibaba.sdk.android.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: Toolkit */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f497a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a  reason: collision with other method in class */
    public static boolean m12a(String str) {
        return str == null || str.length() == 0;
    }

    public static void a(double d) {
        try {
            Thread.sleep((long) (d * 1000.0d));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String a(String str) throws NoSuchAlgorithmException {
        return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
    }

    public static String b(String str) throws NoSuchAlgorithmException {
        return a(MessageDigest.getInstance("SHA-1").digest(str.getBytes()));
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(f497a[(bArr[i] & 240) >>> 4]);
            sb.append(f497a[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
