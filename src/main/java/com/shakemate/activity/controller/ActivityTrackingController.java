package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.ActivityTrackingCreateDTO;
import com.shakemate.activity.dto.ActivityTrackingDTO;
import com.shakemate.activity.dto.ActivityTrackingUpdateDTO;
import com.shakemate.activity.repository.ActivityTrackingRepository;
import com.shakemate.activity.entity.id.ActivityTrackingId;
import com.shakemate.activity.entity.ActivityTracking;
import com.shakemate.activity.service.ActivityTrackingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity-tracking")
@RequiredArgsConstructor
public class ActivityTrackingController {

    private final ActivityTrackingService activityTrackingService;

    // GetById
    @GetMapping("/{activityId}/{userId}")
    public ApiResponse<ActivityTrackingDTO> getById(
            @PathVariable Integer activityId,
            @PathVariable Integer userId) {

        ActivityTrackingId id = new ActivityTrackingId(activityId, userId);
        ActivityTrackingDTO dto = activityTrackingService.getById(id);
        return ApiResponse.success(dto);
    }

    // GetAll
    @GetMapping
    public ApiResponse<List<ActivityTrackingDTO>> getAll() {
        List<ActivityTrackingDTO> list = activityTrackingService.getAll();
        return ApiResponse.success(list);
    }

    // create
    @PostMapping
    public ApiResponse<ActivityTrackingDTO> create(@Valid @RequestBody ActivityTrackingCreateDTO createDTO) {
        ActivityTrackingDTO dto = activityTrackingService.create(createDTO);
        return ApiResponse.success(dto);
    }

    // update
    @PatchMapping("/{activityId}/{userId}")
    public ApiResponse<ActivityTrackingDTO> update(
            @PathVariable Integer activityId,
            @PathVariable Integer userId,
            @RequestBody ActivityTrackingUpdateDTO updateDTO) {

        ActivityTrackingId id = new ActivityTrackingId(activityId, userId);
        ActivityTrackingDTO updatedDTO = activityTrackingService.update(id, updateDTO);
        return ApiResponse.success(updatedDTO);

    }

    @DeleteMapping("/{activityId}/{userId}")
    public ApiResponse<Void> delete(
            @PathVariable Integer activityId,
            @PathVariable Integer userId) {

        ActivityTrackingId id = new ActivityTrackingId(activityId, userId);
        activityTrackingService.delete(id);
        return ApiResponse.success(null);

    }

}
