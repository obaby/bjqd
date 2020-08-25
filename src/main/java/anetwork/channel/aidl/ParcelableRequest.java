package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import anet.channel.request.BodyEntry;
import anet.channel.util.ALog;
import anetwork.channel.Header;
import anetwork.channel.Param;
import anetwork.channel.Request;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class ParcelableRequest implements Parcelable {
    public static final Parcelable.Creator<ParcelableRequest> CREATOR = new e();
    public boolean allowRedirect;
    public String bizId;
    public BodyEntry bodyEntry;
    public String charset;
    public int connectTimeout;
    public Map<String, String> extProperties;
    public Map<String, String> headers = null;
    public String method;
    public Map<String, String> params = null;
    public int readTimeout;
    public Request request;
    public int retryTime;
    public String seqNo;
    public String url;

    public int describeContents() {
        return 0;
    }

    public ParcelableRequest(Request request2) {
        this.request = request2;
        if (request2 != null) {
            this.url = request2.getUrlString();
            this.retryTime = request2.getRetryTime();
            this.charset = request2.getCharset();
            this.allowRedirect = request2.getFollowRedirects();
            this.method = request2.getMethod();
            List<Header> headers2 = request2.getHeaders();
            if (headers2 != null) {
                this.headers = new HashMap();
                for (Header next : headers2) {
                    this.headers.put(next.getName(), next.getValue());
                }
            }
            List<Param> params2 = request2.getParams();
            if (params2 != null) {
                this.params = new HashMap();
                for (Param next2 : params2) {
                    this.params.put(next2.getKey(), next2.getValue());
                }
            }
            this.bodyEntry = request2.getBodyEntry();
            this.connectTimeout = request2.getConnectTimeout();
            this.readTimeout = request2.getReadTimeout();
            this.bizId = request2.getBizId();
            this.seqNo = request2.getSeqNo();
            this.extProperties = request2.getExtProperties();
        }
    }

    public ParcelableRequest() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.request != null) {
            try {
                parcel.writeInt(this.request.getRetryTime());
                parcel.writeString(this.url);
                parcel.writeString(this.request.getCharset());
                parcel.writeInt(this.request.getFollowRedirects() ? 1 : 0);
                parcel.writeString(this.request.getMethod());
                int i2 = 1;
                parcel.writeInt(this.headers == null ? 0 : 1);
                if (this.headers != null) {
                    parcel.writeMap(this.headers);
                }
                parcel.writeInt(this.params == null ? 0 : 1);
                if (this.params != null) {
                    parcel.writeMap(this.params);
                }
                parcel.writeParcelable(this.bodyEntry, 0);
                parcel.writeInt(this.request.getConnectTimeout());
                parcel.writeInt(this.request.getReadTimeout());
                parcel.writeString(this.request.getBizId());
                parcel.writeString(this.request.getSeqNo());
                Map<String, String> extProperties2 = this.request.getExtProperties();
                if (extProperties2 == null) {
                    i2 = 0;
                }
                parcel.writeInt(i2);
                if (extProperties2 != null) {
                    parcel.writeMap(extProperties2);
                }
            } catch (Throwable th) {
                ALog.w("anet.ParcelableRequest", "[writeToParcel]", (String) null, th, new Object[0]);
            }
        }
    }

    public static ParcelableRequest readFromParcel(Parcel parcel) {
        ParcelableRequest parcelableRequest = new ParcelableRequest();
        try {
            parcelableRequest.retryTime = parcel.readInt();
            parcelableRequest.url = parcel.readString();
            parcelableRequest.charset = parcel.readString();
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            parcelableRequest.allowRedirect = z;
            parcelableRequest.method = parcel.readString();
            if (parcel.readInt() != 0) {
                parcelableRequest.headers = parcel.readHashMap(ParcelableRequest.class.getClassLoader());
            }
            if (parcel.readInt() != 0) {
                parcelableRequest.params = parcel.readHashMap(ParcelableRequest.class.getClassLoader());
            }
            parcelableRequest.bodyEntry = (BodyEntry) parcel.readParcelable(ParcelableRequest.class.getClassLoader());
            parcelableRequest.connectTimeout = parcel.readInt();
            parcelableRequest.readTimeout = parcel.readInt();
            parcelableRequest.bizId = parcel.readString();
            parcelableRequest.seqNo = parcel.readString();
            if (parcel.readInt() != 0) {
                parcelableRequest.extProperties = parcel.readHashMap(ParcelableRequest.class.getClassLoader());
            }
        } catch (Throwable th) {
            ALog.w("anet.ParcelableRequest", "[readFromParcel]", (String) null, th, new Object[0]);
        }
        return parcelableRequest;
    }

    public String getExtProperty(String str) {
        if (this.extProperties == null) {
            return null;
        }
        return this.extProperties.get(str);
    }
}
