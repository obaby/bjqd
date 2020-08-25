package cn.xports.qd2.adapter;

import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import java.util.List;

public class CourseResvAdapter extends XBaseAdapter<DataMap> {
    public CourseResvAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_course_appoint;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0046, code lost:
        if (r0.equals("3") != false) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull cn.xports.baselib.adapter.XBaseHolder r12, int r13) {
        /*
            r11 = this;
            java.util.List r0 = r11.list
            java.lang.Object r0 = r0.get(r13)
            r3 = r0
            cn.xports.baselib.bean.DataMap r3 = (cn.xports.baselib.bean.DataMap) r3
            int r0 = cn.xports.qd2.R.id.tv_has_appoint
            java.lang.String r1 = "reserveId"
            java.lang.String r1 = r3.getString(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 1
            r1 = r1 ^ r2
            r12.setVisible(r0, r1)
            java.lang.String r0 = "lessonType"
            java.lang.String r0 = r3.getString(r0)
            java.lang.String r1 = ""
            r4 = -1
            int r5 = r0.hashCode()
            r6 = 2
            switch(r5) {
                case 49: goto L_0x0049;
                case 50: goto L_0x002b;
                case 51: goto L_0x0040;
                case 52: goto L_0x0036;
                case 53: goto L_0x002c;
                default: goto L_0x002b;
            }
        L_0x002b:
            goto L_0x0053
        L_0x002c:
            java.lang.String r2 = "5"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0053
            r2 = 3
            goto L_0x0054
        L_0x0036:
            java.lang.String r2 = "4"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0053
            r2 = 2
            goto L_0x0054
        L_0x0040:
            java.lang.String r5 = "3"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x0053
            goto L_0x0054
        L_0x0049:
            java.lang.String r2 = "1"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0053
            r2 = 0
            goto L_0x0054
        L_0x0053:
            r2 = -1
        L_0x0054:
            switch(r2) {
                case 0: goto L_0x0064;
                case 1: goto L_0x0060;
                case 2: goto L_0x005c;
                case 3: goto L_0x0058;
                default: goto L_0x0057;
            }
        L_0x0057:
            goto L_0x0067
        L_0x0058:
            java.lang.String r1 = "学校课"
            goto L_0x0067
        L_0x005c:
            java.lang.String r1 = "私教课"
            goto L_0x0067
        L_0x0060:
            java.lang.String r1 = "教练课"
            goto L_0x0067
        L_0x0064:
            java.lang.String r1 = "公共课"
        L_0x0067:
            java.lang.String r0 = "coachName"
            java.lang.String r5 = r3.getString(r0)
            java.lang.String r0 = "courseName"
            java.lang.String r7 = r3.getString(r0)
            int r0 = cn.xports.qd2.R.id.tv_type
            r12.setText(r0, r1)
            java.lang.String r0 = "startTime"
            java.lang.String r1 = "0000"
            java.lang.String r0 = r3.getString(r0, r1)
            java.lang.String r1 = "endTime"
            java.lang.String r2 = "0000"
            java.lang.String r1 = r3.getString(r1, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            java.lang.String r4 = ":"
            java.lang.StringBuilder r2 = r2.insert(r6, r4)
            java.lang.String r4 = r2.toString()
            int r2 = cn.xports.qd2.R.id.tv_start_time
            r12.setText(r2, r4)
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r6 = "HHmm"
            r2.<init>(r6)
            r6 = 60000(0xea60, float:8.4078E-41)
            long r0 = com.blankj.utilcode.util.TimeUtils.getTimeSpan(r1, r0, r2, r6)
            int r0 = (int) r0
            int r1 = cn.xports.qd2.R.id.tv_course_long
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "分钟"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r12.setText(r1, r0)
            int r0 = cn.xports.qd2.R.id.tv_name
            r12.setText(r0, r5)
            int r0 = cn.xports.qd2.R.id.tv_course_name
            r12.setText(r0, r7)
            int r0 = cn.xports.qd2.R.id.iv_header
            android.view.View r0 = r12.getView(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            android.content.Context r1 = r0.getContext()
            java.lang.String r2 = "image"
            java.lang.String r2 = r3.getString(r2)
            int r6 = cn.xports.qd2.R.drawable.ic_person_default_round
            com.bumptech.glide.RequestBuilder r1 = cn.xports.baselib.util.GlideUtil.loadImage(r1, r2, r6)
            r1.into((android.widget.ImageView) r0)
            java.lang.String r0 = "lessonDate"
            java.lang.String r1 = "0000000-00"
            java.lang.String r0 = r3.getString(r0, r1)
            r1 = 5
            r2 = 10
            java.lang.String r0 = r0.substring(r1, r2)
            java.lang.String r1 = "-"
            java.lang.String r2 = "/"
            java.lang.String r6 = r0.replace(r1, r2)
            android.view.View r0 = r12.itemView
            cn.xports.qd2.adapter.CourseResvAdapter$1 r10 = new cn.xports.qd2.adapter.CourseResvAdapter$1
            r1 = r10
            r2 = r11
            r8 = r12
            r9 = r13
            r1.<init>(r3, r4, r5, r6, r7, r8, r9)
            r0.setOnClickListener(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.adapter.CourseResvAdapter.onBindViewHolder(cn.xports.baselib.adapter.XBaseHolder, int):void");
    }
}
