package com.shakemate.activity.model;

import com.shakemate.activity.model.vo.ActivityQuestionVO;

import java.util.*;

public interface ActivityQuestionDAO_interface {

    public void insert(ActivityQuestionVO activityQuestionVO);
    public void update(ActivityQuestionVO activityQuestionVO);
    public void delete(Integer questionId);
    public ActivityQuestionVO findByPrimaryKey(Integer questionId);
    public List<ActivityQuestionVO> getAll();

}
