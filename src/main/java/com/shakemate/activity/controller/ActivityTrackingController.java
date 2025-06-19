package com.shakemate.activity.controller;

import com.shakemate.activity.dto.ActivityTrackingDTO;
import com.shakemate.activity.repository.ActivityTrackingRepository;
import com.shakemate.activity.entity.id.ActivityTrackingId;
import com.shakemate.activity.entity.ActivityTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity-tracking")
public class ActivityTrackingController {

    @Autowired
    private ActivityTrackingRepository trackingRepo;

    // 新增追蹤紀錄
    @PostMapping
    public ResponseEntity<ActivityTrackingDTO> addTracking(@RequestBody ActivityTracking tracking) {

        System.out.println("addTracking...");
        ActivityTrackingId id = tracking.getId();

        if (trackingRepo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }

        tracking.setTrackingTime(new Timestamp(System.currentTimeMillis()));
        ActivityTracking saved = trackingRepo.save(tracking);
        ActivityTrackingDTO dto = new ActivityTrackingDTO();
        dto.setActivityId(saved.getActivity().getActivityId());
        dto.setUserId(saved.getUser().getUserId());
        dto.setTrackingState(saved.getTrackingState());
        dto.setTrackingTime(saved.getTrackingTime());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<ActivityTrackingDTO> getAllActivityTracking() {
        List<ActivityTracking> activityTracking = trackingRepo.findAll();
        return activityTracking.stream().map(actTr -> {
            ActivityTrackingDTO dto = new ActivityTrackingDTO();
            dto.setActivityId(actTr.getActivity().getActivityId());
            dto.setUserId(actTr.getUser().getUserId());
            dto.setTrackingState(actTr.getTrackingState());
            dto.setTrackingTime(actTr.getTrackingTime());
            return dto;
        }).toList();
    }

    // 查某會員的所有追蹤活動（狀態 0）
    @GetMapping("/user/{userId}")
    public List<ActivityTrackingDTO> getTrackingByUser(@PathVariable Integer userId) {
        List<ActivityTracking> list = trackingRepo.findByIdUserIdAndTrackingState(userId, (byte) 0);
        List<ActivityTrackingDTO> dtoList = new ArrayList<>();
        for (ActivityTracking vo : list) {
            ActivityTrackingDTO dto = new ActivityTrackingDTO();
            dto.setActivityId(vo.getId().getActivityId());
            dto.setUserId(vo.getId().getUserId());
            dto.setTrackingTime(vo.getTrackingTime());
            dto.setTrackingState(vo.getTrackingState());
            dtoList.add(dto);
        }
        return dtoList;
    }

    //  查某活動被哪些人追蹤（狀態 0）
    @GetMapping("/activity/{activityId}")
    public List<ActivityTrackingDTO> getTrackingByActivity(@PathVariable Integer activityId) {
        List<ActivityTracking> list = trackingRepo.findByIdActivityIdAndTrackingState(activityId, (byte) 0);
        List<ActivityTrackingDTO> dtoList = new ArrayList<>();
        for (ActivityTracking vo : list) {
            ActivityTrackingDTO dto = new ActivityTrackingDTO();
            dto.setActivityId(vo.getId().getActivityId());
            dto.setUserId(vo.getId().getUserId());
            dto.setTrackingTime(vo.getTrackingTime());
            dto.setTrackingState(vo.getTrackingState());
            dtoList.add(dto);
        }
        return dtoList;
    }

    //===

    // 取消追蹤（設為狀態 1）
    @PatchMapping("/{userId}/{activityId}/untrack")
    public ResponseEntity<?> untrack(@PathVariable Integer userId, @PathVariable Integer activityId) {
        ActivityTrackingId id = new ActivityTrackingId(activityId, userId);
        Optional<ActivityTracking> trackingOpt = trackingRepo.findById(id);

        if (trackingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ActivityTracking tracking = trackingOpt.get();
        tracking.setTrackingState((byte) 1);
        ActivityTracking saved = trackingRepo.save(tracking);
        ActivityTrackingDTO dto = new ActivityTrackingDTO();
        dto.setActivityId(saved.getId().getActivityId());
        dto.setUserId(saved.getId().getUserId());
        dto.setTrackingTime(saved.getTrackingTime());
        dto.setTrackingState(saved.getTrackingState());


        return ResponseEntity.ok(dto);
    }

    // 刪除追蹤紀錄
    @DeleteMapping("/{userId}/{activityId}")
    public ResponseEntity<?> deleteTracking(@PathVariable Integer userId, @PathVariable Integer activityId) {
        ActivityTrackingId id = new ActivityTrackingId(activityId, userId);

        if (!trackingRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        trackingRepo.deleteById(id);
        return ResponseEntity.ok("已刪除追蹤紀錄");
    }



}
