package com.shakemate.activity.repository;

import com.shakemate.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    // 你可以在這裡加自訂查詢方法，但不需要自己實作 JpaRepository 方法

}