package ru.zaharova.studentcourse.rest.dto.request.team;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTeamRequest {
    private String name;
    private Long teacherId;
    private String description;
}
