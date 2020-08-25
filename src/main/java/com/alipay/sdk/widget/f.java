package com.alipay.sdk.widget;

import android.content.DialogInterface;
import android.view.KeyEvent;

final class f implements DialogInterface.OnKeyListener {
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    f() {
    }
}
