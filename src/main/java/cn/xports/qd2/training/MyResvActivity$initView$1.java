package cn.xports.qd2.training;

import android.content.Intent;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "viewId", "", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyResvActivity.kt */
final class MyResvActivity$initView$1<T> implements XViewBinder.OnItemClickListener<DataMap> {
    final /* synthetic */ MyResvActivity this$0;

    MyResvActivity$initView$1(MyResvActivity myResvActivity) {
        this.this$0 = myResvActivity;
    }

    public final void onItemClick(DataMap dataMap, int i) {
        MyResvActivity myResvActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(dataMap, "item");
        myResvActivity.clickData = dataMap;
        this.this$0.startActivity(new Intent(this.this$0, MyResvDetailActivity.class).putExtra("hasFinish", dataMap.getBooleanValue("hasFinish")).putExtra(K.resvId, dataMap.getString(K.resvId)));
    }
}
