package com.shakemate.activity.controller;

import com.shakemate.activity.repository.ActivityRepository;
import com.shakemate.activity.entity.ActivityQuestion;
import com.shakemate.activity.repository.ActivityQuestionRepository;

import com.shakemate.activity.entity.Activity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class ActivityQuestionController {


    private final ActivityQuestionRepository repository;
    private final ActivityRepository activityRepository;


    // 依問題ID取得問題
    @GetMapping("/{id}")
    public ResponseEntity<ActivityQuestion> getQuestionById(@PathVariable Integer id) {
        Optional<ActivityQuestion> question = repository.findById(id);
        return question.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 依活動ID取得問題列表
    @GetMapping("/byActivity/{activityId}")
    public List<ActivityQuestion> getQuestionsByActivityId(@PathVariable Integer activityId) {
        return repository.findByActivity_ActivityId(activityId);
    }

    // 新增問題
    @PostMapping
    public ActivityQuestion createQuestion(@RequestBody ActivityQuestion questionVO) {
        // 這邊可加驗證邏輯
        return repository.save(questionVO);
    }

    // 修改問題
    @PutMapping("/{id}")
    public ResponseEntity<ActivityQuestion> updateQuestion(@PathVariable Integer id,
                                                           @RequestBody ActivityQuestion updatedQuestion) {
        return repository.findById(id)
                .map(question -> {
                    // 從 updatedQuestion 拿到 activityId
                    Integer newActivityId = updatedQuestion.getActivity().getActivityId();

                    // 找出對應的 ActivityVO 物件
                    Activity activity = activityRepository.findById(newActivityId)
                            .orElseThrow(() -> new RuntimeException("Activity not found"));

                    // 設定新的 ActivityVO
                    question.setActivity(activity);

                    question.setQuestionText(updatedQuestion.getQuestionText());
                    question.setQuestionOrder(updatedQuestion.getQuestionOrder());

                    repository.save(question);
                    return ResponseEntity.ok(question);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 刪除問題
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
