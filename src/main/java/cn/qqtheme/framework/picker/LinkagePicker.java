package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import cn.qqtheme.framework.util.LogUtils;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;
import java.util.List;

public class LinkagePicker extends WheelPicker {
    protected ArrayList<String> firstList;
    protected OnLinkageListener onLinkageListener;
    protected boolean onlyTwo;
    protected ArrayList<ArrayList<String>> secondList;
    protected int selectedFirstIndex;
    protected String selectedFirstText;
    protected int selectedSecondIndex;
    protected String selectedSecondText;
    protected int selectedThirdIndex;
    protected String selectedThirdText;
    protected ArrayList<ArrayList<ArrayList<String>>> thirdList;

    public interface OnLinkageListener {
        void onPicked(String str, String str2, String str3);
    }

    public LinkagePicker(Activity activity) {
        super(activity);
        this.firstList = new ArrayList<>();
        this.secondList = new ArrayList<>();
        this.thirdList = new ArrayList<>();
        this.selectedFirstText = "";
        this.selectedSecondText = "";
        this.selectedThirdText = "";
        this.selectedFirstIndex = 0;
        this.selectedSecondIndex = 0;
        this.selectedThirdIndex = 0;
        this.onlyTwo = false;
    }

    public LinkagePicker(Activity activity, ArrayList<String> arrayList, ArrayList<ArrayList<String>> arrayList2) {
        this(activity, arrayList, arrayList2, (ArrayList<ArrayList<ArrayList<String>>>) null);
    }

    public LinkagePicker(Activity activity, ArrayList<String> arrayList, ArrayList<ArrayList<String>> arrayList2, ArrayList<ArrayList<ArrayList<String>>> arrayList3) {
        super(activity);
        this.firstList = new ArrayList<>();
        this.secondList = new ArrayList<>();
        this.thirdList = new ArrayList<>();
        this.selectedFirstText = "";
        this.selectedSecondText = "";
        this.selectedThirdText = "";
        this.selectedFirstIndex = 0;
        this.selectedSecondIndex = 0;
        this.selectedThirdIndex = 0;
        this.onlyTwo = false;
        this.firstList = arrayList;
        this.secondList = arrayList2;
        if (arrayList3 == null || arrayList3.size() == 0) {
            this.onlyTwo = true;
        } else {
            this.thirdList = arrayList3;
        }
    }

    public void setSelectedItem(String str, String str2) {
        setSelectedItem(str, str2, "");
    }

    public void setSelectedItem(String str, String str2, String str3) {
        int i = 0;
        while (true) {
            if (i >= this.firstList.size()) {
                break;
            }
            String str4 = this.firstList.get(i);
            if (str4.contains(str)) {
                this.selectedFirstIndex = i;
                LogUtils.debug("init select first text: " + str4 + ", index:" + this.selectedFirstIndex);
                break;
            }
            i++;
        }
        ArrayList arrayList = this.secondList.get(this.selectedFirstIndex);
        int i2 = 0;
        while (true) {
            if (i2 >= arrayList.size()) {
                break;
            }
            String str5 = (String) arrayList.get(i2);
            if (str5.contains(str2)) {
                this.selectedSecondIndex = i2;
                LogUtils.debug("init select second text: " + str5 + ", index:" + this.selectedSecondIndex);
                break;
            }
            i2++;
        }
        if (!TextUtils.isEmpty(str3) && this.thirdList.size() != 0) {
            ArrayList arrayList2 = (ArrayList) this.thirdList.get(this.selectedFirstIndex).get(this.selectedSecondIndex);
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                String str6 = (String) arrayList2.get(i3);
                if (str6.contains(str3)) {
                    this.selectedThirdIndex = i3;
                    LogUtils.debug("init select third text: " + str6 + ", index:" + this.selectedThirdIndex);
                    return;
                }
            }
        }
    }

    public void setOnLinkageListener(OnLinkageListener onLinkageListener2) {
        this.onLinkageListener = onLinkageListener2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public View makeCenterView() {
        if (this.firstList.size() == 0 || this.secondList.size() == 0) {
            throw new IllegalArgumentException("please initial data at first, can't be empty");
        }
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity);
        int i = this.screenWidthPixels / 3;
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        wheelView.setOffset(this.offset);
        linearLayout.addView(wheelView);
        final WheelView wheelView2 = new WheelView(this.activity);
        wheelView2.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        wheelView2.setTextSize(this.textSize);
        wheelView2.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView2.setLineVisible(this.lineVisible);
        wheelView2.setLineColor(this.lineColor);
        wheelView2.setOffset(this.offset);
        linearLayout.addView(wheelView2);
        final WheelView wheelView3 = new WheelView(this.activity);
        wheelView3.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        wheelView3.setTextSize(this.textSize);
        wheelView3.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView3.setLineVisible(this.lineVisible);
        wheelView3.setLineColor(this.lineColor);
        wheelView3.setOffset(this.offset);
        linearLayout.addView(wheelView3);
        if (this.onlyTwo) {
            wheelView3.setVisibility(8);
        }
        wheelView.setItems((List<String>) this.firstList, this.selectedFirstIndex);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                LinkagePicker.this.selectedFirstText = str;
                LinkagePicker.this.selectedFirstIndex = i;
                int i2 = 0;
                LinkagePicker.this.selectedThirdIndex = 0;
                wheelView2.setItems((List<String>) LinkagePicker.this.secondList.get(LinkagePicker.this.selectedFirstIndex), z ? 0 : LinkagePicker.this.selectedSecondIndex);
                if (LinkagePicker.this.thirdList.size() != 0) {
                    WheelView wheelView = wheelView3;
                    List list = (List) LinkagePicker.this.thirdList.get(LinkagePicker.this.selectedFirstIndex).get(0);
                    if (!z) {
                        i2 = LinkagePicker.this.selectedThirdIndex;
                    }
                    wheelView.setItems((List<String>) list, i2);
                }
            }
        });
        wheelView2.setItems((List<String>) this.secondList.get(this.selectedFirstIndex), this.selectedSecondIndex);
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                LinkagePicker.this.selectedSecondText = str;
                LinkagePicker.this.selectedSecondIndex = i;
                if (LinkagePicker.this.thirdList.size() != 0) {
                    wheelView3.setItems((List<String>) (List) LinkagePicker.this.thirdList.get(LinkagePicker.this.selectedFirstIndex).get(LinkagePicker.this.selectedSecondIndex), z ? 0 : LinkagePicker.this.selectedThirdIndex);
                }
            }
        });
        if (this.thirdList.size() == 0) {
            return linearLayout;
        }
        wheelView3.setItems((List<String>) (List) this.thirdList.get(this.selectedFirstIndex).get(this.selectedSecondIndex), this.selectedThirdIndex);
        wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                LinkagePicker.this.selectedThirdText = str;
                LinkagePicker.this.selectedThirdIndex = i;
            }
        });
        return linearLayout;
    }

    public void onSubmit() {
        if (this.onLinkageListener == null) {
            return;
        }
        if (this.onlyTwo) {
            this.onLinkageListener.onPicked(this.selectedFirstText, this.selectedSecondText, (String) null);
        } else {
            this.onLinkageListener.onPicked(this.selectedFirstText, this.selectedSecondText, this.selectedThirdText);
        }
    }
}
