package anet.channel.statist;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import cn.xports.qd2.entity.K;
import java.util.concurrent.atomic.AtomicBoolean;

@Monitor(module = "networkPrefer", monitorPoint = "network")
/* compiled from: Taobao */
public class RequestStatistic extends StatObject {
    @Dimension
    public float accuracy = -1.0f;
    @Dimension
    public volatile String bizId;
    @Dimension
    public volatile String bssid = null;
    @Measure
    public volatile long cacheTime = 0;
    @Measure
    public volatile long callbackTime = 0;
    @Measure
    public volatile long connWaitTime = 0;
    @Dimension
    public volatile String contentEncoding = null;
    public volatile long contentLength = 0;
    @Dimension
    public volatile String contentType = null;
    @Dimension
    public volatile int degraded = 0;
    @Dimension
    public volatile StringBuilder errorTrace = null;
    @Measure
    public volatile long firstDataTime = 0;
    @Dimension
    public volatile String host;
    @Dimension
    public volatile String ip;
    @Dimension
    public volatile int ipRefer = 0;
    @Dimension
    public volatile int ipType = 1;
    @Dimension
    public volatile String isBg = "";
    @Dimension
    public volatile boolean isDNS = false;
    public final AtomicBoolean isDone = new AtomicBoolean(false);
    @Dimension
    public volatile boolean isProxy;
    @Dimension
    public volatile boolean isSSL;
    @Measure
    public volatile long lastProcessTime = 0;
    @Dimension
    public double lat = 90000.0d;
    @Dimension
    public double lng = 90000.0d;
    @Dimension
    public String mnc = K.k0;
    @Dimension(name = "errorMsg")
    public volatile String msg = "";
    @Dimension
    public volatile String netType = "";
    @Measure(max = 60000.0d)
    public volatile long oneWayTime = 0;
    @Dimension
    public volatile int port;
    @Measure
    public volatile long processTime = 0;
    @Dimension
    public volatile String protocolType;
    @Dimension
    public volatile String proxyType = "";
    @Measure
    public volatile long recDataSize = 0;
    @Measure
    public volatile long recDataTime = 0;
    @Measure
    public volatile long reqBodyDeflateSize = 0;
    @Measure
    public volatile long reqBodyInflateSize = 0;
    @Measure
    public volatile long reqHeadDeflateSize = 0;
    @Measure
    public volatile long reqHeadInflateSize = 0;
    public volatile long reqStart = 0;
    @Dimension
    public volatile int ret;
    @Measure
    public volatile long retryCostTime = 0;
    @Dimension
    public volatile int retryTimes;
    @Dimension
    public int roaming = 0;
    @Measure
    public volatile long rspBodyDeflateSize = 0;
    @Measure
    public volatile long rspBodyInflateSize = 0;
    public volatile long rspEnd = 0;
    @Measure
    public volatile long rspHeadDeflateSize = 0;
    @Measure
    public volatile long rspHeadInflateSize = 0;
    public volatile long rspStart = 0;
    @Measure
    public volatile long sendBeforeTime = 0;
    @Measure
    public volatile long sendDataSize = 0;
    @Measure
    public volatile long sendDataTime = 0;
    public volatile long sendEnd = 0;
    public volatile long sendStart = 0;
    @Measure
    public volatile long serverRT = 0;
    public volatile boolean spdyRequestSend = false;
    public volatile long start = 0;
    @Dimension(name = "errorCode")
    public volatile int statusCode = 0;
    @Dimension
    public String unit;
    @Dimension(name = "URL")
    public volatile String url;
    @Deprecated
    public volatile long waitingTime = 0;

    public RequestStatistic(String str, String str2) {
        this.host = str;
        this.proxyType = NetworkStatusHelper.getProxyType();
        this.isProxy = !this.proxyType.isEmpty();
        this.netType = NetworkStatusHelper.getNetworkSubType();
        this.bssid = NetworkStatusHelper.getWifiBSSID();
        this.isBg = GlobalAppRuntimeInfo.isAppBackground() ? "bg" : "fg";
        this.roaming = NetworkStatusHelper.isRoaming() ? 1 : 0;
        this.mnc = NetworkStatusHelper.getSimOp();
        this.bizId = str2;
    }

    public void setConnType(ConnType connType) {
        this.isSSL = connType.isSSL();
        this.protocolType = connType.toString();
    }

    public void setIPAndPort(String str, int i) {
        this.ip = str;
        this.port = i;
        if (str != null && i != 0) {
            this.isDNS = true;
        }
    }

    public void setIpInfo(int i, int i2) {
        this.ipRefer = i;
        this.ipType = i2;
    }

    public void appendErrorTrace(int i) {
        if (this.errorTrace == null) {
            this.errorTrace = new StringBuilder();
        }
        if (this.errorTrace.length() != 0) {
            this.errorTrace.append(",");
        }
        StringBuilder sb = this.errorTrace;
        sb.append(i);
        sb.append("=");
        sb.append(System.currentTimeMillis() - this.reqStart);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("[RequestStatistic]ret=");
        sb.append(this.ret);
        sb.append(",statusCode=");
        sb.append(this.statusCode);
        sb.append(",msg=");
        sb.append(this.msg);
        sb.append(",host=");
        sb.append(this.host);
        sb.append(",ip=");
        sb.append(this.ip);
        sb.append(",port=");
        sb.append(this.port);
        sb.append(",protocolType=");
        sb.append(this.protocolType);
        sb.append(",retryTime=");
        sb.append(this.retryTimes);
        sb.append(",retryCostTime=");
        sb.append(this.retryCostTime);
        sb.append(",processTime=");
        sb.append(this.processTime);
        sb.append(",connWaitTime=");
        sb.append(this.connWaitTime);
        sb.append(",cacheTime=");
        sb.append(this.cacheTime);
        sb.append(",sendDataTime=");
        sb.append(this.sendDataTime);
        sb.append(",firstDataTime=");
        sb.append(this.firstDataTime);
        sb.append(",recDataTime=");
        sb.append(this.recDataTime);
        sb.append(",lastProcessTime=");
        sb.append(this.lastProcessTime);
        sb.append(",oneWayTime=");
        sb.append(this.oneWayTime);
        sb.append(",callbackTime=");
        sb.append(this.callbackTime);
        sb.append(",serverRT=");
        sb.append(this.serverRT);
        sb.append(",sendSize=");
        sb.append(this.sendDataSize);
        sb.append(",recDataSize=");
        sb.append(this.recDataSize);
        sb.append(",originalDataSize=");
        sb.append(this.rspBodyDeflateSize);
        sb.append(",url=");
        sb.append(this.url);
        return sb.toString();
    }

    public boolean beforeCommit() {
        return this.statusCode != -200;
    }
}
