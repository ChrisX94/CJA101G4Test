package com.shakemate.activity.controller;

import com.shakemate.activity.dto.ActivityCardDTO;
import com.shakemate.activity.dto.ActivityCreateDTO;
import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.dto.ActivityUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.mapper.ActivityCardMapper;
import com.shakemate.activity.service.ActivityService;
import com.shakemate.activity.common.ApiResponse;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import com.shakemate.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final UserRepository userRepository;
    private final ActivityCardMapper activityCardMapper;

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

    @GetMapping("/feed")
    public ApiResponse<Page<ActivityDTO>> getFeed(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "latest") String sort) {

        // 取得會員年齡與性別 (假設已實作 UserService)
        Users user = userRepository.findById(userId).orElseThrow();
        int userAge = calculateAge(user.getBirthday());

        Page<Activity> pageVO = activityService.getFilteredActivities(userAge, user.getGender(), page, size, sort);

        Page<ActivityDTO> pageDTO = pageVO.map(this::toDTOWithStatus);

        return ApiResponse.success(pageDTO);
    }

    public int calculateAge(java.sql.Date birthday) {
        if (birthday == null) return 0;
        // 透過 getTime() 建立 Instant，再轉成 LocalDate
        LocalDate birthDate = birthday.toLocalDate(); // java.sql.Date 支援此方法

        return Period.between(birthDate, LocalDate.now()).getYears();
    }


    private ActivityDTO toDTOWithStatus(Activity activity) {
        ActivityDTO dto = new ActivityDTO();
        // 複製基本欄位
        BeanUtils.copyProperties(activity, dto);
        // 計算活動狀態（自己定義邏輯）
        dto.setActivityStatus(calculateStatus(activity));
        return dto;
    }

    private Byte calculateStatus(Activity activity) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = activity.getActivStartTime().toLocalDateTime();
        LocalDateTime endTime = activity.getActivEndTime().toLocalDateTime();

        if (activity.getActivityStatus() == 3) {
            return 3; // 已取消或下架
        } else if (now.isBefore(startTime)) {
            return 0; // 尚未開始
        } else if (now.isAfter(endTime)) {
            return 2; // 已結束
        } else {
            return 1; // 進行中
        }
    }

    @GetMapping("/owner")
    public ApiResponse<Page<ActivityCardDTO>> getOwnerActivities(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "latest") String sort) {

        Page<ActivityCardDTO> pageDTO = activityService.getOwnerActivityCards(userId, page, size, sort);
        return ApiResponse.success(pageDTO);
    }

    // 使用者為團員

    @GetMapping("/member/all")
    public ApiResponse<Page<ActivityCardDTO>> getAllMemberActivities(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "latest") String sort) {

        Page<ActivityCardDTO> pageDTO = activityService.getAllMemberActivityCards(userId, page, size, sort);


        return ApiResponse.success(pageDTO);
    }

    @GetMapping("/tracked-ongoing")
    public ApiResponse<Page<ActivityCardDTO>> getTrackedOngoingActivities(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "latest") String sort) {

        Page<ActivityCardDTO> pageDTO = activityService.getTrackedOngoingActivityCards(userId, page, size, sort);

        return ApiResponse.success(pageDTO);
    }


    @GetMapping("/wall")
    public ApiResponse<Page<ActivityCardDTO>> getActivityWall(
            @RequestParam Integer userId,
            @RequestParam int userAge,
            @RequestParam int userGender,
            Pageable pageable) {

        Page<ActivityCardDTO> result = activityService.getVisibleActivitiesForUser(userId, userAge, userGender, pageable);
        return ApiResponse.success(result);
    }







}
