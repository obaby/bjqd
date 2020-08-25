package cn.xports.qd2.entity;

import java.io.Serializable;

public class CampTeam implements Serializable {
    private String contactPhone;
    private String enrollId;
    private String id;
    private String leaderName;
    private String name;
    private String psptId;

    public String getPsptId() {
        return this.psptId;
    }

    public CampTeam setPsptId(String str) {
        this.psptId = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public CampTeam setId(String str) {
        this.id = str;
        return this;
    }

    public String getEnrollId() {
        return this.enrollId;
    }

    public CampTeam setEnrollId(String str) {
        this.enrollId = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public CampTeam setName(String str) {
        this.name = str;
        return this;
    }

    public String getLeaderName() {
        return this.leaderName;
    }

    public CampTeam setLeaderName(String str) {
        this.leaderName = str;
        return this;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public CampTeam setContactPhone(String str) {
        this.contactPhone = str;
        return this;
    }
}
