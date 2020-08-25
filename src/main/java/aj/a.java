package aj;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f97a;

    private a() {
    }

    public static a a() {
        if (f97a == null) {
            f97a = new a();
        }
        return f97a;
    }

    public String a(String str, String str2, String str3) {
        if (str2 == null) {
            return null;
        }
        try {
            if (str2.length() != 16) {
                return null;
            }
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(str2.getBytes(), "AES"), new IvParameterSpec(str3.getBytes()));
            return Base64.encodeToString(instance.doFinal(str.getBytes("utf-8")), 0);
        } catch (Exception unused) {
            return "异常了····";
        }
    }

    public String b(String str, String str2, String str3) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("ASCII"), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(str3.getBytes()));
            return new String(instance.doFinal(Base64.decode(str, 0)), "utf-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append((char) (((bArr[i] >> 4) & 15) + 97));
            stringBuffer.append((char) ((bArr[i] & 15) + 97));
        }
        return stringBuffer.toString();
    }
}
