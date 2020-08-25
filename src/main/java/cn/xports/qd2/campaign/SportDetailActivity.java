package cn.xports.qd2.campaign;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CampItem;
import cn.xports.qd2.entity.CampaignDetail;
import cn.xports.qd2.entity.CampaignDetailResult;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.sportCoaching.WebViewDetailActivity;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import io.reactivex.Observable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\u0005H\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u000bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcn/xports/qd2/campaign/SportDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "campId", "", "campItemJson", "hasFinish", "", "myTitle", "countDown", "", "endDate", "getChildTitle", "getDetails", "getLayoutId", "", "initData", "initDetails", "detailResult", "Lcn/xports/qd2/entity/CampaignDetailResult;", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SportDetailActivity.kt */
public final class SportDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String campId = "";
    /* access modifiers changed from: private */
    public String campItemJson = "";
    /* access modifiers changed from: private */
    public boolean hasFinish;
    private String myTitle = "";

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
        return "";
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_sport_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        underStatusBar(true, true);
        ((RelativeLayout) _$_findCachedViewById(R.id.tvSignUp)).setOnClickListener(new SportDetailActivity$initView$1(this));
        getDetails();
        ((NestedScrollView) _$_findCachedViewById(R.id.scrollView)).setOnScrollChangeListener(new SportDetailActivity$initView$2(this));
    }

    public void initData() {
        this.campId = getStringExtra(K.campId);
        LogUtils.e(new Object[]{"campID: " + this.campId});
    }

    /* access modifiers changed from: private */
    public final void initDetails(CampaignDetailResult campaignDetailResult) {
        String str;
        CampaignDetail campaign = campaignDetailResult.getCampaign();
        Intrinsics.checkExpressionValueIsNotNull(campaign, "detailResult.campaign");
        if (campaign.getEnrollFee() == 0 && campaignDetailResult.getMargin() == 0) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llSignFee);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llSignFee");
            linearLayout.setVisibility(8);
        } else {
            CampaignDetail campaign2 = campaignDetailResult.getCampaign();
            Intrinsics.checkExpressionValueIsNotNull(campaign2, "detailResult.campaign");
            String str2 = "¥" + MoneyUtil.cent2Yuan(campaign2.getEnrollFee() + campaignDetailResult.getMargin()) + "/人";
            if (campaignDetailResult.getMargin() != 0) {
                str2 = str2 + "(含保证金" + "¥" + MoneyUtil.cent2Yuan(campaignDetailResult.getMargin()) + ")";
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvSportFee);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvSportFee");
            textView.setText(str2);
        }
        CampaignDetail campaign3 = campaignDetailResult.getCampaign();
        String title = campaign3.getTitle();
        Intrinsics.checkExpressionValueIsNotNull(title, WebViewDetailActivity.TITLE);
        this.myTitle = title;
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_sport_name);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_sport_name");
        textView2.setText(campaign3.getTitle());
        Context context = this;
        GlideUtil.loadImage(context, campaign3.getCoverImg()).into((ImageView) _$_findCachedViewById(R.id.ivCover));
        String campStartDate = campaign3.getCampStartDate();
        Intrinsics.checkExpressionValueIsNotNull(campStartDate, K.campStartDate);
        if (campStartDate != null) {
            String substring = campStartDate.substring(0, 10);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String campEndDate = campaign3.getCampEndDate();
            Intrinsics.checkExpressionValueIsNotNull(campEndDate, K.campEndDate);
            if (campEndDate != null) {
                String substring2 = campEndDate.substring(0, 10);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                if (Intrinsics.areEqual(substring, substring2)) {
                    StringBuilder sb = new StringBuilder();
                    String campStartDate2 = campaign3.getCampStartDate();
                    Intrinsics.checkExpressionValueIsNotNull(campStartDate2, K.campStartDate);
                    if (campStartDate2 != null) {
                        String substring3 = campStartDate2.substring(0, 16);
                        Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb.append(StringsKt.replace$default(substring3, "-", "/", false, 4, (Object) null));
                        sb.append(" - ");
                        String campEndDate2 = campaign3.getCampEndDate();
                        Intrinsics.checkExpressionValueIsNotNull(campEndDate2, K.campEndDate);
                        if (campEndDate2 != null) {
                            String substring4 = campEndDate2.substring(11, 16);
                            Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            sb.append(substring4);
                            str = sb.toString();
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    String campStartDate3 = campaign3.getCampStartDate();
                    Intrinsics.checkExpressionValueIsNotNull(campStartDate3, K.campStartDate);
                    if (campStartDate3 != null) {
                        String substring5 = campStartDate3.substring(0, 16);
                        Intrinsics.checkExpressionValueIsNotNull(substring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb2.append(StringsKt.replace$default(substring5, "-", "/", false, 4, (Object) null));
                        sb2.append(" - ");
                        String campEndDate3 = campaign3.getCampEndDate();
                        Intrinsics.checkExpressionValueIsNotNull(campEndDate3, K.campEndDate);
                        if (campEndDate3 != null) {
                            String substring6 = campEndDate3.substring(0, 16);
                            Intrinsics.checkExpressionValueIsNotNull(substring6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            sb2.append(StringsKt.replace$default(substring6, "-", "/", false, 4, (Object) null));
                            str = sb2.toString();
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
                TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvSportTime);
                Intrinsics.checkExpressionValueIsNotNull(textView3, "tvSportTime");
                textView3.setText(str);
                TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvSportLocation);
                Intrinsics.checkExpressionValueIsNotNull(textView4, "tvSportLocation");
                textView4.setText(campaign3.getPlace());
                TextView textView5 = (TextView) _$_findCachedViewById(R.id.tvSignNum);
                Intrinsics.checkExpressionValueIsNotNull(textView5, "tvSignNum");
                textView5.setText("已报" + campaignDetailResult.getEnrollNum() + "/" + ((int) campaign3.getMaxGroupNum()) + "人");
                boolean z = true;
                if (!Intrinsics.areEqual(campaign3.getStatus(), "1") || TimeUtils.getTimeSpanByNow(campaign3.getEnrollEndDate(), 60000) <= 0 || TimeUtils.getTimeSpanByNow(campaign3.getCampEndDate(), 60000) <= 0) {
                    TextView textView6 = (TextView) _$_findCachedViewById(R.id.tv_tip);
                    Intrinsics.checkExpressionValueIsNotNull(textView6, "tv_tip");
                    textView6.setText("报名已结束");
                    this.hasFinish = true;
                    if (TimeUtils.getTimeSpanByNow(campaign3.getCampStartDate(), 60000) < 0 && TimeUtils.getTimeSpanByNow(campaign3.getCampEndDate(), 60000) > 0) {
                        TextView textView7 = (TextView) _$_findCachedViewById(R.id.tv_tip);
                        Intrinsics.checkExpressionValueIsNotNull(textView7, "tv_tip");
                        textView7.setText("活动进行中");
                        this.hasFinish = false;
                        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.tvSignUp);
                        Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "tvSignUp");
                        relativeLayout.setVisibility(8);
                    } else if (TimeUtils.getTimeSpanByNow(campaign3.getCampEndDate(), 60000) < 0) {
                        TextView textView8 = (TextView) _$_findCachedViewById(R.id.tv_tip);
                        Intrinsics.checkExpressionValueIsNotNull(textView8, "tv_tip");
                        textView8.setText("活动已结束");
                    }
                    if (this.hasFinish) {
                        TextView textView9 = (TextView) _$_findCachedViewById(R.id.tvSignText);
                        Intrinsics.checkExpressionValueIsNotNull(textView9, "tvSignText");
                        textView9.setText("查看赛果");
                    }
                    LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llLeftDay);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llLeftDay");
                    linearLayout2.setVisibility(8);
                } else {
                    TextView textView10 = (TextView) _$_findCachedViewById(R.id.tv_tip);
                    Intrinsics.checkExpressionValueIsNotNull(textView10, "tv_tip");
                    textView10.setText("距离报名结束还有");
                    String enrollEndDate = campaign3.getEnrollEndDate();
                    Intrinsics.checkExpressionValueIsNotNull(enrollEndDate, "enrollEndDate");
                    countDown(enrollEndDate);
                    RelativeLayout relativeLayout2 = (RelativeLayout) _$_findCachedViewById(R.id.tvSignUp);
                    Intrinsics.checkExpressionValueIsNotNull(relativeLayout2, "tvSignUp");
                    relativeLayout2.setVisibility(0);
                    LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(R.id.llLeftDay);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llLeftDay");
                    linearLayout3.setVisibility(0);
                }
                if (campaign3.getDescription() != null) {
                    WebView webView = (WebView) _$_findCachedViewById(R.id.tvDesc);
                    Intrinsics.checkExpressionValueIsNotNull(webView, "tvDesc");
                    ViewExt.loadHtmlStr(webView, campaign3.getDescription(), 75);
                }
                List<CampItem> campItemList = campaignDetailResult.getCampItemList();
                if (campItemList != null) {
                    String json = GsonUtils.toJson(campItemList);
                    Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(this)");
                    this.campItemJson = json;
                    for (CampItem campItem : campItemList) {
                        View inflate = LayoutInflater.from(context).inflate(R.layout.text_camp_detail_tags, (LinearLayout) _$_findCachedViewById(R.id.llSportTags), false);
                        TextView textView11 = (TextView) inflate.findViewById(R.id.tvTag);
                        Intrinsics.checkExpressionValueIsNotNull(textView11, "tvTags");
                        Intrinsics.checkExpressionValueIsNotNull(campItem, "it");
                        textView11.setText(campItem.getName());
                        ((LinearLayout) _$_findCachedViewById(R.id.llSportTags)).addView(inflate);
                    }
                }
                Collection campItemList2 = campaignDetailResult.getCampItemList();
                if (campItemList2 != null && !campItemList2.isEmpty()) {
                    z = false;
                }
                if (z) {
                    LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(R.id.llTipTags);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "llTipTags");
                    linearLayout4.setVisibility(8);
                    RelativeLayout relativeLayout3 = (RelativeLayout) _$_findCachedViewById(R.id.tvSignUp);
                    Intrinsics.checkExpressionValueIsNotNull(relativeLayout3, "tvSignUp");
                    relativeLayout3.setVisibility(8);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void getDetails() {
        ApiUtil.getApi2().getCampaignDetail(this.campId).compose(RxUtil.applyErrorsWithIO()).subscribe(new SportDetailActivity$getDetails$1(this, this));
    }

    private final void countDown(String str) {
        long timeSpanByNow = TimeUtils.getTimeSpanByNow(str, 60000);
        if (timeSpanByNow >= 0) {
            RxDisposableManager.getInstance().add(getTAG(), Observable.interval(0, 1, TimeUnit.MINUTES).take(timeSpanByNow).compose(RxUtil.applyIO()).subscribe(new SportDetailActivity$countDown$1(this, timeSpanByNow, str)));
        }
    }
}
