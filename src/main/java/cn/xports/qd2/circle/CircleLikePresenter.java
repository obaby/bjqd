package cn.xports.qd2.circle;

import cn.xports.baselib.http.RxUtil;
import cn.xports.qd2.util.ApiUtil;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcn/xports/qd2/circle/CircleLikePresenter;", "", "iView", "Lcn/xports/qd2/circle/CircleLikeView;", "(Lcn/xports/qd2/circle/CircleLikeView;)V", "addLike", "", "postId", "", "deleteLike", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CircleLikePresenter.kt */
public final class CircleLikePresenter {
    /* access modifiers changed from: private */
    public final CircleLikeView iView;

    public CircleLikePresenter(@NotNull CircleLikeView circleLikeView) {
        Intrinsics.checkParameterIsNotNull(circleLikeView, "iView");
        this.iView = circleLikeView;
    }

    public final void addLike(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "postId");
        Observable<ResponseBody> likeAdd = ApiUtil.getApi2().likeAdd(str);
        Intrinsics.checkExpressionValueIsNotNull(likeAdd, "ApiUtil.getApi2().likeAdd(postId)");
        RxUtil.subscribeDataMapIO$default(likeAdd, this.iView, new CircleLikePresenter$addLike$1(this), new CircleLikePresenter$addLike$2(this), false, 8, (Object) null);
    }

    public final void deleteLike(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "postId");
        Observable<ResponseBody> likeDelete = ApiUtil.getApi2().likeDelete(str);
        Intrinsics.checkExpressionValueIsNotNull(likeDelete, "ApiUtil.getApi2().likeDelete(postId)");
        RxUtil.subscribeDataMapIO$default(likeDelete, this.iView, new CircleLikePresenter$deleteLike$1(this), new CircleLikePresenter$deleteLike$2(this), false, 8, (Object) null);
    }
}
