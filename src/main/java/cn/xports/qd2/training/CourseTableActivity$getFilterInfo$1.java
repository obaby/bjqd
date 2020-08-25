package cn.xports.qd2.training;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.TimeUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/training/CourseTableActivity$getFilterInfo$1", "Lcn/xports/baselib/http/ResponseDataMap;", "onError", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "onSuccess", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
public final class CourseTableActivity$getFilterInfo$1 extends ResponseDataMap {
    final /* synthetic */ CourseTableActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CourseTableActivity$getFilterInfo$1(CourseTableActivity courseTableActivity, IView iView) {
        super(iView);
        this.this$0 = courseTableActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataList(K.students);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "stus");
        for (DataMap dataMap2 : dataList) {
            this.this$0.stuNameMap.put(dataMap2.getString(K.stuId), dataMap2.getString(K.stuName));
        }
        ArrayList<DataMap> dataList2 = dataMap.getDataList(K.venues);
        CourseTableActivity courseTableActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(dataList2, K.venues);
        courseTableActivity.setVenueTypes(dataList2);
        if (!dataList2.isEmpty()) {
            CourseTableActivity courseTableActivity2 = this.this$0;
            String nowString = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd"));
            Intrinsics.checkExpressionValueIsNotNull(nowString, "TimeUtils.getNowString(S…DateFormat(\"yyyy-MM-dd\"))");
            courseTableActivity2.selectDate = nowString;
            CourseTableActivity courseTableActivity3 = this.this$0;
            String string = dataList2.get(0).getString(K.venueId);
            Intrinsics.checkExpressionValueIsNotNull(string, "venues[0].getString(K.venueId)");
            courseTableActivity3.selectVenueId = string;
            CourseTableActivity courseTableActivity4 = this.this$0;
            String string2 = dataList2.get(0).getString(K.studentIds);
            Intrinsics.checkExpressionValueIsNotNull(string2, "venues[0].getString(K.studentIds)");
            courseTableActivity4.selectStuIds = string2;
            this.this$0.getTable();
            return;
        }
        this.this$0.courseAdapter.notifyDataSetChanged();
    }

    public void onError(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        super.onError(dataMap);
        this.this$0.courseAdapter.notifyDataSetChanged();
    }
}
