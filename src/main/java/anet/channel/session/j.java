package anet.channel.session;

import anet.channel.security.ISecurity;
import anet.channel.util.ALog;
import org.android.spdy.AccsSSLCallback;

/* compiled from: Taobao */
class j implements AccsSSLCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TnetSpdySession f242a;

    j(TnetSpdySession tnetSpdySession) {
        this.f242a = tnetSpdySession;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        byte[] bArr2;
        try {
            bArr2 = this.f242a.F.decrypt(this.f242a.f121a, ISecurity.CIPHER_ALGORITHM_AES128, "tnet_pksg_key", bArr);
            if (bArr2 != null) {
                try {
                    if (ALog.isPrintLog(2)) {
                        ALog.i("getSSLPublicKey", (String) null, "decrypt", new String(bArr2));
                    }
                } catch (Throwable th) {
                    th = th;
                    ALog.e("awcn.TnetSpdySession", "getSSLPublicKey", (String) null, th, new Object[0]);
                    return bArr2;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bArr2 = null;
            ALog.e("awcn.TnetSpdySession", "getSSLPublicKey", (String) null, th, new Object[0]);
            return bArr2;
        }
        return bArr2;
    }
}
