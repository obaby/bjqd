package cn.xports.qd2.campaign;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CampEnrolledResult;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/campaign/MySportsActivity$getMyCamps$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CampEnrolledResult;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MySportsActivity.kt */
public final class MySportsActivity$getMyCamps$1 extends ProcessObserver<CampEnrolledResult> {
    final /* synthetic */ int $pageNo;
    final /* synthetic */ MySportsActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MySportsActivity$getMyCamps$1(MySportsActivity mySportsActivity, int i, IView iView) {
        super(iView);
        this.this$0 = mySportsActivity;
        this.$pageNo = i;
    }

    public void next(@Nullable CampEnrolledResult campEnrolledResult) {
        this.this$0.curPage = this.$pageNo;
        if (campEnrolledResult != null) {
            this.this$0.totalPage = campEnrolledResult.getTotalPage();
            Collection campEnrollmentList = campEnrolledResult.getCampEnrollmentList();
            if (!(campEnrollmentList == null || campEnrollmentList.isEmpty())) {
                View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.noDataAndJoin);
                Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "noDataAndJoin");
                _$_findCachedViewById.setVisibility(8);
                this.this$0.camps.addAll(campEnrolledResult.getCampEnrollmentList());
                RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvSportList);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvSportList");
                recyclerView.getAdapter().notifyDataSetChanged();
                RecyclerView recyclerView2 = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvSportList);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvSportList");
                recyclerView2.setVisibility(0);
                return;
            }
            View _$_findCachedViewById2 = this.this$0._$_findCachedViewById(R.id.noDataAndJoin);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "noDataAndJoin");
            _$_findCachedViewById2.setVisibility(0);
            RecyclerView recyclerView3 = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvSportList);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rvSportList");
            recyclerView3.setVisibility(8);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.noDataAndJoin);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "noDataAndJoin");
        _$_findCachedViewById.setVisibility(0);
        RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvSportList");
        recyclerView.setVisibility(8);
    }
}
