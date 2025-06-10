package com.shakemate.activity.model.vo;

import java.sql.Timestamp;

public class ActivityParticipantVO {

    private Integer participantId;
    private Integer activityId;
    private Timestamp admReviewTime;
    private Byte parStatus; // 0: 申請中, 1: 已取消, 2: 已加入, 3: 已退出
    private Timestamp applyingDate;
    private Byte rating; // 1~5 顆星，預設 5
    private String reviewContent;
    private Timestamp reviewTime;

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Timestamp getAdmReviewTime() {
        return admReviewTime;
    }

    public void setAdmReviewTime(Timestamp admReviewTime) {
        this.admReviewTime = admReviewTime;
    }

    public Byte getParStatus() {
        return parStatus;
    }

    public void setParStatus(Byte parStatus) {
        this.parStatus = parStatus;
    }

    public Timestamp getApplyingDate() {
        return applyingDate;
    }

    public void setApplyingDate(Timestamp applyingDate) {
        this.applyingDate = applyingDate;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    public String toString() {
        return "ActivityParticipantVO{" +
                "participantId=" + participantId +
                ", activityId=" + activityId +
                ", admReviewTime=" + admReviewTime +
                ", parStatus=" + parStatus +
                ", applyingDate=" + applyingDate +
                ", rating=" + rating +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewTime=" + reviewTime +
                '}';
    }
}
