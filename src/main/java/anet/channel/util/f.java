package anet.channel.util;

import android.net.SSLCertificateSocketFactory;
import android.os.Build;
import com.alipay.sdk.cons.c;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: Taobao */
public class f extends SSLSocketFactory {

    /* renamed from: a  reason: collision with root package name */
    private final String f346a = "awcn.TlsSniSocketFactory";

    /* renamed from: b  reason: collision with root package name */
    private Method f347b = null;

    /* renamed from: c  reason: collision with root package name */
    private String f348c;

    public Socket createSocket() throws IOException {
        return null;
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return null;
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return null;
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return null;
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return null;
    }

    public f(String str) {
        this.f348c = str;
    }

    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.f348c == null) {
            this.f348c = str;
        }
        if (ALog.isPrintLog(1)) {
            ALog.i("awcn.TlsSniSocketFactory", "customized createSocket", (String) null, c.f, this.f348c);
        }
        InetAddress inetAddress = socket.getInetAddress();
        if (z) {
            socket.close();
        }
        SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0);
        SSLSocket sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket(inetAddress, i);
        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        if (Build.VERSION.SDK_INT >= 17) {
            sSLCertificateSocketFactory.setHostname(sSLSocket, this.f348c);
        } else {
            try {
                if (this.f347b == null) {
                    this.f347b = sSLSocket.getClass().getMethod("setHostname", new Class[]{String.class});
                    this.f347b.setAccessible(true);
                }
                this.f347b.invoke(sSLSocket, new Object[]{this.f348c});
            } catch (Exception e) {
                ALog.w("awcn.TlsSniSocketFactory", "SNI not useable", (String) null, e, new Object[0]);
            }
        }
        SSLSession session = sSLSocket.getSession();
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.TlsSniSocketFactory", (String) null, (String) null, "SSLSession PeerHost", session.getPeerHost());
        }
        return sSLSocket;
    }
}
