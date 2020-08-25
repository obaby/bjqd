package anet.channel.session;

import android.os.Build;
import android.util.Pair;
import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.c;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: Taobao */
public class b {

    /* compiled from: Taobao */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f230a;

        /* renamed from: b  reason: collision with root package name */
        public byte[] f231b;

        /* renamed from: c  reason: collision with root package name */
        public Map<String, List<String>> f232c;
        public int d;
        public boolean e;
    }

    private b() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        anet.channel.util.ALog.e("awcn.HttpConnector", "redirect url is invalid!", r4.getSeq(), "redirect url", r5);
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02d0 A[SYNTHETIC, Splitter:B:105:0x02d0] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02ed A[SYNTHETIC, Splitter:B:114:0x02ed] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x032a A[SYNTHETIC, Splitter:B:125:0x032a] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x035a A[SYNTHETIC, Splitter:B:134:0x035a] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x037a A[SYNTHETIC, Splitter:B:143:0x037a] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x039a A[SYNTHETIC, Splitter:B:152:0x039a] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03b9 A[SYNTHETIC, Splitter:B:161:0x03b9] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x03e4 A[SYNTHETIC, Splitter:B:170:0x03e4] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03ec A[SYNTHETIC, Splitter:B:176:0x03ec] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0220 A[Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x023a A[Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0245 A[SYNTHETIC, Splitter:B:68:0x0245] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0272 A[Catch:{ all -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0277 A[Catch:{ all -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0281 A[Catch:{ all -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0290 A[Catch:{ all -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02a4 A[SYNTHETIC, Splitter:B:96:0x02a4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static anet.channel.session.b.a a(anet.channel.request.Request r17, anet.channel.RequestCb r18) {
        /*
            r1 = r18
            anet.channel.session.b$a r2 = new anet.channel.session.b$a
            r2.<init>()
            r3 = 0
            if (r17 == 0) goto L_0x03fc
            java.net.URL r5 = r17.getUrl()
            if (r5 != 0) goto L_0x0012
            goto L_0x03fc
        L_0x0012:
            r4 = r17
            r5 = r3
        L_0x0015:
            boolean r6 = anet.channel.status.NetworkStatusHelper.isConnected()
            if (r6 != 0) goto L_0x0022
            r5 = -200(0xffffffffffffff38, float:NaN)
            a(r4, r2, r1, r5, r3)
            goto L_0x03e7
        L_0x0022:
            r6 = -402(0xfffffffffffffe6e, float:NaN)
            r7 = 3
            r8 = 2
            r9 = 1
            r10 = 0
            boolean r11 = anet.channel.util.ALog.isPrintLog(r8)     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            if (r11 == 0) goto L_0x0077
            java.lang.String r11 = "awcn.HttpConnector"
            java.lang.String r12 = ""
            java.lang.String r13 = r4.getSeq()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r15 = "request URL"
            r14[r10] = r15     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.net.URL r15 = r4.getUrl()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r15 = r15.toString()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            r14[r9] = r15     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            anet.channel.util.ALog.i(r11, r12, r13, r14)     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r11 = "awcn.HttpConnector"
            java.lang.String r12 = ""
            java.lang.String r13 = r4.getSeq()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r15 = "request Method"
            r14[r10] = r15     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r15 = r4.getMethod()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            r14[r9] = r15     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            anet.channel.util.ALog.i(r11, r12, r13, r14)     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r11 = "awcn.HttpConnector"
            java.lang.String r12 = ""
            java.lang.String r13 = r4.getSeq()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.lang.String r15 = "request headers"
            r14[r10] = r15     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            java.util.Map r15 = r4.getHeaders()     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            r14[r9] = r15     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            anet.channel.util.ALog.i(r11, r12, r13, r14)     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
        L_0x0077:
            java.net.HttpURLConnection r11 = a(r4)     // Catch:{ UnknownHostException -> 0x03bd, SocketTimeoutException -> 0x039e, ConnectTimeoutException -> 0x037f, ConnectException -> 0x035f, SSLHandshakeException -> 0x032f, SSLException -> 0x02ff, CancellationException -> 0x02d5, IOException -> 0x02a9, Exception -> 0x0268, all -> 0x0263 }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.sendStart = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r12 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = r12.sendStart     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r14 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r14 = r14.start     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r16 = 0
            long r12 = r12 - r14
            r5.processTime = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r11.connect()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            a((java.net.HttpURLConnection) r11, (anet.channel.request.Request) r4)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.sendEnd = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r12 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = r12.sendEnd     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r14 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r14 = r14.sendStart     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r16 = 0
            long r12 = r12 - r14
            r5.sendDataTime = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r5 = r11.getResponseCode()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r2.f230a = r5     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map r5 = r11.getHeaderFields()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map r5 = anet.channel.util.HttpHelper.cloneMap(r5)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r2.f232c = r5     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r5 = "awcn.HttpConnector"
            java.lang.String r12 = ""
            java.lang.String r13 = r4.getSeq()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r15 = "response code"
            r14[r10] = r15     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r15 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r14[r9] = r15     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.util.ALog.i(r5, r12, r13, r14)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r5 = "awcn.HttpConnector"
            java.lang.String r12 = ""
            java.lang.String r13 = r4.getSeq()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r15 = "response headers"
            r14[r10] = r15     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r15 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r14[r9] = r15     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.util.ALog.i(r5, r12, r13, r14)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            boolean r5 = anet.channel.util.HttpHelper.checkRedirect(r4, r5)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            if (r5 == 0) goto L_0x0177
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r5 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r12 = "Location"
            java.lang.String r5 = anet.channel.util.HttpHelper.getSingleHeaderFieldByKey(r5, r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            if (r5 == 0) goto L_0x0177
            anet.channel.util.HttpUrl r12 = anet.channel.util.HttpUrl.parse(r5)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            if (r12 == 0) goto L_0x0164
            anet.channel.request.Request$Builder r5 = r4.newBuilder()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r13 = "GET"
            anet.channel.request.Request$Builder r5 = r5.setMethod(r13)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.request.Request$Builder r5 = r5.setBody(r3)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.request.Request$Builder r5 = r5.setUrl((anet.channel.util.HttpUrl) r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r13 = r4.getRedirectTimes()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r13 = r13 + r9
            anet.channel.request.Request$Builder r5 = r5.setRedirectTimes(r13)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.request.Request$Builder r5 = r5.setSslSocketFactory(r3)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.request.Request$Builder r5 = r5.setHostnameVerifier(r3)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.request.Request r5 = r5.build()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r4 = r5.f211a     // Catch:{ UnknownHostException -> 0x0161, SocketTimeoutException -> 0x015e, ConnectTimeoutException -> 0x015b, ConnectException -> 0x0158, SSLHandshakeException -> 0x0155, SSLException -> 0x0152, CancellationException -> 0x014f, IOException -> 0x014c, Exception -> 0x0149 }
            java.lang.String r12 = r12.simpleUrlString()     // Catch:{ UnknownHostException -> 0x0161, SocketTimeoutException -> 0x015e, ConnectTimeoutException -> 0x015b, ConnectException -> 0x0158, SSLHandshakeException -> 0x0155, SSLException -> 0x0152, CancellationException -> 0x014f, IOException -> 0x014c, Exception -> 0x0149 }
            r4.url = r12     // Catch:{ UnknownHostException -> 0x0161, SocketTimeoutException -> 0x015e, ConnectTimeoutException -> 0x015b, ConnectException -> 0x0158, SSLHandshakeException -> 0x0155, SSLException -> 0x0152, CancellationException -> 0x014f, IOException -> 0x014c, Exception -> 0x0149 }
            if (r11 == 0) goto L_0x0145
            r11.disconnect()     // Catch:{ Exception -> 0x013a }
            goto L_0x0145
        L_0x013a:
            r0 = move-exception
            r4 = r0
            java.lang.String r6 = "awcn.HttpConnector"
            java.lang.String r7 = "http disconnect"
            java.lang.Object[] r8 = new java.lang.Object[r10]
            anet.channel.util.ALog.e(r6, r7, r3, r4, r8)
        L_0x0145:
            r4 = r5
            r5 = r11
            goto L_0x0015
        L_0x0149:
            r0 = move-exception
            goto L_0x026b
        L_0x014c:
            r0 = move-exception
            goto L_0x02ac
        L_0x014f:
            r0 = move-exception
            goto L_0x02d8
        L_0x0152:
            r0 = move-exception
            goto L_0x0302
        L_0x0155:
            r0 = move-exception
            goto L_0x0332
        L_0x0158:
            r0 = move-exception
            goto L_0x0362
        L_0x015b:
            r0 = move-exception
            goto L_0x0382
        L_0x015e:
            r0 = move-exception
            goto L_0x03a1
        L_0x0161:
            r0 = move-exception
            goto L_0x03c0
        L_0x0164:
            java.lang.String r12 = "awcn.HttpConnector"
            java.lang.String r13 = "redirect url is invalid!"
            java.lang.String r14 = r4.getSeq()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.Object[] r15 = new java.lang.Object[r8]     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r16 = "redirect url"
            r15[r10] = r16     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r15[r9] = r5     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.util.ALog.e(r12, r13, r14, r15)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x0177:
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r12 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r13 = "Content-Encoding"
            java.lang.String r12 = anet.channel.util.HttpHelper.getSingleHeaderFieldByKey(r12, r13)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.contentEncoding = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r12 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r13 = "Content-Type"
            java.lang.String r12 = anet.channel.util.HttpHelper.getSingleHeaderFieldByKey(r12, r13)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.contentType = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r5 = "HEAD"
            java.lang.String r12 = r4.getMethod()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            boolean r5 = r5.equals(r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            if (r5 != 0) goto L_0x01f6
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r12 = 304(0x130, float:4.26E-43)
            if (r5 == r12) goto L_0x01f6
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r12 = 204(0xcc, float:2.86E-43)
            if (r5 == r12) goto L_0x01f6
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r12 = 100
            if (r5 < r12) goto L_0x01b4
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r12 = 200(0xc8, float:2.8E-43)
            if (r5 >= r12) goto L_0x01b4
            goto L_0x01f6
        L_0x01b4:
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r5 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r5 = anet.channel.util.HttpHelper.parseContentLength(r5)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r2.d = r5     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r12 = r2.d     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = (long) r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.contentLength = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r5 = "gzip"
            anet.channel.statist.RequestStatistic r12 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r12 = r12.contentEncoding     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            boolean r5 = r5.equalsIgnoreCase(r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r2.e = r5     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            boolean r5 = r2.e     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            if (r5 == 0) goto L_0x01e1
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r5 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r12 = "Content-Encoding"
            r5.remove(r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r5 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r12 = "Content-Length"
            r5.remove(r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x01e1:
            if (r1 == 0) goto L_0x01ea
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r12 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r1.onResponseCode(r5, r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x01ea:
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.rspStart = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            a(r11, r4, r2, r1)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            goto L_0x0207
        L_0x01f6:
            if (r1 == 0) goto L_0x01ff
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r12 = r2.f232c     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r1.onResponseCode(r5, r12)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x01ff:
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.rspStart = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x0207:
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r12 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = r12.rspStart     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r14 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r14 = r14.sendEnd     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r16 = 0
            long r12 = r12 - r14
            r5.firstDataTime = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.util.concurrent.atomic.AtomicBoolean r5 = r5.isDone     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            boolean r5 = r5.get()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            if (r5 != 0) goto L_0x0238
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.ret = r9     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            int r12 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.statusCode = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r12 = "SUCCESS"
            r5.msg = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            anet.channel.statist.RequestStatistic r5 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r5.rspEnd = r12     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x0238:
            if (r1 == 0) goto L_0x0243
            int r5 = r2.f230a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            java.lang.String r12 = "SUCCESS"
            anet.channel.statist.RequestStatistic r13 = r4.f211a     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
            r1.onFinish(r5, r12, r13)     // Catch:{ UnknownHostException -> 0x0260, SocketTimeoutException -> 0x025d, ConnectTimeoutException -> 0x025a, ConnectException -> 0x0257, SSLHandshakeException -> 0x0254, SSLException -> 0x0251, CancellationException -> 0x024e, IOException -> 0x024c, Exception -> 0x024a }
        L_0x0243:
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x024a:
            r0 = move-exception
            goto L_0x026a
        L_0x024c:
            r0 = move-exception
            goto L_0x02ab
        L_0x024e:
            r0 = move-exception
            goto L_0x02d7
        L_0x0251:
            r0 = move-exception
            goto L_0x0301
        L_0x0254:
            r0 = move-exception
            goto L_0x0331
        L_0x0257:
            r0 = move-exception
            goto L_0x0361
        L_0x025a:
            r0 = move-exception
            goto L_0x0381
        L_0x025d:
            r0 = move-exception
            goto L_0x03a0
        L_0x0260:
            r0 = move-exception
            goto L_0x03bf
        L_0x0263:
            r0 = move-exception
            r1 = r0
            r11 = r5
            goto L_0x03ea
        L_0x0268:
            r0 = move-exception
            r11 = r5
        L_0x026a:
            r5 = r4
        L_0x026b:
            r4 = r0
            java.lang.String r6 = r4.getMessage()     // Catch:{ all -> 0x03e8 }
            if (r6 == 0) goto L_0x0277
            java.lang.String r6 = r4.getMessage()     // Catch:{ all -> 0x03e8 }
            goto L_0x0279
        L_0x0277:
            java.lang.String r6 = ""
        L_0x0279:
            java.lang.String r7 = "not verified"
            boolean r6 = r6.contains(r7)     // Catch:{ all -> 0x03e8 }
            if (r6 == 0) goto L_0x0290
            anet.channel.strategy.c r6 = anet.channel.strategy.c.a.f278a     // Catch:{ all -> 0x03e8 }
            java.lang.String r7 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r6.b(r7)     // Catch:{ all -> 0x03e8 }
            r6 = -403(0xfffffffffffffe6d, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            goto L_0x0295
        L_0x0290:
            r6 = -101(0xffffffffffffff9b, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
        L_0x0295:
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "HTTP Exception"
            java.lang.String r5 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r5, r4, r7)     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x02a9:
            r0 = move-exception
            r11 = r5
        L_0x02ab:
            r5 = r4
        L_0x02ac:
            r4 = r0
            r6 = -404(0xfffffffffffffe6c, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "IO Exception"
            java.lang.String r12 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x03e8 }
            java.lang.String r13 = "host"
            r7[r10] = r13     // Catch:{ all -> 0x03e8 }
            java.lang.String r5 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r7[r9] = r5     // Catch:{ all -> 0x03e8 }
            r7[r8] = r4     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r12, r7)     // Catch:{ all -> 0x03e8 }
            anet.channel.status.NetworkStatusHelper.printNetworkDetail()     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x02d5:
            r0 = move-exception
            r11 = r5
        L_0x02d7:
            r5 = r4
        L_0x02d8:
            r4 = r0
            r6 = -204(0xffffffffffffff34, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "HTTP Request Cancel"
            java.lang.String r5 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r5, r4, r7)     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x02f2:
            r0 = move-exception
            r1 = r0
            java.lang.String r4 = "awcn.HttpConnector"
            java.lang.String r5 = "http disconnect"
            java.lang.Object[] r6 = new java.lang.Object[r10]
            anet.channel.util.ALog.e(r4, r5, r3, r1, r6)
            goto L_0x03e7
        L_0x02ff:
            r0 = move-exception
            r11 = r5
        L_0x0301:
            r5 = r4
        L_0x0302:
            r4 = r0
            anet.channel.strategy.c r12 = anet.channel.strategy.c.a.f278a     // Catch:{ all -> 0x03e8 }
            java.lang.String r13 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r12.b(r13)     // Catch:{ all -> 0x03e8 }
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "connect SSLException"
            java.lang.String r12 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x03e8 }
            java.lang.String r13 = "host"
            r7[r10] = r13     // Catch:{ all -> 0x03e8 }
            java.lang.String r5 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r7[r9] = r5     // Catch:{ all -> 0x03e8 }
            r7[r8] = r4     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r12, r7)     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x032f:
            r0 = move-exception
            r11 = r5
        L_0x0331:
            r5 = r4
        L_0x0332:
            r4 = r0
            anet.channel.strategy.c r12 = anet.channel.strategy.c.a.f278a     // Catch:{ all -> 0x03e8 }
            java.lang.String r13 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r12.b(r13)     // Catch:{ all -> 0x03e8 }
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "HTTP Connect SSLHandshakeException"
            java.lang.String r12 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x03e8 }
            java.lang.String r13 = "host"
            r7[r10] = r13     // Catch:{ all -> 0x03e8 }
            java.lang.String r5 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r7[r9] = r5     // Catch:{ all -> 0x03e8 }
            r7[r8] = r4     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r12, r7)     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x035f:
            r0 = move-exception
            r11 = r5
        L_0x0361:
            r5 = r4
        L_0x0362:
            r4 = r0
            r6 = -406(0xfffffffffffffe6a, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "HTTP Connect Exception"
            java.lang.String r5 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r5, r4, r7)     // Catch:{ all -> 0x03e8 }
            anet.channel.status.NetworkStatusHelper.printNetworkDetail()     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x037f:
            r0 = move-exception
            r11 = r5
        L_0x0381:
            r5 = r4
        L_0x0382:
            r4 = r0
            r6 = -400(0xfffffffffffffe70, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "HTTP Connect Timeout"
            java.lang.String r5 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r5, r4, r7)     // Catch:{ all -> 0x03e8 }
            anet.channel.status.NetworkStatusHelper.printNetworkDetail()     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x039e:
            r0 = move-exception
            r11 = r5
        L_0x03a0:
            r5 = r4
        L_0x03a1:
            r4 = r0
            r6 = -401(0xfffffffffffffe6f, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "HTTP Socket Timeout"
            java.lang.String r5 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r5, r4, r7)     // Catch:{ all -> 0x03e8 }
            anet.channel.status.NetworkStatusHelper.printNetworkDetail()     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
            goto L_0x03e7
        L_0x03bd:
            r0 = move-exception
            r11 = r5
        L_0x03bf:
            r5 = r4
        L_0x03c0:
            r4 = r0
            r6 = -405(0xfffffffffffffe6b, float:NaN)
            a(r5, r2, r1, r6, r4)     // Catch:{ all -> 0x03e8 }
            java.lang.String r1 = "awcn.HttpConnector"
            java.lang.String r6 = "Unknown Host Exception"
            java.lang.String r12 = r5.getSeq()     // Catch:{ all -> 0x03e8 }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x03e8 }
            java.lang.String r13 = "host"
            r7[r10] = r13     // Catch:{ all -> 0x03e8 }
            java.lang.String r5 = r5.getHost()     // Catch:{ all -> 0x03e8 }
            r7[r9] = r5     // Catch:{ all -> 0x03e8 }
            r7[r8] = r4     // Catch:{ all -> 0x03e8 }
            anet.channel.util.ALog.e(r1, r6, r12, r7)     // Catch:{ all -> 0x03e8 }
            anet.channel.status.NetworkStatusHelper.printNetworkDetail()     // Catch:{ all -> 0x03e8 }
            if (r11 == 0) goto L_0x03e7
            r11.disconnect()     // Catch:{ Exception -> 0x02f2 }
        L_0x03e7:
            return r2
        L_0x03e8:
            r0 = move-exception
            r1 = r0
        L_0x03ea:
            if (r11 == 0) goto L_0x03fb
            r11.disconnect()     // Catch:{ Exception -> 0x03f0 }
            goto L_0x03fb
        L_0x03f0:
            r0 = move-exception
            r2 = r0
            java.lang.Object[] r4 = new java.lang.Object[r10]
            java.lang.String r5 = "awcn.HttpConnector"
            java.lang.String r6 = "http disconnect"
            anet.channel.util.ALog.e(r5, r6, r3, r2, r4)
        L_0x03fb:
            throw r1
        L_0x03fc:
            if (r1 == 0) goto L_0x040c
            r4 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r5 = anet.channel.util.ErrorConstant.getErrMsg(r4)
            anet.channel.statist.RequestStatistic r6 = new anet.channel.statist.RequestStatistic
            r6.<init>(r3, r3)
            r1.onFinish(r4, r5, r6)
        L_0x040c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.b.a(anet.channel.request.Request, anet.channel.RequestCb):anet.channel.session.b$a");
    }

    private static void a(Request request, a aVar, RequestCb requestCb, int i, Throwable th) {
        String errMsg = ErrorConstant.getErrMsg(i);
        ALog.e("awcn.HttpConnector", "onException", request.getSeq(), "errorCode", Integer.valueOf(i), "errMsg", errMsg, "url", request.getUrlString(), c.f, request.getHost());
        if (aVar != null) {
            aVar.f230a = i;
        }
        if (!request.f211a.isDone.get()) {
            request.f211a.statusCode = i;
            request.f211a.msg = errMsg;
            request.f211a.rspEnd = System.currentTimeMillis();
            if (i != -204) {
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(i, errMsg, request.f211a, th));
            }
        }
        if (requestCb != null) {
            requestCb.onFinish(i, errMsg, request.f211a);
        }
    }

    private static HttpURLConnection a(Request request) throws IOException {
        HttpURLConnection httpURLConnection;
        Pair<String, Integer> wifiProxy = NetworkStatusHelper.getWifiProxy();
        Proxy proxy = wifiProxy != null ? new Proxy(Proxy.Type.HTTP, new InetSocketAddress((String) wifiProxy.first, ((Integer) wifiProxy.second).intValue())) : null;
        anet.channel.util.c a2 = anet.channel.util.c.a();
        if (NetworkStatusHelper.getStatus().isMobile() && a2 != null) {
            proxy = a2.b();
        }
        URL url = request.getUrl();
        if (proxy != null) {
            httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(request.getConnectTimeout());
        httpURLConnection.setReadTimeout(request.getReadTimeout());
        httpURLConnection.setRequestMethod(request.getMethod());
        if (request.containsBody()) {
            httpURLConnection.setDoOutput(true);
        }
        Map<String, String> headers = request.getHeaders();
        for (Map.Entry next : headers.entrySet()) {
            httpURLConnection.addRequestProperty((String) next.getKey(), (String) next.getValue());
        }
        String str = headers.get("Host");
        if (str == null) {
            str = request.getHost();
        }
        httpURLConnection.setRequestProperty("Host", str);
        if (NetworkStatusHelper.getApn().equals("cmwap")) {
            httpURLConnection.setRequestProperty(HttpConstant.X_ONLINE_HOST, str);
        }
        if (!headers.containsKey(HttpConstant.ACCEPT_ENCODING)) {
            httpURLConnection.addRequestProperty(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
        if (a2 != null) {
            httpURLConnection.setRequestProperty("Authorization", a2.c());
        }
        if (url.getProtocol().equalsIgnoreCase("https")) {
            a(httpURLConnection, request, str);
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection, Request request, String str) {
        if (Integer.parseInt(Build.VERSION.SDK) < 8) {
            ALog.e("awcn.HttpConnector", "supportHttps", "[supportHttps]Froyo 以下版本不支持https", new Object[0]);
            return;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        if (request.getSslSocketFactory() != null) {
            httpsURLConnection.setSSLSocketFactory(request.getSslSocketFactory());
        } else if (anet.channel.util.b.a() != null) {
            httpsURLConnection.setSSLSocketFactory(anet.channel.util.b.a());
        }
        if (request.getHostnameVerifier() != null) {
            httpsURLConnection.setHostnameVerifier(request.getHostnameVerifier());
        } else if (anet.channel.util.b.b() != null) {
            httpsURLConnection.setHostnameVerifier(anet.channel.util.b.b());
        } else {
            httpsURLConnection.setHostnameVerifier(new c(str));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0045 A[SYNTHETIC, Splitter:B:23:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006a A[SYNTHETIC, Splitter:B:29:0x006a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.net.HttpURLConnection r7, anet.channel.request.Request r8) {
        /*
            boolean r0 = r8.containsBody()
            r1 = 0
            if (r0 == 0) goto L_0x0080
            r0 = 0
            java.io.OutputStream r7 = r7.getOutputStream()     // Catch:{ Exception -> 0x0035 }
            int r0 = r8.postBody(r7)     // Catch:{ Exception -> 0x002e, all -> 0x0029 }
            if (r7 == 0) goto L_0x0027
            r7.flush()     // Catch:{ IOException -> 0x0019 }
            r7.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0027
        L_0x0019:
            r7 = move-exception
            java.lang.String r2 = "awcn.HttpConnector"
            java.lang.String r3 = "postData"
            java.lang.String r4 = r8.getSeq()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            anet.channel.util.ALog.e(r2, r3, r4, r7, r1)
        L_0x0027:
            r1 = r0
            goto L_0x005a
        L_0x0029:
            r0 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x0068
        L_0x002e:
            r0 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x0036
        L_0x0033:
            r7 = move-exception
            goto L_0x0068
        L_0x0035:
            r7 = move-exception
        L_0x0036:
            java.lang.String r2 = "awcn.HttpConnector"
            java.lang.String r3 = "postData error"
            java.lang.String r4 = r8.getSeq()     // Catch:{ all -> 0x0033 }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ all -> 0x0033 }
            anet.channel.util.ALog.e(r2, r3, r4, r7, r5)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x005a
            r0.flush()     // Catch:{ IOException -> 0x004c }
            r0.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x005a
        L_0x004c:
            r7 = move-exception
            java.lang.String r0 = "awcn.HttpConnector"
            java.lang.String r2 = "postData"
            java.lang.String r3 = r8.getSeq()
            java.lang.Object[] r4 = new java.lang.Object[r1]
            anet.channel.util.ALog.e(r0, r2, r3, r7, r4)
        L_0x005a:
            anet.channel.statist.RequestStatistic r7 = r8.f211a
            long r2 = (long) r1
            r7.reqBodyInflateSize = r2
            anet.channel.statist.RequestStatistic r7 = r8.f211a
            r7.reqBodyDeflateSize = r2
            anet.channel.statist.RequestStatistic r7 = r8.f211a
            r7.sendDataSize = r2
            goto L_0x0080
        L_0x0068:
            if (r0 == 0) goto L_0x007f
            r0.flush()     // Catch:{ IOException -> 0x0071 }
            r0.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x007f
        L_0x0071:
            r0 = move-exception
            java.lang.String r8 = r8.getSeq()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "awcn.HttpConnector"
            java.lang.String r3 = "postData"
            anet.channel.util.ALog.e(r2, r3, r8, r0, r1)
        L_0x007f:
            throw r7
        L_0x0080:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.b.a(java.net.HttpURLConnection, anet.channel.request.Request):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00fc A[SYNTHETIC, Splitter:B:57:0x00fc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.net.HttpURLConnection r11, anet.channel.request.Request r12, anet.channel.session.b.a r13, anet.channel.RequestCb r14) throws java.io.IOException, java.util.concurrent.CancellationException {
        /*
            r0 = 0
            r1 = 0
            java.io.InputStream r2 = r11.getInputStream()     // Catch:{ IOException -> 0x0007 }
            goto L_0x0043
        L_0x0007:
            r2 = move-exception
            java.io.InputStream r3 = r11.getErrorStream()     // Catch:{ Exception -> 0x000d }
            goto L_0x0033
        L_0x000d:
            r3 = move-exception
            java.lang.String r4 = "awcn.HttpConnector"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "get error stream failed."
            r5.append(r6)
            java.net.URL r6 = r11.getURL()
            java.lang.String r6 = r6.toString()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = r12.getSeq()
            java.lang.Object[] r7 = new java.lang.Object[r0]
            anet.channel.util.ALog.e(r4, r5, r6, r3, r7)
            r3 = r1
        L_0x0033:
            java.lang.String r4 = "awcn.HttpConnector"
            java.net.URL r11 = r11.getURL()
            java.lang.String r11 = r11.toString()
            java.lang.Object[] r5 = new java.lang.Object[r0]
            anet.channel.util.ALog.w(r4, r11, r1, r2, r5)
            r2 = r3
        L_0x0043:
            if (r2 != 0) goto L_0x004b
            r11 = -404(0xfffffffffffffe6c, float:NaN)
            a(r12, r13, r14, r11, r1)
            return
        L_0x004b:
            if (r14 != 0) goto L_0x0065
            int r11 = r13.d
            if (r11 > 0) goto L_0x0054
            r11 = 1024(0x400, float:1.435E-42)
            goto L_0x005f
        L_0x0054:
            boolean r11 = r13.e
            if (r11 == 0) goto L_0x005d
            int r11 = r13.d
            int r11 = r11 * 2
            goto L_0x005f
        L_0x005d:
            int r11 = r13.d
        L_0x005f:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>(r11)
            goto L_0x0066
        L_0x0065:
            r3 = r1
        L_0x0066:
            anet.channel.util.a r11 = new anet.channel.util.a     // Catch:{ all -> 0x00e3 }
            r11.<init>(r2)     // Catch:{ all -> 0x00e3 }
            boolean r4 = r13.e     // Catch:{ all -> 0x00e1 }
            if (r4 == 0) goto L_0x0076
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x00e1 }
            r4.<init>(r11)     // Catch:{ all -> 0x00e1 }
            r2 = r4
            goto L_0x0077
        L_0x0076:
            r2 = r11
        L_0x0077:
            r4 = r1
        L_0x0078:
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00e1 }
            boolean r5 = r5.isInterrupted()     // Catch:{ all -> 0x00e1 }
            if (r5 != 0) goto L_0x00d9
            if (r4 != 0) goto L_0x008c
            anet.channel.bytes.a r4 = anet.channel.bytes.a.C0005a.f154a     // Catch:{ all -> 0x00e1 }
            r5 = 2048(0x800, float:2.87E-42)
            anet.channel.bytes.ByteArray r4 = r4.a((int) r5)     // Catch:{ all -> 0x00e1 }
        L_0x008c:
            int r5 = r4.readFrom(r2)     // Catch:{ all -> 0x00e1 }
            r6 = -1
            if (r5 == r6) goto L_0x00ae
            if (r3 == 0) goto L_0x0099
            r4.writeTo(r3)     // Catch:{ all -> 0x00e1 }
            goto L_0x009d
        L_0x0099:
            r14.onDataReceive(r4, r0)     // Catch:{ all -> 0x00e1 }
            r4 = r1
        L_0x009d:
            anet.channel.statist.RequestStatistic r6 = r12.f211a     // Catch:{ all -> 0x00e1 }
            long r7 = r6.recDataSize     // Catch:{ all -> 0x00e1 }
            long r9 = (long) r5     // Catch:{ all -> 0x00e1 }
            long r7 = r7 + r9
            r6.recDataSize = r7     // Catch:{ all -> 0x00e1 }
            anet.channel.statist.RequestStatistic r5 = r12.f211a     // Catch:{ all -> 0x00e1 }
            long r6 = r5.rspBodyInflateSize     // Catch:{ all -> 0x00e1 }
            r8 = 0
            long r6 = r6 + r9
            r5.rspBodyInflateSize = r6     // Catch:{ all -> 0x00e1 }
            goto L_0x0078
        L_0x00ae:
            if (r3 == 0) goto L_0x00b4
            r4.recycle()     // Catch:{ all -> 0x00e1 }
            goto L_0x00b8
        L_0x00b4:
            r0 = 1
            r14.onDataReceive(r4, r0)     // Catch:{ all -> 0x00e1 }
        L_0x00b8:
            if (r3 == 0) goto L_0x00c0
            byte[] r14 = r3.toByteArray()     // Catch:{ all -> 0x00e1 }
            r13.f231b = r14     // Catch:{ all -> 0x00e1 }
        L_0x00c0:
            anet.channel.statist.RequestStatistic r13 = r12.f211a
            long r0 = java.lang.System.currentTimeMillis()
            anet.channel.statist.RequestStatistic r14 = r12.f211a
            long r3 = r14.rspStart
            long r0 = r0 - r3
            r13.recDataTime = r0
            anet.channel.statist.RequestStatistic r12 = r12.f211a
            long r13 = r11.a()
            r12.rspBodyDeflateSize = r13
            r2.close()     // Catch:{ IOException -> 0x00d8 }
        L_0x00d8:
            return
        L_0x00d9:
            java.util.concurrent.CancellationException r13 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x00e1 }
            java.lang.String r14 = "task cancelled"
            r13.<init>(r14)     // Catch:{ all -> 0x00e1 }
            throw r13     // Catch:{ all -> 0x00e1 }
        L_0x00e1:
            r13 = move-exception
            goto L_0x00e5
        L_0x00e3:
            r13 = move-exception
            r11 = r1
        L_0x00e5:
            anet.channel.statist.RequestStatistic r14 = r12.f211a
            long r0 = java.lang.System.currentTimeMillis()
            anet.channel.statist.RequestStatistic r3 = r12.f211a
            long r3 = r3.rspStart
            long r0 = r0 - r3
            r14.recDataTime = r0
            anet.channel.statist.RequestStatistic r12 = r12.f211a
            long r0 = r11.a()
            r12.rspBodyDeflateSize = r0
            if (r2 == 0) goto L_0x00ff
            r2.close()     // Catch:{ IOException -> 0x00ff }
        L_0x00ff:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.b.a(java.net.HttpURLConnection, anet.channel.request.Request, anet.channel.session.b$a, anet.channel.RequestCb):void");
    }
}
