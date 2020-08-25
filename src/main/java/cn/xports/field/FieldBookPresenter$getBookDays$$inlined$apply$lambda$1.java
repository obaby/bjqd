package cn.xports.field;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.entity.BookingDay;
import cn.xports.field.FieldBookContract;
import cn.xports.parser.BookDayParser;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"cn/xports/field/FieldBookPresenter$getBookDays$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/BookDayParser;", "next", "", "value", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookPresenter.kt */
public final class FieldBookPresenter$getBookDays$$inlined$apply$lambda$1 extends ProcessObserver<BookDayParser> {
    final /* synthetic */ String $serviceId$inlined;
    final /* synthetic */ String $venueId$inlined;
    final /* synthetic */ FieldBookPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FieldBookPresenter$getBookDays$$inlined$apply$lambda$1(String str, FieldBookPresenter fieldBookPresenter, String str2, String str3) {
        super(str);
        this.this$0 = fieldBookPresenter;
        this.$serviceId$inlined = str2;
        this.$venueId$inlined = str3;
    }

    public void next(@Nullable BookDayParser bookDayParser) {
        FieldBookContract.View view;
        if (bookDayParser != null && (view = (FieldBookContract.View) this.this$0.getRootView()) != null) {
            List<BookingDay> week = bookDayParser.getWeek();
            Intrinsics.checkExpressionValueIsNotNull(week, "week");
            view.showBookDay(week);
        }
    }
}
