package com.example.study_quiz_ai.core.base;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedRequest {
    @Min(value = 0)
    private int page;

    @Min(value = 10)
    @Max(value = 100)
    private int size;

    @Nullable
    private String filterText;

    @Nullable
    private String sortBy;
}
