package anet.channel.session;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* compiled from: Taobao */
final class c implements HostnameVerifier {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f233a;

    c(String str) {
        this.f233a = str;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.f233a, sSLSession);
    }
}
