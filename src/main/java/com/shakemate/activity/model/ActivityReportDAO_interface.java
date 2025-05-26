package com.shakemate.activity.model;

import java.util.*;

public interface ActivityReportDAO_interface {

    public void insert(ActivityReportVO activityReportVO);
    public void update(ActivityReportVO activityReportVO);
    public void delete(Integer rpUserId);
    public ActivityReportVO findByPrimaryKey(Integer rpUserId);
    public List<ActivityReportVO> getAll();
}
