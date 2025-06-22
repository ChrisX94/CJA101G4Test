package com.shakemate.activity.service.impl;

import com.shakemate.activity.dto.ActivityQuestionCreateDTO;
import com.shakemate.activity.dto.ActivityQuestionDTO;
import com.shakemate.activity.dto.ActivityQuestionUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityQuestion;
import com.shakemate.activity.mapper.ActivityQuestionMapper;
import com.shakemate.activity.repository.ActivityQuestionRepository;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.service.ActivityQuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityQuestionServiceImpl implements ActivityQuestionService {

    private final ActivityQuestionRepository activityQuestionRepository;
    private final ActivityRepository activityRepository;
    private final ActivityQuestionMapper activityQuestionMapper;

    @Override
    public ActivityQuestionDTO getById(Integer id) {
        ActivityQuestion activityQuestion = activityQuestionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("找不到該活動問題"));
        return activityQuestionMapper.toDto(activityQuestion);
    }

    @Override
    public List<ActivityQuestionDTO> getAll() {
        List<ActivityQuestion> list = activityQuestionRepository.findAll();
        return list.stream()
                .map(activityQuestionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityQuestionDTO create(ActivityQuestionCreateDTO createDTO) {

        // createDTO -> Entity
        ActivityQuestion activityQuestion = activityQuestionMapper.toEntity(createDTO);

        // activity
        Activity activity = activityRepository.findById(createDTO.getActivityId())
                .orElseThrow(() -> new EntityNotFoundException("找不到該活動"));
        activityQuestion.setActivity(activity);

        // save
        ActivityQuestion saved = activityQuestionRepository.save(activityQuestion);

        // Entity -> DTO
        return activityQuestionMapper.toDto(saved);


    }

    @Override
    public ActivityQuestionDTO update(Integer id, ActivityQuestionUpdateDTO updateDTO) {
        ActivityQuestion entity = activityQuestionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("查無資料"));

        if(updateDTO.getQuestionText() != null && updateDTO.getQuestionText().trim().isEmpty()) {
            // throw...
            throw new IllegalArgumentException("問題內容不可為空或只包含空白字元");
        }

        activityQuestionMapper.updateEntityFromDto(updateDTO, entity);

        ActivityQuestion updated = activityQuestionRepository.save(entity);
        ActivityQuestionDTO dto = activityQuestionMapper.toDto(updated);

        return dto;
    }

    @Override
    public void delete(Integer id) {
        boolean exists = activityQuestionRepository.existsById(id);
        if(!exists) {
            throw new EntityNotFoundException("查無資料");
        }
        activityQuestionRepository.deleteById(id);

    }
}
