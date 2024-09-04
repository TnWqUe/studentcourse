package ru.zaharova.studentcourse.rest.dto.response.teacher;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeacherDto {
    private String mpeiUrl;
    private List<Long> teamIds;
    private List<Long> electiveCourseIds;
}
