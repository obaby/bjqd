package cn.xports.qd2.campaign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.MemberInfo;
import com.blankj.utilcode.util.GsonUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a>\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001\u001aF\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"ADD_MEMBER", "", "EDIT_MEMBER", "EDIT_MEMBER_REQUEST", "SHOW_MEMBER", "EditMemberForResult", "", "activity", "Landroid/app/Activity;", "campId", "", "members", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/MemberInfo;", "Lkotlin/collections/ArrayList;", "maxCount", "opType", "update", "", "xports2_productRelease"}, k = 2, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivityKt {
    public static final int ADD_MEMBER = 22;
    public static final int EDIT_MEMBER = 11;
    public static final int EDIT_MEMBER_REQUEST = 1010;
    public static final int SHOW_MEMBER = 33;

    public static final void EditMemberForResult(@NotNull Activity activity, @NotNull String str, @NotNull ArrayList<MemberInfo> arrayList, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(str, K.campId);
        Intrinsics.checkParameterIsNotNull(arrayList, "members");
        EditMemberForResult(activity, str, arrayList, i, i2, false);
    }

    public static final void EditMemberForResult(@NotNull Activity activity, @NotNull String str, @NotNull ArrayList<MemberInfo> arrayList, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(str, K.campId);
        Intrinsics.checkParameterIsNotNull(arrayList, "members");
        Intent intent = new Intent(activity, AddMemberActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("members", GsonUtils.toJson(arrayList));
        bundle.putString(K.campId, str);
        bundle.putInt("maxCount", i);
        bundle.putInt("opType", i2);
        bundle.putBoolean("update", z);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 1010);
    }
}
