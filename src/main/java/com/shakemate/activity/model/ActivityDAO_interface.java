package com.shakemate.activity.model;

import java.util.*;

public interface ActivityDAO_interface {

    public void insert(ActivityVO activityVO);
    public void update(ActivityVO activityVO);
    public void delete(Integer activityId);
    public ActivityVO findByPrimaryKey(Integer activityId);
    public List<ActivityVO> getAll();

}
