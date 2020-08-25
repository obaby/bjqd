package cn.xports.order;

import cn.xports.baselib.http.RxDisposableManager;
import io.reactivex.functions.Action;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
final class OrderPayPresenter$countDownTime$sub$3 implements Action {
    public static final OrderPayPresenter$countDownTime$sub$3 INSTANCE = new OrderPayPresenter$countDownTime$sub$3();

    OrderPayPresenter$countDownTime$sub$3() {
    }

    public final void run() {
        RxDisposableManager.getInstance().clear("time");
    }
}
