package anet.channel.session;

import android.content.Context;
import anet.channel.AwcnConfig;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.entity.ConnType;
import anet.channel.entity.a;
import anet.channel.entity.b;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.statist.RequestStatistic;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.Utils;
import anet.channel.util.f;
import com.alipay.sdk.cons.c;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: Taobao */
public class d extends Session {
    private SSLSocketFactory v;

    /* access modifiers changed from: protected */
    public Runnable getRecvTimeOutRunnable() {
        return null;
    }

    public d(Context context, a aVar) {
        super(context, aVar);
        if (this.j == null) {
            this.i = (this.f123c == null || !this.f123c.startsWith("https")) ? ConnType.HTTP : ConnType.HTTPS;
        } else if (AwcnConfig.isHttpsSniEnable() && this.i.equals(ConnType.HTTPS)) {
            this.v = new f(this.d);
        }
    }

    public boolean isAvailable() {
        return this.m == 4;
    }

    public void connect() {
        try {
            ALog.i("awcn.HttpSession", "HttpSession connect", (String) null, c.f, this.f123c);
            Request.Builder redirectEnable = new Request.Builder().setUrl(this.f123c).setSeq(this.o).setConnectTimeout((int) (((float) this.q) * Utils.getNetworkTimeFactor())).setReadTimeout((int) (((float) this.r) * Utils.getNetworkTimeFactor())).setRedirectEnable(false);
            if (this.v != null) {
                redirectEnable.setSslSocketFactory(this.v);
            }
            if (this.l) {
                redirectEnable.addHeader("Host", this.e);
            }
            Request build = redirectEnable.build();
            build.setDnsOptimize(this.e, this.f);
            ThreadPoolExecutorFactory.submitPriorityTask(new e(this, build), ThreadPoolExecutorFactory.Priority.LOW);
        } catch (Throwable th) {
            ALog.e("awcn.HttpSession", "HTTP connect fail.", (String) null, th, new Object[0]);
        }
    }

    public void close() {
        notifyStatus(6, (b) null);
    }

    public void close(boolean z) {
        this.s = false;
        close();
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        anet.channel.request.b bVar = anet.channel.request.b.NULL;
        Request.Builder builder = null;
        RequestStatistic requestStatistic = request != null ? request.f211a : new RequestStatistic(this.d, (String) null);
        requestStatistic.setConnType(this.i);
        if (requestStatistic.start == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            requestStatistic.reqStart = currentTimeMillis;
            requestStatistic.start = currentTimeMillis;
        }
        if (request == null || requestCb == null) {
            if (requestCb != null) {
                requestCb.onFinish(-102, ErrorConstant.getErrMsg(-102), requestStatistic);
            }
            return bVar;
        }
        try {
            if (request.getSslSocketFactory() == null && this.v != null) {
                builder = request.newBuilder().setSslSocketFactory(this.v);
            }
            if (this.l) {
                if (builder == null) {
                    builder = request.newBuilder();
                }
                builder.addHeader("Host", this.e);
            }
            if (builder != null) {
                request = builder.build();
            }
            request.setDnsOptimize(this.e, this.f);
            request.setUrlScheme(this.i.isSSL());
            if (this.j != null) {
                request.f211a.setIpInfo(this.j.getIpSource(), this.j.getIpType());
            } else {
                request.f211a.setIpInfo(1, 1);
            }
            request.f211a.unit = this.k;
            return new anet.channel.request.b(ThreadPoolExecutorFactory.submitPriorityTask(new f(this, request, requestCb, requestStatistic), anet.channel.util.d.a(request)), request.getSeq());
        } catch (Throwable th) {
            if (requestCb == null) {
                return bVar;
            }
            requestCb.onFinish(-101, ErrorConstant.formatMsg(-101, th.toString()), requestStatistic);
            return bVar;
        }
    }
}
