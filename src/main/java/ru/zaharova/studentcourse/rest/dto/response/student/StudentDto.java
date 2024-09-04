package ru.zaharova.studentcourse.rest.dto.response.student;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentDto {
    private String studGroup;
    private Long departmentId;
    private List<Long> electiveCoursesIds;
    private List<Long> teamsIds;
}