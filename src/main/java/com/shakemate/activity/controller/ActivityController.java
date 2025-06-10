package com.shakemate.activity.controller;

import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.vo.ActivityVO;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    // 取得所有活動
    @GetMapping
    public List<ActivityDTO> getAllActivities() {
        List<ActivityVO> activities = activityRepository.findAll();
        return activities.stream().map(activity -> {
            ActivityDTO dto = new ActivityDTO();
            dto.setActivityId(activity.getActivityId());
            dto.setTitle(activity.getTitle());
            dto.setUserId(activity.getUser() != null ? activity.getUser().getUserId() : null);
            dto.setCreatedTime(activity.getCreatedTime());
            dto.setUpdatedTime(activity.getUpdatedTime());
            return dto;
        }).toList();
    }

    // 依ID取得活動
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable Integer id) {
        Optional<ActivityVO> optional = activityRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        ActivityVO activity = optional.get();

        ActivityDTO dto = new ActivityDTO();
        dto.setActivityId(activity.getActivityId());
        dto.setTitle(activity.getTitle());
        dto.setUserId(activity.getUser().getUserId()); // 只取 userId，不回傳整包 user
        dto.setCreatedTime(activity.getCreatedTime());
        dto.setUpdatedTime(activity.getUpdatedTime());

        return ResponseEntity.ok(dto);
    }

    // 新增活動
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityVO activity) {
        // 先取得 userId（活動裡的 user 物件必須有 userId）
        Integer userId = activity.getUser() != null ? activity.getUser().getUserId() : null;
        if (userId == null) {
            // userId 沒傳，回 400 Bad Request
            return ResponseEntity.badRequest().build();
        }

        // 用 Optional 找 User
        Optional<Users> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            // 找不到 user，回 400 或 404（看需求）
            return ResponseEntity.badRequest().body(null);
        }

        // 找到 user，取得物件
        Users user = userOpt.get();

        // 把完整 user 設回活動
        activity.setUser(user);
        activity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        activity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));


        // 儲存活動
        ActivityVO savedActivity = activityRepository.save(activity);

        ActivityDTO dto = new ActivityDTO();
        dto.setActivityId(savedActivity.getActivityId());
        dto.setTitle(savedActivity.getTitle());
        dto.setUserId(savedActivity.getUser().getUserId()); // 只取 userId，不回傳整包 user
        dto.setCreatedTime(activity.getCreatedTime());
        dto.setUpdatedTime(activity.getUpdatedTime());


        return ResponseEntity.ok(dto);

        // 回傳成功，附上新增的活動
//        return ResponseEntity.ok(savedActivity);
    }

    // 更新活動
    @PatchMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Integer id, @RequestBody ActivityVO activityDetails) {
        Optional<ActivityVO> optionalActivity = activityRepository.findById(id);
        if (optionalActivity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ActivityVO existingActivity = optionalActivity.get();

        // 更新可變更欄位
        existingActivity.setTitle(activityDetails.getTitle());
        existingActivity.setContent(activityDetails.getContent());
        existingActivity.setImageUrl(activityDetails.getImageUrl());
        existingActivity.setLocation(activityDetails.getLocation());
        existingActivity.setActivityStatus(activityDetails.getActivityStatus());
        existingActivity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

        // 如果有傳 userId，要重新關聯 user
//        if (activityDetails.getUser() != null && activityDetails.getUser().getUserId() != null) {
//            Optional<Users> userOpt = userRepository.findById(activityDetails.getUser().getUserId());
//            if (userOpt.isPresent()) {
//                existingActivity.setUser(userOpt.get());
//            } else {
//                return ResponseEntity.badRequest().body(null); // 傳入的 userId 不存在
//            }
//        }

        ActivityVO updatedActivity = activityRepository.save(existingActivity);

        ActivityDTO dto = new ActivityDTO();
        dto.setActivityId(updatedActivity.getActivityId());
        dto.setTitle(updatedActivity.getTitle());
        dto.setUserId(updatedActivity.getUser().getUserId()); // 只取 userId，不回傳整包 user
        dto.setCreatedTime(updatedActivity.getCreatedTime());
        dto.setUpdatedTime(updatedActivity.getUpdatedTime());



        return ResponseEntity.ok(dto);


//        return ResponseEntity.ok(updatedActivity);
    }

    // 刪除活動
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) {

        if (!activityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        activityRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
