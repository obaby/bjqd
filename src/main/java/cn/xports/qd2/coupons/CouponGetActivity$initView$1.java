package cn.xports.qd2.coupons;

import android.text.InputFilter;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u000e\u0010\u0007\u001a\n \u0002*\u0004\u0018\u00010\b0\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "source", "start", "", "end", "dest", "Landroid/text/Spanned;", "dstart", "dend", "filter"}, k = 3, mv = {1, 1, 15})
/* compiled from: CouponGetActivity.kt */
final class CouponGetActivity$initView$1 implements InputFilter {
    public static final CouponGetActivity$initView$1 INSTANCE = new CouponGetActivity$initView$1();

    CouponGetActivity$initView$1() {
    }

    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        Intrinsics.checkExpressionValueIsNotNull(charSequence, "source");
        if (new Regex("\\(.*?\\)|\\)|（.*?）|）").matches(charSequence)) {
            return "";
        }
        return new Regex("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]").matches(charSequence) ? "" : null;
    }
}
