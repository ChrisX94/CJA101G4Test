package com.shakemate.activity.vo;

import java.sql.Timestamp;

/**
 * 表格名稱：活動追蹤 (ACTIVITY_TRACKING)
 *
 * 主鍵：
 *   - ACTIVITY_ID + USER_ID
 *
 * 外來鍵：
 *   - ACTIVITY_ID ➔ ACTIVITY(ACTIVITY_ID)
 *   - USER_ID ➔ USERS(USER_ID)
 *
 * 欄位說明：
 * ------------------------------------------------------------------------------
 * | 欄位名稱       | 欄位敘述   | 資料型態 | 長度 | 備註                          |
 * ------------------------------------------------------------------------------
 * | ACTIVITY_ID    | 活動編號   | INT      |      | Not Null, 主鍵, 外來鍵        |
 * | USER_ID        | 會員編號   | INT      |      | Not Null, 主鍵, 外來鍵        |
 * | TRACKING_TIME  | 追蹤時間   | DATETIME |      | Not Null                       |
 * | TRACKING_STATE | 追蹤狀態   | TINYINT  |      | Not Null                       |
 * |                |            |          |      | 0: 正在追蹤                    |
 * |                |            |          |      | 1: 取消追蹤                    |
 * ------------------------------------------------------------------------------
 */


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
