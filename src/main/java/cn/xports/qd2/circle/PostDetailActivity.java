package cn.xports.qd2.circle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.rxbind.widget.RxTextView;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CircleCommentAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.sportCoaching.DateShowUtils;
import cn.xports.widget.CornerRLayout;
import cn.xports.widget.CornerTextView;
import cn.xports.widget.EmptyRecyclerView;
import com.alipay.sdk.cons.c;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.previewlibrary.ZoomMediaLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\b\u0010\u001e\u001a\u00020\u001aH\u0002J\b\u0010\u001f\u001a\u00020\u0005H\u0014J\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u0016H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0014J\b\u0010#\u001a\u00020\u001aH\u0002J\b\u0010$\u001a\u00020\u001aH\u0002J\b\u0010%\u001a\u00020\u001aH\u0016J\u0010\u0010&\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\b\u0010'\u001a\u00020\u001aH\u0014J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcn/xports/qd2/circle/PostDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "accountId", "", "adapter", "Lcn/xports/qd2/adapter/CircleCommentAdapter;", "canComment", "", "circleId", "commentList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "fromCircle", "hasLike", "hasNextPage", "imgMap", "Ljava/util/HashMap;", "joinState", "pageNum", "", "post", "postId", "addLike", "", "deleteComment", "commentId", "deleteLike", "deletePost", "getChildTitle", "getCommentList", "page", "getLayoutId", "getPostDetail", "goOtherCenter", "initData", "initPostContent", "initView", "postComment", "comment", "showLike", "show", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
public final class PostDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String accountId = "";
    /* access modifiers changed from: private */
    public final CircleCommentAdapter adapter = new CircleCommentAdapter(this.commentList);
    /* access modifiers changed from: private */
    public boolean canComment = true;
    /* access modifiers changed from: private */
    public String circleId = "";
    /* access modifiers changed from: private */
    public ArrayList<DataMap> commentList = new ArrayList<>();
    private boolean fromCircle;
    /* access modifiers changed from: private */
    public boolean hasLike;
    /* access modifiers changed from: private */
    public boolean hasNextPage;
    private final HashMap<String, DataMap> imgMap = new HashMap<>();
    /* access modifiers changed from: private */
    public String joinState = "2";
    /* access modifiers changed from: private */
    public int pageNum = 1;
    private DataMap post = new DataMap();
    private String postId = "";

    private final void goOtherCenter() {
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String getChildTitle() {
        return "动态详情";
    }

    public void initData() {
        this.postId = getStringExtra("postId");
        this.joinState = getStringExtra("joinState");
        this.fromCircle = getIntent().getBooleanExtra("fromCircle", false);
        ZoomMediaLoader.getInstance().init(new ImageLoader());
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_post_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        getPostDetail();
        getCommentList(this.pageNum);
        SmartRefreshLayout _$_findCachedViewById = _$_findCachedViewById(R.id.srfLayout);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "srfLayout");
        ViewExt.setRefreshAndLoad(_$_findCachedViewById, new PostDetailActivity$initView$1(this), new PostDetailActivity$initView$2(this));
        _$_findCachedViewById(R.id.srfLayout).setEnableRefresh(false);
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCommentList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCommentList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCommentList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCommentList");
        emptyRecyclerView2.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(PostDetailActivity$initView$3.INSTANCE);
        ((TextView) _$_findCachedViewById(R.id.tvCircleCare)).setOnClickListener(new PostDetailActivity$initView$4(this));
        ((ImageView) _$_findCachedViewById(R.id.ivLike)).setOnClickListener(new PostDetailActivity$initView$5(this));
        ((TextView) _$_findCachedViewById(R.id.tvDelete)).setOnClickListener(new PostDetailActivity$initView$6(this));
        KeyboardUtils.registerSoftInputChangedListener(this, new PostDetailActivity$initView$7(this));
        ((RelativeLayout) _$_findCachedViewById(R.id.rlInputAdd)).setOnClickListener(new PostDetailActivity$initView$8(this));
        EditText editText = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etComment");
        ViewExt.filterEmoji(editText);
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etComment");
        ViewExt.filterBlank(editText2);
        EditText editText3 = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "etComment");
        ViewExt.setMaxLength(editText3, 100, "最多输入100字");
        EditText editText4 = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "etComment");
        RxTextView.textChangeEvents(editText4).subscribe(new PostDetailActivity$initView$9(this));
        ((CornerTextView) _$_findCachedViewById(R.id.ctPost)).setOnClickListener(new PostDetailActivity$initView$10(this));
        if (this.fromCircle) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvInCircle);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvInCircle");
            textView.setVisibility(8);
        }
        ((CornerRLayout) _$_findCachedViewById(R.id.cr_head)).setOnClickListener(new PostDetailActivity$initView$11(this));
        this.adapter.setOnItemClickListener(new PostDetailActivity$initView$12(this));
    }

    /* access modifiers changed from: private */
    public final void getPostDetail() {
        Observable<ResponseBody> postDetail = ApiUtil.getApi2().getPostDetail(this.postId);
        Intrinsics.checkExpressionValueIsNotNull(postDetail, "ApiUtil.getApi2().getPostDetail(postId)");
        RxUtil.subscribeDataMapIO(postDetail, this, new PostDetailActivity$getPostDetail$1(this), new PostDetailActivity$getPostDetail$2(this), false);
    }

    /* access modifiers changed from: private */
    public final void getCommentList(int i) {
        Observable<ResponseBody> commentById = ApiUtil.getApi2().getCommentById(this.postId, "2", "20", Integer.valueOf(i));
        Intrinsics.checkExpressionValueIsNotNull(commentById, "ApiUtil.getApi2().getCom…(postId, \"2\", \"20\", page)");
        RxUtil.subscribeDataMapIO(commentById, this, new PostDetailActivity$getCommentList$1(this, i), new PostDetailActivity$getCommentList$2(this), false);
    }

    /* access modifiers changed from: private */
    public final void addLike() {
        Observable<ResponseBody> likeAdd = ApiUtil.getApi2().likeAdd(this.postId);
        Intrinsics.checkExpressionValueIsNotNull(likeAdd, "ApiUtil.getApi2().likeAdd(postId)");
        RxUtil.subscribeDataMapIO(likeAdd, this, new PostDetailActivity$addLike$1(this), PostDetailActivity$addLike$2.INSTANCE, false);
    }

    /* access modifiers changed from: private */
    public final void deleteLike() {
        Observable<ResponseBody> likeDelete = ApiUtil.getApi2().likeDelete(this.postId);
        Intrinsics.checkExpressionValueIsNotNull(likeDelete, "ApiUtil.getApi2().likeDelete(postId)");
        RxUtil.subscribeDataMapIO(likeDelete, this, new PostDetailActivity$deleteLike$1(this), PostDetailActivity$deleteLike$2.INSTANCE, false);
    }

    /* access modifiers changed from: private */
    public final void postComment(String str) {
        Observable<ResponseBody> postComment = ApiUtil.getApi2().postComment(this.postId, str, "2");
        Intrinsics.checkExpressionValueIsNotNull(postComment, "ApiUtil.getApi2().postCo…ent(postId, comment, \"2\")");
        RxUtil.subscribeDataMapIO$default(postComment, this, new PostDetailActivity$postComment$1(this), new PostDetailActivity$postComment$2(this), false, 8, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void deleteComment(String str) {
        Observable<ResponseBody> deleteComment = ApiUtil.getApi2().deleteComment(str);
        Intrinsics.checkExpressionValueIsNotNull(deleteComment, "ApiUtil.getApi2().deleteComment(commentId)");
        RxUtil.subscribeDataMapIO$default(deleteComment, this, new PostDetailActivity$deleteComment$1(this), (Function1) null, false, 12, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void deletePost() {
        Observable<ResponseBody> deletePost = ApiUtil.getApi2().deletePost(this.postId);
        Intrinsics.checkExpressionValueIsNotNull(deletePost, "ApiUtil.getApi2().deletePost(postId)");
        RxUtil.subscribeDataMapIO$default(deletePost, this, new PostDetailActivity$deletePost$1(this), (Function1) null, false, 12, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void initPostContent(DataMap dataMap) {
        LinearLayoutManager linearLayoutManager;
        LinearLayoutManager linearLayoutManager2;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvName");
        textView.setText(dataMap.getString("createAccountName"));
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvIssueTime);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvIssueTime");
        textView2.setText(DateShowUtils.getPostFriendlyTime(dataMap.getString(K.createTime)));
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvContent);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvContent");
        textView3.setText(dataMap.getString("content"));
        String string = dataMap.getString("circleRole");
        int i = 8;
        if (string != null && string.hashCode() == 49 && string.equals("1")) {
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.ivCircleTag);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "ivCircleTag");
            imageView.setVisibility(0);
        } else {
            ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.ivCircleTag);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "ivCircleTag");
            imageView2.setVisibility(8);
        }
        String string2 = dataMap.getString("commentNum");
        if (TextUtils.isEmpty(string2) || !Intrinsics.areEqual(string2, K.k0)) {
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvCircleMsg);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tvCircleMsg");
            textView4.setVisibility(0);
            TextView textView5 = (TextView) _$_findCachedViewById(R.id.tvCircleMsg);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "tvCircleMsg");
            textView5.setText("评论 " + string2);
        } else {
            TextView textView6 = (TextView) _$_findCachedViewById(R.id.tvCircleMsg);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "tvCircleMsg");
            textView6.setVisibility(8);
        }
        Integer intValue = dataMap.getIntValue("likeNum");
        TextView textView7 = (TextView) _$_findCachedViewById(R.id.tvCircleCare);
        Intrinsics.checkExpressionValueIsNotNull(textView7, "tvCircleCare");
        textView7.setText((intValue != null && intValue.intValue() == 0) ? "" : String.valueOf(intValue));
        Collection dataList = dataMap.getDataList("likeList");
        showLike(!(dataList == null || dataList.isEmpty()));
        Context context = this;
        GlideUtil.loadImage(context, dataMap.getString("createAccountAvatar"), R.drawable.ic_circle_default_head).into((ImageView) _$_findCachedViewById(R.id.ivHead));
        ArrayList<DataMap> dataList2 = dataMap.getDataList("media");
        if (dataList2 != null && dataList2.size() > 0) {
            if (Intrinsics.areEqual(dataList2.get(0).getString("mediaType"), "1")) {
                ((ImageView) _$_findCachedViewById(R.id.ivPlayerState)).setVisibility(8);
                ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setVisibility(0);
                ((RoundImageView) _$_findCachedViewById(R.id.rivContent)).setVisibility(8);
                if (dataList2.size() == 1) {
                    linearLayoutManager2 = new LinearLayoutManager(context);
                } else {
                    linearLayoutManager2 = new GridLayoutManager(context, 3);
                }
                PostsImgAdapter postsImgAdapter = new PostsImgAdapter(dataMap.getDataList("media"), context, linearLayoutManager2, this.imgMap);
                ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setLayoutManager(linearLayoutManager2);
                ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setAdapter(postsImgAdapter);
            } else {
                ((ImageView) _$_findCachedViewById(R.id.ivPlayerState)).setVisibility(0);
                ((RoundImageView) _$_findCachedViewById(R.id.rivContent)).setVisibility(0);
                if (dataList2.size() == 1) {
                    linearLayoutManager = new LinearLayoutManager(context);
                } else {
                    linearLayoutManager = new GridLayoutManager(context, 3);
                }
                PostsImgAdapter postsImgAdapter2 = new PostsImgAdapter(CollectionsKt.arrayListOf(new DataMap[]{new DataMap().set("mediaUrl", dataMap.getString("thumbMediaUrl"))}), context, linearLayoutManager, this.imgMap);
                ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setLayoutManager(linearLayoutManager);
                ((RecyclerView) _$_findCachedViewById(R.id.rvImage)).setAdapter(postsImgAdapter2);
                ((RoundImageView) _$_findCachedViewById(R.id.rivContent)).setOnClickListener(new PostDetailActivity$initPostContent$1(this, dataList2));
            }
        }
        DataMap dataMap2 = dataMap.getDataMap("circle");
        GlideUtil.loadImage(context, dataMap2.getString("avatar"), R.drawable.ic_circle_default).into((ImageView) _$_findCachedViewById(R.id.ivCircleHead));
        TextView textView8 = (TextView) _$_findCachedViewById(R.id.tvCircleName);
        Intrinsics.checkExpressionValueIsNotNull(textView8, "tvCircleName");
        textView8.setText(dataMap2.getString(c.e));
        TextView textView9 = (TextView) _$_findCachedViewById(R.id.tvCircleDesc);
        Intrinsics.checkExpressionValueIsNotNull(textView9, "tvCircleDesc");
        textView9.setText("帖子数" + dataMap2.getString("postNum") + "/人数" + dataMap2.getString("memberNum"));
        ((TextView) _$_findCachedViewById(R.id.tvInCircle)).setOnClickListener(new PostDetailActivity$initPostContent$2(this, dataMap));
        TextView textView10 = (TextView) _$_findCachedViewById(R.id.tvDelete);
        Intrinsics.checkExpressionValueIsNotNull(textView10, "tvDelete");
        if (Intrinsics.areEqual(dataMap.getString("createAccountId"), SPUtil.Companion.getINSTANCE().getStringValue("coAccountId"))) {
            i = 0;
        }
        textView10.setVisibility(i);
        String string3 = dataMap.getString("createAccountId");
        Intrinsics.checkExpressionValueIsNotNull(string3, "post.getString(\"createAccountId\")");
        this.accountId = string3;
        String string4 = dataMap.getString("circleId");
        Intrinsics.checkExpressionValueIsNotNull(string4, "post.getString(\"circleId\")");
        this.circleId = string4;
    }

    /* access modifiers changed from: private */
    public final void showLike(boolean z) {
        this.hasLike = z;
        if (!z) {
            ((TextView) _$_findCachedViewById(R.id.tvCircleCare)).setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_care_black), (Drawable) null, (Drawable) null, (Drawable) null);
            ((TextView) _$_findCachedViewById(R.id.tvCircleCare)).setTextColor(ColorUtils.getColor(R.color.gray_333));
            ((ImageView) _$_findCachedViewById(R.id.ivLike)).setImageResource(R.drawable.ic_care_black);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tvCircleCare)).setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_care_red), (Drawable) null, (Drawable) null, (Drawable) null);
        ((TextView) _$_findCachedViewById(R.id.tvCircleCare)).setTextColor(ColorUtils.getColor(R.color.red_fd4));
        ((ImageView) _$_findCachedViewById(R.id.ivLike)).setImageResource(R.drawable.ic_care_red);
    }
}
