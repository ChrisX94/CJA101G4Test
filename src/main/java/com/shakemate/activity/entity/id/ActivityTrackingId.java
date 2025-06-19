package com.shakemate.activity.entity.id;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ActivityTrackingId implements Serializable {

    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @Column(name = "USER_ID")
    private Integer userId;

}
