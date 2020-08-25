package anet.channel.statist;

import anet.channel.entity.a;
import anet.channel.util.ALog;

@Monitor(module = "networkPrefer", monitorPoint = "session")
/* compiled from: Taobao */
public class SessionStatistic extends StatObject {
    public static int maxRetryTime;
    @Measure
    public long ackTime;
    @Measure(max = 15000.0d)
    public long authTime;
    @Measure
    public long cfRCount;
    @Dimension
    public String closeReason;
    @Measure(max = 15000.0d, name = "connTime")
    public long connectionTime;
    @Dimension(name = "protocolType")
    public String conntype;
    @Dimension
    public long errorCode;
    @Dimension
    public String host;
    @Measure
    public long inceptCount;
    @Dimension
    public String ip;
    @Dimension
    public int ipRefer = 0;
    @Dimension
    public int ipType = 1;
    @Dimension
    public boolean isBackground;
    public boolean isCommitted = false;
    @Dimension
    public long isKL;
    @Dimension
    public int isProxy = 0;
    @Dimension
    public String isTunnel;
    @Measure
    public int lastPingInterval;
    @Measure(max = 86400.0d)
    public long liveTime = 0;
    @Dimension
    public String netType;
    @Measure
    public long pRate;
    @Dimension
    public int port;
    @Measure
    public long ppkgCount;
    @Measure
    public long recvSizeCount;
    @Measure(constantValue = 1.0d)
    public long requestCount = 1;
    @Dimension
    public int ret;
    @Dimension
    public long retryTimes;
    @Dimension
    public int sdkv;
    @Measure
    public long sendSizeCount;
    @Measure(max = 15000.0d)
    public long sslCalTime;
    @Measure(max = 15000.0d)
    public long sslTime;
    @Measure(constantValue = 0.0d)
    public long stdRCount = 1;

    public SessionStatistic(a aVar) {
        this.ip = aVar.a();
        this.port = aVar.b();
        if (aVar.f175a != null) {
            this.ipRefer = aVar.f175a.getIpSource();
            this.ipType = aVar.f175a.getIpType();
        }
        this.pRate = (long) aVar.g();
        this.conntype = aVar.c().toString();
        this.retryTimes = (long) aVar.f176b;
        maxRetryTime = aVar.f177c;
    }

    public boolean beforeCommit() {
        if (this.ret == 0 && (this.retryTimes != ((long) maxRetryTime) || this.errorCode == -2613 || this.errorCode == -2601)) {
            if (ALog.isPrintLog(1)) {
                ALog.d("SessionStat no need commit", (String) null, "retry:", Long.valueOf(this.retryTimes), "maxRetryTime", Integer.valueOf(maxRetryTime), "errorCode", Long.valueOf(this.errorCode));
            }
            return false;
        } else if (this.isCommitted) {
            return false;
        } else {
            this.isCommitted = true;
            return true;
        }
    }

    public AlarmObject getAlarmObject() {
        AlarmObject alarmObject = new AlarmObject();
        alarmObject.module = "networkPrefer";
        alarmObject.modulePoint = "connect_succ_rate";
        alarmObject.isSuccess = this.ret != 0;
        if (alarmObject.isSuccess) {
            alarmObject.arg = this.closeReason;
        } else {
            alarmObject.errorCode = String.valueOf(this.errorCode);
        }
        return alarmObject;
    }
}
