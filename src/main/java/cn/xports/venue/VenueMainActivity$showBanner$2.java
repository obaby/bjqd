package cn.xports.venue;

import android.view.View;
import cn.bingoogolapple.bgabanner.BGABanner;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "banner", "Lcn/bingoogolapple/bgabanner/BGABanner;", "kotlin.jvm.PlatformType", "itemView", "Landroid/view/View;", "model", "", "position", "", "onBannerItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueMainActivity.kt */
final class VenueMainActivity$showBanner$2<V extends View, M> implements BGABanner.Delegate<View, Object> {
    final /* synthetic */ List $bannerAd;
    final /* synthetic */ VenueMainActivity this$0;

    VenueMainActivity$showBanner$2(VenueMainActivity venueMainActivity, List list) {
        this.this$0 = venueMainActivity;
        this.$bannerAd = list;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: cn.xports.parser.HomeBannerBean$AdList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: cn.xports.parser.HomeBannerBean$AdList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onBannerItemClick(cn.bingoogolapple.bgabanner.BGABanner r2, android.view.View r3, @org.jetbrains.annotations.Nullable java.lang.Object r4, int r5) {
        /*
            r1 = this;
            android.content.Intent r2 = new android.content.Intent
            cn.xports.venue.VenueMainActivity r3 = r1.this$0
            android.content.Context r3 = (android.content.Context) r3
            java.lang.Class<cn.xports.sportCoaching.WebViewDetailActivity> r4 = cn.xports.sportCoaching.WebViewDetailActivity.class
            r2.<init>(r3, r4)
            java.lang.String r3 = "title"
            java.util.List r4 = r1.$bannerAd
            r0 = 0
            if (r4 == 0) goto L_0x0019
            java.lang.Object r4 = r4.get(r5)
            cn.xports.parser.HomeBannerBean$AdList r4 = (cn.xports.parser.HomeBannerBean.AdList) r4
            goto L_0x001a
        L_0x0019:
            r4 = r0
        L_0x001a:
            java.lang.String r4 = r4.title
            r2.putExtra(r3, r4)
            java.lang.String r3 = "date"
            java.util.List r4 = r1.$bannerAd
            if (r4 == 0) goto L_0x002c
            java.lang.Object r4 = r4.get(r5)
            cn.xports.parser.HomeBannerBean$AdList r4 = (cn.xports.parser.HomeBannerBean.AdList) r4
            goto L_0x002d
        L_0x002c:
            r4 = r0
        L_0x002d:
            java.lang.String r4 = r4.createTime
            java.lang.String r4 = cn.xports.sportCoaching.DateShowUtils.dateToYYYYMMDDHHMM(r4)
            r2.putExtra(r3, r4)
            java.util.List r3 = r1.$bannerAd
            if (r3 == 0) goto L_0x0041
            java.lang.Object r3 = r3.get(r5)
            cn.xports.parser.HomeBannerBean$AdList r3 = (cn.xports.parser.HomeBannerBean.AdList) r3
            goto L_0x0042
        L_0x0041:
            r3 = r0
        L_0x0042:
            java.lang.String r3 = r3.contType
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x007b
            java.util.List r3 = r1.$bannerAd
            if (r3 == 0) goto L_0x0057
            java.lang.Object r3 = r3.get(r5)
            cn.xports.parser.HomeBannerBean$AdList r3 = (cn.xports.parser.HomeBannerBean.AdList) r3
            goto L_0x0058
        L_0x0057:
            r3 = r0
        L_0x0058:
            java.lang.String r3 = r3.contType
            java.lang.String r4 = "1"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L_0x007b
            java.lang.String r3 = "content_type"
            r4 = 1
            r2.putExtra(r3, r4)
            java.lang.String r3 = "content"
            java.util.List r4 = r1.$bannerAd
            if (r4 == 0) goto L_0x0075
            java.lang.Object r4 = r4.get(r5)
            r0 = r4
            cn.xports.parser.HomeBannerBean$AdList r0 = (cn.xports.parser.HomeBannerBean.AdList) r0
        L_0x0075:
            java.lang.String r4 = r0.content
            r2.putExtra(r3, r4)
            goto L_0x0093
        L_0x007b:
            java.lang.String r3 = "content_type"
            r4 = 0
            r2.putExtra(r3, r4)
            java.lang.String r3 = "content"
            java.util.List r4 = r1.$bannerAd
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r4.get(r5)
            r0 = r4
            cn.xports.parser.HomeBannerBean$AdList r0 = (cn.xports.parser.HomeBannerBean.AdList) r0
        L_0x008e:
            java.lang.String r4 = r0.outerLink
            r2.putExtra(r3, r4)
        L_0x0093:
            cn.xports.venue.VenueMainActivity r3 = r1.this$0
            r3.startActivity(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.venue.VenueMainActivity$showBanner$2.onBannerItemClick(cn.bingoogolapple.bgabanner.BGABanner, android.view.View, java.lang.Object, int):void");
    }
}
