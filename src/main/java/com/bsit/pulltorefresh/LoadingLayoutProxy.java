package com.bsit.pulltorefresh;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.bsit.pulltorefresh.internal.LoadingLayout;
import java.util.HashSet;
import java.util.Iterator;

public class LoadingLayoutProxy implements ILoadingLayout {
    private final HashSet<LoadingLayout> mLoadingLayouts = new HashSet<>();

    LoadingLayoutProxy() {
    }

    public void addLayout(LoadingLayout loadingLayout) {
        if (loadingLayout != null) {
            this.mLoadingLayouts.add(loadingLayout);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        Iterator<LoadingLayout> it = this.mLoadingLayouts.iterator();
        while (it.hasNext()) {
            it.next().setLastUpdatedLabel(charSequence);
        }
    }

    public void setLoadingDrawable(Drawable drawable) {
        Iterator<LoadingLayout> it = this.mLoadingLayouts.iterator();
        while (it.hasNext()) {
            it.next().setLoadingDrawable(drawable);
        }
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        Iterator<LoadingLayout> it = this.mLoadingLayouts.iterator();
        while (it.hasNext()) {
            it.next().setRefreshingLabel(charSequence);
        }
    }

    public void setPullLabel(CharSequence charSequence) {
        Iterator<LoadingLayout> it = this.mLoadingLayouts.iterator();
        while (it.hasNext()) {
            it.next().setPullLabel(charSequence);
        }
    }

    public void setReleaseLabel(CharSequence charSequence) {
        Iterator<LoadingLayout> it = this.mLoadingLayouts.iterator();
        while (it.hasNext()) {
            it.next().setReleaseLabel(charSequence);
        }
    }

    public void setTextTypeface(Typeface typeface) {
        Iterator<LoadingLayout> it = this.mLoadingLayouts.iterator();
        while (it.hasNext()) {
            it.next().setTextTypeface(typeface);
        }
    }
}
