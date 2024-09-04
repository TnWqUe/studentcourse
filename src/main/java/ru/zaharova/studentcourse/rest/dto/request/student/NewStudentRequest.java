package ru.zaharova.studentcourse.rest.dto.request.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewStudentRequest {
    private String studGroup;
    private Long departmentId;
    private Long userId;
}
