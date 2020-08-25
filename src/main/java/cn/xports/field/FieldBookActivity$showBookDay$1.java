package cn.xports.field;

import android.support.design.widget.TabLayout;
import cn.xports.entity.BookingDay;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, d2 = {"cn/xports/field/FieldBookActivity$showBookDay$1", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "onTabReselected", "", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "p0", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookActivity.kt */
public final class FieldBookActivity$showBookDay$1 implements TabLayout.OnTabSelectedListener {
    final /* synthetic */ List $bookDays;
    final /* synthetic */ FieldBookActivity this$0;

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    FieldBookActivity$showBookDay$1(FieldBookActivity fieldBookActivity, List list) {
        this.this$0 = fieldBookActivity;
        this.$bookDays = list;
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            FieldBookActivity fieldBookActivity = this.this$0;
            String date = ((BookingDay) this.$bookDays.get(tab.getPosition())).getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "bookDays[position].date");
            fieldBookActivity.selectDate = date;
            FieldBookPresenter fieldBookPresenter = (FieldBookPresenter) this.this$0.getPresenter();
            if (fieldBookPresenter != null) {
                fieldBookPresenter.getFieldTypes(this.this$0.selectDate, this.this$0.mServiceId, this.this$0.mVenueId);
            }
        }
    }
}
