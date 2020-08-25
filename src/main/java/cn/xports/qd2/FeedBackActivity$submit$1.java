package cn.xports.qd2;

import cn.xports.base.GlobalDialog;
import cn.xports.baselib.bean.DataMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: FeedBackActivity.kt */
final class FeedBackActivity$submit$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ FeedBackActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FeedBackActivity$submit$1(FeedBackActivity feedBackActivity) {
        super(1);
        this.this$0 = feedBackActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        new GlobalDialog(this.this$0, dataMap.getMessage()).setCancelVisible(false).setButtonClick(new GlobalDialog.OnButtonClick(this) {
            final /* synthetic */ FeedBackActivity$submit$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(int i) {
                this.this$0.this$0.finish();
            }
        });
    }
}
