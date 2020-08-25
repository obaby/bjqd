package cn.xports.qd2.training;

import cn.xports.qd2.entity.K;
import cn.xports.widget.RatingBar;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "num", "", "onChange"}, k = 3, mv = {1, 1, 15})
/* compiled from: CourseCommentActivity.kt */
final class CourseCommentActivity$initView$1 implements RatingBar.OnStarChangeListener {
    final /* synthetic */ CourseCommentActivity this$0;

    CourseCommentActivity$initView$1(CourseCommentActivity courseCommentActivity) {
        this.this$0 = courseCommentActivity;
    }

    public final void onChange(int i) {
        this.this$0.subMap.set(K.grade, String.valueOf(i));
    }
}
