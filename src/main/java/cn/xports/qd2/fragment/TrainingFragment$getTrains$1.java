package cn.xports.qd2.fragment;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.PageInfo;
import cn.xports.qd2.entity.TrainItem;
import cn.xports.qd2.entity.TrainListResult;
import cn.xports.qd2.entity.VenueLocationItem;
import com.blankj.utilcode.util.ObjectUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/fragment/TrainingFragment$getTrains$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/TrainListResult;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingFragment.kt */
public final class TrainingFragment$getTrains$1 extends ProcessObserver<TrainListResult> {
    final /* synthetic */ int $page;
    final /* synthetic */ TrainingFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingFragment$getTrains$1(TrainingFragment trainingFragment, int i, IView iView) {
        super(iView);
        this.this$0 = trainingFragment;
        this.$page = i;
    }

    public void next(@NotNull TrainListResult trainListResult) {
        Intrinsics.checkParameterIsNotNull(trainListResult, "value");
        this.this$0.items.clear();
        if (ObjectUtils.isNotEmpty(trainListResult.getPageInfo())) {
            TrainingFragment trainingFragment = this.this$0;
            PageInfo<TrainItem> pageInfo = trainListResult.getPageInfo();
            Intrinsics.checkExpressionValueIsNotNull(pageInfo, "value.pageInfo");
            trainingFragment.totalPage = pageInfo.getPages();
            this.this$0.pageNo = this.$page;
        }
        PageInfo<TrainItem> pageInfo2 = trainListResult.getPageInfo();
        Intrinsics.checkExpressionValueIsNotNull(pageInfo2, "value.pageInfo");
        if (ObjectUtils.isNotEmpty(pageInfo2.getList())) {
            PageInfo<TrainItem> pageInfo3 = trainListResult.getPageInfo();
            Intrinsics.checkExpressionValueIsNotNull(pageInfo3, "value.pageInfo");
            List<TrainItem> list = pageInfo3.getList();
            HashMap hashMap = new HashMap();
            Intrinsics.checkExpressionValueIsNotNull(list, "list");
            for (TrainItem trainItem : list) {
                StringBuilder sb = new StringBuilder();
                Intrinsics.checkExpressionValueIsNotNull(trainItem, "it");
                sb.append(trainItem.getCenterName());
                sb.append(trainItem.getVenueName());
                String sb2 = sb.toString();
                if (hashMap.get(sb2) == null) {
                    hashMap.put(sb2, new ArrayList());
                }
                ArrayList arrayList = (ArrayList) hashMap.get(sb2);
                if (arrayList != null) {
                    arrayList.add(trainItem);
                }
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                Collection collection = (Collection) entry.getValue();
                if (!(collection == null || collection.isEmpty())) {
                    VenueLocationItem name = new VenueLocationItem().setName((String) entry.getKey());
                    Object obj = ((ArrayList) entry.getValue()).get(0);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "it.value[0]");
                    VenueLocationItem latitude = name.setLatitude(((TrainItem) obj).getLatitude());
                    Object obj2 = ((ArrayList) entry.getValue()).get(0);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "it.value[0]");
                    VenueLocationItem longitude = latitude.setLongitude(((TrainItem) obj2).getLongitude());
                    Object obj3 = ((ArrayList) entry.getValue()).get(0);
                    Intrinsics.checkExpressionValueIsNotNull(obj3, "it.value[0]");
                    this.this$0.items.add(longitude.setVenueId(((TrainItem) obj3).getVenueId()));
                    this.this$0.items.addAll((Collection) entry.getValue());
                }
            }
        }
        this.this$0.adapter.notifyDataSetChanged();
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        this.this$0.adapter.notifyDataSetChanged();
    }
}
