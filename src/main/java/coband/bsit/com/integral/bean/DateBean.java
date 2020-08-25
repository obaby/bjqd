package coband.bsit.com.integral.bean;

public class DateBean {
    private String[] lunar;
    private String lunarHoliday;
    private int[] solar;
    private String solarHoliday;
    private String term;
    private int type;

    public int[] getSolar() {
        return this.solar;
    }

    public void setSolar(int i, int i2, int i3) {
        this.solar = new int[]{i, i2, i3};
    }

    public String[] getLunar() {
        return this.lunar;
    }

    public void setLunar(String[] strArr) {
        this.lunar = strArr;
    }

    public String getSolarHoliday() {
        return this.solarHoliday;
    }

    public void setSolarHoliday(String str) {
        this.solarHoliday = str;
    }

    public String getLunarHoliday() {
        return this.lunarHoliday;
    }

    public void setLunarHoliday(String str) {
        this.lunarHoliday = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getTerm() {
        return this.term;
    }

    public void setTerm(String str) {
        this.term = str;
    }
}
