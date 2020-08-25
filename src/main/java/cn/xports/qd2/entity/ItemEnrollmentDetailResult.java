package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.CampaignInfo;
import cn.xports.entity.OrderInfo;
import java.io.Serializable;
import java.util.List;

public class ItemEnrollmentDetailResult extends Response implements Serializable {
    private List<MemberInfo> newPlayerList;
    private List<MemberInfo> playerList;
    private CampItem publicCampItem;
    private CampScore publicCampScore;
    private CampTeam publicCampTeam;
    private CampaignInfo publicCampaign;
    private OrderInfo trade;

    public CampScore getPublicCampScore() {
        return this.publicCampScore;
    }

    public void setPublicCampScore(CampScore campScore) {
        this.publicCampScore = campScore;
    }

    public CampItem getPublicCampItem() {
        return this.publicCampItem;
    }

    public ItemEnrollmentDetailResult setPublicCampItem(CampItem campItem) {
        this.publicCampItem = campItem;
        return this;
    }

    public CampTeam getPublicCampTeam() {
        return this.publicCampTeam;
    }

    public ItemEnrollmentDetailResult setPublicCampTeam(CampTeam campTeam) {
        this.publicCampTeam = campTeam;
        return this;
    }

    public CampaignInfo getPublicCampaign() {
        return this.publicCampaign;
    }

    public ItemEnrollmentDetailResult setPublicCampaign(CampaignInfo campaignInfo) {
        this.publicCampaign = campaignInfo;
        return this;
    }

    public List<MemberInfo> getPlayerList() {
        return this.playerList;
    }

    public ItemEnrollmentDetailResult setPlayerList(List<MemberInfo> list) {
        this.playerList = list;
        return this;
    }

    public List<MemberInfo> getNewPlayerList() {
        return this.newPlayerList;
    }

    public ItemEnrollmentDetailResult setNewPlayerList(List<MemberInfo> list) {
        this.newPlayerList = list;
        return this;
    }

    public OrderInfo getTrade() {
        return this.trade;
    }

    public ItemEnrollmentDetailResult setTrade(OrderInfo orderInfo) {
        this.trade = orderInfo;
        return this;
    }

    public static class CampScore implements Serializable {
        private String campId;
        private String campItemId;
        private String id;
        private String rank;
        private String score;
        private String state;
        private String teamId;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getTeamId() {
            return this.teamId;
        }

        public void setTeamId(String str) {
            this.teamId = str;
        }

        public String getCampId() {
            return this.campId;
        }

        public void setCampId(String str) {
            this.campId = str;
        }

        public String getCampItemId() {
            return this.campItemId;
        }

        public void setCampItemId(String str) {
            this.campItemId = str;
        }

        public String getRank() {
            return this.rank;
        }

        public void setRank(String str) {
            this.rank = str;
        }

        public String getScore() {
            return this.score;
        }

        public void setScore(String str) {
            this.score = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }
    }
}
