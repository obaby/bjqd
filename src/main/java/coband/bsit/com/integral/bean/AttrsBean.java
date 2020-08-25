package coband.bsit.com.integral.bean;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import coband.bsit.com.integral.R;
import java.util.List;
import java.util.Map;

public class AttrsBean {
    private int chooseType = 0;
    private int colorChoose = 0;
    private int colorHoliday = Color.parseColor("#EC9729");
    private int colorLunar = Color.parseColor("#999999");
    private int colorSolar = ViewCompat.MEASURED_STATE_MASK;
    private int dayBg = R.mipmap.integral_icon_jinbi;
    private int[] disableEndDate;
    private int[] disableStartDate;
    private int[] endDate;
    private List<int[]> multiDates;
    private boolean showHoliday = true;
    private boolean showLastNext = true;
    private boolean showLunar = true;
    private boolean showTerm = true;
    private int[] singleDate;
    private int sizeLunar = 8;
    private int sizeSolar = 16;
    private Map<String, String> specifyMap;
    private int[] startDate;
    private boolean switchChoose = true;

    public int[] getStartDate() {
        return this.startDate;
    }

    public void setStartDate(int[] iArr) {
        this.startDate = iArr;
    }

    public int[] getEndDate() {
        return this.endDate;
    }

    public void setEndDate(int[] iArr) {
        this.endDate = iArr;
    }

    public int[] getSingleDate() {
        return this.singleDate;
    }

    public void setSingleDate(int[] iArr) {
        this.singleDate = iArr;
    }

    public List<int[]> getMultiDates() {
        return this.multiDates;
    }

    public int[] getDisableStartDate() {
        return this.disableStartDate;
    }

    public void setDisableStartDate(int[] iArr) {
        this.disableStartDate = iArr;
    }

    public int[] getDisableEndDate() {
        return this.disableEndDate;
    }

    public void setDisableEndDate(int[] iArr) {
        this.disableEndDate = iArr;
    }

    public void setMultiDates(List<int[]> list) {
        this.multiDates = list;
    }

    public boolean isShowLastNext() {
        return this.showLastNext;
    }

    public void setShowLastNext(boolean z) {
        this.showLastNext = z;
    }

    public boolean isShowLunar() {
        return this.showLunar;
    }

    public void setShowLunar(boolean z) {
        this.showLunar = z;
    }

    public boolean isShowHoliday() {
        return this.showHoliday;
    }

    public void setShowHoliday(boolean z) {
        this.showHoliday = z;
    }

    public boolean isShowTerm() {
        return this.showTerm;
    }

    public void setShowTerm(boolean z) {
        this.showTerm = z;
    }

    public boolean isSwitchChoose() {
        return this.switchChoose;
    }

    public void setSwitchChoose(boolean z) {
        this.switchChoose = z;
    }

    public int getColorSolar() {
        return this.colorSolar;
    }

    public void setColorSolar(int i) {
        this.colorSolar = i;
    }

    public int getColorLunar() {
        return this.colorLunar;
    }

    public void setColorLunar(int i) {
        this.colorLunar = i;
    }

    public int getColorHoliday() {
        return this.colorHoliday;
    }

    public void setColorHoliday(int i) {
        this.colorHoliday = i;
    }

    public int getColorChoose() {
        return this.colorChoose;
    }

    public void setColorChoose(int i) {
        this.colorChoose = i;
    }

    public int getSizeSolar() {
        return this.sizeSolar;
    }

    public void setSizeSolar(int i) {
        this.sizeSolar = i;
    }

    public int getSizeLunar() {
        return this.sizeLunar;
    }

    public void setSizeLunar(int i) {
        this.sizeLunar = i;
    }

    public int getDayBg() {
        return this.dayBg;
    }

    public void setDayBg(int i) {
        this.dayBg = i;
    }

    public Map<String, String> getSpecifyMap() {
        return this.specifyMap;
    }

    public void setSpecifyMap(Map<String, String> map) {
        this.specifyMap = map;
    }

    public int getChooseType() {
        return this.chooseType;
    }

    public void setChooseType(int i) {
        this.chooseType = i;
    }
}
