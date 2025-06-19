package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.ActivityCommentCreateDTO;
import com.shakemate.activity.dto.ActivityCommentDTO;
import com.shakemate.activity.dto.ActivityCommentUpdateDTO;
import com.shakemate.activity.repository.ActivityCommentRepository;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.entity.ActivityComment;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.service.ActivityCommentService;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity-comments")
@RequiredArgsConstructor
public class ActivityCommentController {

    private final ActivityCommentService activityCommentService;

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