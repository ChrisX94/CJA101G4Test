package com.shakemate.activity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityCommentUpdateDTO {

    private String content;                // 留言內容

    private Integer parentCommentId;       // 父留言（可為 null）

    private Timestamp commentTime;         // 留言時間（可選）

    private Integer commentCount;

}
