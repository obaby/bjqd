package anet.channel.strategy;

/* compiled from: Taobao */
class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f273a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Object f274b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ a f275c;

    b(a aVar, String str, Object obj) {
        this.f275c = aVar;
        this.f273a = str;
        this.f274b = obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d5, code lost:
        if (anet.channel.util.ALog.isPrintLog(1) != false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d7, code lost:
        anet.channel.util.ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns failed", (java.lang.String) null, com.alipay.sdk.cons.c.f, r13.f273a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ec, code lost:
        monitor-enter(r13.f275c.f272b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r13.f275c.f272b.remove(r13.f273a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f9, code lost:
        monitor-enter(r13.f274b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        r13.f274b.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x010b, code lost:
        monitor-enter(r13.f275c.f272b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r13.f275c.f272b.remove(r13.f273a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0118, code lost:
        monitor-enter(r13.f274b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        r13.f274b.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x011f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00d1 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A[Catch:{ Exception -> 0x00d1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r13 = this;
            r0 = 2
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = r13.f273a     // Catch:{ Exception -> 0x00d1 }
            java.net.InetAddress r4 = java.net.InetAddress.getByName(r4)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r4 = r4.getHostAddress()     // Catch:{ Exception -> 0x00d1 }
            boolean r5 = anet.channel.strategy.utils.c.a((java.lang.String) r4)     // Catch:{ Exception -> 0x00d1 }
            if (r5 == 0) goto L_0x00a5
            java.util.LinkedList r12 = new java.util.LinkedList     // Catch:{ Exception -> 0x00d1 }
            r12.<init>()     // Catch:{ Exception -> 0x00d1 }
            anet.channel.strategy.StrategyTemplate r5 = anet.channel.strategy.StrategyTemplate.getInstance()     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r6 = r13.f273a     // Catch:{ Exception -> 0x00d1 }
            anet.channel.strategy.ConnProtocol r7 = r5.getConnProtocol(r6)     // Catch:{ Exception -> 0x00d1 }
            if (r7 == 0) goto L_0x0054
            java.lang.String r5 = r7.protocol     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r6 = "https"
            boolean r5 = r5.equalsIgnoreCase(r6)     // Catch:{ Exception -> 0x00d1 }
            if (r5 != 0) goto L_0x003a
            java.lang.String r5 = r7.publicKey     // Catch:{ Exception -> 0x00d1 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x00d1 }
            if (r5 != 0) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            r5 = 0
            goto L_0x003b
        L_0x003a:
            r5 = 1
        L_0x003b:
            if (r5 != 0) goto L_0x0042
            r5 = 80
            r6 = 80
            goto L_0x0046
        L_0x0042:
            r5 = 443(0x1bb, float:6.21E-43)
            r6 = 443(0x1bb, float:6.21E-43)
        L_0x0046:
            r8 = 0
            r9 = 0
            r10 = 1
            r11 = 45000(0xafc8, float:6.3058E-41)
            r5 = r4
            anet.channel.strategy.IPConnStrategy r5 = anet.channel.strategy.IPConnStrategy.a(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x00d1 }
            r12.add(r5)     // Catch:{ Exception -> 0x00d1 }
        L_0x0054:
            r6 = 80
            anet.channel.strategy.ConnProtocol r7 = anet.channel.strategy.ConnProtocol.HTTP     // Catch:{ Exception -> 0x00d1 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r5 = r4
            anet.channel.strategy.IPConnStrategy r5 = anet.channel.strategy.IPConnStrategy.a(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x00d1 }
            r12.add(r5)     // Catch:{ Exception -> 0x00d1 }
            r6 = 443(0x1bb, float:6.21E-43)
            anet.channel.strategy.ConnProtocol r7 = anet.channel.strategy.ConnProtocol.HTTPS     // Catch:{ Exception -> 0x00d1 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r5 = r4
            anet.channel.strategy.IPConnStrategy r5 = anet.channel.strategy.IPConnStrategy.a(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x00d1 }
            r12.add(r5)     // Catch:{ Exception -> 0x00d1 }
            anet.channel.strategy.a r5 = r13.f275c     // Catch:{ Exception -> 0x00d1 }
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<anet.channel.strategy.IPConnStrategy>> r5 = r5.f271a     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r6 = r13.f273a     // Catch:{ Exception -> 0x00d1 }
            r5.put(r6, r12)     // Catch:{ Exception -> 0x00d1 }
            boolean r5 = anet.channel.util.ALog.isPrintLog(r3)     // Catch:{ Exception -> 0x00d1 }
            if (r5 == 0) goto L_0x00b0
            java.lang.String r5 = "awcn.LocalDnsStrategyTable"
            java.lang.String r6 = "resolve ip by local dns"
            r7 = 6
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r8 = "host"
            r7[r2] = r8     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r8 = r13.f273a     // Catch:{ Exception -> 0x00d1 }
            r7[r3] = r8     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r8 = "ip"
            r7[r0] = r8     // Catch:{ Exception -> 0x00d1 }
            r8 = 3
            r7[r8] = r4     // Catch:{ Exception -> 0x00d1 }
            r4 = 4
            java.lang.String r8 = "list"
            r7[r4] = r8     // Catch:{ Exception -> 0x00d1 }
            r4 = 5
            r7[r4] = r12     // Catch:{ Exception -> 0x00d1 }
            anet.channel.util.ALog.d(r5, r6, r1, r7)     // Catch:{ Exception -> 0x00d1 }
            goto L_0x00b0
        L_0x00a5:
            anet.channel.strategy.a r4 = r13.f275c     // Catch:{ Exception -> 0x00d1 }
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<anet.channel.strategy.IPConnStrategy>> r4 = r4.f271a     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r5 = r13.f273a     // Catch:{ Exception -> 0x00d1 }
            java.util.List r6 = java.util.Collections.EMPTY_LIST     // Catch:{ Exception -> 0x00d1 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x00d1 }
        L_0x00b0:
            anet.channel.strategy.a r0 = r13.f275c
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r0.f272b
            monitor-enter(r0)
            anet.channel.strategy.a r1 = r13.f275c     // Catch:{ all -> 0x00cc }
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r1.f272b     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = r13.f273a     // Catch:{ all -> 0x00cc }
            r1.remove(r2)     // Catch:{ all -> 0x00cc }
            monitor-exit(r0)     // Catch:{ all -> 0x00cc }
            java.lang.Object r1 = r13.f274b
            monitor-enter(r1)
            java.lang.Object r0 = r13.f274b     // Catch:{ all -> 0x00c9 }
            r0.notifyAll()     // Catch:{ all -> 0x00c9 }
            monitor-exit(r1)     // Catch:{ all -> 0x00c9 }
            goto L_0x0100
        L_0x00c9:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00c9 }
            throw r0
        L_0x00cc:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00cc }
            throw r1
        L_0x00cf:
            r0 = move-exception
            goto L_0x0107
        L_0x00d1:
            boolean r4 = anet.channel.util.ALog.isPrintLog(r3)     // Catch:{ all -> 0x00cf }
            if (r4 == 0) goto L_0x00e8
            java.lang.String r4 = "awcn.LocalDnsStrategyTable"
            java.lang.String r5 = "resolve ip by local dns failed"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x00cf }
            java.lang.String r6 = "host"
            r0[r2] = r6     // Catch:{ all -> 0x00cf }
            java.lang.String r2 = r13.f273a     // Catch:{ all -> 0x00cf }
            r0[r3] = r2     // Catch:{ all -> 0x00cf }
            anet.channel.util.ALog.d(r4, r5, r1, r0)     // Catch:{ all -> 0x00cf }
        L_0x00e8:
            anet.channel.strategy.a r0 = r13.f275c
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r0.f272b
            monitor-enter(r0)
            anet.channel.strategy.a r1 = r13.f275c     // Catch:{ all -> 0x0104 }
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r1.f272b     // Catch:{ all -> 0x0104 }
            java.lang.String r2 = r13.f273a     // Catch:{ all -> 0x0104 }
            r1.remove(r2)     // Catch:{ all -> 0x0104 }
            monitor-exit(r0)     // Catch:{ all -> 0x0104 }
            java.lang.Object r1 = r13.f274b
            monitor-enter(r1)
            java.lang.Object r0 = r13.f274b     // Catch:{ all -> 0x0101 }
            r0.notifyAll()     // Catch:{ all -> 0x0101 }
            monitor-exit(r1)     // Catch:{ all -> 0x0101 }
        L_0x0100:
            return
        L_0x0101:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0101 }
            throw r0
        L_0x0104:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0104 }
            throw r1
        L_0x0107:
            anet.channel.strategy.a r1 = r13.f275c
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r1.f272b
            monitor-enter(r1)
            anet.channel.strategy.a r2 = r13.f275c     // Catch:{ all -> 0x0123 }
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = r2.f272b     // Catch:{ all -> 0x0123 }
            java.lang.String r3 = r13.f273a     // Catch:{ all -> 0x0123 }
            r2.remove(r3)     // Catch:{ all -> 0x0123 }
            monitor-exit(r1)     // Catch:{ all -> 0x0123 }
            java.lang.Object r2 = r13.f274b
            monitor-enter(r2)
            java.lang.Object r1 = r13.f274b     // Catch:{ all -> 0x0120 }
            r1.notifyAll()     // Catch:{ all -> 0x0120 }
            monitor-exit(r2)     // Catch:{ all -> 0x0120 }
            throw r0
        L_0x0120:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0120 }
            throw r0
        L_0x0123:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0123 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.strategy.b.run():void");
    }
}
