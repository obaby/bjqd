package cn.xports.qd2.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.baselib.rxbind.view.RxView;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.MyToastUtils;
import cn.xports.widget.EmptyRecyclerView;
import cn.xports.widget.NoScrollViewPager;
import com.alipay.sdk.packet.d;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\r\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013\u001a*\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016\u001a\u001c\u0010\u0017\u001a\u00020\u0001*\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001a\u001a\u00020\b\u001a4\u0010\u001b\u001a\u00020\u0001*\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016\u001a\u001a\u0010\u001e\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"\u001a,\u0010#\u001a\u00020\u0001*\u00020$2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016\u001a\u001c\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010&\u001a\u00020\b2\b\b\u0002\u0010'\u001a\u00020\u0013\u001a\u0012\u0010(\u001a\u00020)*\u00020)2\u0006\u0010*\u001a\u00020+\u001aR\u0010,\u001a\u00020\u0001*\u00020-2!\u0010.\u001a\u001d\u0012\u0013\u0012\u001100¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00010/2#\b\u0002\u00104\u001a\u001d\u0012\u0013\u0012\u001100¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00010/\u001a*\u00105\u001a\u00020)*\u00020)2\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\u000b\u001a\u001a\u0010:\u001a\u00020\u0001*\u00020;2\u0006\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\b\u001a*\u0010>\u001a\u00020)*\u00020)2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00130@2\u0006\u0010A\u001a\u00020\b2\b\b\u0002\u0010B\u001a\u00020\b\u001a \u0010C\u001a\u00020\u0001*\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020F0@2\u0006\u0010G\u001a\u00020H\u001a\u0012\u0010I\u001a\u00020\u0001*\u00020J2\u0006\u0010'\u001a\u00020\u0013\u001a\u0018\u0010K\u001a\u00020\u0001*\u00020\u00102\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016\u001a\u001f\u0010M\u001a\u00020\u0001\"\u0006\b\u0000\u0010N\u0018\u0001*\u00020O2\b\b\u0002\u0010P\u001a\u00020\u001fH\b\u001a\n\u0010Q\u001a\u00020\u0001*\u00020;¨\u0006R"}, d2 = {"addFilter", "", "Landroid/widget/EditText;", "filter", "Landroid/text/InputFilter;", "changeTabText", "Landroid/support/design/widget/TabLayout$Tab;", "visibility", "", "color", "textSize", "", "filterBlank", "filterEmoji", "filterSpecialChar", "getEmptyView", "Landroid/view/View;", "Landroid/support/v7/widget/RecyclerView;", "tip", "", "action", "method", "Lkotlin/Function0;", "loadHtmlStr", "Landroid/webkit/WebView;", "content", "padding", "loadMore", "curPage", "totalPage", "set", "Landroid/os/Bundle;", "key", "value", "", "setEmptyView", "Lcn/xports/widget/EmptyRecyclerView;", "setMaxLength", "max", "msg", "setOnTabListener", "Landroid/support/design/widget/TabLayout;", "listener", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "setRefreshAndLoad", "Lcom/scwang/smartrefresh/layout/SmartRefreshLayout;", "refresh", "Lkotlin/Function1;", "Lcom/scwang/smartrefresh/layout/api/RefreshLayout;", "Lkotlin/ParameterName;", "name", "refreshLayout", "load", "setTextColorSize", "selectTextColorSrc", "selectTextSize", "unSelectedTextColorSrc", "unSelectedTextSize", "setTextMax", "Landroid/widget/TextView;", "text", "maxLength", "setUpTabLayout", "titleList", "", "layoutId", "indicatorColor", "showFragments", "Landroid/support/v4/view/ViewPager;", "fragments", "Landroid/support/v4/app/Fragment;", "canScroll", "", "showImageToast", "Landroid/content/Context;", "singleClick", "click", "startMyActivity", "T", "Landroid/app/Activity;", "extras", "verticalScroll", "xports2_productRelease"}, k = 2, mv = {1, 1, 15})
@JvmName(name = "ViewExt")
/* compiled from: ViewExt.kt */
public final class ViewExt {
    public static /* synthetic */ TabLayout setUpTabLayout$default(TabLayout tabLayout, List list, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = -1111111;
        }
        return setUpTabLayout(tabLayout, list, i, i2);
    }

    @NotNull
    public static final TabLayout setUpTabLayout(@NotNull TabLayout tabLayout, @NotNull List<String> list, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(tabLayout, "$this$setUpTabLayout");
        Intrinsics.checkParameterIsNotNull(list, "titleList");
        tabLayout.setTabMode(1);
        int i3 = 0;
        tabLayout.setSelectedTabIndicatorHeight(0);
        int size = list.size();
        while (i3 < size) {
            View inflate = LayoutInflater.from(tabLayout.getContext()).inflate(i, (ViewGroup) null);
            TabLayout.Tab newTab = tabLayout.newTab();
            Intrinsics.checkExpressionValueIsNotNull(newTab, "this.newTab()");
            View findViewById = inflate.findViewById(R.id.tv_item_tab);
            if (findViewById != null) {
                ((TextView) findViewById).setText(list.get(i3));
                newTab.setCustomView(inflate);
                if (i2 != -1111111) {
                    View findViewById2 = inflate.findViewById(R.id.item_tab_indicator);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "customView.findViewById(R.id.item_tab_indicator)");
                    findViewById2.setBackgroundColor(i2);
                }
                tabLayout.addTab(newTab);
                i3++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
        }
        return tabLayout;
    }

    @NotNull
    public static final TabLayout setTextColorSize(@NotNull TabLayout tabLayout, int i, float f, int i2, float f2) {
        Intrinsics.checkParameterIsNotNull(tabLayout, "$this$setTextColorSize");
        tabLayout.addOnTabSelectedListener(new ViewExt$setTextColorSize$1(i2, f2, i, f));
        int tabCount = tabLayout.getTabCount();
        for (int i3 = 0; i3 < tabCount; i3++) {
            if (i3 == 0) {
                TabLayout.Tab tabAt = tabLayout.getTabAt(i3);
                if (tabAt != null) {
                    changeTabText(tabAt, 0, i, f);
                }
            } else {
                TabLayout.Tab tabAt2 = tabLayout.getTabAt(i3);
                if (tabAt2 != null) {
                    changeTabText(tabAt2, 8, i2, f2);
                }
            }
        }
        return tabLayout;
    }

    @NotNull
    public static final TabLayout.Tab changeTabText(@NotNull TabLayout.Tab tab, int i, int i2, float f) {
        Intrinsics.checkParameterIsNotNull(tab, "$this$changeTabText");
        View customView = tab.getCustomView();
        if (customView != null) {
            View findViewById = customView.findViewById(R.id.tv_item_tab);
            if (findViewById != null) {
                TextView textView = (TextView) findViewById;
                View findViewById2 = customView.findViewById(R.id.item_tab_indicator);
                Intrinsics.checkExpressionValueIsNotNull(findViewById2, "singleTabView.findViewBy…(R.id.item_tab_indicator)");
                textView.setTextSize(2, f);
                if (i2 != -1) {
                    textView.setTextColor(ContextCompat.getColor(Utils.getApp(), i2));
                }
                findViewById2.setVisibility(i);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
        }
        return tab;
    }

    @NotNull
    public static final TabLayout setOnTabListener(@NotNull TabLayout tabLayout, @NotNull TabLayout.OnTabSelectedListener onTabSelectedListener) {
        Intrinsics.checkParameterIsNotNull(tabLayout, "$this$setOnTabListener");
        Intrinsics.checkParameterIsNotNull(onTabSelectedListener, "listener");
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
        return tabLayout;
    }

    public static final void loadMore(@NotNull RecyclerView recyclerView, @NotNull Function0<Integer> function0, @NotNull Function0<Integer> function02, @NotNull Function0<Unit> function03) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "$this$loadMore");
        Intrinsics.checkParameterIsNotNull(function0, "curPage");
        Intrinsics.checkParameterIsNotNull(function02, "totalPage");
        Intrinsics.checkParameterIsNotNull(function03, d.q);
        recyclerView.setOnScrollListener(new ViewExt$loadMore$1(function0, function02, function03));
    }

    public static final void loadHtmlStr(@NotNull WebView webView, @Nullable String str, int i) {
        Intrinsics.checkParameterIsNotNull(webView, "$this$loadHtmlStr");
        if (str != null) {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setSupportZoom(false);
            settings.setBuiltInZoomControls(false);
            settings.setDisplayZoomControls(false);
            webView.setVerticalScrollBarEnabled(false);
            webView.loadDataWithBaseURL((String) null, str, "text/html", "utf-8", (String) null);
            webView.setWebViewClient(new ViewExt$loadHtmlStr$1(webView, i));
        }
    }

    public static final void showFragments(@NotNull ViewPager viewPager, @NotNull List<? extends Fragment> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(viewPager, "$this$showFragments");
        Intrinsics.checkParameterIsNotNull(list, "fragments");
        if (!list.isEmpty()) {
            Fragment fragment = (Fragment) list.get(0);
            Activity topActivity = ActivityUtils.getTopActivity();
            if (topActivity != null) {
                FragmentManager supportFragmentManager = ((FragmentActivity) topActivity).getSupportFragmentManager();
                if (supportFragmentManager != null) {
                    viewPager.setAdapter(new ViewExt$showFragments$$inlined$let$lambda$1(supportFragmentManager, supportFragmentManager, viewPager, list));
                    PagerAdapter adapter = viewPager.getAdapter();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
                if (viewPager instanceof NoScrollViewPager) {
                    ((NoScrollViewPager) viewPager).setCanScroll(z);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
        }
    }

    public static final void filterBlank(@NotNull EditText editText) {
        Intrinsics.checkParameterIsNotNull(editText, "$this$filterBlank");
        InputFilter[] filters = editText.getFilters();
        Intrinsics.checkExpressionValueIsNotNull(filters, "filters");
        Object[] copyOf = Arrays.copyOf(filters, editText.getFilters().length + 1);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        InputFilter[] inputFilterArr = (InputFilter[]) copyOf;
        inputFilterArr[inputFilterArr.length - 1] = ViewExt$filterBlank$filter$1.INSTANCE;
        editText.setFilters(inputFilterArr);
    }

    public static final void addFilter(@NotNull EditText editText, @NotNull InputFilter inputFilter) {
        Intrinsics.checkParameterIsNotNull(editText, "$this$addFilter");
        Intrinsics.checkParameterIsNotNull(inputFilter, "filter");
        InputFilter[] filters = editText.getFilters();
        Intrinsics.checkExpressionValueIsNotNull(filters, "filters");
        Object[] copyOf = Arrays.copyOf(filters, editText.getFilters().length + 1);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        InputFilter[] inputFilterArr = (InputFilter[]) copyOf;
        inputFilterArr[inputFilterArr.length - 1] = inputFilter;
        editText.setFilters(inputFilterArr);
    }

    public static final void filterEmoji(@NotNull EditText editText) {
        Intrinsics.checkParameterIsNotNull(editText, "$this$filterEmoji");
        addFilter(editText, ViewExt$filterEmoji$1.INSTANCE);
    }

    public static final void filterSpecialChar(@NotNull EditText editText) {
        Intrinsics.checkParameterIsNotNull(editText, "$this$filterSpecialChar");
        addFilter(editText, ViewExt$filterSpecialChar$1.INSTANCE);
    }

    public static /* synthetic */ void setMaxLength$default(EditText editText, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "";
        }
        setMaxLength(editText, i, str);
    }

    public static final void setMaxLength(@NotNull EditText editText, int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(editText, "$this$setMaxLength");
        Intrinsics.checkParameterIsNotNull(str, "msg");
        LengthFilter lengthFilter = new LengthFilter(i, str);
        InputFilter[] filters = editText.getFilters();
        Intrinsics.checkExpressionValueIsNotNull(filters, "filters");
        Object[] copyOf = Arrays.copyOf(filters, editText.getFilters().length + 1);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        InputFilter[] inputFilterArr = (InputFilter[]) copyOf;
        inputFilterArr[inputFilterArr.length - 1] = lengthFilter;
        editText.setFilters(inputFilterArr);
    }

    public static /* synthetic */ View getEmptyView$default(RecyclerView recyclerView, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = (Function0) ViewExt$getEmptyView$1.INSTANCE;
        }
        return getEmptyView(recyclerView, str, str2, function0);
    }

    @NotNull
    public static final View getEmptyView(@NotNull RecyclerView recyclerView, @NotNull String str, @NotNull String str2, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "$this$getEmptyView");
        Intrinsics.checkParameterIsNotNull(str, "tip");
        Intrinsics.checkParameterIsNotNull(str2, d.o);
        Intrinsics.checkParameterIsNotNull(function0, d.q);
        View inflate = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.layout_no_data_and_add, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvJoinTip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(str);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvJoin);
        textView2.setText(str2);
        textView2.setOnClickListener(new ViewExt$getEmptyView$2(function0));
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return inflate;
    }

    @NotNull
    public static final View getEmptyView(@NotNull RecyclerView recyclerView, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "$this$getEmptyView");
        Intrinsics.checkParameterIsNotNull(str, "tip");
        View inflate = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.layout_no_data_and_add, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvJoinTip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(str);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvJoin);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "actionView");
        textView2.setVisibility(8);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return inflate;
    }

    public static final void singleClick(@NotNull View view, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(view, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(function0, "click");
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new ViewExt$singleClick$1(function0));
    }

    public static /* synthetic */ void setEmptyView$default(EmptyRecyclerView emptyRecyclerView, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            function0 = (Function0) ViewExt$setEmptyView$1.INSTANCE;
        }
        setEmptyView(emptyRecyclerView, str, str2, function0);
    }

    public static final void setEmptyView(@NotNull EmptyRecyclerView emptyRecyclerView, @NotNull String str, @NotNull String str2, @NotNull Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(emptyRecyclerView, "$this$setEmptyView");
        Intrinsics.checkParameterIsNotNull(str, "tip");
        Intrinsics.checkParameterIsNotNull(str2, d.o);
        Intrinsics.checkParameterIsNotNull(function0, d.q);
        View inflate = LayoutInflater.from(emptyRecyclerView.getContext()).inflate(R.layout.layout_no_data_and_add, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvJoinTip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(str);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvJoin);
        CharSequence charSequence = str2;
        if (TextUtils.isEmpty(charSequence)) {
            Intrinsics.checkExpressionValueIsNotNull(textView2, "actionView");
            textView2.setVisibility(8);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(textView2, "actionView");
            textView2.setText(charSequence);
            textView2.setOnClickListener(new ViewExt$setEmptyView$2(function0));
        }
        emptyRecyclerView.setOverScrollMode(2);
        emptyRecyclerView.setEmptyView(inflate);
    }

    public static /* synthetic */ void startMyActivity$default(Activity activity, Bundle bundle, int i, Object obj) {
        if ((i & 1) != 0) {
            bundle = new Bundle();
        }
        Intrinsics.checkParameterIsNotNull(activity, "$this$startMyActivity");
        Intrinsics.checkParameterIsNotNull(bundle, "extras");
        Intrinsics.reifiedOperationMarker(4, "T");
        activity.startActivity(new Intent(activity, Object.class).putExtras(bundle));
    }

    public static final /* synthetic */ <T> void startMyActivity(@NotNull Activity activity, @NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(activity, "$this$startMyActivity");
        Intrinsics.checkParameterIsNotNull(bundle, "extras");
        Intrinsics.reifiedOperationMarker(4, "T");
        activity.startActivity(new Intent(activity, Object.class).putExtras(bundle));
    }

    @NotNull
    public static final Bundle set(@NotNull Bundle bundle, @NotNull String str, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(bundle, "$this$set");
        Intrinsics.checkParameterIsNotNull(str, "key");
        Intrinsics.checkParameterIsNotNull(obj, "value");
        if (obj instanceof String) {
            bundle.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Number) obj).intValue());
        } else if (obj instanceof Float) {
            bundle.putFloat(str, ((Number) obj).floatValue());
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Number) obj).longValue());
        } else if (obj instanceof int[]) {
            bundle.putIntArray(str, (int[]) obj);
        }
        return bundle;
    }

    public static /* synthetic */ void setRefreshAndLoad$default(SmartRefreshLayout smartRefreshLayout, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 2) != 0) {
            function12 = (Function1) ViewExt$setRefreshAndLoad$1.INSTANCE;
        }
        setRefreshAndLoad(smartRefreshLayout, function1, function12);
    }

    public static final void setRefreshAndLoad(@NotNull SmartRefreshLayout smartRefreshLayout, @NotNull Function1<? super RefreshLayout, Unit> function1, @NotNull Function1<? super RefreshLayout, Unit> function12) {
        Intrinsics.checkParameterIsNotNull(smartRefreshLayout, "$this$setRefreshAndLoad");
        Intrinsics.checkParameterIsNotNull(function1, "refresh");
        Intrinsics.checkParameterIsNotNull(function12, "load");
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(smartRefreshLayout.getContext()));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(smartRefreshLayout.getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        smartRefreshLayout.setOnRefreshListener(new ViewExt$sam$com_scwang_smartrefresh_layout_listener_OnRefreshListener$0(function1)).setOnLoadMoreListener(new ViewExt$sam$com_scwang_smartrefresh_layout_listener_OnLoadMoreListener$0(function12));
    }

    public static final void showImageToast(@NotNull Context context, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(context, "$this$showImageToast");
        Intrinsics.checkParameterIsNotNull(str, "msg");
        new MyToastUtils().ToastShow(context, str, R.drawable.ic_right_white);
    }

    public static final void setTextMax(@NotNull TextView textView, @NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(textView, "$this$setTextMax");
        Intrinsics.checkParameterIsNotNull(str, "text");
        if (str.length() > i) {
            StringBuilder sb = new StringBuilder();
            String substring = str.substring(0, i);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append("...");
            textView.setText(sb.toString());
            return;
        }
        textView.setText(str);
    }

    public static final void verticalScroll(@NotNull TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$this$verticalScroll");
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
