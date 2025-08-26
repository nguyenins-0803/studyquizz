package com.example.study_quiz_ai.modules.topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.core.base.PagedAndResult;
import com.example.study_quiz_ai.modules.topic.dto.CreateOrEditTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicDto;
import com.example.study_quiz_ai.modules.topic.dto.GetTopicForFilterDto;
import com.example.study_quiz_ai.modules.topic.service.TopicService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "Topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid CreateOrEditTopicDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.create(dto));
    }

    @GetMapping()
    public ResponseEntity<PagedAndResult<GetTopicDto>> getAll(@ModelAttribute @Valid GetTopicForFilterDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getAll(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTopicDto> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CreateOrEditTopicDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
