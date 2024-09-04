package ru.zaharova.studentcourse.rest.dto.response.team;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamDto {
    private String name;
    private Long teacherId;
    private List<Long> studentsIds;
    private List<Long> olympToursIds;
    private String description;
}
