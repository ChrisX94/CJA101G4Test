package com.shakemate.activity.model;

import com.shakemate.activity.model.vo.ActivityTrackingVO;

import java.util.*;

public interface ActivityTrackingDAO_interface {

    public void insert(ActivityTrackingVO activityTrackingVO);
    public void update(ActivityTrackingVO activityTrackingVO);
    public void delete(Integer activityId);
    public ActivityTrackingVO findByPrimaryKey(Integer activityId);
    public List<ActivityTrackingVO> getAll();

}
