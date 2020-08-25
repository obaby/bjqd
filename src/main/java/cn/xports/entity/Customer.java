package cn.xports.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    private String acctId;
    private boolean birth;
    private String birthday;
    private String centerId;
    private String contactPhone;
    private String custId;
    private String custName;
    private String custState;
    private String custType;
    private String ecardCustId;
    private String ecardId;
    private String ecardNo;
    private String enterpriseName;
    private String gender;
    private String inDate;
    private int inStaffId;
    private String openMode;
    private String photo;
    private String psptAddress;
    private String psptId;
    private String psptTypeId;
    private String remark;
    private String telephone;
    private int updateStaffId;
    private String updateTime;
    private String userId;
    private String userRemoveTag;
    private String userStatus;
    private String userType;
    private String venueId;

    public String getCustId() {
        return this.custId;
    }

    public Customer setCustId(String str) {
        this.custId = str;
        return this;
    }

    public String getCustName() {
        return this.custName;
    }

    public Customer setCustName(String str) {
        this.custName = str;
        return this;
    }

    public String getCustType() {
        return this.custType;
    }

    public Customer setCustType(String str) {
        this.custType = str;
        return this;
    }

    public String getCustState() {
        return this.custState;
    }

    public Customer setCustState(String str) {
        this.custState = str;
        return this;
    }

    public String getPsptTypeId() {
        return this.psptTypeId;
    }

    public Customer setPsptTypeId(String str) {
        this.psptTypeId = str;
        return this;
    }

    public String getPsptId() {
        return this.psptId;
    }

    public Customer setPsptId(String str) {
        this.psptId = str;
        return this;
    }

    public String getPsptAddress() {
        return this.psptAddress;
    }

    public Customer setPsptAddress(String str) {
        this.psptAddress = str;
        return this;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public Customer setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public int getInStaffId() {
        return this.inStaffId;
    }

    public Customer setInStaffId(int i) {
        this.inStaffId = i;
        return this;
    }

    public String getInDate() {
        return this.inDate;
    }

    public Customer setInDate(String str) {
        this.inDate = str;
        return this;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public Customer setUpdateTime(String str) {
        this.updateTime = str;
        return this;
    }

    public int getUpdateStaffId() {
        return this.updateStaffId;
    }

    public Customer setUpdateStaffId(int i) {
        this.updateStaffId = i;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public Customer setRemark(String str) {
        this.remark = str;
        return this;
    }

    public String getOpenMode() {
        return this.openMode;
    }

    public Customer setOpenMode(String str) {
        this.openMode = str;
        return this;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public Customer setContactPhone(String str) {
        this.contactPhone = str;
        return this;
    }

    public String getEcardCustId() {
        return this.ecardCustId;
    }

    public Customer setEcardCustId(String str) {
        this.ecardCustId = str;
        return this;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Customer setTelephone(String str) {
        this.telephone = str;
        return this;
    }

    public String getGender() {
        return this.gender;
    }

    public Customer setGender(String str) {
        this.gender = str;
        return this;
    }

    public String getEnterpriseName() {
        return this.enterpriseName;
    }

    public Customer setEnterpriseName(String str) {
        this.enterpriseName = str;
        return this;
    }

    public String getCenterId() {
        return this.centerId;
    }

    public Customer setCenterId(String str) {
        this.centerId = str;
        return this;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public Customer setBirthday(String str) {
        this.birthday = str;
        return this;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Customer setPhoto(String str) {
        this.photo = str;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public Customer setUserId(String str) {
        this.userId = str;
        return this;
    }

    public String getAcctId() {
        return this.acctId;
    }

    public Customer setAcctId(String str) {
        this.acctId = str;
        return this;
    }

    public String getEcardId() {
        return this.ecardId;
    }

    public Customer setEcardId(String str) {
        this.ecardId = str;
        return this;
    }

    public String getEcardNo() {
        return this.ecardNo;
    }

    public Customer setEcardNo(String str) {
        this.ecardNo = str;
        return this;
    }

    public String getUserRemoveTag() {
        return this.userRemoveTag;
    }

    public Customer setUserRemoveTag(String str) {
        this.userRemoveTag = str;
        return this;
    }

    public String getUserStatus() {
        return this.userStatus;
    }

    public Customer setUserStatus(String str) {
        this.userStatus = str;
        return this;
    }

    public String getUserType() {
        return this.userType;
    }

    public Customer setUserType(String str) {
        this.userType = str;
        return this;
    }

    public boolean isBirth() {
        return this.birth;
    }

    public Customer setBirth(boolean z) {
        this.birth = z;
        return this;
    }
}
