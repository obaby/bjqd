package cn.xports.qd2.shareCampaign;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import cn.xports.qd2.R;
import cn.xports.qd2.campaign.TeamInfoActivity;

public class PopuSelectShare {
    /* access modifiers changed from: private */
    public SelectLisenter lisenter;

    public interface SelectLisenter {
        void add();

        void invite();
    }

    public void init(Context context, View view) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pupu_select_share_layout, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate);
        inflate.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        inflate.findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PopuSelectShare.this.lisenter != null) {
                    PopuSelectShare.this.lisenter.add();
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
        inflate.findViewById(R.id.tv_invite).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PopuSelectShare.this.lisenter != null) {
                    PopuSelectShare.this.lisenter.invite();
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
        popupWindow.setWidth(-1);
        popupWindow.setHeight(((TeamInfoActivity) context).getWindowManager().getDefaultDisplay().getHeight());
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAsDropDown(view);
    }

    public void setOnSelectLisenter(SelectLisenter selectLisenter) {
        this.lisenter = selectLisenter;
    }
}
