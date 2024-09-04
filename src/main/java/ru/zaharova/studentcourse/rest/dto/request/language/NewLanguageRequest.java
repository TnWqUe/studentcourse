package ru.zaharova.studentcourse.rest.dto.request.language;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewLanguageRequest {
    private String name;
}
