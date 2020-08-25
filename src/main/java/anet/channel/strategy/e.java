package anet.channel.strategy;

/* compiled from: Taobao */
class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f295a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ StrategyInfoHolder f296b;

    e(StrategyInfoHolder strategyInfoHolder, String str) {
        this.f296b = strategyInfoHolder;
        this.f295a = str;
    }

    public void run() {
        this.f296b.a(this.f295a, true);
    }
}
