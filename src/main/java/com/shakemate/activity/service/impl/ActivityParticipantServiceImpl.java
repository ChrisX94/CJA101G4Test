package com.shakemate.activity.service.impl;

import com.shakemate.activity.common.ActivityStatusUtil;
import com.shakemate.activity.common.ApiResponse;
import com.shakemate.activity.dto.*;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityParticipant;
import com.shakemate.activity.entity.id.ActivityParticipantId;
import com.shakemate.activity.mapper.ActivityMapper;
import com.shakemate.activity.mapper.ActivityParticipantMapper;
import com.shakemate.activity.repository.ActivityParticipantRepository;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.service.ActivityParticipantService;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityParticipantServiceImpl implements ActivityParticipantService {

    private final ActivityParticipantRepository participantRepository;
    private final UserRepository usersRepository;
    private final ActivityRepository activityRepository;
    private final ActivityParticipantMapper mapper;
    private final ActivityMapper activityMapper;


    @Override
    public ActivityParticipantDTO ActivityParticipantById(ActivityParticipantId id) {
        ActivityParticipant entity = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("查無資料"));
        ActivityParticipantDTO dto = mapper.toDTO(entity);
        dto.setParticipantId(entity.getId().getParticipantId());
        dto.setActivityId(entity.getId().getActivityId());
        return dto;
    }

    @Override
    public List<ActivityParticipantDTO> getAllActivityParticipant() {
        return participantRepository.findAll()
                .stream()
                .map(entity -> {
                    ActivityParticipantDTO dto = mapper.toDTO(entity);
                    dto.setParticipantId(entity.getId().getParticipantId());
                    dto.setActivityId(entity.getId().getActivityId());
                    return dto;
                })
                .toList();
    }

    @Override
    public ActivityParticipantDTO createActivityParticipant(ActivityParticipantCreateDTO createDTO) {
        Users user = usersRepository.findById(createDTO.getParticipantId())
                .orElseThrow(() -> new EntityNotFoundException("找不到參與者"));
        Activity activity = activityRepository.findById(createDTO.getActivityId())
                .orElseThrow(() -> new EntityNotFoundException("找不到活動"));

        ActivityParticipant entity = mapper.toEntity(createDTO);
        entity.setParticipant(user);
        entity.setActivity(activity);
        entity.setId(new ActivityParticipantId(user.getUserId(), activity.getActivityId()));

        participantRepository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public ActivityParticipantDTO updateActivityParticipant(ActivityParticipantId id, ActivityParticipantUpdateDTO updateDTO) {
        ActivityParticipant entity = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("查無資料"));

        // 利用 Mapper 更新 entity（忽略 null 欄位）
        mapper.updateEntityFromDto(updateDTO, entity);



        ActivityParticipant updated = participantRepository.save(entity);

        ActivityParticipantDTO dto = mapper.toDTO(updated);
        dto.setParticipantId(updated.getId().getParticipantId());
        dto.setActivityId(updated.getId().getActivityId());
        return dto;
    }

    @Override
    public void deleteActivityParticipant(ActivityParticipantId id) {
        boolean exists = participantRepository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("查無資料");
        }
        participantRepository.deleteById(id);
    }

    @Override
    public Page<ActivityParticipantDTO> getApplicants(Integer activityId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivityParticipant> pageResult = participantRepository.findAllApplicantsByActivityId(activityId, pageable);
        return pageResult.map(mapper::toDTO);
    }

    @Override
    public Page<ActivityParticipantDTO> getAcceptedMembers(Integer activityId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivityParticipant> pageResult = participantRepository.findAllAcceptedByActivityId(activityId, pageable);
        return pageResult.map(mapper::toDTO);
    }

    @Override
    public Page<ActivityParticipantDTO> getActivityReviews(Integer activityId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivityParticipant> pageResult = participantRepository.findAllReviewsByActivityId(activityId, pageable);
        return pageResult.map(mapper::toDTO);
    }

    public Double getAverageRating(Integer activityId) {
        return participantRepository.findAverageRatingByActivityId(activityId);
    }










}
