package com.example.study_quiz_ai.modules.subject;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study_quiz_ai.core.base.ApiResponse;
import com.example.study_quiz_ai.modules.subject.dto.CreateOrEditSubjectDto;
import com.example.study_quiz_ai.modules.subject.dto.GetSubjectDto;
import com.example.study_quiz_ai.modules.subject.service.SubjectService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subjects")
@Tag(name = "Subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrEditSubjectDto>> create(
            @RequestBody @Valid CreateOrEditSubjectDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.create(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetSubjectDto>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetSubjectDto>> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateOrEditSubjectDto>> update(@PathVariable Long id,
            @RequestBody @Valid CreateOrEditSubjectDto dto) {
        return ResponseEntity.ok(subjectService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(subjectService.delete(id));
    }
}
