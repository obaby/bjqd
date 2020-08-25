package cn.xports.qd2.entity;

import android.text.TextUtils;
import cn.xports.qd2.entity.CampItemListResult;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CampItem implements Serializable {
    private long campId;
    private String demand;
    private String description;
    private String enrollEndDate;
    private int enrollFee;
    private String enrollStartDate;
    private String enrollType;
    private String enrolledNum;
    private String enrolledTag;
    private long id;
    private long itemGroupId;
    private String maxGroupNum;
    private String maxPersonNum;
    private String minPersonNum;
    private String name;
    private String noBasicEnrollFee;
    private List<CampItemListResult.CampPackage> packages;
    private String smsTag;
    private String state;

    public List<CampItemListResult.CampPackage> getPackages() {
        if (this.packages == null) {
            this.packages = new LinkedList();
        }
        return this.packages;
    }

    public CampItem addPackage(CampItemListResult.CampPackage campPackage) {
        getPackages().add(campPackage);
        return this;
    }

    public int getEnrollFee() {
        return this.enrollFee;
    }

    public CampItem setEnrollFee(int i) {
        this.enrollFee = i;
        return this;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public long getCampId() {
        return this.campId;
    }

    public void setCampId(long j) {
        this.campId = j;
    }

    public long getItemGroupId() {
        return this.itemGroupId;
    }

    public void setItemGroupId(long j) {
        this.itemGroupId = j;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getEnrollType() {
        return this.enrollType;
    }

    public void setEnrollType(String str) {
        this.enrollType = str;
    }

    public String getEnrollStartDate() {
        return this.enrollStartDate;
    }

    public void setEnrollStartDate(String str) {
        this.enrollStartDate = str;
    }

    public String getEnrollEndDate() {
        return this.enrollEndDate;
    }

    public void setEnrollEndDate(String str) {
        this.enrollEndDate = str;
    }

    public String getMaxGroupNum() {
        return this.maxGroupNum;
    }

    public void setMaxGroupNum(String str) {
        this.maxGroupNum = str;
    }

    public String getMinPersonNum() {
        return this.minPersonNum;
    }

    public void setMinPersonNum(String str) {
        this.minPersonNum = str;
    }

    public String getMaxPersonNum() {
        if (TextUtils.isEmpty(this.maxPersonNum)) {
            return K.k0;
        }
        return this.maxPersonNum;
    }

    public void setMaxPersonNum(String str) {
        this.maxPersonNum = str;
    }

    public String getSmsTag() {
        return this.smsTag;
    }

    public void setSmsTag(String str) {
        this.smsTag = str;
    }

    public String getDemand() {
        return this.demand;
    }

    public void setDemand(String str) {
        this.demand = str;
    }

    public String getEnrolledTag() {
        return this.enrolledTag;
    }

    public void setEnrolledTag(String str) {
        this.enrolledTag = str;
    }

    public String getEnrolledNum() {
        return this.enrolledNum;
    }

    public void setEnrolledNum(String str) {
        this.enrolledNum = str;
    }

    public String getNoBasicEnrollFee() {
        return this.noBasicEnrollFee;
    }

    public void setNoBasicEnrollFee(String str) {
        this.noBasicEnrollFee = str;
    }
}
