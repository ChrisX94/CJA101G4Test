package com.shakemate.activity.service;

import com.shakemate.activity.dto.ActivityQuestionCreateDTO;
import com.shakemate.activity.dto.ActivityQuestionDTO;
import com.shakemate.activity.dto.ActivityQuestionUpdateDTO;
import com.shakemate.activity.entity.ActivityQuestion;

import java.util.List;

public interface ActivityQuestionService {

    ActivityQuestionDTO getById(Integer id);
    List<ActivityQuestionDTO> getAll();
    ActivityQuestionDTO create(ActivityQuestionCreateDTO createDTO);
    ActivityQuestionDTO update(Integer id, ActivityQuestionUpdateDTO updateDTO);
    void delete(Integer id);

}
