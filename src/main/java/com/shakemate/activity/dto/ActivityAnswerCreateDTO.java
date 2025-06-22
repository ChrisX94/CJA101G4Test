package com.shakemate.activity.dto;

import com.shakemate.activity.entity.Activity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivityAnswerCreateDTO {

    @NotNull(message = "活動編號不可為空")
    private Integer activityId;

    @NotNull(message = "問題編號不可為空")
    private Integer questionId;

    @NotNull(message = "用戶編號不可為空")
    private Integer userId;

    @NotBlank(message = "回答不可為空")
    private String answerText;

}
