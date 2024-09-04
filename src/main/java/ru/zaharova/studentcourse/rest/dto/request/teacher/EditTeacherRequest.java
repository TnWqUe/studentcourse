package ru.zaharova.studentcourse.rest.dto.request.teacher;

import lombok.Data;

import java.util.List;

@Data
public class EditTeacherRequest {
    private Long id;
    private String mpeiUrl;
    private List<Long> teamIds;
    private List<Long> electiveCourseIds;
}
