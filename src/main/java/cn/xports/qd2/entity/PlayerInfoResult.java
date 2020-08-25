package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;

public class PlayerInfoResult extends Response implements Serializable {
    private MemberInfo campPlayerInfo;
    private CampEnrollment publicCampEnrollment;
    private CampItem publicCampItem;
    private String self;

    public CampEnrollment getPublicCampEnrollment() {
        return this.publicCampEnrollment;
    }

    public PlayerInfoResult setPublicCampEnrollment(CampEnrollment campEnrollment) {
        this.publicCampEnrollment = campEnrollment;
        return this;
    }

    public MemberInfo getCampPlayerInfo() {
        return this.campPlayerInfo;
    }

    public PlayerInfoResult setCampPlayerInfo(MemberInfo memberInfo) {
        this.campPlayerInfo = memberInfo;
        return this;
    }

    public CampItem getPublicCampItem() {
        return this.publicCampItem;
    }

    public PlayerInfoResult setPublicCampItem(CampItem campItem) {
        this.publicCampItem = campItem;
        return this;
    }

    public String getSelf() {
        return this.self;
    }

    public PlayerInfoResult setSelf(String str) {
        this.self = str;
        return this;
    }
}
