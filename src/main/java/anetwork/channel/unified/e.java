package anetwork.channel.unified;

import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.bytes.ByteArray;
import anet.channel.flow.FlowStat;
import anet.channel.flow.NetworkAnalysis;
import anet.channel.monitor.b;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpHelper;
import anet.channel.util.HttpUrl;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.cache.Cache;
import anetwork.channel.cache.a;
import anetwork.channel.cookie.CookieManager;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Taobao */
class e implements RequestCb {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Request f429a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ c f430b;

    e(c cVar, Request request) {
        this.f430b = cVar;
        this.f429a = request;
    }

    public void onResponseCode(int i, Map<String, List<String>> map) {
        String singleHeaderFieldByKey;
        if (!this.f430b.h.get()) {
            if (ALog.isPrintLog(2)) {
                ALog.i(c.TAG, "onResponseCode", this.f429a.getSeq(), "code", Integer.valueOf(i));
                ALog.i(c.TAG, "onResponseCode", this.f429a.getSeq(), "headers", map);
            }
            if (HttpHelper.checkRedirect(this.f429a, i) && (singleHeaderFieldByKey = HttpHelper.getSingleHeaderFieldByKey(map, "Location")) != null) {
                HttpUrl parse = HttpUrl.parse(singleHeaderFieldByKey);
                if (parse == null) {
                    ALog.e(c.TAG, "redirect url is invalid!", this.f429a.getSeq(), "redirect url", singleHeaderFieldByKey);
                } else if (this.f430b.h.compareAndSet(false, true)) {
                    parse.lockScheme();
                    this.f430b.f423a.f431a.a(parse);
                    this.f430b.f423a.d = new AtomicBoolean();
                    this.f430b.f423a.e = new c(this.f430b.f423a, (Cache) null, (Cache.Entry) null);
                    ThreadPoolExecutorFactory.submitPriorityTask(this.f430b.f423a.e, ThreadPoolExecutorFactory.Priority.HIGH);
                    return;
                } else {
                    return;
                }
            }
            try {
                this.f430b.f423a.a();
                CookieManager.setCookie(this.f430b.f423a.f431a.g(), map);
                this.f430b.i = HttpHelper.parseContentLength(map);
                if (i != 304 || this.f430b.f425c == null) {
                    if (this.f430b.f424b != null && Request.Method.GET.equals(this.f429a.getMethod())) {
                        this.f430b.f425c = a.a(map);
                        if (this.f430b.f425c != null) {
                            HttpHelper.removeHeaderFiledByKey(map, "Cache-Control");
                            map.put("Cache-Control", Arrays.asList(new String[]{"no-store"}));
                            this.f430b.d = new ByteArrayOutputStream(this.f430b.i != 0 ? this.f430b.i : 5120);
                        }
                    }
                    this.f430b.f423a.f432b.onResponseCode(i, map);
                    return;
                }
                this.f430b.f425c.responseHeaders.putAll(map);
                this.f430b.f423a.f432b.onResponseCode(200, this.f430b.f425c.responseHeaders);
                this.f430b.f423a.f432b.onDataReceiveSize(1, this.f430b.f425c.data.length, ByteArray.wrap(this.f430b.f425c.data));
            } catch (Exception e) {
                ALog.w(c.TAG, "[onResponseCode] error.", this.f430b.f423a.f433c, e, new Object[0]);
            }
        }
    }

    public void onDataReceive(ByteArray byteArray, boolean z) {
        if (!this.f430b.h.get()) {
            if (this.f430b.j == 0) {
                ALog.i(c.TAG, "[onDataReceive] receive first data chunk!", this.f430b.f423a.f433c, new Object[0]);
            }
            if (z) {
                ALog.i(c.TAG, "[onDataReceive] receive last data chunk!", this.f430b.f423a.f433c, new Object[0]);
            }
            try {
                this.f430b.j++;
                this.f430b.f423a.f432b.onDataReceiveSize(this.f430b.j, this.f430b.i, byteArray);
                if (this.f430b.d != null) {
                    this.f430b.d.write(byteArray.getBuffer(), 0, byteArray.getDataLength());
                    if (z) {
                        String g = this.f430b.f423a.f431a.g();
                        this.f430b.f425c.data = this.f430b.d.toByteArray();
                        long currentTimeMillis = System.currentTimeMillis();
                        this.f430b.f424b.put(g, this.f430b.f425c);
                        ALog.i(c.TAG, "write cache", this.f430b.f423a.f433c, "cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "size", Integer.valueOf(this.f430b.f425c.data.length), "key", g);
                    }
                }
            } catch (Exception e) {
                ALog.w(c.TAG, "[onDataReceive] error.", this.f430b.f423a.f433c, e, new Object[0]);
            }
        }
    }

    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        DefaultFinishEvent defaultFinishEvent;
        if (!this.f430b.h.getAndSet(true)) {
            if (ALog.isPrintLog(2)) {
                ALog.i(c.TAG, "[onFinish]", this.f430b.f423a.f433c, "code", Integer.valueOf(i), "msg", str);
            }
            if (i < 0) {
                try {
                    if (this.f430b.f423a.f431a.d()) {
                        if (this.f430b.j == 0) {
                            this.f430b.f423a.f431a.k();
                            this.f430b.f423a.d = new AtomicBoolean();
                            this.f430b.f423a.e = new c(this.f430b.f423a, this.f430b.f424b, this.f430b.f425c);
                            requestStatistic.appendErrorTrace(i);
                            long currentTimeMillis = System.currentTimeMillis();
                            requestStatistic.retryCostTime += currentTimeMillis - requestStatistic.start;
                            requestStatistic.start = currentTimeMillis;
                            ThreadPoolExecutorFactory.submitPriorityTask(this.f430b.f423a.e, ThreadPoolExecutorFactory.Priority.HIGH);
                            return;
                        }
                        requestStatistic.msg += ":回调数据后触发重试";
                        ALog.e(c.TAG, "ERROR!!! Retry request after onDataReceived callback!!!", this.f430b.f423a.f433c, new Object[0]);
                        AppMonitor.getInstance().commitStat(new ExceptionStatistic(9876, "回调数据后触发重试", "rt"));
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            this.f430b.f423a.a();
            requestStatistic.isDone.set(true);
            if (!(!this.f430b.f423a.f431a.j() || requestStatistic.contentLength == 0 || requestStatistic.contentLength == requestStatistic.rspBodyDeflateSize)) {
                requestStatistic.ret = 0;
                requestStatistic.statusCode = ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH;
                str = ErrorConstant.getErrMsg(ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH);
                requestStatistic.msg = str;
                ALog.e(c.TAG, "received data lenght not match with content-length", this.f430b.f423a.f433c, "content-lenght", Integer.valueOf(this.f430b.i), "recDataLength", Long.valueOf(requestStatistic.rspBodyDeflateSize));
                ExceptionStatistic exceptionStatistic = new ExceptionStatistic(ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH, str, "rt");
                exceptionStatistic.url = this.f430b.f423a.f431a.g();
                AppMonitor.getInstance().commitStat(exceptionStatistic);
                i = ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH;
            }
            if (i == 0) {
                i = requestStatistic.statusCode;
            }
            if (i != 304 || this.f430b.f425c == null) {
                defaultFinishEvent = new DefaultFinishEvent(i, str, requestStatistic);
            } else {
                requestStatistic.protocolType = "cache";
                defaultFinishEvent = new DefaultFinishEvent(200, str, requestStatistic);
            }
            this.f430b.f423a.f432b.onFinish(defaultFinishEvent);
            if (i >= 0) {
                b.a().a(requestStatistic.sendStart, requestStatistic.rspEnd, requestStatistic.rspHeadDeflateSize + requestStatistic.rspBodyDeflateSize);
            }
            NetworkAnalysis.getInstance().commitFlow(new FlowStat(this.f430b.e, requestStatistic));
        }
    }
}
