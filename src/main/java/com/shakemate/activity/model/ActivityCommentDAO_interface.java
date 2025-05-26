package com.shakemate.activity.model;


import java.util.*;

public interface ActivityCommentDAO_interface {
    public void insert(ActivityCommentVO activityCommentVO);
    public void update(ActivityCommentVO activityCommentVO);
    public void delete(Integer commentId);
    public ActivityCommentVO findByPrimaryKey(Integer commentId);
    public List<ActivityCommentVO> getAll();
}
