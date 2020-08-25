package cn.xports.qd2.training;

import cn.xports.qd2.entity.K;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyCourseDetailActivity.kt */
final class MyCourseDetailActivity$initView$2<T> implements Consumer<String> {
    final /* synthetic */ MyCourseDetailActivity this$0;

    MyCourseDetailActivity$initView$2(MyCourseDetailActivity myCourseDetailActivity) {
        this.this$0 = myCourseDetailActivity;
    }

    public final void accept(String str) {
        if (Intrinsics.areEqual(str, K.commentTag)) {
            this.this$0.getDetail();
        }
    }
}
