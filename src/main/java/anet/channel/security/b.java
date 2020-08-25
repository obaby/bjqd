package anet.channel.security;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.util.ALog;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
class b implements ISecurity {

    /* renamed from: a  reason: collision with root package name */
    private static String f223a = "awcn.DefaultSecurityGuard";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f224b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, Integer> f225c;
    private String d = null;

    public boolean isSecOff() {
        return false;
    }

    static {
        try {
            Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            f224b = true;
            f225c = new HashMap();
            f225c.put(ISecurity.SIGN_ALGORITHM_HMAC_SHA1, 3);
            f225c.put(ISecurity.CIPHER_ALGORITHM_AES128, 16);
        } catch (Throwable unused) {
            f224b = false;
        }
    }

    b(String str) {
        this.d = str;
    }

    public String sign(Context context, String str, String str2, String str3) {
        if (!f224b || context == null || TextUtils.isEmpty(str2) || !f225c.containsKey(str)) {
            return null;
        }
        try {
            ISecureSignatureComponent secureSignatureComp = SecurityGuardManager.getInstance(context).getSecureSignatureComp();
            if (secureSignatureComp != null) {
                SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                securityGuardParamContext.appKey = str2;
                securityGuardParamContext.paramMap.put("INPUT", str3);
                securityGuardParamContext.requestType = f225c.get(str).intValue();
                return secureSignatureComp.signRequest(securityGuardParamContext, this.d);
            }
        } catch (Throwable th) {
            ALog.e(f223a, "Securityguard sign request failed.", (String) null, th, new Object[0]);
        }
        return null;
    }

    public byte[] decrypt(Context context, String str, String str2, byte[] bArr) {
        Integer num;
        IStaticDataEncryptComponent staticDataEncryptComp;
        if (!f224b || context == null || bArr == null || TextUtils.isEmpty(str2) || !f225c.containsKey(str) || (num = f225c.get(str)) == null) {
            return null;
        }
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            if (!(instance == null || (staticDataEncryptComp = instance.getStaticDataEncryptComp()) == null)) {
                return staticDataEncryptComp.staticBinarySafeDecryptNoB64(num.intValue(), str2, bArr, this.d);
            }
        } catch (Throwable th) {
            ALog.e(f223a, "staticBinarySafeDecryptNoB64", (String) null, th, new Object[0]);
        }
        return null;
    }

    public boolean saveBytes(Context context, String str, byte[] bArr) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        if (context == null || bArr == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            if (instance == null || (dynamicDataStoreComp = instance.getDynamicDataStoreComp()) == null || dynamicDataStoreComp.putByteArray(str, bArr) == 0) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            ALog.e(f223a, "saveBytes", (String) null, th, new Object[0]);
            return false;
        }
    }

    public byte[] getBytes(Context context, String str) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            if (instance == null || (dynamicDataStoreComp = instance.getDynamicDataStoreComp()) == null) {
                return null;
            }
            return dynamicDataStoreComp.getByteArray(str);
        } catch (Throwable th) {
            ALog.e(f223a, "getBytes", (String) null, th, new Object[0]);
            return null;
        }
    }
}
