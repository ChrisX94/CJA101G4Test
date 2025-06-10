package com.shakemate.activity.model;

import com.shakemate.activity.model.vo.ActivityAnswerVO;

import java.util.*;

public interface ActivityAnswerDAO_interface {

    public void insert(ActivityAnswerVO activityAnswerVO);
    public void update(ActivityAnswerVO activityAnswerVO);
    public void delete(Integer answerId);
    public ActivityAnswerVO findByPrimaryKey(Integer answerId);
    public List<ActivityAnswerVO> getAll();
}
