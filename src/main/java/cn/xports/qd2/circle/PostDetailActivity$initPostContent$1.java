package cn.xports.qd2.circle;

import android.content.Intent;
import android.view.View;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.circle.videoPlayer.SurfaceActivity;
import cn.xports.qd2.circle.videoPlayer.entity.VideoInfo;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initPostContent$1 implements View.OnClickListener {
    final /* synthetic */ ArrayList $mediaList;
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initPostContent$1(PostDetailActivity postDetailActivity, ArrayList arrayList) {
        this.this$0 = postDetailActivity;
        this.$mediaList = arrayList;
    }

    public final void onClick(View view) {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setMimeType("1");
        videoInfo.setFilePath(((DataMap) this.$mediaList.get(0)).getString("mediaUrl"));
        Intent intent = new Intent(this.this$0, SurfaceActivity.class);
        intent.putExtra("VIDEO_INFO", videoInfo);
        intent.putExtra("VIDEO_TYPE", 1);
        intent.setFlags(268435456);
        this.this$0.startActivity(intent);
    }
}
