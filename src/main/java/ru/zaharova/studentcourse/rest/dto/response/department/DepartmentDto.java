package ru.zaharova.studentcourse.rest.dto.response.department;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartmentDto {
    private String name;
    private Long parentId;
    private List<Long> studentsIds;
}
