package cn.xports.qd2.credit;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import com.alipay.sdk.packet.d;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "data", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyCreditActivity.kt */
final class MyCreditActivity$getCardList$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ MyCreditActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCreditActivity$getCardList$1(MyCreditActivity myCreditActivity) {
        super(1);
        this.this$0 = myCreditActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, d.k);
        ArrayList<DataMap> dataList = dataMap.getDataList("cardList");
        Intrinsics.checkExpressionValueIsNotNull(dataList, "list");
        if (!dataList.isEmpty()) {
            this.this$0.cardList.addAll(dataList.get(0).getDataList("cardInfo"));
            ViewPager viewPager = (ViewPager) this.this$0._$_findCachedViewById(R.id.viewPager);
            Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            if (!(!this.this$0.cardList.isEmpty())) {
                return;
            }
            if (this.this$0.cardList.size() == 1) {
                MyCreditActivity myCreditActivity = this.this$0;
                Object obj = this.this$0.cardList.get(0);
                Intrinsics.checkExpressionValueIsNotNull(obj, "cardList[0]");
                myCreditActivity.getCreditDetails((DataMap) obj);
                return;
            }
            ((ViewPager) this.this$0._$_findCachedViewById(R.id.viewPager)).setCurrentItem(1, false);
            ((ViewPager) this.this$0._$_findCachedViewById(R.id.viewPager)).addOnPageChangeListener(this.this$0.listener);
            ((ViewPager) this.this$0._$_findCachedViewById(R.id.viewPager)).postDelayed(new Runnable(this) {
                final /* synthetic */ MyCreditActivity$getCardList$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    ((ViewPager) this.this$0.this$0._$_findCachedViewById(R.id.viewPager)).setCurrentItem(0, false);
                }
            }, 5);
        }
    }
}
