package com.shakemate.activity.repository;

import com.shakemate.activity.entity.ActivityAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityAnswerRepository extends JpaRepository<ActivityAnswer, Integer> {
}
