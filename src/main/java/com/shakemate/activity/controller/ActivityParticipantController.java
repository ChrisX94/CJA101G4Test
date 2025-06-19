package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.dto.ActivityParticipantCreateDTO;
import com.shakemate.activity.dto.ActivityParticipantDTO;
import com.shakemate.activity.dto.ActivityParticipantUpdateDTO;
import com.shakemate.activity.repository.ActivityParticipantRepository;
import com.shakemate.activity.entity.id.ActivityParticipantId;
import com.shakemate.activity.entity.ActivityParticipant;
import com.shakemate.activity.service.ActivityParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity-participants")
@RequiredArgsConstructor
public class ActivityParticipantController {

    private final ActivityParticipantService participantService;

    // 依複合主鍵查詢
    @GetMapping("/{participantId}/{activityId}")
    public ApiResponse<ActivityParticipantDTO> getById(
            @PathVariable Integer participantId,
            @PathVariable Integer activityId) {

        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
        ActivityParticipantDTO dto = participantService.ActivityParticipantById(id);
        return ApiResponse.success(dto);
    }

    // 查全部
    @GetMapping
    public ApiResponse<List<ActivityParticipantDTO>> getAll() {
        List<ActivityParticipantDTO> list = participantService.getAllActivityParticipant();
        return ApiResponse.success(list);
    }

    // 新增活動
    @PostMapping
    public ApiResponse<ActivityParticipantDTO> createActivityParticipant(@Valid @RequestBody ActivityParticipantCreateDTO createDTO) {
        ActivityParticipantDTO dto = participantService.createActivityParticipant(createDTO);
        return ApiResponse.success(dto);
    }


    @PatchMapping("/{participantId}/{activityId}")
    public ApiResponse<ActivityParticipantDTO> updateActivityParticipant(
            @PathVariable Integer participantId,
            @PathVariable Integer activityId,
            @RequestBody ActivityParticipantUpdateDTO updateDTO) {

        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
        ActivityParticipantDTO updatedDTO = participantService.updateActivityParticipant(id, updateDTO);
        return ApiResponse.success(updatedDTO);
    }

    @DeleteMapping("/{participantId}/{activityId}")
    public ApiResponse<Void> deleteActivityParticipant(
            @PathVariable Integer participantId,
            @PathVariable Integer activityId) {

        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
        participantService.deleteActivityParticipant(id);
        return ApiResponse.success(null);
    }

}