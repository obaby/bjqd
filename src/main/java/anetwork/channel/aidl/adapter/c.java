package anetwork.channel.aidl.adapter;

/* compiled from: Taobao */
class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ byte f370a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Object f371b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ParcelableNetworkListenerWrapper f372c;

    c(ParcelableNetworkListenerWrapper parcelableNetworkListenerWrapper, byte b2, Object obj) {
        this.f372c = parcelableNetworkListenerWrapper;
        this.f370a = b2;
        this.f371b = obj;
    }

    public void run() {
        this.f372c.dispatchCallback(this.f370a, this.f371b);
    }
}
