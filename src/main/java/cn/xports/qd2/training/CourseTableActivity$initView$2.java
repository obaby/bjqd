package cn.xports.qd2.training;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.DateWeekday;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"cn/xports/qd2/training/CourseTableActivity$initView$2", "Landroid/support/v7/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "newState", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
public final class CourseTableActivity$initView$2 extends RecyclerView.OnScrollListener {
    final /* synthetic */ CourseTableActivity this$0;

    CourseTableActivity$initView$2(CourseTableActivity courseTableActivity) {
        this.this$0 = courseTableActivity;
    }

    public void onScrollStateChanged(@Nullable RecyclerView recyclerView, int i) {
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager != null) {
                Object obj = this.this$0.dayItems.get(((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition());
                if (obj != null) {
                    TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvMonth);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "tvMonth");
                    StringBuilder sb = new StringBuilder();
                    String date = ((DateWeekday) obj).getDate();
                    Intrinsics.checkExpressionValueIsNotNull(date, "day.date");
                    if (date != null) {
                        String substring = date.substring(5, 7);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb.append(Integer.parseInt(substring));
                        sb.append(10);
                        sb.append("月");
                        textView.setText(sb.toString());
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                throw new TypeCastException("null cannot be cast to non-null type cn.xports.qd2.entity.DateWeekday");
            }
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.LinearLayoutManager");
        }
    }
}
