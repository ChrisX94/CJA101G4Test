package com.shakemate.activity.repository;

import com.shakemate.activity.entity.ActivityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityQuestionRepository  extends JpaRepository<ActivityQuestion, Integer> {

    List<ActivityQuestion> findByActivity_ActivityId(Integer activityId);


}
