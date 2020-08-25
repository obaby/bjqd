package cn.xports.qd2.circle;

import android.widget.EditText;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.util.ViewExt;
import com.blankj.utilcode.util.KeyboardUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$postComment$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ PostDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PostDetailActivity$postComment$1(PostDetailActivity postDetailActivity) {
        super(1);
        this.this$0 = postDetailActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        RxBus.get().post("ADD_COMMENT");
        ViewExt.showImageToast(this.this$0, "评论成功");
        ((EditText) this.this$0._$_findCachedViewById(R.id.etComment)).setText("");
        this.this$0.getCommentList(1);
        this.this$0.getPostDetail();
        KeyboardUtils.hideSoftInput(this.this$0);
    }
}
