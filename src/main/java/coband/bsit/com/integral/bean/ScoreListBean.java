package coband.bsit.com.integral.bean;

import java.io.Serializable;

public class ScoreListBean implements Serializable {
    private String imgUrl;
    private String scoreAdd;
    private String scoreDate;
    private String scoreName;
    private String scoreOrderId;

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getScoreName() {
        return this.scoreName;
    }

    public void setScoreName(String str) {
        this.scoreName = str;
    }

    public String getScoreOrderId() {
        return this.scoreOrderId;
    }

    public void setScoreOrderId(String str) {
        this.scoreOrderId = str;
    }

    public String getScoreDate() {
        return this.scoreDate;
    }

    public void setScoreDate(String str) {
        this.scoreDate = str;
    }

    public String getScoreAdd() {
        return this.scoreAdd;
    }

    public void setScoreAdd(String str) {
        this.scoreAdd = str;
    }
}
