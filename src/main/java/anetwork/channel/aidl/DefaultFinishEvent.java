package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ErrorConstant;
import anetwork.channel.NetworkEvent;
import anetwork.channel.statist.StatisticData;

/* compiled from: Taobao */
public class DefaultFinishEvent implements Parcelable, NetworkEvent.FinishEvent {
    public static final Parcelable.Creator<DefaultFinishEvent> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    Object f350a;

    /* renamed from: b  reason: collision with root package name */
    int f351b;

    /* renamed from: c  reason: collision with root package name */
    String f352c;
    StatisticData d;
    public final RequestStatistic rs;

    public int describeContents() {
        return 0;
    }

    public Object getContext() {
        return this.f350a;
    }

    public void setContext(Object obj) {
        this.f350a = obj;
    }

    public int getHttpCode() {
        return this.f351b;
    }

    public String getDesc() {
        return this.f352c;
    }

    public StatisticData getStatisticData() {
        return this.d;
    }

    public DefaultFinishEvent(int i) {
        this(i, (String) null, (RequestStatistic) null);
    }

    public DefaultFinishEvent(int i, String str, RequestStatistic requestStatistic) {
        this.d = new StatisticData();
        this.f351b = i;
        this.f352c = str == null ? ErrorConstant.getErrMsg(i) : str;
        this.rs = requestStatistic;
    }

    public String toString() {
        return "DefaultFinishEvent [" + "code=" + this.f351b + ", desc=" + this.f352c + ", context=" + this.f350a + ", statisticData=" + this.d + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f351b);
        parcel.writeString(this.f352c);
        if (this.d != null) {
            parcel.writeSerializable(this.d);
        }
    }

    static DefaultFinishEvent a(Parcel parcel) {
        DefaultFinishEvent defaultFinishEvent = new DefaultFinishEvent(0);
        try {
            defaultFinishEvent.f351b = parcel.readInt();
            defaultFinishEvent.f352c = parcel.readString();
            defaultFinishEvent.d = (StatisticData) parcel.readSerializable();
        } catch (Throwable unused) {
        }
        return defaultFinishEvent;
    }
}
