package cn.xports.qd2.fragment;

import cn.xports.entity.Service;
import cn.xports.qd2.entity.Option;
import com.blankj.utilcode.util.CollectionUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "index", "", "item", "Lcn/xports/entity/Service;", "kotlin.jvm.PlatformType", "execute"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingFragment.kt */
final class TrainingFragment$getFilter$1$onSuccess$2<E> implements CollectionUtils.Closure<E> {
    final /* synthetic */ TrainingFragment$getFilter$1 this$0;

    TrainingFragment$getFilter$1$onSuccess$2(TrainingFragment$getFilter$1 trainingFragment$getFilter$1) {
        this.this$0 = trainingFragment$getFilter$1;
    }

    public final void execute(int i, Service service) {
        ArrayList access$getServiceList$p = this.this$0.this$0.serviceList;
        Option data = new Option().setData(service);
        Intrinsics.checkExpressionValueIsNotNull(service, "item");
        Option name = data.setName(service.getServiceName());
        StringBuilder sb = new StringBuilder();
        String serviceId = service.getServiceId();
        if (serviceId == null) {
            Intrinsics.throwNpe();
        }
        sb.append(serviceId.toString());
        sb.append("");
        access$getServiceList$p.add(name.setValue(sb.toString()));
    }
}
