package anet.channel.appmonitor;

import anet.channel.statist.AlarmObject;
import anet.channel.statist.CountObject;
import anet.channel.statist.StatObject;

/* compiled from: Taobao */
public class AppMonitor {
    private static volatile IAppMonitor appMonitor = new a((IAppMonitor) null);

    public static IAppMonitor getInstance() {
        return appMonitor;
    }

    public static void setInstance(IAppMonitor iAppMonitor) {
        appMonitor = new a(iAppMonitor);
    }

    /* compiled from: Taobao */
    static class a implements IAppMonitor {

        /* renamed from: a  reason: collision with root package name */
        IAppMonitor f142a = null;

        @Deprecated
        public void register() {
        }

        @Deprecated
        public void register(Class<?> cls) {
        }

        a(IAppMonitor iAppMonitor) {
            this.f142a = iAppMonitor;
        }

        public void commitStat(StatObject statObject) {
            if (this.f142a != null) {
                this.f142a.commitStat(statObject);
            }
        }

        public void commitAlarm(AlarmObject alarmObject) {
            if (this.f142a != null) {
                this.f142a.commitAlarm(alarmObject);
            }
        }

        public void commitCount(CountObject countObject) {
            if (this.f142a != null) {
                this.f142a.commitCount(countObject);
            }
        }
    }
}
