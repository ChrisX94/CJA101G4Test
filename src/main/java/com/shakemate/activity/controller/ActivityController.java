package com.shakemate.activity.controller;

import com.shakemate.activity.dto.ActivityCreateDTO;
import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.dto.ActivityUpdateDTO;
import com.shakemate.activity.service.ActivityService;
import com.shakemate.activity.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // 取得單一活動
    @GetMapping("/{id}")
    public ApiResponse<ActivityDTO> getActivity(@PathVariable Integer id) {
        ActivityDTO dto = activityService.getActivityById(id);
        return ApiResponse.success(dto);
    }

    // 取得所有活動
    @GetMapping
    public ApiResponse<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> list = activityService.getAllActivities();
        return ApiResponse.success(list);
    }

    // 新增活動
    @PostMapping
    public ApiResponse<ActivityDTO> createActivity(@Valid @RequestBody ActivityCreateDTO createDTO) {
        ActivityDTO dto = activityService.createActivity(createDTO);
        return ApiResponse.success(dto);
    }

    // 更新活動
    @PatchMapping("/{id}")
    public ApiResponse<ActivityDTO> updateActivity(@PathVariable Integer id,
                                                   @Valid @RequestBody ActivityUpdateDTO updateDTO) {
        ActivityDTO dto = activityService.updateActivity(id, updateDTO);
        return ApiResponse.success(dto);
    }

    // 刪除活動
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteActivity(@PathVariable Integer id) {
        activityService.deleteActivity(id);
        return ApiResponse.success(null);
    }
}
