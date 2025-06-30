package com.shakemate.activity.service;

import com.shakemate.activity.dto.*;
import com.shakemate.activity.dto.request.ActivityParticipantCreateDTO;
import com.shakemate.activity.dto.request.ActivityParticipantUpdateDTO;
import com.shakemate.activity.entity.id.ActivityParticipantId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActivityParticipantService {

    ActivityParticipantDTO ActivityParticipantById(ActivityParticipantId id);
    List<ActivityParticipantDTO> getAllActivityParticipant();
    ActivityParticipantDTO createActivityParticipant(ActivityParticipantCreateDTO createDTO);
    ActivityParticipantDTO updateActivityParticipant(ActivityParticipantId id, ActivityParticipantUpdateDTO updateDTO);
    void deleteActivityParticipant(ActivityParticipantId id);

    Page<ActivityParticipantDTO> getApplicants(Integer activityId, int page, int size);
    Page<ActivityParticipantDTO> getAcceptedMembers(Integer activityId, int page, int size);
    Page<ActivityParticipantDTO> getActivityReviews(Integer activityId, int page, int size);
    Double getAverageRating(Integer activityId);


}
