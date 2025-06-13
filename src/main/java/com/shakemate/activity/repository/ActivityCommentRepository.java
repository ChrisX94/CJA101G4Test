package com.shakemate.activity.repository;

import com.shakemate.activity.vo.ActivityCommentVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityCommentRepository extends JpaRepository<ActivityCommentVO, Integer> {
}
