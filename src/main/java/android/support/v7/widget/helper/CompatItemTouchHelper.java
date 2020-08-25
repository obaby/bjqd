package android.support.v7.widget.helper;

import android.support.v7.widget.helper.ItemTouchHelper;

public class CompatItemTouchHelper extends ItemTouchHelper {
    public CompatItemTouchHelper(ItemTouchHelper.Callback callback) {
        super(callback);
    }

    public ItemTouchHelper.Callback getCallback() {
        return this.mCallback;
    }
}
