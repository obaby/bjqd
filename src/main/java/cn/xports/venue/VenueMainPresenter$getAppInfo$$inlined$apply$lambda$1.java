package cn.xports.venue;

import cn.xports.base.Constant;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.SPUtil;
import cn.xports.entity.App;
import cn.xports.parser.AppParser;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"cn/xports/venue/VenueMainPresenter$getAppInfo$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/AppParser;", "next", "", "value", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainPresenter.kt */
public final class VenueMainPresenter$getAppInfo$$inlined$apply$lambda$1 extends ProcessObserver<AppParser> {
    final /* synthetic */ String $outerUid$inlined;
    final /* synthetic */ String $phone$inlined;
    final /* synthetic */ VenueMainPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueMainPresenter$getAppInfo$$inlined$apply$lambda$1(IView iView, VenueMainPresenter venueMainPresenter, String str, String str2) {
        super(iView);
        this.this$0 = venueMainPresenter;
        this.$phone$inlined = str;
        this.$outerUid$inlined = str2;
    }

    public void next(@Nullable AppParser appParser) {
        String str;
        String str2;
        String str3;
        if (appParser != null) {
            SPUtil instance = SPUtil.Companion.getINSTANCE();
            App app = appParser.getApp();
            if (app == null || (str = app.getCenterId()) == null) {
                str = "";
            }
            SPUtil save = instance.save("centerId", str);
            App app2 = appParser.getApp();
            if (app2 == null || (str2 = app2.getAppId()) == null) {
                str2 = "";
            }
            SPUtil save2 = save.save("appId", str2);
            App app3 = appParser.getApp();
            if (app3 == null || (str3 = app3.getOssUrl()) == null) {
                str3 = "";
            }
            save2.save("ossUrl", str3).apply();
            VenueMainPresenter venueMainPresenter = this.this$0;
            String str4 = this.$phone$inlined;
            if (str4 == null) {
                str4 = "";
            }
            String str5 = this.$outerUid$inlined;
            if (str5 == null) {
                str5 = "";
            }
            venueMainPresenter.login(str4, str5);
            App app4 = appParser.getApp();
            Constant.PAYMODES = app4 != null ? app4.getPayModes() : null;
            this.this$0.getOrderNum();
        }
    }
}
