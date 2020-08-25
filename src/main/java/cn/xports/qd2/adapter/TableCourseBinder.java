package cn.xports.qd2.adapter;

import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;

public class TableCourseBinder extends XViewBinder<DataMap> {
    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_table_course;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull cn.xports.baselib.adapter.XBaseHolder r10, @android.support.annotation.NonNull cn.xports.baselib.bean.DataMap r11) {
        /*
            r9 = this;
            java.lang.String r0 = "lessonType"
            java.lang.String r0 = r11.getString(r0)
            java.lang.String r1 = ""
            int r2 = r0.hashCode()
            r3 = 0
            r4 = -1
            switch(r2) {
                case 49: goto L_0x0030;
                case 50: goto L_0x0011;
                case 51: goto L_0x0026;
                case 52: goto L_0x001c;
                case 53: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x003a
        L_0x0012:
            java.lang.String r2 = "5"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003a
            r0 = 3
            goto L_0x003b
        L_0x001c:
            java.lang.String r2 = "4"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003a
            r0 = 2
            goto L_0x003b
        L_0x0026:
            java.lang.String r2 = "3"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003a
            r0 = 1
            goto L_0x003b
        L_0x0030:
            java.lang.String r2 = "1"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003a
            r0 = 0
            goto L_0x003b
        L_0x003a:
            r0 = -1
        L_0x003b:
            switch(r0) {
                case 0: goto L_0x004b;
                case 1: goto L_0x0047;
                case 2: goto L_0x0043;
                case 3: goto L_0x003f;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x004e
        L_0x003f:
            java.lang.String r1 = "学校课"
            goto L_0x004e
        L_0x0043:
            java.lang.String r1 = "私教课"
            goto L_0x004e
        L_0x0047:
            java.lang.String r1 = "教练课"
            goto L_0x004e
        L_0x004b:
            java.lang.String r1 = "公共课"
        L_0x004e:
            int r0 = cn.xports.qd2.R.id.tv_type_name
            r10.setText(r0, r1)
            int r0 = cn.xports.qd2.R.id.tv_subject_name
            java.lang.String r1 = "subjectName"
            java.lang.String r1 = r11.getString(r1)
            r10.setText(r0, r1)
            int r0 = cn.xports.qd2.R.id.ll_points
            android.view.View r0 = r10.getView(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r0.removeAllViews()
            java.lang.String r1 = "studentIds"
            java.lang.String r1 = r11.getString(r1)
            java.lang.String r2 = ","
            java.lang.String[] r1 = r1.split(r2)
            java.lang.String r2 = "stuColorMap"
            cn.xports.baselib.bean.DataMap r11 = r11.getDataMap(r2)
            int r2 = r1.length
            if (r2 <= 0) goto L_0x00b3
            if (r11 == 0) goto L_0x00b3
            int r2 = r1.length
            r5 = 0
        L_0x0082:
            if (r5 >= r2) goto L_0x00b3
            r6 = r1[r5]
            boolean r7 = r11.hasKey(r6)
            if (r7 == 0) goto L_0x00b0
            android.view.View r7 = r10.itemView
            android.content.Context r7 = r7.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r8 = cn.xports.qd2.R.layout.course_round_8dp
            android.view.View r7 = r7.inflate(r8, r0, r3)
            int r8 = cn.xports.qd2.R.id.cr_round
            android.view.View r8 = r7.findViewById(r8)
            java.lang.String r6 = r11.getString(r6)
            int r6 = android.graphics.Color.parseColor(r6)
            r8.setBackgroundColor(r6)
            r0.addView(r7)
        L_0x00b0:
            int r5 = r5 + 1
            goto L_0x0082
        L_0x00b3:
            int r11 = r0.getChildCount()
            if (r11 <= 0) goto L_0x00cf
            int r11 = cn.xports.qd2.R.id.cr_table_course
            int r0 = cn.xports.qd2.R.color.blue_2e6
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
            r10.setBackgroundColor(r11, r0)
            int r11 = cn.xports.qd2.R.id.tv_type_name
            r10.setTextColor(r11, r4)
            int r11 = cn.xports.qd2.R.id.tv_subject_name
            r10.setTextColor(r11, r4)
            goto L_0x00ea
        L_0x00cf:
            int r11 = cn.xports.qd2.R.id.cr_table_course
            r10.setBackgroundColor(r11, r4)
            int r11 = cn.xports.qd2.R.id.tv_type_name
            int r0 = cn.xports.qd2.R.color.second_text_color
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
            r10.setTextColor(r11, r0)
            int r11 = cn.xports.qd2.R.id.tv_subject_name
            int r0 = cn.xports.qd2.R.color.main_text_color
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
            r10.setTextColor(r11, r0)
        L_0x00ea:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.adapter.TableCourseBinder.onBindViewHolder(cn.xports.baselib.adapter.XBaseHolder, cn.xports.baselib.bean.DataMap):void");
    }
}
