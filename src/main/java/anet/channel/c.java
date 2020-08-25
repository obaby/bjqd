package anet.channel;

import anet.channel.security.ISecurity;
import anet.channel.strategy.dispatch.IAmdcSign;

/* compiled from: Taobao */
class c implements IAmdcSign {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f155a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ISecurity f156b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ SessionCenter f157c;

    c(SessionCenter sessionCenter, String str, ISecurity iSecurity) {
        this.f157c = sessionCenter;
        this.f155a = str;
        this.f156b = iSecurity;
    }

    public String getAppkey() {
        return this.f155a;
    }

    public String sign(String str) {
        return this.f156b.sign(this.f157c.f126b, ISecurity.SIGN_ALGORITHM_HMAC_SHA1, getAppkey(), str);
    }

    public boolean useSecurityGuard() {
        return !this.f156b.isSecOff();
    }
}
