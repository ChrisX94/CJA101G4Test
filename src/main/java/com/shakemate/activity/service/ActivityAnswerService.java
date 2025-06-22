package com.shakemate.activity.service;

import com.shakemate.activity.dto.ActivityAnswerCreateDTO;
import com.shakemate.activity.dto.ActivityAnswerDTO;
import com.shakemate.activity.dto.ActivityAnswerUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityAnswer;
import com.shakemate.activity.entity.ActivityComment;

import java.util.List;

public interface ActivityAnswerService {

    ActivityAnswerDTO getById(Integer id);
    List<ActivityAnswerDTO> getAll();
    ActivityAnswerDTO create(ActivityAnswerCreateDTO createDTO);
    ActivityAnswerDTO update(Integer id, ActivityAnswerUpdateDTO updateDTO);
    void delete(Integer id);
}
