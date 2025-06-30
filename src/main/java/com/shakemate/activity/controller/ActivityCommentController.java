package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.request.ActivityCommentCreateDTO;
import com.shakemate.activity.dto.ActivityCommentDTO;
import com.shakemate.activity.dto.request.ActivityCommentUpdateDTO;
import com.shakemate.activity.mapper.ActivityCommentMapper;
import com.shakemate.activity.service.ActivityCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-comments")
@RequiredArgsConstructor
public class ActivityCommentController {

    private final ActivityCommentService activityCommentService;
    private final ActivityCommentMapper activityCommentMapper;

    @GetMapping("/{id}")
    public ApiResponse<ActivityCommentDTO> getActivityComment(@PathVariable Integer id) {
        ActivityCommentDTO dto = activityCommentService.getById(id);
        return ApiResponse.success(dto);
    }

    @GetMapping
    public ApiResponse<List<ActivityCommentDTO>> getAllActivityComments() {
        List<ActivityCommentDTO> list = activityCommentService.getAll();
        return ApiResponse.success(list);
    }

    @PostMapping
    public ApiResponse<ActivityCommentDTO> createActivityComment(@Valid @RequestBody ActivityCommentCreateDTO createDTO) {
        ActivityCommentDTO dto = activityCommentService.create(createDTO);
        return ApiResponse.success(dto);
    }

    @PatchMapping("/{id}")
    public ApiResponse<ActivityCommentDTO> updateActivityComment(@PathVariable Integer id,
                                                                 @Valid @RequestBody ActivityCommentUpdateDTO updateDTO) {
        ActivityCommentDTO dto = activityCommentService.update(id, updateDTO);
        return ApiResponse.success(dto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteActivityComment(@PathVariable Integer id) {
        activityCommentService.delete(id);
        return ApiResponse.success(null);
    }




}