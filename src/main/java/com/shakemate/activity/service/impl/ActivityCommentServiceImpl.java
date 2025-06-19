package com.shakemate.activity.service.impl;

import com.shakemate.activity.dto.ActivityCommentCreateDTO;
import com.shakemate.activity.dto.ActivityCommentDTO;
import com.shakemate.activity.dto.ActivityCommentUpdateDTO;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityComment;
import com.shakemate.activity.mapper.ActivityCommentMapper;
import com.shakemate.activity.repository.ActivityCommentRepository;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.service.ActivityCommentService;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityCommentServiceImpl implements ActivityCommentService {

    private final ActivityCommentRepository activityCommentRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityCommentMapper activityCommentMapper;

    @Override
    public ActivityCommentDTO getById(Integer id) {
        ActivityComment activityComment = activityCommentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("找不到該活動留言"));
        return activityCommentMapper.toDto(activityComment);
    }

    @Override
    public List<ActivityCommentDTO> getAll() {
        List<ActivityComment> list = activityCommentRepository.findAll();
        return list.stream()
                .map(activityCommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityCommentDTO create(ActivityCommentCreateDTO createDTO) {

        // createDTO -> Entity
        ActivityComment activityComment = activityCommentMapper.toEntity(createDTO);

        // activity
        Activity activity = activityRepository.findById(createDTO.getActivityId())
                .orElseThrow(() -> new EntityNotFoundException("找不到該活動"));
        activityComment.setActivity(activity);
        // user
        Users user = userRepository.findById(createDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("找不到該使用者"));
        activityComment.setUser(user);

        ActivityComment parentComment = null;
        // parentComment
        if(createDTO.getParentCommentId() != null) {
            parentComment = activityCommentRepository.findById(createDTO.getParentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException("找不到該父留言"));
        }
        activityComment.setParentComment(parentComment);

        // save
        ActivityComment saved = activityCommentRepository.save(activityComment);

        // Entity -> DTO
        return activityCommentMapper.toDto(saved);


    }

    @Override
    public ActivityCommentDTO update(Integer id, ActivityCommentUpdateDTO updateDTO) {
        ActivityComment entity = activityCommentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("查無資料"));

        if(updateDTO.getContent() != null && updateDTO.getContent().trim().isEmpty()){
            // throw...
            throw new IllegalArgumentException("留言內容不可為空或只包含空白字元");
        }

        activityCommentMapper.updateEntityFromDto(updateDTO, entity);

        ActivityComment parentComment = null;
        // parentComment
        if(updateDTO.getParentCommentId() != null) {
            parentComment = activityCommentRepository.findById(updateDTO.getParentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException("找不到該父留言"));
        }
        entity.setParentComment(parentComment);


        ActivityComment updated = activityCommentRepository.save(entity);
        ActivityCommentDTO dto = activityCommentMapper.toDto(updated);

        return dto;

    }

    @Override
    public void delete(Integer id) {
        boolean exists = activityCommentRepository.existsById(id);
        if(!exists) {
            throw new EntityNotFoundException("查無資料");
        }
        activityCommentRepository.deleteById(id);
    }
}
