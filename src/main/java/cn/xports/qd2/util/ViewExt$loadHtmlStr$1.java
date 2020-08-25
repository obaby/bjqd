package cn.xports.qd2.util;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"cn/xports/qd2/util/ViewExt$loadHtmlStr$1", "Landroid/webkit/WebViewClient;", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
public final class ViewExt$loadHtmlStr$1 extends WebViewClient {
    final /* synthetic */ int $padding;
    final /* synthetic */ WebView $this_loadHtmlStr;

    ViewExt$loadHtmlStr$1(WebView webView, int i) {
        this.$this_loadHtmlStr = webView;
        this.$padding = i;
    }

    public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        if (webView != null) {
            webView.measure(makeMeasureSpec, makeMeasureSpec2);
        }
        WebView webView2 = this.$this_loadHtmlStr;
        webView2.loadUrl("javascript:document.body.style.paddingBottom=\"" + this.$padding + "\"; void 0");
    }
}
