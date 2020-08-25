package anet.channel.util;

import com.alipay.sdk.util.j;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Taobao */
public class HMacUtil {
    private static final String TAG = "awcn.HMacUtil";

    public static String hmacSha1Hex(byte[] bArr, byte[] bArr2) {
        try {
            return StringUtils.bytesToHexString(hmacSha1(bArr, bArr2));
        } catch (Throwable th) {
            ALog.e(TAG, "hmacSha1Hex", (String) null, j.f740c, "", th);
            return "";
        }
    }

    private static byte[] hmacSha1(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "HmacSHA256");
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
