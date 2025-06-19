package com.shakemate.activity.service.impl;

import com.shakemate.activity.dto.ActivityCreateDTO;
import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.dto.ActivityUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.mapper.ActivityMapper;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.service.ActivityService;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityMapper activityMapper;

    @Override
    public ActivityDTO createActivity(ActivityCreateDTO createDTO) {

        // 轉換 createDTO → Entity
        Activity activity = activityMapper.toEntity(createDTO);

        // 設定關聯 user（createDTO 需帶 userId）
        Users user = userRepository.findById(createDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("找不到該使用者"));
        activity.setUser(user);

        // 儲存
        Activity saved = activityRepository.save(activity);

        // Entity → DTO 回傳
        return activityMapper.toDTO(saved);
    }

    @Override
    public ActivityDTO updateActivity(Integer id, ActivityUpdateDTO updateDTO) {
        // 找到舊資料
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("找不到該活動"));

        // 用 MapStruct 的 updateEntityFromDto 只更新有值的欄位，null 不會覆蓋
        activityMapper.updateEntityFromDto(updateDTO, activity);

        // 若 updateDTO 有 userId 欄位，代表要更新 user 關聯
//        if (updateDTO.getUserId() != null) {
//            Users user = userRepository.findById(updateDTO.getUserId())
//                    .orElseThrow(() -> new EntityNotFoundException("找不到該使用者"));
//            activity.setUser(user);
//        }

        // 儲存更新後資料
        Activity updated = activityRepository.save(activity);

        // Entity → DTO 回傳
        return activityMapper.toDTO(updated);
    }

    @Override
    public ActivityDTO getActivityById(Integer id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("找不到該活動"));
        return activityMapper.toDTO(activity);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        List<Activity> list = activityRepository.findAll();
        return list.stream()
                .map(activityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
}
