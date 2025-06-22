package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.ActivityAnswerCreateDTO;
import com.shakemate.activity.dto.ActivityAnswerDTO;
import com.shakemate.activity.dto.ActivityAnswerUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityAnswer;
import com.shakemate.activity.service.ActivityAnswerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-answers")
@RequiredArgsConstructor
public class ActivityAnswerController {

    private final ActivityAnswerService activityAnswerService;

    @GetMapping("/{id}")
    public ApiResponse<ActivityAnswerDTO> getById(@PathVariable Integer id) {
        ActivityAnswerDTO dto = activityAnswerService.getById(id);
        return ApiResponse.success(dto);
    }

    @GetMapping
    public ApiResponse<List<ActivityAnswerDTO>> getAll() {
        List<ActivityAnswerDTO> list = activityAnswerService.getAll();
        return ApiResponse.success(list);
    }

    @PostMapping
    public ApiResponse<ActivityAnswerDTO> create(@Valid @RequestBody ActivityAnswerCreateDTO createDTO) {
        ActivityAnswerDTO dto = activityAnswerService.create(createDTO);
        return ApiResponse.success(dto);
    }

    @PatchMapping("/{id}")
    public ApiResponse<ActivityAnswerDTO> update(@PathVariable Integer id,
                                                 @Valid @RequestBody ActivityAnswerUpdateDTO updateDTO) {
        ActivityAnswerDTO dto = activityAnswerService.update(id, updateDTO);
        return ApiResponse.success(dto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        activityAnswerService.delete(id);
        return ApiResponse.success(null);
    }

}
