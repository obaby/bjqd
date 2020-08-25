package com.bsit.pulltorefresh;

import android.annotation.TargetApi;
import android.util.Log;
import android.view.View;
import com.bsit.pulltorefresh.PullToRefreshBase;

@TargetApi(9)
public final class OverscrollHelper {
    static final float DEFAULT_OVERSCROLL_SCALE = 1.0f;
    static final String LOG_TAG = "OverscrollHelper";

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, boolean z) {
        overScrollBy(pullToRefreshBase, i, i2, i3, i4, 0, z);
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, boolean z) {
        overScrollBy(pullToRefreshBase, i, i2, i3, i4, i5, 0, DEFAULT_OVERSCROLL_SCALE, z);
    }

    /* renamed from: com.bsit.pulltorefresh.OverscrollHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation = new int[PullToRefreshBase.Orientation.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.bsit.pulltorefresh.PullToRefreshBase$Orientation[] r0 = com.bsit.pulltorefresh.PullToRefreshBase.Orientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation = r0
                int[] r0 = $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bsit.pulltorefresh.PullToRefreshBase$Orientation r1 = com.bsit.pulltorefresh.PullToRefreshBase.Orientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bsit.pulltorefresh.PullToRefreshBase$Orientation r1 = com.bsit.pulltorefresh.PullToRefreshBase.Orientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bsit.pulltorefresh.OverscrollHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z) {
        int i7;
        int i8;
        int i9;
        if (AnonymousClass1.$SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation[pullToRefreshBase.getPullToRefreshScrollDirection().ordinal()] != 1) {
            i8 = i4;
            i7 = pullToRefreshBase.getScrollY();
            i9 = i3;
        } else {
            i8 = i2;
            i7 = pullToRefreshBase.getScrollX();
            i9 = i;
        }
        if (pullToRefreshBase.isPullToRefreshOverScrollEnabled() && !pullToRefreshBase.isRefreshing()) {
            PullToRefreshBase.Mode mode = pullToRefreshBase.getMode();
            if (mode.permitsPullToRefresh() && !z && i9 != 0) {
                int i10 = i9 + i8;
                Log.d(LOG_TAG, "OverScroll. DeltaX: " + i + ", ScrollX: " + i2 + ", DeltaY: " + i3 + ", ScrollY: " + i4 + ", NewY: " + i10 + ", ScrollRange: " + i5 + ", CurrentScroll: " + i7);
                if (i10 < 0 - i6) {
                    if (mode.showHeaderLoadingLayout()) {
                        if (i7 == 0) {
                            pullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setHeaderScroll((int) (f * ((float) (i7 + i10))));
                    }
                } else if (i10 > i5 + i6) {
                    if (mode.showFooterLoadingLayout()) {
                        if (i7 == 0) {
                            pullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setHeaderScroll((int) (f * ((float) ((i7 + i10) - i5))));
                    }
                } else if (Math.abs(i10) <= i6 || Math.abs(i10 - i5) <= i6) {
                    pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
                }
            } else if (z && PullToRefreshBase.State.OVERSCROLLING == pullToRefreshBase.getState()) {
                pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
            }
        }
    }

    static boolean isAndroidOverScrollEnabled(View view) {
        return view.getOverScrollMode() != 2;
    }
}
