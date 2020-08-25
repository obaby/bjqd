package cn.xports.qd2.entity;

import java.io.Serializable;
import java.util.List;

public class MemberInfo implements Serializable {
    private String avatar;
    private String email;
    private String enrollId;
    private List<EnrollElementInfo> enrollInfoList;
    private String gender;
    private String id;
    private String mobileNum;
    private String name;
    private String psptId;
    private String psptType;
    private String state;
    private String teamId;
    private String teamLogo;
    private String teamName;

    public String getEnrollId() {
        return this.enrollId;
    }

    public MemberInfo setEnrollId(String str) {
        this.enrollId = str;
        return this;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public MemberInfo setTeamId(String str) {
        this.teamId = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public MemberInfo setId(String str) {
        this.id = str;
        return this;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public MemberInfo setAvatar(String str) {
        this.avatar = str;
        return this;
    }

    public List<EnrollElementInfo> getEnrollInfoList() {
        return this.enrollInfoList;
    }

    public MemberInfo setEnrollInfoList(List<EnrollElementInfo> list) {
        this.enrollInfoList = list;
        return this;
    }

    public String getTeamLogo() {
        return this.teamLogo;
    }

    public MemberInfo setTeamLogo(String str) {
        this.teamLogo = str;
        return this;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public MemberInfo setTeamName(String str) {
        this.teamName = str;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public MemberInfo setEmail(String str) {
        this.email = str;
        return this;
    }

    public String getGender() {
        return this.gender;
    }

    public MemberInfo setGender(String str) {
        this.gender = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public MemberInfo setName(String str) {
        this.name = str;
        return this;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public MemberInfo setMobileNum(String str) {
        this.mobileNum = str;
        return this;
    }

    public String getPsptId() {
        return this.psptId;
    }

    public MemberInfo setPsptId(String str) {
        this.psptId = str;
        return this;
    }

    public String getPsptType() {
        return this.psptType;
    }

    public MemberInfo setPsptType(String str) {
        this.psptType = str;
        return this;
    }

    public String getState() {
        return this.state;
    }

    public MemberInfo setState(String str) {
        this.state = str;
        return this;
    }
}
