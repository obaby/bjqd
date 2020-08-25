package cn.bingoogolapple.bgabanner;

import android.view.View;

public abstract class BGAOnNoDoubleClickListener implements View.OnClickListener {
    private long mLastClickTime = 0;
    private int mThrottleFirstTime = 1000;

    public abstract void onNoDoubleClick(View view);

    public BGAOnNoDoubleClickListener() {
    }

    public BGAOnNoDoubleClickListener(int i) {
        this.mThrottleFirstTime = i;
    }

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > ((long) this.mThrottleFirstTime)) {
            this.mLastClickTime = currentTimeMillis;
            onNoDoubleClick(view);
        }
    }
}
