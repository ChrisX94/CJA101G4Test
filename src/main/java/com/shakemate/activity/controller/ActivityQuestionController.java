package com.shakemate.activity.controller;

import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.ActivityQuestionCreateDTO;
import com.shakemate.activity.dto.ActivityQuestionDTO;
import com.shakemate.activity.dto.ActivityQuestionUpdateDTO;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.entity.ActivityQuestion;
import com.shakemate.activity.repository.ActivityQuestionRepository;

import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.service.ActivityQuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activity-questions")
public class ActivityQuestionController {

    private final ActivityQuestionService activityQuestionService;

    @GetMapping("/{id}")
    public ApiResponse<ActivityQuestionDTO> getById(@PathVariable Integer id) {
        ActivityQuestionDTO dto = activityQuestionService.getById(id);
        return ApiResponse.success(dto);
    }

    @GetMapping
    public ApiResponse<List<ActivityQuestionDTO>> getAll() {
        List<ActivityQuestionDTO> list = activityQuestionService.getAll();
        return ApiResponse.success(list);
    }

    @PostMapping
    public ApiResponse<ActivityQuestionDTO> create(@Valid @RequestBody ActivityQuestionCreateDTO createDTO) {
        ActivityQuestionDTO dto = activityQuestionService.create(createDTO);
        return ApiResponse.success(dto);
    }

    @PatchMapping("/{id}")
    public ApiResponse<ActivityQuestionDTO> update(@PathVariable Integer id,
                                                   @Valid @RequestBody ActivityQuestionUpdateDTO updateDTO) {
        ActivityQuestionDTO dto = activityQuestionService.update(id, updateDTO);
        return ApiResponse.success(dto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        activityQuestionService.delete(id);
        return ApiResponse.success(null);
    }

}
