package cn.xports.qd2.credit;

import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.K;
import com.alipay.sdk.packet.d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "data", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyCreditActivity.kt */
final class MyCreditActivity$getCreditDetails$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ MyCreditActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCreditActivity$getCreditDetails$1(MyCreditActivity myCreditActivity) {
        super(1);
        this.this$0 = myCreditActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, d.k);
        this.this$0.creditDetails.clear();
        this.this$0.creditDetails.addAll(dataMap.getDataList(K.poAccountChangeList));
        this.this$0.creditDetailAdapter.notifyDataSetChanged();
    }
}
