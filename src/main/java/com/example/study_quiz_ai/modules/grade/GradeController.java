package com.example.study_quiz_ai.modules.grade;

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
import com.example.study_quiz_ai.modules.grade.dto.CreateOrEditGradeDto;
import com.example.study_quiz_ai.modules.grade.dto.GetGradeForViewDto;
import com.example.study_quiz_ai.modules.grade.service.GradeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grades")
@Tag(name = "Grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrEditGradeDto>> create(@RequestBody @Valid CreateOrEditGradeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.create(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetGradeForViewDto>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(gradeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetGradeForViewDto>> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gradeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateOrEditGradeDto>> update(@PathVariable Long id,
            @RequestBody @Valid CreateOrEditGradeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(gradeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        gradeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
