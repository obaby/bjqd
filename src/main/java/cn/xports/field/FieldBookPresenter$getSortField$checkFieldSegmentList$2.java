package cn.xports.field;

import cn.xports.entity.FieldSegment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/entity/FieldSegment;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: FieldBookPresenter.kt */
final class FieldBookPresenter$getSortField$checkFieldSegmentList$2 extends Lambda implements Function1<FieldSegment, Integer> {
    public static final FieldBookPresenter$getSortField$checkFieldSegmentList$2 INSTANCE = new FieldBookPresenter$getSortField$checkFieldSegmentList$2();

    FieldBookPresenter$getSortField$checkFieldSegmentList$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Integer.valueOf(invoke((FieldSegment) obj));
    }

    public final int invoke(@NotNull FieldSegment fieldSegment) {
        Intrinsics.checkParameterIsNotNull(fieldSegment, "it");
        return fieldSegment.getStartSegment();
    }
}
