package com.shakemate.activity.controller;

import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.dto.ActivityParticipantDTO;
import com.shakemate.activity.repository.ActivityParticipantRepository;
import com.shakemate.activity.vo.ActivityParticipantId;
import com.shakemate.activity.vo.ActivityParticipantVO;
import com.shakemate.activity.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activityParticipants")
public class ActivityParticipantController {
    @Autowired
    private ActivityParticipantRepository repository;

    @GetMapping
    public List<ActivityParticipantDTO> getAllActivitiesParticipant() {
        List<ActivityParticipantVO> activityParticipants = repository.findAll();
        return activityParticipants.stream().map(activityPart -> {
            ActivityParticipantDTO dto = new ActivityParticipantDTO();
            dto.setActivityId(activityPart.getActivity().getActivityId());
            dto.setParticipantId(activityPart.getParticipant().getUserId());
            dto.setParStatus(activityPart.getParStatus());
            dto.setReviewContent(activityPart.getReviewContent());
            return dto;
        }).toList();
    }

    @GetMapping("/{participantId}/{activityId}")
    public ResponseEntity<ActivityParticipantDTO> getParticipant(
            @PathVariable Integer participantId,
            @PathVariable Integer activityId) {

        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
        Optional<ActivityParticipantVO> opt = repository.findById(id);

        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        ActivityParticipantVO vo = opt.get();
        ActivityParticipantDTO dto = new ActivityParticipantDTO();
        dto.setActivityId(activityId);
        dto.setParticipantId(participantId);
        dto.setParStatus(vo.getParStatus());
        dto.setReviewContent(vo.getReviewContent());

        return ResponseEntity.ok(dto);
//        return ResponseEntity.ok(opt.get());
    }


    @PostMapping
    public ResponseEntity<ActivityParticipantDTO> createParticipant(@RequestBody ActivityParticipantVO participant) {
        // 可先檢查是否已存在 (用 findById)
        System.out.println("➡️ 收到新增請求：" + participant);
        ActivityParticipantId id = participant.getId();

        if (repository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
//        Integer participantId = participant.getParticipant().getUserId();
//        Integer activityId = participant.getActivity().getActivityId();
//        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
//        Optional<ActivityParticipantVO> existing = repository.findById(id);
//        if(existing.isPresent()) {
//            // 已存在，可能回傳錯誤或進行其他處理
//            return ResponseEntity.badRequest().build();
//        }

        ActivityParticipantVO saved = repository.save(participant);
        ActivityParticipantDTO dto = new ActivityParticipantDTO();
        dto.setParticipantId(saved.getId().getParticipantId());
        dto.setActivityId(saved.getId().getActivityId());
        dto.setParStatus(saved.getParStatus());
        dto.setReviewContent(saved.getReviewContent());

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{participantId}/{activityId}")
    public ResponseEntity<ActivityParticipantDTO> updateParticipant(
            @PathVariable Integer participantId,
            @PathVariable Integer activityId,
            @RequestBody ActivityParticipantVO updates) {

        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
        Optional<ActivityParticipantVO> opt = repository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        ActivityParticipantVO existing = opt.get();
        if(updates.getAdmReviewTime() != null) existing.setAdmReviewTime(updates.getAdmReviewTime());
        if(updates.getRating() != null) existing.setRating(updates.getRating());
        if (updates.getParStatus() != null) existing.setParStatus(updates.getParStatus());
        if (updates.getReviewContent() != null) existing.setReviewContent(updates.getReviewContent());
        if(updates.getReviewTime() != null) existing.setReviewTime(updates.getReviewTime());

        // 其他欄位照需求更新

        ActivityParticipantVO saved = repository.save(existing);
        ActivityParticipantDTO dto = new ActivityParticipantDTO();
        dto.setActivityId(activityId);
        dto.setParticipantId(participantId);
        dto.setParStatus(saved.getParStatus());
        dto.setReviewContent(saved.getReviewContent());

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{participantId}/{activityId}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Integer participantId, @PathVariable Integer activityId) {
        ActivityParticipantId id = new ActivityParticipantId(participantId, activityId);
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
