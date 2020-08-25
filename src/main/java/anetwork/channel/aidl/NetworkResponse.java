package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.Response;
import anetwork.channel.statist.StatisticData;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class NetworkResponse implements Parcelable, Response {
    public static final Parcelable.Creator<NetworkResponse> CREATOR = new c();

    /* renamed from: a  reason: collision with root package name */
    int f357a;

    /* renamed from: b  reason: collision with root package name */
    byte[] f358b;

    /* renamed from: c  reason: collision with root package name */
    private String f359c;
    private Map<String, List<String>> d;
    private Throwable e;
    private StatisticData f;

    public int describeContents() {
        return 0;
    }

    public void setStatusCode(int i) {
        this.f357a = i;
        this.f359c = ErrorConstant.getErrMsg(i);
    }

    public byte[] getBytedata() {
        return this.f358b;
    }

    public void setBytedata(byte[] bArr) {
        this.f358b = bArr;
    }

    public void setConnHeadFields(Map<String, List<String>> map) {
        this.d = map;
    }

    public Map<String, List<String>> getConnHeadFields() {
        return this.d;
    }

    public void setDesc(String str) {
        this.f359c = str;
    }

    public String getDesc() {
        return this.f359c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NetworkResponse [");
        sb.append("statusCode=");
        sb.append(this.f357a);
        sb.append(", desc=");
        sb.append(this.f359c);
        sb.append(", connHeadFields=");
        sb.append(this.d);
        sb.append(", bytedata=");
        sb.append(this.f358b != null ? new String(this.f358b) : "");
        sb.append(", error=");
        sb.append(this.e);
        sb.append(", statisticData=");
        sb.append(this.f);
        sb.append("]");
        return sb.toString();
    }

    public NetworkResponse() {
    }

    public NetworkResponse(int i) {
        this.f357a = i;
        this.f359c = ErrorConstant.getErrMsg(i);
    }

    public int getStatusCode() {
        return this.f357a;
    }

    public Throwable getError() {
        return this.e;
    }

    public void setError(Throwable th) {
        this.e = th;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f357a);
        parcel.writeString(this.f359c);
        int length = this.f358b != null ? this.f358b.length : 0;
        parcel.writeInt(length);
        if (length > 0) {
            parcel.writeByteArray(this.f358b);
        }
        parcel.writeMap(this.d);
        if (this.f != null) {
            parcel.writeSerializable(this.f);
        }
    }

    public static NetworkResponse readFromParcel(Parcel parcel) {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            networkResponse.f357a = parcel.readInt();
            networkResponse.f359c = parcel.readString();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                networkResponse.f358b = new byte[readInt];
                parcel.readByteArray(networkResponse.f358b);
            }
            networkResponse.d = parcel.readHashMap(NetworkResponse.class.getClassLoader());
            try {
                networkResponse.f = (StatisticData) parcel.readSerializable();
            } catch (Throwable unused) {
                ALog.i("anet.NetworkResponse", "[readFromParcel] source.readSerializable() error", (String) null, new Object[0]);
            }
        } catch (Exception e2) {
            ALog.w("anet.NetworkResponse", "[readFromParcel]", (String) null, e2, new Object[0]);
        }
        return networkResponse;
    }

    public void setStatisticData(StatisticData statisticData) {
        this.f = statisticData;
    }

    public StatisticData getStatisticData() {
        return this.f;
    }
}
