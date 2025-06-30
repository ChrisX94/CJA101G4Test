package com.shakemate.activity.service;

import com.shakemate.activity.dto.*;
import com.shakemate.activity.dto.request.ActivityCommentCreateDTO;
import com.shakemate.activity.dto.request.ActivityCommentUpdateDTO;

import java.util.List;

public interface ActivityCommentService {

    ActivityCommentDTO getById(Integer id);
    List<ActivityCommentDTO> getAll();
    ActivityCommentDTO create(ActivityCommentCreateDTO createDTO);
    ActivityCommentDTO update(Integer id, ActivityCommentUpdateDTO updateDTO);
    void delete(Integer id);


}
