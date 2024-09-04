package ru.zaharova.studentcourse.rest.dto.request.teacher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTeacherRequest {
    private String mpeiUrl;
    private Long userId;
}
