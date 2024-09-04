package ru.zaharova.studentcourse.rest.dto.response.discipline;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class DisciplineDto {
    private String name;
    private List<Long> electiveCoursesIds;
}
