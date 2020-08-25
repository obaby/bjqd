package cn.xports.qd2;

import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.TimeUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyCenterActivity.kt */
final class MyCenterActivity$getAppoints$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ MyCenterActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCenterActivity$getAppoints$1(MyCenterActivity myCenterActivity) {
        super(1);
        this.this$0 = myCenterActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataList(K.appoints);
        Intrinsics.checkExpressionValueIsNotNull(dataList, K.courseList);
        int i = 0;
        for (DataMap string : dataList) {
            if (TimeUtils.isToday(string.getString(K.lessonDate))) {
                i++;
            }
        }
        this.this$0.setAppointView(dataList.size(), i);
    }
}
