package com.shakemate.activity.controller;

import com.shakemate.activity.dto.ActivityCommentDTO;
import com.shakemate.activity.dto.ActivityDTO;
import com.shakemate.activity.repository.ActivityCommentRepository;
import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.vo.ActivityCommentVO;
import com.shakemate.activity.vo.ActivityVO;
import com.shakemate.user.model.Users;
import com.shakemate.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity-comments")
public class ActivityCommentController {

    @Autowired
    private ActivityCommentRepository commentRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody ActivityCommentVO comment) {
        // 驗證活動是否存在
        if (comment.getActivity() == null || comment.getActivity().getActivityId() == null) {
            return ResponseEntity.badRequest().body("Missing activity ID");
        }
        if (comment.getUser() == null || comment.getUser().getUserId() == null) {
            return ResponseEntity.badRequest().body("Missing user ID");
        }

        // 綁定活動與使用者
//        activityRepo.findById(comment.getActivity().getActivityId()).ifPresent(comment::setActivity);
//        userRepo.findById(comment.getUser().getUserId()).ifPresent(comment::setUser);
        Optional<ActivityVO> activityOpt = activityRepo.findById(comment.getActivity().getActivityId());
        if (activityOpt.isPresent()) {
            comment.setActivity(activityOpt.get());
        }
        Optional<Users> userOpt = userRepo.findById(comment.getUser().getUserId());
        if (userOpt.isPresent()) {
            comment.setUser(userOpt.get());
        }


        // 處理父留言（可為 null）
        if (comment.getParentComment() != null && comment.getParentComment().getCommentId() != null) {
            Optional<ActivityCommentVO> parentOpt = commentRepo.findById(comment.getParentComment().getCommentId());
            comment.setParentComment(parentOpt.orElse(null));
        }

        ActivityCommentVO saved = commentRepo.save(comment);
        ActivityCommentDTO dto = new ActivityCommentDTO();
        dto.setCommentId(saved.getCommentId());
        dto.setUserId(saved.getUser().getUserId());
        dto.setActivityId(saved.getActivity().getActivityId());
        dto.setContent(saved.getContent());
        dto.setCommentTime(saved.getCommentTime());
        dto.setParentCommentId(saved.getParentComment() != null ? saved.getParentComment().getCommentId() : null);
        dto.setCommentCount(saved.getCommentCount());

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<ActivityCommentDTO> getAllComments() {
        List<ActivityCommentVO> actComment = commentRepo.findAll();
        return actComment.stream().map(comment -> {
            ActivityCommentDTO dto = new ActivityCommentDTO();
            dto.setCommentId(comment.getCommentId());
            dto.setUserId(comment.getUser().getUserId());
            dto.setActivityId(comment.getActivity().getActivityId());
            dto.setContent(comment.getContent());
            dto.setCommentTime(comment.getCommentTime());
            dto.setParentCommentId(comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null);
            dto.setCommentCount(comment.getCommentCount());
            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityCommentDTO> getById(@PathVariable Integer id) {
        Optional<ActivityCommentVO> optional = commentRepo.findById(id);
        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        ActivityCommentVO actCommentVO = optional.get();
        ActivityCommentDTO dto = new ActivityCommentDTO();
        dto.setCommentId(actCommentVO.getCommentId());
        dto.setUserId(actCommentVO.getUser().getUserId());
        dto.setActivityId(actCommentVO.getActivity().getActivityId());
        dto.setContent(actCommentVO.getContent());
        dto.setCommentTime(actCommentVO.getCommentTime());
        dto.setParentCommentId(actCommentVO.getParentComment() != null ? actCommentVO.getParentComment().getCommentId() : null);
        dto.setCommentCount(actCommentVO.getCommentCount());

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ActivityCommentDTO> updateComment(@PathVariable Integer id, @RequestBody ActivityCommentVO updateData) {
        Optional<ActivityCommentVO> opt = commentRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ActivityCommentVO existingActComment = opt.get();
        existingActComment.setContent(updateData.getContent());
        commentRepo.save(existingActComment);

        ActivityCommentDTO dto = new ActivityCommentDTO();
        dto.setCommentId(existingActComment.getCommentId());
        dto.setUserId(existingActComment.getUser().getUserId());
        dto.setActivityId(existingActComment.getActivity().getActivityId());
        dto.setContent(existingActComment.getContent());
        dto.setCommentTime(existingActComment.getCommentTime());
        dto.setParentCommentId(existingActComment.getParentComment() != null ? existingActComment.getParentComment().getCommentId() : null);
        dto.setCommentCount(existingActComment.getCommentCount());

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        if (!commentRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        commentRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}