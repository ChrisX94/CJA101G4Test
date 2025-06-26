package com.shakemate.activity.repository;

import com.shakemate.activity.entity.ActivityComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityCommentRepository extends JpaRepository<ActivityComment, Integer> {



}
