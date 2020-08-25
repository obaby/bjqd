package cn.xports.qd2.adapter;

import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import java.util.List;

public class CircleMemberAdapter extends XBaseAdapter<DataMap> {
    private boolean canSelect = false;
    private String yourRole = "2";

    public CircleMemberAdapter(List<DataMap> list) {
        super(list);
    }

    public CircleMemberAdapter setYourRole(String str) {
        this.yourRole = str;
        return this;
    }

    public int getLayoutId() {
        return R.layout.item_circle_member;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0121  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull cn.xports.baselib.adapter.XBaseHolder r9, final int r10) {
        /*
            r8 = this;
            java.util.List r0 = r8.list
            java.lang.Object r0 = r0.get(r10)
            cn.xports.baselib.bean.DataMap r0 = (cn.xports.baselib.bean.DataMap) r0
            int r1 = cn.xports.qd2.R.id.ct_member_tag
            android.view.View r1 = r9.getView(r1)
            cn.xports.widget.CornerTextView r1 = (cn.xports.widget.CornerTextView) r1
            int r2 = cn.xports.qd2.R.id.iv_member_tag
            android.view.View r2 = r9.getView(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            java.lang.String r3 = "role"
            java.lang.String r3 = r0.getString(r3)
            int r4 = r3.hashCode()
            r5 = 49
            r6 = 1
            r7 = 0
            if (r4 == r5) goto L_0x0037
            r5 = 51
            if (r4 == r5) goto L_0x002d
            goto L_0x0041
        L_0x002d:
            java.lang.String r4 = "3"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0041
            r4 = 0
            goto L_0x0042
        L_0x0037:
            java.lang.String r4 = "1"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0041
            r4 = 1
            goto L_0x0042
        L_0x0041:
            r4 = -1
        L_0x0042:
            switch(r4) {
                case 0: goto L_0x0071;
                case 1: goto L_0x0050;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r4 = cn.xports.qd2.R.drawable.ic_circle_not_manager
            r2.setImageResource(r4)
            r2 = 8
            r1.setVisibility(r2)
            goto L_0x0091
        L_0x0050:
            r1.setVisibility(r7)
            java.lang.String r4 = "圈主"
            r1.setText(r4)
            java.lang.String r4 = "#fff0df"
            int r4 = android.graphics.Color.parseColor(r4)
            r1.setBgColor(r4)
            int r4 = cn.xports.qd2.R.color.yellow_ffa
            int r4 = com.blankj.utilcode.util.ColorUtils.getColor(r4)
            r1.setTextColor(r4)
            int r1 = cn.xports.qd2.R.drawable.ic_circle_master
            r2.setImageResource(r1)
            goto L_0x0091
        L_0x0071:
            r1.setVisibility(r7)
            java.lang.String r4 = "管理"
            r1.setText(r4)
            java.lang.String r4 = "#e0eaff"
            int r4 = android.graphics.Color.parseColor(r4)
            r1.setBgColor(r4)
            int r4 = cn.xports.qd2.R.color.blue_2e6
            int r4 = com.blankj.utilcode.util.ColorUtils.getColor(r4)
            r1.setTextColor(r4)
            int r1 = cn.xports.qd2.R.drawable.ic_circle_is_manager
            r2.setImageResource(r1)
        L_0x0091:
            int r1 = cn.xports.qd2.R.id.tv_name
            java.lang.String r2 = "accountName"
            java.lang.String r2 = r0.getString(r2)
            r9.setText(r1, r2)
            int r1 = cn.xports.qd2.R.id.iv_head
            android.view.View r1 = r9.getView(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            android.content.Context r2 = r1.getContext()
            java.lang.String r4 = "accountAvatar"
            java.lang.String r4 = r0.getString(r4)
            int r5 = cn.xports.qd2.R.drawable.ic_circle_default_head
            com.bumptech.glide.RequestBuilder r2 = cn.xports.baselib.util.GlideUtil.loadImage(r2, r4, r5)
            r2.into((android.widget.ImageView) r1)
            java.lang.String r1 = "1"
            java.lang.String r2 = r8.yourRole
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00cb
            int r1 = cn.xports.qd2.R.id.iv_member_tag
            cn.xports.qd2.adapter.CircleMemberAdapter$1 r2 = new cn.xports.qd2.adapter.CircleMemberAdapter$1
            r2.<init>(r3, r0, r10)
            r9.setOnClickListener(r1, r2)
        L_0x00cb:
            android.view.View r10 = r9.itemView
            com.yanzhenjie.recyclerview.swipe.SwipeMenuLayout r10 = (com.yanzhenjie.recyclerview.swipe.SwipeMenuLayout) r10
            java.lang.String r1 = r8.yourRole
            java.lang.String r2 = "2"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0121
            java.lang.String r1 = r8.yourRole
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0121
            java.lang.String r1 = "1"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0121
            boolean r1 = r8.canSelect
            if (r1 == 0) goto L_0x010c
            int r1 = cn.xports.qd2.R.id.checkbox
            r9.setVisible(r1, r6)
            int r1 = cn.xports.qd2.R.id.checkbox
            android.view.View r9 = r9.getView(r1)
            android.widget.CheckBox r9 = (android.widget.CheckBox) r9
            java.lang.String r1 = "checked"
            boolean r1 = r0.getBooleanValue(r1)
            r9.setChecked(r1)
            cn.xports.qd2.adapter.CircleMemberAdapter$2 r1 = new cn.xports.qd2.adapter.CircleMemberAdapter$2
            r1.<init>(r0)
            r9.setOnCheckedChangeListener(r1)
            goto L_0x011a
        L_0x010c:
            int r1 = cn.xports.qd2.R.id.checkbox
            r9.setVisible(r1, r7)
            java.lang.String r9 = "checked"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            r0.set(r9, r1)
        L_0x011a:
            boolean r9 = r8.canSelect
            r9 = r9 ^ r6
            r10.setSwipeEnable(r9)
            goto L_0x0132
        L_0x0121:
            int r1 = cn.xports.qd2.R.id.checkbox
            r9.setVisible(r1, r7)
            java.lang.String r9 = "checked"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            r0.set(r9, r1)
            r10.setSwipeEnable(r7)
        L_0x0132:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.adapter.CircleMemberAdapter.onBindViewHolder(cn.xports.baselib.adapter.XBaseHolder, int):void");
    }

    public String getYourRole() {
        return this.yourRole;
    }

    public boolean isCanSelect() {
        return this.canSelect;
    }

    public CircleMemberAdapter setCanSelect(boolean z) {
        this.canSelect = z;
        return this;
    }
}
