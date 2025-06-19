package com.shakemate.activity.service;

import com.shakemate.activity.dto.*;
import com.shakemate.activity.entity.ActivityParticipant;
import com.shakemate.activity.entity.id.ActivityParticipantId;

import java.util.List;

public interface ActivityParticipantService {

    ActivityParticipantDTO ActivityParticipantById(ActivityParticipantId id);
    List<ActivityParticipantDTO> getAllActivityParticipant();
    ActivityParticipantDTO createActivityParticipant(ActivityParticipantCreateDTO createDTO);
    ActivityParticipantDTO updateActivityParticipant(ActivityParticipantId id, ActivityParticipantUpdateDTO updateDTO);
    void deleteActivityParticipant(ActivityParticipantId id);
}
