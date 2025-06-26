package com.shakemate.activity.ai.controller;

import com.shakemate.activity.ai.dto.ContentRequestDTO;
import com.shakemate.activity.ai.dto.TitleTagDTO;
import com.shakemate.activity.ai.service.AIAssistService;
import com.shakemate.activity.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIAssistController {

    private final AIAssistService aiAssistService;

    @PostMapping("/generate-title-tags")
    public ApiResponse<TitleTagDTO> generateTitleAndTags(@RequestBody ContentRequestDTO request) {
        return aiAssistService.generateWrappedResponse(request.getContent());
    }
}
