package cn.xports.field;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.base.Constant;
import cn.xports.baselib.util.DateUtil;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.BookingRule;
import cn.xports.entity.FieldSegment;
import cn.xports.entity.FieldTypeBean;
import cn.xports.field.FieldBookContract;
import cn.xports.order.OrderPayActivity;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import cn.xports.widget.HVScrollView;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 N2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0001NB\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eH\u0016J \u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u001eH\u0016J&\u0010(\u001a\u00020 2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0*2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020\u001eH\u0016J\u0018\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u00020\u000bH\u0016J\b\u00100\u001a\u00020\u000bH\u0014J\b\u00101\u001a\u00020\u001eH\u0014J\b\u00102\u001a\u00020 H\u0016J\b\u00103\u001a\u00020 H\u0014J\u001a\u00104\u001a\u00020 2\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u0016H\u0016J\u0010\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020\u000bH\u0016J!\u0010:\u001a\u00020 2\u0012\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0<\"\u00020\u000bH\u0016¢\u0006\u0002\u0010=J\u0016\u0010>\u001a\u00020 2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001a0*H\u0016J\u0010\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020\u001eH\u0002J\u0016\u0010B\u001a\u00020 2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0*H\u0016J\u001e\u0010E\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u000b2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00100*H\u0016J\u0010\u0010G\u001a\u00020 2\u0006\u0010H\u001a\u00020\u0016H\u0016J\u0012\u0010I\u001a\u00020 2\b\u0010J\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010K\u001a\u00020 2\u0006\u0010L\u001a\u00020\u000bH\u0002J\u0010\u0010M\u001a\u00020 2\u0006\u0010L\u001a\u00020\u000bH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\rX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\rj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00100\rj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001a0\rj\b\u0012\u0004\u0012\u00020\u001a`\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcn/xports/field/FieldBookActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/field/FieldBookPresenter;", "Lcn/xports/field/FieldBookContract$View;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "Lcn/xports/widget/HVScrollView$OnScrollListener;", "()V", "defaultEffectiveFieldSegments", "", "Lcn/xports/entity/FieldSegment;", "defaultFullTag", "", "effectiveFieldSegments", "Ljava/util/ArrayList;", "fieldSegments", "fullFieldTypes", "Lcn/xports/entity/FieldTypeBean;", "Lkotlin/collections/ArrayList;", "fullTag", "halfFieldTypes", "hasFull", "hasNoMatchRule", "", "mServiceId", "mVenueId", "rule", "Lcn/xports/entity/BookingRule;", "ruleList", "selectDate", "selectFieldType", "", "OnScroll", "", "x", "y", "addFieldName", "layoutParams", "Landroid/widget/LinearLayout$LayoutParams;", "fieldName", "padding", "addLineField", "singleLineFieldList", "", "fieldLayoutParams", "paddingTop", "addTimeView", "timeLayoutParams", "startTime", "getChildTitle", "getLayoutId", "initData", "initView", "onCheckedChanged", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "onGetTradeId", "tradeId", "saveParam", "params", "", "([Ljava/lang/String;)V", "saveRule", "rules", "setRule", "fieldType", "showBookDay", "bookDays", "Lcn/xports/entity/BookingDay;", "showFieldTypes", "fieldTypeList", "showFieldView", "hasField", "showTopTip", "manual", "updateFullTag", "tag", "updateTypes", "Companion", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookActivity.kt */
public final class FieldBookActivity extends BaseBussActivity<FieldBookPresenter> implements FieldBookContract.View, CompoundButton.OnCheckedChangeListener, HVScrollView.OnScrollListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public List<FieldSegment> defaultEffectiveFieldSegments = new ArrayList();
    private String defaultFullTag = K.k0;
    /* access modifiers changed from: private */
    public final ArrayList<FieldSegment> effectiveFieldSegments = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<FieldSegment> fieldSegments = new ArrayList<>();
    private final ArrayList<FieldTypeBean> fullFieldTypes = new ArrayList<>();
    private String fullTag = K.k0;
    private final ArrayList<FieldTypeBean> halfFieldTypes = new ArrayList<>();
    private String hasFull = K.k0;
    private boolean hasNoMatchRule;
    /* access modifiers changed from: private */
    public String mServiceId = "";
    /* access modifiers changed from: private */
    public String mVenueId = "";
    /* access modifiers changed from: private */
    public BookingRule rule;
    private final ArrayList<BookingRule> ruleList = new ArrayList<>();
    /* access modifiers changed from: private */
    public String selectDate = DateUtil.getCurrentTime();
    /* access modifiers changed from: private */
    public int selectFieldType;

    static {
        StubApp.interface11(3237);
    }

    @JvmStatic
    public static final void start(@NotNull Context context, @NotNull String str, @NotNull String str2, int i, @NotNull String str3) {
        Companion.start(context, str, str2, i, str3);
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
        return "场地预订";
    }

    public void onGetTradeId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        startActivity(new Intent(this, OrderPayActivity.class).putExtra("tradeId", str));
    }

    public void showTopTip(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llTopTip);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llTopTip");
            linearLayout.setVisibility(8);
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llTopTip);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llTopTip");
        linearLayout2.setVisibility(0);
        ((LinearLayout) _$_findCachedViewById(R.id.llTopTip)).setOnClickListener(new FieldBookActivity$showTopTip$1(this, str));
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvManual);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvManual");
        textView.setText("订场须知: " + Html.fromHtml(str));
    }

    public void saveParam(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "params");
        this.defaultFullTag = strArr[0];
        this.fullTag = this.defaultFullTag;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvBottomTip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvBottomTip");
        textView.setText(strArr[1]);
        if (Intrinsics.areEqual(strArr[1], "")) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llAgreement);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llAgreement");
            linearLayout.setVisibility(8);
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llAgreement);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llAgreement");
        linearLayout2.setVisibility(0);
    }

    public void saveRule(@NotNull List<? extends BookingRule> list) {
        Intrinsics.checkParameterIsNotNull(list, "rules");
        this.ruleList.clear();
        this.ruleList.addAll(list);
    }

    public void showFieldTypes(@NotNull String str, @NotNull List<? extends FieldTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(str, "hasFull");
        Intrinsics.checkParameterIsNotNull(list, "fieldTypeList");
        this.hasFull = str;
        this.fullFieldTypes.clear();
        this.halfFieldTypes.clear();
        for (FieldTypeBean fieldTypeBean : list) {
            if (Intrinsics.areEqual(fieldTypeBean.getFullTag(), "1")) {
                this.fullFieldTypes.add(fieldTypeBean);
            } else {
                this.halfFieldTypes.add(fieldTypeBean);
            }
        }
        updateTypes(this.defaultFullTag);
        if (Intrinsics.areEqual(str, "1")) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llFullTag);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llFullTag");
            linearLayout.setVisibility(0);
            updateFullTag(this.defaultFullTag);
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llFullTag);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llFullTag");
        linearLayout2.setVisibility(8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
        if ((r7.selectDate.length() == 0) != false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showBookDay(@org.jetbrains.annotations.NotNull java.util.List<? extends cn.xports.entity.BookingDay> r8) {
        /*
            r7 = this;
            java.lang.String r0 = "bookDays"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = r7.selectDate
            java.lang.String r1 = cn.xports.baselib.util.DateUtil.getCurrentTime()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r1 = 0
            if (r0 != 0) goto L_0x0021
            java.lang.String r0 = r7.selectDate
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            if (r0 == 0) goto L_0x0032
        L_0x0021:
            java.lang.Object r0 = r8.get(r1)
            cn.xports.entity.BookingDay r0 = (cn.xports.entity.BookingDay) r0
            java.lang.String r0 = r0.getDate()
            java.lang.String r2 = "bookDays[0].date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r7.selectDate = r0
        L_0x0032:
            cn.xports.field.TabAdapter r0 = new cn.xports.field.TabAdapter
            r2 = r7
            android.content.Context r2 = (android.content.Context) r2
            int r3 = cn.xports.field.TabAdapter.FIELD_TAB_TYPE
            r0.<init>(r2, r3)
            int r2 = r8.size()
            r3 = 0
        L_0x0041:
            if (r1 >= r2) goto L_0x0080
            int r4 = cn.xports.qdplugin.R.id.tabLayoutField
            android.view.View r4 = r7._$_findCachedViewById(r4)
            android.support.design.widget.TabLayout r4 = (android.support.design.widget.TabLayout) r4
            int r5 = cn.xports.qdplugin.R.id.tabLayoutField
            android.view.View r5 = r7._$_findCachedViewById(r5)
            android.support.design.widget.TabLayout r5 = (android.support.design.widget.TabLayout) r5
            android.support.design.widget.TabLayout$Tab r5 = r5.newTab()
            java.lang.Object r6 = r8.get(r1)
            cn.xports.entity.BookingDay r6 = (cn.xports.entity.BookingDay) r6
            android.view.View r6 = r0.getView(r6)
            android.support.design.widget.TabLayout$Tab r5 = r5.setCustomView((android.view.View) r6)
            r4.addTab(r5)
            if (r3 != 0) goto L_0x007d
            java.lang.Object r4 = r8.get(r1)
            cn.xports.entity.BookingDay r4 = (cn.xports.entity.BookingDay) r4
            java.lang.String r4 = r4.getDate()
            java.lang.String r5 = r7.selectDate
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L_0x007d
            r3 = r1
        L_0x007d:
            int r1 = r1 + 1
            goto L_0x0041
        L_0x0080:
            int r0 = cn.xports.qdplugin.R.id.tabLayoutField
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.support.design.widget.TabLayout r0 = (android.support.design.widget.TabLayout) r0
            cn.xports.field.FieldBookActivity$showBookDay$1 r1 = new cn.xports.field.FieldBookActivity$showBookDay$1
            r1.<init>(r7, r8)
            android.support.design.widget.TabLayout$OnTabSelectedListener r1 = (android.support.design.widget.TabLayout.OnTabSelectedListener) r1
            r0.addOnTabSelectedListener(r1)
            int r0 = cn.xports.qdplugin.R.id.tabLayoutField
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.support.design.widget.TabLayout r0 = (android.support.design.widget.TabLayout) r0
            android.support.design.widget.TabLayout$Tab r0 = r0.getTabAt(r3)
            if (r0 == 0) goto L_0x00a3
            r0.select()
        L_0x00a3:
            java.lang.Object r8 = r8.get(r3)
            cn.xports.entity.BookingDay r8 = (cn.xports.entity.BookingDay) r8
            java.lang.String r8 = r8.getDate()
            java.lang.String r0 = "bookDays[tag].date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            r7.selectDate = r8
            cn.xports.baselib.mvp.IPresenter r8 = r7.getPresenter()
            cn.xports.field.FieldBookPresenter r8 = (cn.xports.field.FieldBookPresenter) r8
            if (r8 == 0) goto L_0x00c5
            java.lang.String r0 = r7.selectDate
            java.lang.String r1 = r7.mServiceId
            java.lang.String r2 = r7.mVenueId
            r8.getFieldTypes(r0, r1, r2)
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.field.FieldBookActivity.showBookDay(java.util.List):void");
    }

    public void OnScroll(int i, int i2) {
        if (i2 == 0) {
            HVScrollView hVScrollView = (HVScrollView) _$_findCachedViewById(R.id.hvScrollView);
            Intrinsics.checkExpressionValueIsNotNull(hVScrollView, "hvScrollView");
            ((LinearLayout) _$_findCachedViewById(R.id.bookFieldNameList)).scrollTo(hVScrollView.getScrollX(), i2);
        }
        if (i == 0) {
            HVScrollView hVScrollView2 = (HVScrollView) _$_findCachedViewById(R.id.hvScrollView);
            Intrinsics.checkExpressionValueIsNotNull(hVScrollView2, "hvScrollView");
            ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTimeList)).scrollTo(0, hVScrollView2.getScrollY());
        }
    }

    public void onCheckedChanged(@Nullable CompoundButton compoundButton, boolean z) {
        if (compoundButton != null) {
            int i = 0;
            if (compoundButton.getId() == R.id.price_item) {
                Object tag = compoundButton.getTag(R.id.book_ticket_tag);
                if (tag != null) {
                    FieldSegment fieldSegment = (FieldSegment) tag;
                    if (z) {
                        this.fieldSegments.add(fieldSegment);
                        this.effectiveFieldSegments.remove(fieldSegment);
                    } else {
                        this.fieldSegments.remove(fieldSegment);
                        this.effectiveFieldSegments.add(fieldSegment);
                        FieldBookPresenter fieldBookPresenter = (FieldBookPresenter) getPresenter();
                    }
                    FieldBookPresenter fieldBookPresenter2 = (FieldBookPresenter) getPresenter();
                    if (fieldBookPresenter2 != null) {
                        fieldBookPresenter2.checkRules(this.fieldSegments, this.effectiveFieldSegments, this.defaultEffectiveFieldSegments, this.rule);
                    }
                    for (FieldSegment price : this.fieldSegments) {
                        i += price.getPrice();
                    }
                    TextView textView = (TextView) _$_findCachedViewById(R.id.tvTotalMoney);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "tvTotalMoney");
                    textView.setText("¥" + MoneyUtil.simpleYuan(i));
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type cn.xports.entity.FieldSegment");
            } else if (compoundButton.getId() == R.id.cb_field_type_item) {
                if (z) {
                    Object tag2 = compoundButton.getTag(R.id.book_ticket_tag);
                    if (tag2 != null) {
                        int intValue = ((Integer) tag2).intValue();
                        setRule(intValue);
                        compoundButton.setClickable(false);
                        FieldBookPresenter fieldBookPresenter3 = (FieldBookPresenter) getPresenter();
                        if (fieldBookPresenter3 != null) {
                            fieldBookPresenter3.getFieldList(this.selectDate, intValue, this.mServiceId, this.mVenueId);
                        }
                        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList);
                        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "bookFieldTypeList");
                        int childCount = linearLayout.getChildCount();
                        for (int i2 = 0; i2 < childCount; i2++) {
                            if (!Intrinsics.areEqual(((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).getChildAt(i2), compoundButton)) {
                                View childAt = ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).getChildAt(i2);
                                if (childAt == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
                                } else if (((CheckBox) childAt).isChecked()) {
                                    View childAt2 = ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).getChildAt(i2);
                                    if (childAt2 != null) {
                                        ((CheckBox) childAt2).setChecked(false);
                                        View childAt3 = ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).getChildAt(i2);
                                        Intrinsics.checkExpressionValueIsNotNull(childAt3, "bookFieldTypeList.getChildAt(i)");
                                        childAt3.setClickable(true);
                                        return;
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
                                }
                            }
                        }
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
            } else if (compoundButton.getId() == R.id.cbFullField) {
                if (z) {
                    this.fullTag = "1";
                    updateTypes(this.fullTag);
                    updateFullTag(this.fullTag);
                }
            } else if (compoundButton.getId() == R.id.cbHalfField && z) {
                this.fullTag = K.k0;
                updateTypes(this.fullTag);
                updateFullTag(this.fullTag);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_book_field;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        HVScrollView hVScrollView = (HVScrollView) _$_findCachedViewById(R.id.hvScrollView);
        Intrinsics.checkExpressionValueIsNotNull(hVScrollView, "hvScrollView");
        hVScrollView.setOnScrollListener(this);
        setPresenter(new FieldBookPresenter(new FieldBookModel(), this));
        FieldBookPresenter fieldBookPresenter = (FieldBookPresenter) getPresenter();
        if (fieldBookPresenter != null) {
            fieldBookPresenter.getBookRule(this.mVenueId);
            fieldBookPresenter.getParams(this.mVenueId, this.mServiceId);
            fieldBookPresenter.getBookDays(this.mServiceId, this.mVenueId);
        }
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this;
        ((CheckBox) _$_findCachedViewById(R.id.cbHalfField)).setOnCheckedChangeListener(onCheckedChangeListener);
        ((CheckBox) _$_findCachedViewById(R.id.cbFullField)).setOnCheckedChangeListener(onCheckedChangeListener);
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvSubmit);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvSubmit");
        textView.setEnabled(!TextUtils.isEmpty(Constant.PAYMODES));
        ((TextView) _$_findCachedViewById(R.id.tvSubmit)).setOnClickListener(new FieldBookActivity$initView$2(this));
    }

    public void initData() {
        this.mServiceId = getStringExtra(K.serviceId);
        String stringExtra = getIntent().getStringExtra(K.venueId);
        Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"venueId\")");
        this.mVenueId = stringExtra;
        this.selectFieldType = getIntent().getIntExtra("fieldType", 0);
        String stringExtra2 = getIntent().getStringExtra("selectDate");
        Intrinsics.checkExpressionValueIsNotNull(stringExtra2, "intent.getStringExtra(\"selectDate\")");
        this.selectDate = stringExtra2;
    }

    public void showFieldView(boolean z) {
        this.fieldSegments.clear();
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvTotalMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvTotalMoney");
        textView.setText("¥0");
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldNameList)).removeAllViews();
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldNameList)).scrollTo(0, 0);
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTimeList)).removeAllViews();
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTimeList)).scrollTo(0, 0);
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldPriceList)).scrollTo(0, 0);
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldPriceList)).removeAllViews();
        if (!z) {
            showNoData("暂无场地信息");
        }
    }

    public void addTimeView(@NotNull LinearLayout.LayoutParams layoutParams, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "timeLayoutParams");
        Intrinsics.checkParameterIsNotNull(str, K.startTime);
        TextView textView = new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setText(str);
        textView.setGravity(17);
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTimeList)).addView(textView);
    }

    public void addFieldName(@NotNull LinearLayout.LayoutParams layoutParams, @NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(layoutParams, "layoutParams");
        Intrinsics.checkParameterIsNotNull(str, "fieldName");
        TextView textView = new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setText(str);
        textView.setGravity(17);
        textView.setTextSize(0, getResources().getDimension(R.dimen.commonsdk_text_size_micro));
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        double d = (double) i;
        Double.isNaN(d);
        int i2 = (int) (d * 1.5d);
        textView.setPadding(i2, 0, i2, 0);
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldNameList)).addView(textView);
    }

    public void addLineField(@NotNull List<? extends FieldSegment> list, @NotNull LinearLayout.LayoutParams layoutParams, int i) {
        String str;
        Intrinsics.checkParameterIsNotNull(list, "singleLineFieldList");
        Intrinsics.checkParameterIsNotNull(layoutParams, "fieldLayoutParams");
        Context context = this;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            FieldSegment fieldSegment = (FieldSegment) list.get(i2);
            View inflate = LayoutInflater.from(context).inflate(R.layout.book_item_field_price, linearLayout, false);
            if (inflate != null) {
                CheckBox checkBox = (CheckBox) inflate;
                checkBox.setLayoutParams(layoutParams);
                if (fieldSegment.getPrice() < 0) {
                    str = "暂不出售";
                } else {
                    str = "¥" + MoneyUtil.simpleYuan(fieldSegment.getPrice());
                }
                checkBox.setText(str);
                if ((!Intrinsics.areEqual(K.k0, fieldSegment.getState())) || fieldSegment.getPrice() < 0) {
                    checkBox.setBackground(getResources().getDrawable(R.drawable.bg_gray_c2c_corner_2dp));
                    checkBox.setText("");
                    checkBox.setEnabled(false);
                } else {
                    this.effectiveFieldSegments.add(fieldSegment);
                }
                checkBox.setTag(R.id.book_ticket_tag, fieldSegment);
                checkBox.setTag(R.id.book_ticket_price, Integer.valueOf(fieldSegment.getPrice()));
                checkBox.setOnCheckedChangeListener(this);
                linearLayout.addView(checkBox);
                i2++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
            }
        }
        this.defaultEffectiveFieldSegments = new ArrayList(this.effectiveFieldSegments);
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldPriceList)).addView(linearLayout);
    }

    private final void setRule(int i) {
        this.selectFieldType = i;
        for (BookingRule bookingRule : this.ruleList) {
            if (bookingRule.getFieldType() == i) {
                this.rule = bookingRule;
            }
        }
    }

    private final void updateTypes(String str) {
        List list = this.halfFieldTypes;
        if (Intrinsics.areEqual(str, "1")) {
            list = this.fullFieldTypes;
        }
        ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).removeAllViews();
        int i = 0;
        int i2 = 0;
        for (Object next : list) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FieldTypeBean fieldTypeBean = (FieldTypeBean) next;
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_field_type, (LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList), false);
            if (inflate != null) {
                CheckBox checkBox = (CheckBox) inflate;
                checkBox.setText(fieldTypeBean.getFieldTypeName());
                checkBox.setTag(R.id.book_ticket_tag, Integer.valueOf(fieldTypeBean.getFieldType()));
                checkBox.setOnCheckedChangeListener(this);
                ViewGroup.LayoutParams layoutParams = checkBox.getLayoutParams();
                if (layoutParams != null) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.setMargins(0, 0, DensityUtil.dp2px(7.0f), 0);
                    checkBox.setLayoutParams(layoutParams2);
                    ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).addView(checkBox);
                    if (this.selectFieldType == fieldTypeBean.getFieldType()) {
                        i2 = i;
                    }
                    i = i3;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
            }
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "bookFieldTypeList");
        if (linearLayout.getChildCount() == 0) {
            showFieldView(false);
            return;
        }
        View childAt = ((LinearLayout) _$_findCachedViewById(R.id.bookFieldTypeList)).getChildAt(i2);
        if (childAt != null) {
            ((CheckBox) childAt).setChecked(true);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0007¨\u0006\r"}, d2 = {"Lcn/xports/field/FieldBookActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "venueId", "", "serviceId", "fieldType", "", "selectDate", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: FieldBookActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void start(@NotNull Context context, @NotNull String str, @NotNull String str2, int i, @NotNull String str3) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(str, K.venueId);
            Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
            Intrinsics.checkParameterIsNotNull(str3, "selectDate");
            Intent intent = new Intent(context, FieldBookActivity.class);
            intent.putExtra(K.venueId, str);
            intent.putExtra(K.serviceId, str2);
            intent.putExtra("fieldType", i);
            intent.putExtra("selectDate", str3);
            context.startActivity(intent);
        }
    }

    private final void updateFullTag(String str) {
        if (Intrinsics.areEqual(str, "1")) {
            CheckBox checkBox = (CheckBox) _$_findCachedViewById(R.id.cbFullField);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "cbFullField");
            if (!checkBox.isChecked()) {
                CheckBox checkBox2 = (CheckBox) _$_findCachedViewById(R.id.cbFullField);
                Intrinsics.checkExpressionValueIsNotNull(checkBox2, "cbFullField");
                checkBox2.setChecked(true);
            }
            CheckBox checkBox3 = (CheckBox) _$_findCachedViewById(R.id.cbHalfField);
            Intrinsics.checkExpressionValueIsNotNull(checkBox3, "cbHalfField");
            checkBox3.setChecked(false);
            CheckBox checkBox4 = (CheckBox) _$_findCachedViewById(R.id.cbHalfField);
            Intrinsics.checkExpressionValueIsNotNull(checkBox4, "cbHalfField");
            checkBox4.setClickable(true);
            CheckBox checkBox5 = (CheckBox) _$_findCachedViewById(R.id.cbFullField);
            Intrinsics.checkExpressionValueIsNotNull(checkBox5, "cbFullField");
            checkBox5.setClickable(false);
            return;
        }
        CheckBox checkBox6 = (CheckBox) _$_findCachedViewById(R.id.cbHalfField);
        Intrinsics.checkExpressionValueIsNotNull(checkBox6, "cbHalfField");
        if (!checkBox6.isChecked()) {
            CheckBox checkBox7 = (CheckBox) _$_findCachedViewById(R.id.cbHalfField);
            Intrinsics.checkExpressionValueIsNotNull(checkBox7, "cbHalfField");
            checkBox7.setChecked(true);
        }
        CheckBox checkBox8 = (CheckBox) _$_findCachedViewById(R.id.cbFullField);
        Intrinsics.checkExpressionValueIsNotNull(checkBox8, "cbFullField");
        checkBox8.setChecked(false);
        CheckBox checkBox9 = (CheckBox) _$_findCachedViewById(R.id.cbFullField);
        Intrinsics.checkExpressionValueIsNotNull(checkBox9, "cbFullField");
        checkBox9.setClickable(true);
        CheckBox checkBox10 = (CheckBox) _$_findCachedViewById(R.id.cbHalfField);
        Intrinsics.checkExpressionValueIsNotNull(checkBox10, "cbHalfField");
        checkBox10.setClickable(false);
    }
}
