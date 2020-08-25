package cn.xports.venue;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.SPUtil;
import cn.xports.parser.LoginParser;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"cn/xports/venue/VenueMainPresenter$login$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/LoginParser;", "next", "", "value", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainPresenter.kt */
public final class VenueMainPresenter$login$$inlined$apply$lambda$1 extends ProcessObserver<LoginParser> {
    final /* synthetic */ String $outerUid$inlined;
    final /* synthetic */ String $phone$inlined;
    final /* synthetic */ VenueMainPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueMainPresenter$login$$inlined$apply$lambda$1(IView iView, VenueMainPresenter venueMainPresenter, String str, String str2) {
        super(iView);
        this.this$0 = venueMainPresenter;
        this.$outerUid$inlined = str;
        this.$phone$inlined = str2;
    }

    public void next(@Nullable LoginParser loginParser) {
        String str;
        String str2;
        String str3;
        String str4;
        SPUtil instance = SPUtil.Companion.getINSTANCE();
        if (loginParser == null || (str = loginParser.getNetUserId()) == null) {
            str = "";
        }
        SPUtil save = instance.save("netUserId", str);
        if (loginParser == null || (str2 = loginParser.getAccessToken()) == null) {
            str2 = "";
        }
        SPUtil save2 = save.save("accessToken", str2);
        if (loginParser == null || (str3 = loginParser.getCoAppId()) == null) {
            str3 = "";
        }
        SPUtil save3 = save2.save("coAppId", str3);
        if (loginParser == null || (str4 = loginParser.getCoAccountId()) == null) {
            str4 = "";
        }
        save3.save("coAccountId", str4).apply();
        this.this$0.getServiceList();
        this.this$0.getCenterAd();
    }
}
