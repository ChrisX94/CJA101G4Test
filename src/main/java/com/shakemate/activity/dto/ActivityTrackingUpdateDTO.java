package com.shakemate.activity.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityTrackingUpdateDTO {

    private Timestamp trackingTime;
    private Byte trackingState; // 0: 正在追蹤, 1: 取消追蹤

}
