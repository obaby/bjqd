package cn.xports.qd2.campaign;

import android.widget.TextView;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.TimeUtils;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: SportDetailActivity.kt */
final class SportDetailActivity$countDown$1<T> implements Consumer<Long> {
    final /* synthetic */ String $endDate;
    final /* synthetic */ long $origin1;
    final /* synthetic */ SportDetailActivity this$0;

    SportDetailActivity$countDown$1(SportDetailActivity sportDetailActivity, long j, String str) {
        this.this$0 = sportDetailActivity;
        this.$origin1 = j;
        this.$endDate = str;
    }

    public final void accept(Long l) {
        long j = this.$origin1;
        Intrinsics.checkExpressionValueIsNotNull(l, "it");
        if (j - l.longValue() < ((long) 1440)) {
            ((TextView) this.this$0._$_findCachedViewById(R.id.ctMinute)).setBackgroundColor(ColorUtils.getColor(R.color.red_fd4));
            ((TextView) this.this$0._$_findCachedViewById(R.id.ctDay)).setBackgroundColor(ColorUtils.getColor(R.color.red_fd4));
            ((TextView) this.this$0._$_findCachedViewById(R.id.ctHour)).setBackgroundColor(ColorUtils.getColor(R.color.red_fd4));
        }
        long timeSpanByNow = TimeUtils.getTimeSpanByNow(this.$endDate, 86400000);
        long timeSpanByNow2 = TimeUtils.getTimeSpanByNow(this.$endDate, 3600000) - (((long) 24) * timeSpanByNow);
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.ctDay);
        Intrinsics.checkExpressionValueIsNotNull(textView, "ctDay");
        textView.setText(String.valueOf(timeSpanByNow));
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.ctHour);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "ctHour");
        textView2.setText(String.valueOf(timeSpanByNow2));
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.ctMinute);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "ctMinute");
        textView3.setText(String.valueOf(TimeUtils.getTimeSpanByNow(this.$endDate, 60000) - (TimeUtils.getTimeSpanByNow(this.$endDate, 3600000) * ((long) 60))));
    }
}
