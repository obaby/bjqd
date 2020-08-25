package cn.xports.sportCoaching;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import cn.xports.qdplugin.R;

public class ProgressWebView extends WebView {
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;

    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgressBar = new ProgressBar(context, (AttributeSet) null, 16842872);
        this.mProgressBar.setLayoutParams(new LinearLayout.LayoutParams(-1, 8));
        this.mProgressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.web_progress_bar_states));
        addView(this.mProgressBar);
        setWebChromeClient(new WebChromeClient());
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        public WebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                ProgressWebView.this.mProgressBar.setVisibility(8);
            } else {
                if (ProgressWebView.this.mProgressBar.getVisibility() == 8) {
                    ProgressWebView.this.mProgressBar.setVisibility(0);
                }
                ProgressWebView.this.mProgressBar.setProgress(i);
            }
            super.onProgressChanged(webView, i);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) this.mProgressBar.getLayoutParams();
        layoutParams.x = i;
        layoutParams.y = i2;
        this.mProgressBar.setLayoutParams(layoutParams);
        super.onScrollChanged(i, i2, i3, i4);
    }
}
