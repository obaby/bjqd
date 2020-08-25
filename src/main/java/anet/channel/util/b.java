package anet.channel.util;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* compiled from: Taobao */
public class b {
    public static final HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new a();
    public static final SSLSocketFactory TRUST_ALL_SSL_SOCKET_FACTORY = C0007b.a();

    /* renamed from: a  reason: collision with root package name */
    static SSLSocketFactory f339a;

    /* renamed from: b  reason: collision with root package name */
    static HostnameVerifier f340b;

    public static SSLSocketFactory a() {
        return f339a;
    }

    public static void a(SSLSocketFactory sSLSocketFactory) {
        f339a = sSLSocketFactory;
    }

    public static HostnameVerifier b() {
        return f340b;
    }

    public static void a(HostnameVerifier hostnameVerifier) {
        f340b = hostnameVerifier;
    }

    /* compiled from: Taobao */
    private static class a implements HostnameVerifier {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        private a() {
        }
    }

    /* renamed from: anet.channel.util.b$b  reason: collision with other inner class name */
    /* compiled from: Taobao */
    private static class C0007b {
        private C0007b() {
        }

        /* renamed from: anet.channel.util.b$b$a */
        /* compiled from: Taobao */
        private static class a implements X509TrustManager {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            private a() {
            }
        }

        public static SSLSocketFactory a() {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, new TrustManager[]{new a()}, (SecureRandom) null);
                return instance.getSocketFactory();
            } catch (Throwable th) {
                ALog.w("awcn.SSLTrustAllSocketFactory", "getSocketFactory error :" + th.getMessage(), (String) null, new Object[0]);
                return null;
            }
        }
    }
}
