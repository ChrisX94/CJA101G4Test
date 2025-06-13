package com.shakemate.activity.repository;

import com.shakemate.activity.vo.ActivityTrackingId;
import com.shakemate.activity.vo.ActivityTrackingVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityTrackingRepository extends JpaRepository<ActivityTrackingVO, ActivityTrackingId> {

    // 查某個會員目前追蹤的所有活動
    List<ActivityTrackingVO> findByIdUserIdAndTrackingState(Integer userId, Byte trackingState);

    // 查某活動被哪些人追蹤
    List<ActivityTrackingVO> findByIdActivityIdAndTrackingState(Integer activityId, Byte trackingState);

    // 查某個會員是否正在追蹤某個活動
    boolean existsByIdUserIdAndIdActivityIdAndTrackingState(Integer userId, Integer activityId, Byte trackingState);

}
