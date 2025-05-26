package com.shakemate.activity.model;

import java.util.*;

public interface ActivityParticipantDAO_interface {
    public void insert(ActivityParticipantVO activityParticipantVO);
    public void update(ActivityParticipantVO activityParticipantVO);
    public void delete(Integer participantId, Integer activityId);
    public ActivityParticipantVO findByPrimaryKey(Integer participantId, Integer activityId);
    public List<ActivityParticipantVO> getAll();
}
