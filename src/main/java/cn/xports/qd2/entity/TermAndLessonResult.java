package cn.xports.qd2.entity;

import android.text.TextUtils;
import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class TermAndLessonResult extends Response implements Serializable {
    private String chooseClassPlace;
    private String chooseClassTime;
    private String chooseDays;
    private String classTag;
    private String lessonNum;
    private List<TcPlace> placeList;
    private List<SubjectPrice> subjectPriceList;
    private String subjectTag;
    private String sysdate;
    private List<TcLongLesson> tcLongLessonList;
    private List<TcTermLesson> tcTermList;
    private String validPeriod;

    public String getSubjectTag() {
        return this.subjectTag;
    }

    public void setSubjectTag(String str) {
        this.subjectTag = str;
    }

    public List<SubjectPrice> getSubjectPriceList() {
        return this.subjectPriceList;
    }

    public void setSubjectPriceList(List<SubjectPrice> list) {
        this.subjectPriceList = list;
    }

    public String getClassTag() {
        return this.classTag;
    }

    public void setClassTag(String str) {
        this.classTag = str;
    }

    public String getSysdate() {
        return this.sysdate;
    }

    public void setSysdate(String str) {
        this.sysdate = str;
    }

    public int getChooseDays() {
        if (TextUtils.isEmpty(this.chooseDays)) {
            return 0;
        }
        return Integer.valueOf(this.chooseDays).intValue();
    }

    public void setChooseDays(String str) {
        this.chooseDays = str;
    }

    public void setChooseDays(int i) {
        this.chooseDays = String.valueOf(i);
    }

    public String getValidPeriod() {
        return this.validPeriod;
    }

    public void setValidPeriod(String str) {
        this.validPeriod = str;
    }

    public List<TcTermLesson> getTcTermList() {
        return this.tcTermList;
    }

    public void setTcTermList(List<TcTermLesson> list) {
        this.tcTermList = list;
    }

    public String getChooseClassTime() {
        return this.chooseClassTime;
    }

    public void setChooseClassTime(String str) {
        this.chooseClassTime = str;
    }

    public String getChooseClassPlace() {
        return this.chooseClassPlace;
    }

    public void setChooseClassPlace(String str) {
        this.chooseClassPlace = str;
    }

    public String getLessonNum() {
        if (TextUtils.isEmpty(this.lessonNum)) {
            return K.k0;
        }
        return this.lessonNum;
    }

    public void setLessonNum(String str) {
        this.lessonNum = str;
    }

    public List<TcLongLesson> getTcLongLessonList() {
        return this.tcLongLessonList;
    }

    public void setTcLongLessonList(List<TcLongLesson> list) {
        this.tcLongLessonList = list;
    }

    public List<TcPlace> getPlaceList() {
        return this.placeList;
    }

    public void setPlaceList(List<TcPlace> list) {
        this.placeList = list;
    }

    public boolean isShowPlace() {
        return TextUtils.equals("1", this.chooseClassPlace);
    }

    public boolean isShowLessonTime() {
        return TextUtils.equals("1", this.chooseClassTime);
    }

    public static class TcLongLesson implements Serializable {
        public static final int CHECKED = 1;
        public static final int FORBIDDEN = -1;
        public static final int NORMAL = 0;
        private int checkState;
        private String endTime;
        private String formatTime;
        private String longLessonId;
        private int outLessonNum;
        private String placeId;
        private String placeName;
        private String serviceName;
        private String startTime;
        private String state;
        private String subjectName;
        private String trialTag;
        private String weekday;

        public String getTrialTag() {
            return this.trialTag;
        }

        public void setTrialTag(String str) {
            this.trialTag = str;
        }

        public String getLongLessonId() {
            return this.longLessonId;
        }

        public void setLongLessonId(String str) {
            this.longLessonId = str;
        }

        public String getWeekday() {
            return this.weekday;
        }

        public void setWeekday(String str) {
            this.weekday = str;
        }

        public String getPlaceId() {
            return this.placeId;
        }

        public void setPlaceId(String str) {
            this.placeId = str;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getServiceName() {
            return this.serviceName;
        }

        public void setServiceName(String str) {
            this.serviceName = str;
        }

        public String getPlaceName() {
            return this.placeName;
        }

        public void setPlaceName(String str) {
            this.placeName = str;
        }

        public String getSubjectName() {
            return this.subjectName;
        }

        public void setSubjectName(String str) {
            this.subjectName = str;
        }

        public String getFormatTime() {
            return this.formatTime;
        }

        public void setFormatTime(String str) {
            this.formatTime = str;
        }

        public int getCheckState() {
            return this.checkState;
        }

        public void setCheckState(int i) {
            this.checkState = i;
        }

        public int getOutLessonNum() {
            return this.outLessonNum;
        }

        public void setOutLessonNum(int i) {
            this.outLessonNum = i;
        }

        public boolean isForbidden() {
            return this.checkState == -1;
        }

        public boolean isChecked() {
            return this.checkState == 1;
        }

        public boolean isNormal() {
            return this.checkState == 0;
        }

        public void resetState() {
            this.checkState = 0;
        }
    }

    public static class TcPlace implements Serializable {
        private boolean isChecked;
        private String placeId;
        private String placeName;

        public String getPlaceName() {
            return this.placeName;
        }

        public void setPlaceName(String str) {
            this.placeName = str;
        }

        public String getPlaceId() {
            return this.placeId;
        }

        public void setPlaceId(String str) {
            this.placeId = str;
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
        }
    }

    public static class TcTermLesson implements Serializable {
        private String centerId;
        private String courseId;
        private String createTime;
        private String endDate;
        private int enrollNum;
        private boolean isChecked;
        private int maxNum;
        private String startDate;
        private String state;
        private String termId;
        private String termName;

        public String getCenterId() {
            return this.centerId;
        }

        public void setCenterId(String str) {
            this.centerId = str;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public void setEndDate(String str) {
            this.endDate = str;
        }

        public int getMaxNum() {
            return this.maxNum;
        }

        public void setMaxNum(int i) {
            this.maxNum = i;
        }

        public String getTermId() {
            return this.termId;
        }

        public void setTermId(String str) {
            this.termId = str;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public int getEnrollNum() {
            return this.enrollNum;
        }

        public void setEnrollNum(int i) {
            this.enrollNum = i;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getTermName() {
            return this.termName;
        }

        public void setTermName(String str) {
            this.termName = str;
        }

        public String getCourseId() {
            return this.courseId;
        }

        public void setCourseId(String str) {
            this.courseId = str;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
        }
    }

    public static class SubjectPrice implements Serializable {
        private int basePrice;
        private String lessonSubjectId;
        private List<CourseSubject> packageList;
        private String subjectName;

        public String getLessonSubjectId() {
            return this.lessonSubjectId;
        }

        public void setLessonSubjectId(String str) {
            this.lessonSubjectId = str;
        }

        public String getSubjectName() {
            return this.subjectName;
        }

        public void setSubjectName(String str) {
            this.subjectName = str;
        }

        public int getBasePrice() {
            return this.basePrice;
        }

        public void setBasePrice(int i) {
            this.basePrice = i;
        }

        public List<CourseSubject> getPackageList() {
            return this.packageList;
        }

        public void setPackageList(List<CourseSubject> list) {
            this.packageList = list;
        }
    }

    public static class CourseSubject implements Serializable {
        private int lessonNum;
        private String lessonSubjectId;
        private int price;
        private String priceId;
        private String type;

        public String getLessonSubjectId() {
            return this.lessonSubjectId;
        }

        public void setLessonSubjectId(String str) {
            this.lessonSubjectId = str;
        }

        public int getLessonNum() {
            return this.lessonNum;
        }

        public void setLessonNum(int i) {
            this.lessonNum = i;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public int getPrice() {
            return this.price;
        }

        public void setPrice(int i) {
            this.price = i;
        }

        public String getPriceId() {
            return this.priceId;
        }

        public void setPriceId(String str) {
            this.priceId = str;
        }
    }
}
