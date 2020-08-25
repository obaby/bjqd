package cn.xports.photo_lib.widget.crop.scrollerproxy;

import android.content.Context;

public class IcsScroller extends GingerScroller {
    public IcsScroller(Context context) {
        super(context);
    }

    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }
}
