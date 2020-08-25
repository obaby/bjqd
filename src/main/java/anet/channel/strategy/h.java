package anet.channel.strategy;

/* compiled from: Taobao */
class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f302a;

    h(g gVar) {
        this.f302a = gVar;
    }

    public void run() {
        if (!this.f302a.a()) {
            this.f302a.f300b.c();
        }
    }
}
