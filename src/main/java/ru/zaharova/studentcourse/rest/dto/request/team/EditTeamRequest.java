package ru.zaharova.studentcourse.rest.dto.request.team;

import lombok.Data;

import java.util.List;

@Data
public class EditTeamRequest {
    private Long id;
    private String name;
    private Long teacherId;
    private List<Long> studentsIds;
    private List<Long> olympToursIds;
    private String description;
}