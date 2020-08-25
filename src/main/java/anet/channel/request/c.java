package anet.channel.request;

import anet.channel.util.ALog;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdySession;

/* compiled from: Taobao */
public class c implements Cancelable {
    public static final c NULL = new c((SpdySession) null, 0, (String) null);

    /* renamed from: a  reason: collision with root package name */
    private final int f219a;

    /* renamed from: b  reason: collision with root package name */
    private final SpdySession f220b;

    /* renamed from: c  reason: collision with root package name */
    private final String f221c;

    public c(SpdySession spdySession, int i, String str) {
        this.f220b = spdySession;
        this.f219a = i;
        this.f221c = str;
    }

    public void cancel() {
        try {
            if (this.f220b != null && this.f219a != 0) {
                ALog.i("awcn.TnetCancelable", "cancel tnet request", this.f221c, "streamId", Integer.valueOf(this.f219a));
                this.f220b.streamReset((long) this.f219a, 5);
            }
        } catch (SpdyErrorException e) {
            ALog.e("awcn.TnetCancelable", "request cancel failed.", this.f221c, e, new Object[0]);
        }
    }
}
