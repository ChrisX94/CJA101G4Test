package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.request.ActivityParticipantCreateDTO;
import com.shakemate.activity.dto.ActivityParticipantDTO;
import com.shakemate.activity.dto.request.ActivityParticipantUpdateDTO;
import com.shakemate.activity.entity.id.ActivityParticipantId;
import com.shakemate.activity.service.ActivityParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    // 查看所有申請者
    @GetMapping("/applicants/{activityId}")
    public Page<ActivityParticipantDTO> getApplicants(
            @PathVariable Integer activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return participantService.getApplicants(activityId, page, size);
    }

    // 查看所有已加入者
    @GetMapping("/accepted/{activityId}")
    public Page<ActivityParticipantDTO> getAcceptedMembers(
            @PathVariable Integer activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return participantService.getAcceptedMembers(activityId, page, size);
    }


    // 查看活動評語
    @GetMapping("/reviews/{activityId}")
    public Page<ActivityParticipantDTO> getActivityReviews(
            @PathVariable Integer activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return participantService.getActivityReviews(activityId, page, size);
    }

    // 查看活動平均星星數
    @GetMapping("/rating/avg/{activityId}")
    public Double getAverageRating(@PathVariable Integer activityId) {
        return participantService.getAverageRating(activityId);
    }



}