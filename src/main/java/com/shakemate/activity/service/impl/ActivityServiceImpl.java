package com.shakemate.activity.service.impl;

import com.shakemate.activity.common.ActivityStatusUtil;
import com.shakemate.activity.dto.*;
import com.shakemate.activity.entity.Activity;
import com.shakemate.activity.entity.ActivityParticipant;
import com.shakemate.activity.mapper.ActivityCardMapper;
import com.shakemate.activity.mapper.ActivityMapper;
import com.shakemate.activity.repository.ActivityParticipantRepository;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.service.ActivityService;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityParticipantRepository activityParticipantRepository;
    private final UserRepository userRepository;
    private final ActivityMapper activityMapper;
    private final ActivityCardMapper activityCardMapper;

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

    @Override
    public Page<Activity> getFilteredActivities(int userAge, int userGender, int page, int size, String sort) {
        // 若是 spots 則用 custom query，不走 sortSpec
        if ("spots".equals(sort)) {
            Pageable pageable = PageRequest.of(page, size); // 不傳 Sort
            return activityRepository.findByUserAgeGenderOrderBySpotsLeft(userAge, userGender, pageable);
        }

        // 其餘照原有邏輯
        Sort sortSpec = getSortSpec(sort);
        Pageable pageable = PageRequest.of(page, size, sortSpec);
        return activityRepository.findByUserAgeGender(userAge, userGender, pageable);
    }


    private Sort getSortSpec(String sort) {
        return switch (sort) {
            case "earliest" -> Sort.by("createdTime").ascending();
            case "signup" -> Sort.by(Sort.Order.desc("signupCount"), Sort.Order.desc("createdTime"));
            case "deadline" -> Sort.by(Sort.Order.asc("regEndTime"), Sort.Order.desc("createdTime"));
            case "spots" -> Sort.unsorted(); // 交由 JPQL 決定排序
            default -> Sort.by(Sort.Order.desc("createdTime")); // latest
        };
    }

    // Card
    // 使用者為團主
    @Override
    public Page<ActivityCardDTO> getOwnerActivityCards(Integer userId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, getSortSpec(sort));
        Page<Activity> activityPage = activityRepository.findByUserId(userId, pageable);

        return activityPage.map(activity -> {
            ActivityCardDTO dto = activityCardMapper.toCardDTO(activity);
            dto.setActivityStatus(ActivityStatusUtil.calculateStatus(activity));
            return dto;
        });
    }

    // 自訂排序條件
    private Sort getSortOwnerSpec(String sort) {
        return switch (sort) {
            case "earliest" -> Sort.by("createdTime").ascending();
            case "latest" -> Sort.by("createdTime").descending();
            default -> Sort.by("createdTime").descending();
        };
    }

    // 使用者為團員
    @Override
    public Page<ActivityCardDTO> getAllMemberActivityCards(Integer userId, int page, int size, String sort) {
        Sort sortSpec = getSortMemberSpec(sort);
        Pageable pageable = PageRequest.of(page, size, sortSpec);
        Page<Activity> activityPage = activityRepository.findAllMemberActivities(userId, pageable);

        return activityPage.map(activity -> {
            ActivityCardDTO dto = activityCardMapper.toCardDTO(activity);
            dto.setActivityStatus(ActivityStatusUtil.calculateStatus(activity));
            return dto;
        });

    }

    // 自訂排序條件
    private Sort getSortMemberSpec(String sort) {
        Sort baseSort = switch (sort) {
            case "earliest" -> Sort.by("createdTime").ascending();
            case "signup" -> Sort.by(Sort.Order.desc("signupCount"), Sort.Order.desc("createdTime"));
            case "deadline" -> Sort.by(Sort.Order.asc("regEndTime"), Sort.Order.desc("createdTime"));
            default -> Sort.by(Sort.Order.desc("createdTime"));
        };
        // 把排序欄位都加上 activity. 前綴
        return Sort.by(baseSort.stream()
                .map(order -> new Sort.Order(order.getDirection(), "activity." + order.getProperty()))
                .toList()
        );
    }

    @Override
    public Page<ActivityCardDTO> getTrackedOngoingActivityCards(Integer userId, int page, int size, String sort) {
        Sort sortSpec = getSortTrackingSpec(sort);
        Pageable pageable = PageRequest.of(page, size, sortSpec);
        Page<Activity> activityPage = activityRepository.findTrackedOngoingActivities(userId, pageable);

        return activityPage.map(activity -> {
            ActivityCardDTO dto = activityCardMapper.toCardDTO(activity);
            dto.setActivityStatus(ActivityStatusUtil.calculateStatus(activity));
            return dto;
        });

    }

    private Sort getSortTrackingSpec(String sort) {
        Sort baseSort = switch (sort) {
            case "earliest" -> Sort.by("createdTime").ascending();
            case "signup" -> Sort.by(Sort.Order.desc("signupCount"), Sort.Order.desc("createdTime"));
            case "deadline" -> Sort.by(Sort.Order.asc("regEndTime"), Sort.Order.desc("createdTime"));
            default -> Sort.by(Sort.Order.desc("createdTime"));
        };
        // 把排序欄位都加上 activity. 前綴
        return Sort.by(baseSort.stream()
                .map(order -> new Sort.Order(order.getDirection(), "activity." + order.getProperty()))
                .toList()
        );
    }



    public Page<ActivityCardDTO> getVisibleActivitiesForUser(
            Integer userId, int userAge, int userGender, Pageable pageable) {

        Page<Activity> activityPage = activityRepository.findByUserAgeGender(userAge, userGender, pageable);

        List<Integer> activityIds = activityPage.getContent().stream()
                .map(Activity::getActivityId)
                .toList();

        List<ActivityParticipant> participantList =
                activityParticipantRepository.findByParticipantUserIdAndActivityActivityIdIn(userId, activityIds);

        Map<Integer, Byte> statusMap = participantList.stream()
                .collect(Collectors.toMap(
                        ap -> ap.getActivity().getActivityId(),
                        ActivityParticipant::getParStatus
                ));

        List<ActivityCardDTO> dtoList = activityPage.getContent().stream()
                .map(a -> {
                    Byte parStatus = statusMap.get(a.getActivityId());
                    String joinStatus;

                    if (parStatus == null) {
                        joinStatus = "NOT_JOINED";
                    } else {
                        switch (parStatus) {
                            case 0: joinStatus = "PENDING"; break;
                            case 1: joinStatus = "CANCELED"; break;
                            case 2: joinStatus = "APPROVED"; break;
                            case 3: joinStatus = "LEFT"; break;
                            default: joinStatus = "UNKNOWN";
                        }
                    }

                    return ActivityCardDTO.builder()
                            .activityId(a.getActivityId())
                            .title(a.getTitle())
                            .createdTime(a.getCreatedTime())
                            .remainingQuota(a.getMaxPeople() - a.getSignupCount())
                            .activEndTime(a.getRegEndTime())
                            .joinStatus(joinStatus)
                            .build();
                })
                .toList();

        return new PageImpl<>(dtoList, pageable, activityPage.getTotalElements());
    }












}
