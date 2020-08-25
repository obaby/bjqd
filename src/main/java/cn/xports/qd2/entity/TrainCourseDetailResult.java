package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.CardInfo;
import cn.xports.entity.TrainCourse;
import java.io.Serializable;
import java.util.List;

public class TrainCourseDetailResult extends Response implements Serializable {
    private List<CardInfo> cardInfoList;
    private String enrollId;
    private CardInfo memberCard;
    private TrainCourse trainingCourseInfo;

    public String getEnrollId() {
        return this.enrollId;
    }

    public void setEnrollId(String str) {
        this.enrollId = str;
    }

    public List<CardInfo> getCardInfoList() {
        return this.cardInfoList;
    }

    public void setCardInfoList(List<CardInfo> list) {
        this.cardInfoList = list;
    }

    public CardInfo getMemberCard() {
        return this.memberCard;
    }

    public void setMemberCard(CardInfo cardInfo) {
        this.memberCard = cardInfo;
    }

    public TrainCourse getTrainingCourseInfo() {
        return this.trainingCourseInfo;
    }

    public void setTrainingCourseInfo(TrainCourse trainCourse) {
        this.trainingCourseInfo = trainCourse;
    }
}
