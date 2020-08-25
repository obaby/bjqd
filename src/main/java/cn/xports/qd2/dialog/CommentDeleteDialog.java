package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;

public class CommentDeleteDialog extends BaseBottomDialog {
    public CommentDeleteDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_comment_delete);
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommentDeleteDialog.this.dismiss();
            }
        });
        findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommentDeleteDialog.this.performClick(R.id.tv_delete, new Object[0]);
                CommentDeleteDialog.this.dismiss();
            }
        });
    }
}
