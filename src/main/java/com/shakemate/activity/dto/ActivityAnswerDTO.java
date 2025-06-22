package com.shakemate.activity.dto;

import com.shakemate.activity.entity.Activity;
import lombok.Data;

@Data
public class ActivityAnswerDTO {

    private Integer answerId;
    private Integer activityId;
    private Integer questionId;
    private Integer userId;
    private String answerText;

}
