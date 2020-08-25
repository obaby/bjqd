package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;

public class ShareDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public ShareClickListener exchangeClickListener;

    public interface ShareClickListener {
        void clickWXFriendListener();

        void clickWXListener();
    }

    public ShareDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_share_wx);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        findViewById(R.id.tv_wx).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ShareDialog.this.exchangeClickListener != null) {
                    ShareDialog.this.exchangeClickListener.clickWXListener();
                }
                ShareDialog.this.dismiss();
            }
        });
        findViewById(R.id.tv_wx_friend).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ShareDialog.this.exchangeClickListener != null) {
                    ShareDialog.this.exchangeClickListener.clickWXFriendListener();
                }
                ShareDialog.this.dismiss();
            }
        });
    }

    public void setExchangeClickListener(ShareClickListener shareClickListener) {
        this.exchangeClickListener = shareClickListener;
    }
}
