package cn.xports.qd2.circle;

import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import com.alipay.sdk.packet.d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$getPostDetail$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ PostDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PostDetailActivity$getPostDetail$1(PostDetailActivity postDetailActivity) {
        super(1);
        this.this$0 = postDetailActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        this.this$0._$_findCachedViewById(R.id.srfLayout).finishRefresh();
        PostDetailActivity postDetailActivity = this.this$0;
        Integer intValue = dataMap.getDataMap(d.k).getIntValue("canComment");
        boolean z = true;
        if (intValue == null || intValue.intValue() != 1) {
            z = false;
        }
        postDetailActivity.canComment = z;
        PostDetailActivity postDetailActivity2 = this.this$0;
        DataMap dataMap2 = dataMap.getDataMap(d.k);
        Intrinsics.checkExpressionValueIsNotNull(dataMap2, "it.getDataMap(\"data\")");
        postDetailActivity2.initPostContent(dataMap2);
    }
}
