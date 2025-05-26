package com.shakemate.activity.model;

import java.sql.Timestamp;

public class ActivityTrackingVO {

    private Integer activityId;
    private Integer userId;
    private Timestamp trackingTime;
    private Byte trackingState; // 0: 正在追蹤, 1: 取消追蹤

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTrackingTime() {
        return trackingTime;
    }

    public void setTrackingTime(Timestamp trackingTime) {
        this.trackingTime = trackingTime;
    }

    public Byte getTrackingState() {
        return trackingState;
    }

    public void setTrackingState(Byte trackingState) {
        this.trackingState = trackingState;
    }
}
