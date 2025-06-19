package com.shakemate.activity.service;

import com.shakemate.activity.dto.ActivityCreateDTO;
import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.dto.ActivityUpdateDTO;

import java.util.List;

public interface ActivityService {

    ActivityDTO getActivityById(Integer id);
    List<ActivityDTO> getAllActivities();
    ActivityDTO createActivity(ActivityCreateDTO createDTO);
    ActivityDTO updateActivity(Integer id, ActivityUpdateDTO updateDTO);
    void deleteActivity(Integer id);
}
