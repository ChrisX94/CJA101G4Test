package com.shakemate.activity.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityQuestionDTO {

    @NotNull(message = "activityId不能為空")
    private Integer activityId;

    @NotBlank(message = "questionText不能為空")
    @Size(max = 255)
    private String questionText;

    @Min(1)
    @Max(5)
    private Byte questionOrder;
}
