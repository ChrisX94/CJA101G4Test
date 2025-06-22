package com.shakemate.activity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityTrackingCreateDTO {

    @NotNull(message = "活動編號不可為空")
    private Integer activityId;
    @NotNull(message = "用戶編號不可為空")
    private Integer userId;
    @NotNull(message = "追蹤時間不可為空")
    private Timestamp trackingTime;
    @NotNull(message = "追蹤狀態不可為空")
    private Byte trackingState = 0; // 0: 正在追蹤, 1: 取消追蹤

}
