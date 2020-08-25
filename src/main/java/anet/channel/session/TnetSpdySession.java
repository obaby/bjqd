package anet.channel.session;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.Config;
import anet.channel.DataFrameCb;
import anet.channel.IAuth;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.SessionInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.entity.b;
import anet.channel.heartbeat.HeartbeatManager;
import anet.channel.heartbeat.IHeartbeat;
import anet.channel.request.Request;
import anet.channel.security.ISecurity;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.ConnEvent;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpHelper;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import java.util.List;
import java.util.Map;
import org.android.spdy.SessionCb;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

/* compiled from: Taobao */
public class TnetSpdySession extends Session implements SessionCb {
    protected int A = -1;
    protected DataFrameCb B = null;
    protected IHeartbeat C = null;
    protected IAuth D = null;
    protected String E = null;
    protected ISecurity F = null;
    /* access modifiers changed from: private */
    public int G = 0;
    protected SpdyAgent v;
    protected SpdySession w;
    protected volatile boolean x = false;
    protected long y;
    protected long z = 0;

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
    }

    static /* synthetic */ int b(TnetSpdySession tnetSpdySession) {
        int i = tnetSpdySession.G + 1;
        tnetSpdySession.G = i;
        return i;
    }

    public TnetSpdySession(Context context, anet.channel.entity.a aVar) {
        super(context, aVar);
    }

    public void initConfig(Config config) {
        if (config != null) {
            this.E = config.getAppkey();
            this.F = config.getSecurity();
        }
    }

    public void initSessionInfo(SessionInfo sessionInfo) {
        if (sessionInfo != null) {
            this.B = sessionInfo.dataFrameCb;
            this.D = sessionInfo.auth;
            if (sessionInfo.isKeepAlive) {
                this.p.isKL = 1;
                this.s = true;
                this.C = sessionInfo.heartbeat;
                if (this.C == null) {
                    this.C = HeartbeatManager.getDefaultHeartbeat();
                }
            }
        }
    }

    public void setTnetPublicKey(int i) {
        this.A = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0111 A[Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0125 A[Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0174 A[Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01b1 A[Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public anet.channel.request.Cancelable request(anet.channel.request.Request r21, anet.channel.RequestCb r22) {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            r2 = r22
            anet.channel.request.c r3 = anet.channel.request.c.NULL
            if (r0 == 0) goto L_0x000d
            anet.channel.statist.RequestStatistic r4 = r0.f211a
            goto L_0x0015
        L_0x000d:
            anet.channel.statist.RequestStatistic r4 = new anet.channel.statist.RequestStatistic
            java.lang.String r5 = r1.d
            r6 = 0
            r4.<init>(r5, r6)
        L_0x0015:
            anet.channel.entity.ConnType r5 = r1.i
            r4.setConnType(r5)
            long r5 = r4.start
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x002a
            long r5 = java.lang.System.currentTimeMillis()
            r4.reqStart = r5
            r4.start = r5
        L_0x002a:
            java.lang.String r5 = r1.e
            int r6 = r1.f
            r4.setIPAndPort(r5, r6)
            anet.channel.strategy.IConnStrategy r5 = r1.j
            int r5 = r5.getIpSource()
            r4.ipRefer = r5
            anet.channel.strategy.IConnStrategy r5 = r1.j
            int r5 = r5.getIpType()
            r4.ipType = r5
            java.lang.String r5 = r1.k
            r4.unit = r5
            if (r0 == 0) goto L_0x020a
            if (r2 != 0) goto L_0x004b
            goto L_0x020a
        L_0x004b:
            r5 = 0
            r6 = 2
            org.android.spdy.SpdySession r7 = r1.w     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r7 == 0) goto L_0x01bd
            int r7 = r1.m     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r7 == 0) goto L_0x005a
            int r7 = r1.m     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r8 = 4
            if (r7 != r8) goto L_0x01bd
        L_0x005a:
            boolean r7 = r1.l     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r7 == 0) goto L_0x0065
            java.lang.String r7 = r1.e     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            int r8 = r1.f     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r0.setDnsOptimize(r7, r8)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x0065:
            anet.channel.entity.ConnType r7 = r1.i     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            boolean r7 = r7.isSSL()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r0.setUrlScheme(r7)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.net.URL r9 = r21.getUrl()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            boolean r7 = anet.channel.util.ALog.isPrintLog(r6)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r19 = 1
            if (r7 == 0) goto L_0x00bf
            java.lang.String r7 = "awcn.TnetSpdySession"
            java.lang.String r8 = ""
            java.lang.String r10 = r21.getSeq()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = "request URL"
            r11[r5] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = r9.toString()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r11[r19] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.util.ALog.i(r7, r8, r10, r11)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r7 = "awcn.TnetSpdySession"
            java.lang.String r8 = ""
            java.lang.String r10 = r21.getSeq()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = "request Method"
            r11[r5] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = r21.getMethod()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r11[r19] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.util.ALog.i(r7, r8, r10, r11)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r7 = "awcn.TnetSpdySession"
            java.lang.String r8 = ""
            java.lang.String r10 = r21.getSeq()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = "request headers"
            r11[r5] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.util.Map r12 = r21.getHeaders()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r11[r19] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.util.ALog.i(r7, r8, r10, r11)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x00bf:
            java.lang.String r7 = r1.g     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r7 != 0) goto L_0x00ed
            int r7 = r1.h     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r7 > 0) goto L_0x00cc
            goto L_0x00ed
        L_0x00cc:
            org.android.spdy.SpdyRequest r7 = new org.android.spdy.SpdyRequest     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r10 = r9.getHost()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            int r11 = r9.getPort()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = r1.g     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            int r13 = r1.h     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r14 = r21.getMethod()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            org.android.spdy.RequestPriority r15 = org.android.spdy.RequestPriority.DEFAULT_PRIORITY     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r16 = -1
            int r17 = r21.getConnectTimeout()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r18 = 0
            r8 = r7
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            goto L_0x00fe
        L_0x00ed:
            org.android.spdy.SpdyRequest r7 = new org.android.spdy.SpdyRequest     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r10 = r21.getMethod()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            org.android.spdy.RequestPriority r11 = org.android.spdy.RequestPriority.DEFAULT_PRIORITY     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r12 = -1
            int r13 = r21.getConnectTimeout()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r8 = r7
            r8.<init>(r9, r10, r11, r12, r13)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x00fe:
            int r8 = r21.getReadTimeout()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r7.setRequestRdTimeoutMs(r8)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.util.Map r8 = r21.getHeaders()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r9 = "Host"
            boolean r9 = r8.containsKey(r9)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r9 != 0) goto L_0x0125
            r7.addHeaders(r8)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r8 = ":host"
            boolean r9 = r1.l     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r9 == 0) goto L_0x011d
            java.lang.String r9 = r1.e     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            goto L_0x0121
        L_0x011d:
            java.lang.String r9 = r21.getHost()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x0121:
            r7.addHeader(r8, r9)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            goto L_0x0144
        L_0x0125:
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.util.Map r9 = r21.getHeaders()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r8.<init>(r9)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r9 = "Host"
            java.lang.Object r9 = r8.remove(r9)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r10 = ":host"
            boolean r11 = r1.l     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r11 == 0) goto L_0x013e
            java.lang.String r9 = r1.e     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x013e:
            r8.put(r10, r9)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r7.addHeaders(r8)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x0144:
            byte[] r8 = r21.getBodyBytes()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            org.android.spdy.SpdyDataProvider r9 = new org.android.spdy.SpdyDataProvider     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r9.<init>(r8)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.statist.RequestStatistic r8 = r0.f211a     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r8.sendStart = r10     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.statist.RequestStatistic r8 = r0.f211a     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.statist.RequestStatistic r10 = r0.f211a     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            long r10 = r10.sendStart     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.statist.RequestStatistic r12 = r0.f211a     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            long r12 = r12.start     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r14 = 0
            long r10 = r10 - r12
            r8.processTime = r10     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            org.android.spdy.SpdySession r8 = r1.w     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.session.TnetSpdySession$a r10 = new anet.channel.session.TnetSpdySession$a     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r10.<init>(r0, r2)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            int r7 = r8.submitRequest(r7, r9, r1, r10)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            boolean r8 = anet.channel.util.ALog.isPrintLog(r19)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            if (r8 == 0) goto L_0x018b
            java.lang.String r8 = "awcn.TnetSpdySession"
            java.lang.String r9 = ""
            java.lang.String r10 = r21.getSeq()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r12 = "streamId"
            r11[r5] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r7)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r11[r19] = r12     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.util.ALog.d(r8, r9, r10, r11)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
        L_0x018b:
            anet.channel.request.c r8 = new anet.channel.request.c     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            org.android.spdy.SpdySession r9 = r1.w     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            java.lang.String r0 = r21.getSeq()     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r8.<init>(r9, r7, r0)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.statist.SessionStatistic r0 = r1.p     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            long r9 = r0.requestCount     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            r11 = 1
            long r9 = r9 + r11
            r0.requestCount = r9     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            anet.channel.statist.SessionStatistic r0 = r1.p     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            long r9 = r0.stdRCount     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            r3 = 0
            long r9 = r9 + r11
            r0.stdRCount = r9     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            r1.y = r9     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            anet.channel.heartbeat.IHeartbeat r0 = r1.C     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            if (r0 == 0) goto L_0x01b6
            anet.channel.heartbeat.IHeartbeat r0 = r1.C     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
            r0.reSchedule()     // Catch:{ SpdyErrorException -> 0x01ba, Exception -> 0x01b8 }
        L_0x01b6:
            r3 = r8
            goto L_0x0209
        L_0x01b8:
            r3 = r8
            goto L_0x01c9
        L_0x01ba:
            r0 = move-exception
            r3 = r8
            goto L_0x01d4
        L_0x01bd:
            r7 = -301(0xfffffffffffffed3, float:NaN)
            java.lang.String r8 = anet.channel.util.ErrorConstant.getErrMsg(r7)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            anet.channel.statist.RequestStatistic r0 = r0.f211a     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            r2.onFinish(r7, r8, r0)     // Catch:{ SpdyErrorException -> 0x01d3, Exception -> 0x01c9 }
            goto L_0x0209
        L_0x01c9:
            r0 = -101(0xffffffffffffff9b, float:NaN)
            java.lang.String r5 = anet.channel.util.ErrorConstant.getErrMsg(r0)
            r2.onFinish(r0, r5, r4)
            goto L_0x0209
        L_0x01d3:
            r0 = move-exception
        L_0x01d4:
            int r7 = r0.SpdyErrorGetCode()
            r8 = -1104(0xfffffffffffffbb0, float:NaN)
            if (r7 == r8) goto L_0x01e4
            int r7 = r0.SpdyErrorGetCode()
            r8 = -1103(0xfffffffffffffbb1, float:NaN)
            if (r7 != r8) goto L_0x01f8
        L_0x01e4:
            java.lang.String r7 = "awcn.TnetSpdySession"
            java.lang.String r8 = "Send request on closed session!!!"
            java.lang.String r9 = r1.o
            java.lang.Object[] r5 = new java.lang.Object[r5]
            anet.channel.util.ALog.e(r7, r8, r9, r5)
            r5 = 6
            anet.channel.entity.b r7 = new anet.channel.entity.b
            r7.<init>(r6)
            r1.notifyStatus(r5, r7)
        L_0x01f8:
            int r0 = r0.SpdyErrorGetCode()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r5 = -300(0xfffffffffffffed4, float:NaN)
            java.lang.String r0 = anet.channel.util.ErrorConstant.formatMsg(r5, r0)
            r2.onFinish(r5, r0, r4)
        L_0x0209:
            return r3
        L_0x020a:
            if (r2 == 0) goto L_0x0215
            r0 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r5 = anet.channel.util.ErrorConstant.getErrMsg(r0)
            r2.onFinish(r0, r5, r4)
        L_0x0215:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.TnetSpdySession.request(anet.channel.request.Request, anet.channel.RequestCb):anet.channel.request.Cancelable");
    }

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
        try {
            if (this.B != null) {
                ALog.e("awcn.TnetSpdySession", "sendCustomFrame", this.o, "dataId", Integer.valueOf(i), d.p, Integer.valueOf(i2));
                if (this.m != 4 || this.w == null) {
                    ALog.e("awcn.TnetSpdySession", "sendCustomFrame", this.o, "sendCustomFrame con invalid mStatus:" + this.m);
                    a(i, ErrorConstant.ERROR_SESSION_INVALID, true, "session invalid");
                } else if (bArr == null || bArr.length <= 16384) {
                    this.w.sendCustomControlFrame(i, i2, 0, bArr == null ? 0 : bArr.length, bArr);
                    this.p.requestCount++;
                    this.p.cfRCount++;
                    this.y = System.currentTimeMillis();
                    if (this.C != null) {
                        this.C.reSchedule();
                    }
                } else {
                    a(i, ErrorConstant.ERROR_DATA_TOO_LARGE, false, (String) null);
                }
            }
        } catch (SpdyErrorException e) {
            ALog.e("awcn.TnetSpdySession", "sendCustomFrame error", this.o, e, new Object[0]);
            a(i, ErrorConstant.ERROR_TNET_EXCEPTION, true, "SpdyErrorException: " + e.toString());
        } catch (Exception e2) {
            ALog.e("awcn.TnetSpdySession", "sendCustomFrame error", this.o, e2, new Object[0]);
            a(i, -101, true, e2.toString());
        }
    }

    private void a(int i, int i2, boolean z2, String str) {
        if (this.B != null) {
            this.B.onException(i, i2, z2, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x010e A[Catch:{ Throwable -> 0x014c }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0125 A[Catch:{ Throwable -> 0x014c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect() {
        /*
            r14 = this;
            int r0 = r14.m
            r10 = 1
            if (r0 == r10) goto L_0x015c
            int r0 = r14.m
            if (r0 == 0) goto L_0x015c
            int r0 = r14.m
            r1 = 4
            if (r0 != r1) goto L_0x0010
            goto L_0x015c
        L_0x0010:
            r11 = 0
            r12 = 2
            r13 = 0
            org.android.spdy.SpdyAgent r0 = r14.v     // Catch:{ Throwable -> 0x014c }
            if (r0 != 0) goto L_0x001a
            r14.c()     // Catch:{ Throwable -> 0x014c }
        L_0x001a:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x014c }
            java.lang.String r7 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x014c }
            java.lang.String r0 = "awcn.TnetSpdySession"
            java.lang.String r2 = "[connect]"
            java.lang.String r3 = r14.o     // Catch:{ Throwable -> 0x014c }
            r4 = 12
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x014c }
            java.lang.String r5 = "host"
            r4[r13] = r5     // Catch:{ Throwable -> 0x014c }
            java.lang.String r5 = r14.f123c     // Catch:{ Throwable -> 0x014c }
            r4[r10] = r5     // Catch:{ Throwable -> 0x014c }
            java.lang.String r5 = "connect "
            r4[r12] = r5     // Catch:{ Throwable -> 0x014c }
            r5 = 3
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x014c }
            r6.<init>()     // Catch:{ Throwable -> 0x014c }
            java.lang.String r8 = r14.e     // Catch:{ Throwable -> 0x014c }
            r6.append(r8)     // Catch:{ Throwable -> 0x014c }
            java.lang.String r8 = ":"
            r6.append(r8)     // Catch:{ Throwable -> 0x014c }
            int r8 = r14.f     // Catch:{ Throwable -> 0x014c }
            r6.append(r8)     // Catch:{ Throwable -> 0x014c }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x014c }
            r4[r5] = r6     // Catch:{ Throwable -> 0x014c }
            java.lang.String r5 = "sessionId"
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            r1 = 5
            r4[r1] = r7     // Catch:{ Throwable -> 0x014c }
            r1 = 6
            java.lang.String r5 = "SpdyProtocol,"
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            r1 = 7
            anet.channel.entity.ConnType r5 = r14.i     // Catch:{ Throwable -> 0x014c }
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            r1 = 8
            java.lang.String r5 = "proxyIp,"
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            r1 = 9
            java.lang.String r5 = r14.g     // Catch:{ Throwable -> 0x014c }
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            r1 = 10
            java.lang.String r5 = "proxyPort,"
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            r1 = 11
            int r5 = r14.h     // Catch:{ Throwable -> 0x014c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x014c }
            r4[r1] = r5     // Catch:{ Throwable -> 0x014c }
            anet.channel.util.ALog.e(r0, r2, r3, r4)     // Catch:{ Throwable -> 0x014c }
            org.android.spdy.SessionInfo r0 = new org.android.spdy.SessionInfo     // Catch:{ Throwable -> 0x014c }
            java.lang.String r2 = r14.e     // Catch:{ Throwable -> 0x014c }
            int r3 = r14.f     // Catch:{ Throwable -> 0x014c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x014c }
            r1.<init>()     // Catch:{ Throwable -> 0x014c }
            java.lang.String r4 = r14.f123c     // Catch:{ Throwable -> 0x014c }
            r1.append(r4)     // Catch:{ Throwable -> 0x014c }
            java.lang.String r4 = "_"
            r1.append(r4)     // Catch:{ Throwable -> 0x014c }
            java.lang.String r4 = r14.E     // Catch:{ Throwable -> 0x014c }
            r1.append(r4)     // Catch:{ Throwable -> 0x014c }
            java.lang.String r4 = r1.toString()     // Catch:{ Throwable -> 0x014c }
            java.lang.String r5 = r14.g     // Catch:{ Throwable -> 0x014c }
            int r6 = r14.h     // Catch:{ Throwable -> 0x014c }
            anet.channel.entity.ConnType r1 = r14.i     // Catch:{ Throwable -> 0x014c }
            int r9 = r1.getTnetConType()     // Catch:{ Throwable -> 0x014c }
            r1 = r0
            r8 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x014c }
            int r1 = r14.q     // Catch:{ Throwable -> 0x014c }
            float r1 = (float) r1     // Catch:{ Throwable -> 0x014c }
            float r2 = anet.channel.util.Utils.getNetworkTimeFactor()     // Catch:{ Throwable -> 0x014c }
            float r1 = r1 * r2
            int r1 = (int) r1     // Catch:{ Throwable -> 0x014c }
            r0.setConnectionTimeoutMs(r1)     // Catch:{ Throwable -> 0x014c }
            anet.channel.entity.ConnType r1 = r14.i     // Catch:{ Throwable -> 0x014c }
            boolean r1 = r1.isPublicKeyAuto()     // Catch:{ Throwable -> 0x014c }
            if (r1 != 0) goto L_0x00f2
            anet.channel.entity.ConnType r1 = r14.i     // Catch:{ Throwable -> 0x014c }
            boolean r1 = r1.isH2S()     // Catch:{ Throwable -> 0x014c }
            if (r1 == 0) goto L_0x00ce
            goto L_0x00f2
        L_0x00ce:
            int r1 = r14.A     // Catch:{ Throwable -> 0x014c }
            if (r1 < 0) goto L_0x00d8
            int r1 = r14.A     // Catch:{ Throwable -> 0x014c }
            r0.setPubKeySeqNum(r1)     // Catch:{ Throwable -> 0x014c }
            goto L_0x00fe
        L_0x00d8:
            anet.channel.entity.ConnType r1 = r14.i     // Catch:{ Throwable -> 0x014c }
            anet.channel.security.ISecurity r2 = r14.F     // Catch:{ Throwable -> 0x014c }
            if (r2 == 0) goto L_0x00e5
            anet.channel.security.ISecurity r2 = r14.F     // Catch:{ Throwable -> 0x014c }
            boolean r2 = r2.isSecOff()     // Catch:{ Throwable -> 0x014c }
            goto L_0x00e6
        L_0x00e5:
            r2 = 1
        L_0x00e6:
            int r1 = r1.getTnetPublicKey(r2)     // Catch:{ Throwable -> 0x014c }
            r14.A = r1     // Catch:{ Throwable -> 0x014c }
            int r1 = r14.A     // Catch:{ Throwable -> 0x014c }
            r0.setPubKeySeqNum(r1)     // Catch:{ Throwable -> 0x014c }
            goto L_0x00fe
        L_0x00f2:
            boolean r1 = r14.l     // Catch:{ Throwable -> 0x014c }
            if (r1 == 0) goto L_0x00f9
            java.lang.String r1 = r14.e     // Catch:{ Throwable -> 0x014c }
            goto L_0x00fb
        L_0x00f9:
            java.lang.String r1 = r14.d     // Catch:{ Throwable -> 0x014c }
        L_0x00fb:
            r0.setCertHost(r1)     // Catch:{ Throwable -> 0x014c }
        L_0x00fe:
            org.android.spdy.SpdyAgent r1 = r14.v     // Catch:{ Throwable -> 0x014c }
            org.android.spdy.SpdySession r0 = r1.createSession(r0)     // Catch:{ Throwable -> 0x014c }
            r14.w = r0     // Catch:{ Throwable -> 0x014c }
            org.android.spdy.SpdySession r0 = r14.w     // Catch:{ Throwable -> 0x014c }
            int r0 = r0.getRefCount()     // Catch:{ Throwable -> 0x014c }
            if (r0 <= r10) goto L_0x0125
            java.lang.String r0 = "awcn.TnetSpdySession"
            java.lang.String r1 = "get session ref count > 1!!!"
            java.lang.String r2 = r14.o     // Catch:{ Throwable -> 0x014c }
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ Throwable -> 0x014c }
            anet.channel.util.ALog.e(r0, r1, r2, r3)     // Catch:{ Throwable -> 0x014c }
            anet.channel.entity.b r0 = new anet.channel.entity.b     // Catch:{ Throwable -> 0x014c }
            r0.<init>(r10)     // Catch:{ Throwable -> 0x014c }
            r14.notifyStatus(r13, r0)     // Catch:{ Throwable -> 0x014c }
            r14.b()     // Catch:{ Throwable -> 0x014c }
            return
        L_0x0125:
            r14.notifyStatus(r10, r11)     // Catch:{ Throwable -> 0x014c }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x014c }
            r14.y = r0     // Catch:{ Throwable -> 0x014c }
            anet.channel.statist.SessionStatistic r0 = r14.p     // Catch:{ Throwable -> 0x014c }
            java.lang.String r1 = r14.g     // Catch:{ Throwable -> 0x014c }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x014c }
            r1 = r1 ^ r10
            r0.isProxy = r1     // Catch:{ Throwable -> 0x014c }
            anet.channel.statist.SessionStatistic r0 = r14.p     // Catch:{ Throwable -> 0x014c }
            java.lang.String r1 = "false"
            r0.isTunnel = r1     // Catch:{ Throwable -> 0x014c }
            anet.channel.statist.SessionStatistic r0 = r14.p     // Catch:{ Throwable -> 0x014c }
            boolean r1 = anet.channel.GlobalAppRuntimeInfo.isAppBackground()     // Catch:{ Throwable -> 0x014c }
            r0.isBackground = r1     // Catch:{ Throwable -> 0x014c }
            r0 = 0
            r14.z = r0     // Catch:{ Throwable -> 0x014c }
            goto L_0x015b
        L_0x014c:
            r0 = move-exception
            r14.notifyStatus(r12, r11)
            java.lang.String r1 = "awcn.TnetSpdySession"
            java.lang.String r2 = "connect exception "
            java.lang.String r3 = r14.o
            java.lang.Object[] r4 = new java.lang.Object[r13]
            anet.channel.util.ALog.e(r1, r2, r3, r0, r4)
        L_0x015b:
            return
        L_0x015c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.TnetSpdySession.connect():void");
    }

    public void close() {
        ALog.e("awcn.TnetSpdySession", "force close!", this.o, "session", this);
        notifyStatus(7, (b) null);
        try {
            if (this.C != null) {
                this.C.stop();
                this.C = null;
            }
            if (this.w != null) {
                this.w.closeSession();
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDisconnect() {
        this.x = false;
    }

    /* access modifiers changed from: protected */
    public Runnable getRecvTimeOutRunnable() {
        return new h(this);
    }

    public void ping(boolean z2) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.TnetSpdySession", "ping", this.o, c.f, this.f123c, "thread", Thread.currentThread().getName());
        }
        if (z2) {
            try {
                if (this.w == null) {
                    if (this.p != null) {
                        this.p.closeReason = "session null";
                    }
                    ALog.e("awcn.TnetSpdySession", this.f123c + " session null", this.o, new Object[0]);
                    close();
                } else if (this.m == 0 || this.m == 4) {
                    handleCallbacks(64, (b) null);
                    this.x = true;
                    this.p.ppkgCount++;
                    this.w.submitPing();
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.TnetSpdySession", this.f123c + " submit ping ms:" + (System.currentTimeMillis() - this.y) + " force:" + z2, this.o, new Object[0]);
                    }
                    setPingTimeout();
                    this.y = System.currentTimeMillis();
                    if (this.C != null) {
                        this.C.reSchedule();
                    }
                }
            } catch (SpdyErrorException e) {
                if (e.SpdyErrorGetCode() == -1104 || e.SpdyErrorGetCode() == -1103) {
                    ALog.e("awcn.TnetSpdySession", "Send request on closed session!!!", this.o, new Object[0]);
                    notifyStatus(6, new b(2));
                }
                ALog.e("awcn.TnetSpdySession", "ping", this.o, e, new Object[0]);
            } catch (Exception e2) {
                ALog.e("awcn.TnetSpdySession", "ping", this.o, e2, new Object[0]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.D != null) {
            this.D.auth(this, new i(this));
            return;
        }
        notifyStatus(4, (b) null);
        this.p.ret = 1;
        if (this.C != null) {
            this.C.start(this);
        }
    }

    public boolean isAvailable() {
        return this.m == 4;
    }

    private void c() {
        try {
            SpdyAgent.enableDebug = false;
            this.v = SpdyAgent.getInstance(this.f121a, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
            if (this.F != null && !this.F.isSecOff()) {
                this.v.setAccsSslCallback(new j(this));
            }
        } catch (Exception e) {
            ALog.e("awcn.TnetSpdySession", "Init failed.", (String) null, e, new Object[0]);
        }
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        this.p.connectionTime = (long) superviseConnectInfo.connectTime;
        this.p.sslTime = (long) superviseConnectInfo.handshakeTime;
        this.p.sslCalTime = (long) superviseConnectInfo.doHandshakeTime;
        this.p.netType = NetworkStatusHelper.getNetworkSubType();
        this.z = System.currentTimeMillis();
        notifyStatus(0, new b(1));
        b();
        ALog.e("awcn.TnetSpdySession", "spdySessionConnectCB connect", this.o, "connectTime", Integer.valueOf(superviseConnectInfo.connectTime), "sslTime:", Integer.valueOf(superviseConnectInfo.handshakeTime));
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        if (ALog.isPrintLog(2)) {
            ALog.i("awcn.TnetSpdySession", "ping receive", this.o, "Host", this.f123c, "id", Long.valueOf(j));
        }
        if (j >= 0) {
            this.x = false;
            handleCallbacks(128, (b) null);
        }
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        ALog.e("awcn.TnetSpdySession", "[spdyCustomControlFrameRecvCallback]", this.o, "len", Integer.valueOf(i4), "frameCb", this.B);
        if (ALog.isPrintLog(1) && i4 < 512) {
            String str = "";
            for (int i5 = 0; i5 < bArr.length; i5++) {
                str = str + Integer.toHexString(bArr[i5] & 255) + " ";
            }
            ALog.e("awcn.TnetSpdySession", (String) null, this.o, "str", str);
        }
        if (this.B != null) {
            this.B.onDataReceive(this, bArr, i, i2);
        } else {
            ALog.e("awcn.TnetSpdySession", "AccsFrameCb is null", this.o, new Object[0]);
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(-105, (String) null, "rt"));
        }
        this.p.inceptCount++;
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("awcn.TnetSpdySession", "[spdySessionFailedError]session clean up failed!", (String) null, e, new Object[0]);
            }
        }
        notifyStatus(2, new b(256, i, "tnet connect fail"));
        ALog.e("awcn.TnetSpdySession", (String) null, this.o, " errorId:", Integer.valueOf(i));
        this.p.errorCode = (long) i;
        this.p.ret = 0;
        this.p.netType = NetworkStatusHelper.getNetworkSubType();
        AppMonitor.getInstance().commitStat(this.p);
        AppMonitor.getInstance().commitAlarm(this.p.getAlarmObject());
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        ALog.e("awcn.TnetSpdySession", "spdySessionCloseCallback", this.o, " errorCode:", Integer.valueOf(i));
        if (this.C != null) {
            this.C.stop();
            this.C = null;
        }
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("awcn.TnetSpdySession", "session clean up failed!", (String) null, e, new Object[0]);
            }
        }
        notifyStatus(6, new b(2));
        if (superviseConnectInfo != null) {
            this.p.requestCount = (long) superviseConnectInfo.reused_counter;
            this.p.liveTime = (long) superviseConnectInfo.keepalive_period_second;
        }
        if (this.p.errorCode == 0) {
            this.p.errorCode = (long) i;
        }
        this.p.lastPingInterval = (int) (System.currentTimeMillis() - this.y);
        AppMonitor.getInstance().commitStat(this.p);
        AppMonitor.getInstance().commitAlarm(this.p.getAlarmObject());
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        ALog.e("awcn.TnetSpdySession", "spdyCustomControlFrameFailCallback", this.o, "dataId", Integer.valueOf(i));
        a(i, i2, true, "tnet error");
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        String domain = spdySession.getDomain();
        if (TextUtils.isEmpty(domain)) {
            ALog.i("awcn.TnetSpdySession", "get sslticket host is null", (String) null, new Object[0]);
            return null;
        }
        try {
            if (this.F == null) {
                return null;
            }
            ISecurity iSecurity = this.F;
            Context context = this.f121a;
            return iSecurity.getBytes(context, "accs_ssl_key2_" + domain);
        } catch (Throwable th) {
            ALog.e("awcn.TnetSpdySession", "getSSLMeta", (String) null, th, new Object[0]);
            return null;
        }
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        String domain = spdySession.getDomain();
        if (TextUtils.isEmpty(domain)) {
            return -1;
        }
        int i = 0;
        try {
            if (this.F == null) {
                return -1;
            }
            ISecurity iSecurity = this.F;
            Context context = this.f121a;
            if (!iSecurity.saveBytes(context, "accs_ssl_key2_" + domain, bArr)) {
                i = -1;
            }
            return i;
        } catch (Throwable th) {
            ALog.e("awcn.TnetSpdySession", "putSSLMeta", (String) null, th, new Object[0]);
            return -1;
        }
    }

    /* compiled from: Taobao */
    private class a extends a {

        /* renamed from: b  reason: collision with root package name */
        private Request f228b;

        /* renamed from: c  reason: collision with root package name */
        private RequestCb f229c;
        private int d = 0;
        private long e = 0;

        public a(Request request, RequestCb requestCb) {
            this.f228b = request;
            this.f229c = requestCb;
        }

        public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.TnetSpdySession", "spdyDataChunkRecvCB", this.f228b.getSeq(), "len", Integer.valueOf(spdyByteArray.getDataLength()), "fin", Boolean.valueOf(z));
            }
            this.e += (long) spdyByteArray.getDataLength();
            this.f228b.f211a.recDataSize += (long) spdyByteArray.getDataLength();
            if (this.f229c != null) {
                ByteArray a2 = a.C0005a.f154a.a(spdyByteArray.getByteArray(), spdyByteArray.getDataLength());
                spdyByteArray.recycle();
                this.f229c.onDataReceive(a2, z);
            }
            TnetSpdySession.this.handleCallbacks(32, (b) null);
        }

        public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.TnetSpdySession", "spdyStreamCloseCallback", this.f228b.getSeq(), "streamId", Long.valueOf(j), "errorCode", Integer.valueOf(i));
            }
            String str = "SUCCESS";
            if (i != 0) {
                this.d = ErrorConstant.ERROR_TNET_REQUEST_FAIL;
                str = ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_REQUEST_FAIL, String.valueOf(i));
                if (i != -2005) {
                    AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_TNET_EXCEPTION, str, this.f228b.f211a, (Throwable) null));
                }
                ALog.e("awcn.TnetSpdySession", "spdyStreamCloseCallback error", this.f228b.getSeq(), "session", TnetSpdySession.this.o, "status code", Integer.valueOf(i), "URL", this.f228b.getHttpUrl().simpleUrlString());
            }
            a(superviseData, this.d, str);
            if (this.f229c != null) {
                this.f229c.onFinish(i, str, this.f228b.f211a);
            }
            if (i == -2004 && TnetSpdySession.b(TnetSpdySession.this) >= 2) {
                ConnEvent connEvent = new ConnEvent();
                connEvent.isSuccess = false;
                StrategyCenter.getInstance().notifyConnEvent(TnetSpdySession.this.d, TnetSpdySession.this.j, connEvent);
                TnetSpdySession.this.close(true);
            }
        }

        private void a(SuperviseData superviseData, int i, String str) {
            try {
                this.f228b.f211a.rspEnd = System.currentTimeMillis();
                if (!this.f228b.f211a.isDone.get()) {
                    if (i > 0) {
                        this.f228b.f211a.ret = 1;
                    }
                    this.f228b.f211a.statusCode = i;
                    this.f228b.f211a.msg = str;
                    if (superviseData != null) {
                        this.f228b.f211a.rspEnd = superviseData.responseEnd;
                        this.f228b.f211a.sendBeforeTime = superviseData.sendStart - superviseData.requestStart;
                        this.f228b.f211a.sendDataTime = superviseData.sendEnd - this.f228b.f211a.sendStart;
                        this.f228b.f211a.firstDataTime = superviseData.responseStart - superviseData.sendEnd;
                        this.f228b.f211a.recDataTime = superviseData.responseEnd - superviseData.responseStart;
                        this.f228b.f211a.sendDataSize = (long) (superviseData.bodySize + superviseData.compressSize);
                        this.f228b.f211a.recDataSize = this.e + ((long) superviseData.recvUncompressSize);
                        this.f228b.f211a.reqHeadInflateSize = (long) superviseData.uncompressSize;
                        this.f228b.f211a.reqHeadDeflateSize = (long) superviseData.compressSize;
                        this.f228b.f211a.reqBodyInflateSize = (long) superviseData.bodySize;
                        this.f228b.f211a.reqBodyDeflateSize = (long) superviseData.bodySize;
                        this.f228b.f211a.rspHeadDeflateSize = (long) superviseData.recvCompressSize;
                        this.f228b.f211a.rspHeadInflateSize = (long) superviseData.recvUncompressSize;
                        this.f228b.f211a.rspBodyDeflateSize = (long) superviseData.recvBodySize;
                        this.f228b.f211a.rspBodyInflateSize = this.e;
                        if (this.f228b.f211a.contentLength == 0) {
                            this.f228b.f211a.contentLength = (long) superviseData.originContentLength;
                        }
                        TnetSpdySession.this.p.recvSizeCount += (long) (superviseData.recvBodySize + superviseData.recvCompressSize);
                        TnetSpdySession.this.p.sendSizeCount += (long) (superviseData.bodySize + superviseData.compressSize);
                    }
                }
            } catch (Exception unused) {
            }
        }

        public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
            this.f228b.f211a.firstDataTime = System.currentTimeMillis() - this.f228b.f211a.sendStart;
            this.d = HttpHelper.parseStatusCode(map);
            int unused = TnetSpdySession.this.G = 0;
            ALog.i("awcn.TnetSpdySession", "", this.f228b.getSeq(), "statusCode", Integer.valueOf(this.d));
            ALog.i("awcn.TnetSpdySession", "", this.f228b.getSeq(), "response headers", map);
            if (this.f229c != null) {
                this.f229c.onResponseCode(this.d, HttpHelper.cloneMap(map));
            }
            TnetSpdySession.this.handleCallbacks(16, (b) null);
            this.f228b.f211a.contentEncoding = HttpHelper.getSingleHeaderFieldByKey(map, "Content-Encoding");
            this.f228b.f211a.contentType = HttpHelper.getSingleHeaderFieldByKey(map, "Content-Type");
            this.f228b.f211a.contentLength = (long) HttpHelper.parseContentLength(map);
            this.f228b.f211a.serverRT = HttpHelper.parseServerRT(map);
            TnetSpdySession.this.handleResponseCode(this.f228b, this.d);
            TnetSpdySession.this.handleResponseHeaders(this.f228b, map);
        }
    }
}
