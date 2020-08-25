package cn.xports.qd2.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import cn.xports.qd2.entity.EnrollElementInfo;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.entity.ViewElementData;

public abstract class ElementView extends LinearLayout {
    protected Context context;
    protected ViewElementData elementData;

    public abstract void clear();

    public abstract String getValue();

    /* access modifiers changed from: protected */
    public abstract void init();

    public abstract void setEnable(boolean z);

    public abstract void setShowValue(String str);

    public abstract void showLine(boolean z);

    public ElementView(ViewElementData viewElementData, Context context2) {
        super(context2);
        this.context = context2;
        this.elementData = viewElementData;
        if (viewElementData != null) {
            init();
        }
    }

    public static boolean checkInput(LinearLayout linearLayout) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            if ((childAt instanceof ElementView) && !((ElementView) childAt).canSubmit()) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.xports.qd2.entity.MemberInfo createMemberInfo(android.widget.LinearLayout r8, cn.xports.qd2.entity.MemberInfo r9) {
        /*
            boolean r0 = checkInput(r8)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            cn.xports.qd2.entity.MemberInfo r0 = new cn.xports.qd2.entity.MemberInfo
            r0.<init>()
            if (r9 == 0) goto L_0x0010
            goto L_0x0011
        L_0x0010:
            r9 = r0
        L_0x0011:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = 0
            r3 = 0
        L_0x0018:
            int r4 = r8.getChildCount()
            if (r3 >= r4) goto L_0x00e1
            android.view.View r4 = r8.getChildAt(r3)
            boolean r5 = r4 instanceof cn.xports.qd2.widget.ElementView
            if (r5 == 0) goto L_0x00dd
            cn.xports.qd2.widget.ElementView r4 = (cn.xports.qd2.widget.ElementView) r4
            java.lang.String r5 = r4.getInputTypeKey()
            r6 = -1
            int r7 = r5.hashCode()
            switch(r7) {
                case -1244789649: goto L_0x0071;
                case -310821915: goto L_0x0067;
                case -139323735: goto L_0x005d;
                case -139277431: goto L_0x0053;
                case 35635375: goto L_0x0049;
                case 1106754474: goto L_0x003f;
                case 2053373326: goto L_0x0035;
                default: goto L_0x0034;
            }
        L_0x0034:
            goto L_0x007b
        L_0x0035:
            java.lang.String r7 = "$PSPT_TYPE"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 3
            goto L_0x007c
        L_0x003f:
            java.lang.String r7 = "$PHONE"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 2
            goto L_0x007c
        L_0x0049:
            java.lang.String r7 = "$NAME"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 1
            goto L_0x007c
        L_0x0053:
            java.lang.String r7 = "$TEAM_NAME"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 0
            goto L_0x007c
        L_0x005d:
            java.lang.String r7 = "$TEAM_LOGO"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 6
            goto L_0x007c
        L_0x0067:
            java.lang.String r7 = "$GENDER"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 5
            goto L_0x007c
        L_0x0071:
            java.lang.String r7 = "$PSPT_ID"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x007b
            r5 = 4
            goto L_0x007c
        L_0x007b:
            r5 = -1
        L_0x007c:
            switch(r5) {
                case 0: goto L_0x00d6;
                case 1: goto L_0x00ce;
                case 2: goto L_0x00c6;
                case 3: goto L_0x00be;
                case 4: goto L_0x00b6;
                case 5: goto L_0x00ae;
                case 6: goto L_0x00a6;
                default: goto L_0x007f;
            }
        L_0x007f:
            java.lang.String r5 = r4.getValue()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x00dd
            cn.xports.qd2.entity.EnrollElementInfo r5 = new cn.xports.qd2.entity.EnrollElementInfo
            r5.<init>()
            cn.xports.qd2.entity.ViewElementData r6 = r4.getElementData()
            java.lang.String r6 = r6.getElementId()
            cn.xports.qd2.entity.EnrollElementInfo r5 = r5.setElementId(r6)
            java.lang.String r4 = r4.getValue()
            cn.xports.qd2.entity.EnrollElementInfo r4 = r5.setValue(r4)
            r0.add(r4)
            goto L_0x00dd
        L_0x00a6:
            java.lang.String r4 = r4.getValue()
            r9.setTeamLogo(r4)
            goto L_0x00dd
        L_0x00ae:
            java.lang.String r4 = r4.getValue()
            r9.setGender(r4)
            goto L_0x00dd
        L_0x00b6:
            java.lang.String r4 = r4.getValue()
            r9.setPsptId(r4)
            goto L_0x00dd
        L_0x00be:
            java.lang.String r4 = r4.getValue()
            r9.setPsptType(r4)
            goto L_0x00dd
        L_0x00c6:
            java.lang.String r4 = r4.getValue()
            r9.setMobileNum(r4)
            goto L_0x00dd
        L_0x00ce:
            java.lang.String r4 = r4.getValue()
            r9.setName(r4)
            goto L_0x00dd
        L_0x00d6:
            java.lang.String r4 = r4.getValue()
            r9.setTeamName(r4)
        L_0x00dd:
            int r3 = r3 + 1
            goto L_0x0018
        L_0x00e1:
            java.lang.String r8 = "0"
            java.lang.String r2 = r9.getPsptType()
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x00fe
            java.lang.String r8 = r9.getPsptId()
            boolean r8 = cn.xports.qd2.util.IDCardUtil.checkIDCard(r8)
            if (r8 != 0) goto L_0x00fe
            java.lang.String r8 = "请输入正确的身份证号 "
            com.blankj.utilcode.util.ToastUtils.showShort(r8)
            return r1
        L_0x00fe:
            r9.setEnrollInfoList(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.widget.ElementView.createMemberInfo(android.widget.LinearLayout, cn.xports.qd2.entity.MemberInfo):cn.xports.qd2.entity.MemberInfo");
    }

    public static ElementView createElementView(Context context2, ViewElementData viewElementData) {
        if (viewElementData == null) {
            return null;
        }
        if (viewElementData.getInputType() == null) {
            return new TextInputView(viewElementData, context2);
        }
        String inputType = viewElementData.getInputType();
        if (inputType.equals("$PSPT_TYPE") || inputType.equals("$GENDER") || inputType.equals("RADIO")) {
            return new SelectElementView(viewElementData, context2);
        }
        if (inputType.equals("CHECKBOX")) {
            return new SelectElementView(viewElementData, context2).setSingle(false);
        }
        if (inputType.equals("DATE")) {
            return new TimePickElementView(viewElementData, context2);
        }
        if (inputType.equals("PICTURE") || inputType.equals("$TEAM_LOGO")) {
            return new ImagePickElementView(viewElementData, context2);
        }
        return new TextInputView(viewElementData, context2);
    }

    public static ElementView createElementView(Context context2, ViewElementData viewElementData, MemberInfo memberInfo) {
        ElementView elementView;
        if (viewElementData == null) {
            return null;
        }
        if (viewElementData.getInputType() == null) {
            return new TextInputView(viewElementData, context2);
        }
        String inputType = viewElementData.getInputType();
        if (inputType.equals("$PSPT_TYPE") || inputType.equals("$GENDER") || inputType.equals("RADIO")) {
            elementView = new SelectElementView(viewElementData, context2);
        } else if (inputType.equals("CHECKBOX")) {
            elementView = new SelectElementView(viewElementData, context2).setSingle(false);
        } else if (inputType.equals("DATE")) {
            elementView = new TimePickElementView(viewElementData, context2);
        } else if (inputType.equals("PICTURE") || inputType.equals("$TEAM_LOGO")) {
            elementView = new ImagePickElementView(viewElementData, context2);
        } else {
            elementView = new TextInputView(viewElementData, context2);
        }
        if (memberInfo == null) {
            return elementView;
        }
        if (inputType.equals("$PSPT_TYPE")) {
            elementView.setShowValue(memberInfo.getPsptType());
            return elementView;
        } else if (inputType.equals("$GENDER")) {
            elementView.setShowValue(memberInfo.getGender());
            return elementView;
        } else if (inputType.equals("$TEAM_NAME")) {
            elementView.setShowValue(memberInfo.getTeamName());
            return elementView;
        } else if (inputType.equals("$NAME")) {
            elementView.setShowValue(memberInfo.getName());
            return elementView;
        } else if (inputType.equals("$PHONE")) {
            elementView.setShowValue(memberInfo.getMobileNum());
            return elementView;
        } else if (inputType.equals("$PSPT_ID")) {
            elementView.setShowValue(memberInfo.getPsptId());
            return elementView;
        } else if (inputType.equals("$TEAM_LOGO")) {
            elementView.setShowValue(memberInfo.getTeamLogo());
            return elementView;
        } else if (memberInfo.getEnrollInfoList() == null) {
            return elementView;
        } else {
            for (EnrollElementInfo next : memberInfo.getEnrollInfoList()) {
                if (viewElementData.getElementId().equals(next.getElementId())) {
                    elementView.setShowValue(next.getValue());
                    return elementView;
                }
            }
            return elementView;
        }
    }

    public String getTitle() {
        if (this.elementData == null) {
            return "";
        }
        return this.elementData.getName();
    }

    public String getInputTypeKey() {
        if (this.elementData == null) {
            return "";
        }
        return this.elementData.getInputType();
    }

    public boolean canSubmit() {
        if (this.elementData == null) {
            return true;
        }
        return !"1".equals(this.elementData.getRequired());
    }

    public ViewElementData getElementData() {
        return this.elementData;
    }

    public EnrollElementInfo enrollInfo() {
        return new EnrollElementInfo().setElementId(this.elementData.getElementId()).setValue(getValue());
    }
}
