package com.shakemate.activity.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class ActivityQuestionDTO {

    private Integer questionId;
    private Integer activityId;
    private String questionText;
    private Byte questionOrder;

}
