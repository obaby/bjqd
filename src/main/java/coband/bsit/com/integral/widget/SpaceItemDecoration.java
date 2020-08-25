package coband.bsit.com.integral.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView.getChildAdapterPosition(view) != 0) {
            rect.left = this.mSpace;
        }
    }

    public SpaceItemDecoration(int i) {
        this.mSpace = i;
    }
}
