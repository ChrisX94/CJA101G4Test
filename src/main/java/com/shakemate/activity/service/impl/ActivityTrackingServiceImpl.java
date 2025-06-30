package com.shakemate.activity.service.impl;

import com.shakemate.activity.dto.request.ActivityTrackingCreateDTO;
import com.shakemate.activity.dto.ActivityTrackingDTO;
import com.shakemate.activity.dto.request.ActivityTrackingUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityTracking;
import com.shakemate.activity.entity.id.ActivityTrackingId;
import com.shakemate.activity.mapper.ActivityTrackingMapper;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.repository.ActivityTrackingRepository;
import com.shakemate.activity.service.ActivityTrackingService;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityTrackingServiceImpl implements ActivityTrackingService {

    private final ActivityTrackingRepository activityTrackingRepository;
    private final UserRepository usersRepository;
    private final ActivityRepository activityRepository;
    private final ActivityTrackingMapper mapper;

    @Override
    public ActivityTrackingDTO getById(ActivityTrackingId id) {
        ActivityTracking entity = activityTrackingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("查無資料"));
        ActivityTrackingDTO dto = mapper.toDTO(entity);
        dto.setUserId(entity.getId().getUserId());
        dto.setActivityId(entity.getId().getActivityId());
        return dto;
    }

    @Override
    public List<ActivityTrackingDTO> getAll() {
        return activityTrackingRepository.findAll()
                .stream()
                .map(entity -> {
                    ActivityTrackingDTO dto = mapper.toDTO(entity);
                    dto.setUserId(entity.getId().getUserId());
                    dto.setActivityId(entity.getId().getActivityId());
                    return dto;
                })
                .toList();
    }

    @Override
    public ActivityTrackingDTO create(ActivityTrackingCreateDTO createDTO) {
        Users user = usersRepository.findById(createDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("找不到使用者"));
        Activity activity = activityRepository.findById(createDTO.getActivityId())
                .orElseThrow(() -> new EntityNotFoundException("找不到活動"));

        ActivityTracking entity = mapper.toEntity(createDTO);
        entity.setUser(user);
        entity.setActivity(activity);
        entity.setId(new ActivityTrackingId(activity.getActivityId(), user.getUserId()));

        activityTrackingRepository.save(entity);

        ActivityTrackingDTO dto = mapper.toDTO(entity);
        dto.setActivityId(entity.getId().getActivityId());
        dto.setUserId(entity.getId().getUserId());
        return dto;
    }

    @Override
    public ActivityTrackingDTO update(ActivityTrackingId id, ActivityTrackingUpdateDTO updateDTO) {
        ActivityTracking entity = activityTrackingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("查無資料"));

        mapper.updateEntityFromDto(updateDTO, entity);

        ActivityTracking updated = activityTrackingRepository.save(entity);

        ActivityTrackingDTO dto = mapper.toDTO(updated);
        dto.setUserId(updated.getId().getUserId());
        dto.setActivityId(updated.getId().getActivityId());
        return dto;
    }

    @Override
    public void delete(ActivityTrackingId id) {
        boolean exists = activityTrackingRepository.existsById(id);
        if(!exists) {
            throw new EntityNotFoundException("查無資料");
        }
        activityTrackingRepository.deleteById(id);
    }
}
