package ru.zaharova.studentcourse.rest.dto.request.electivecourse;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class EditElectiveCourseRequest {
    private Long id;
    private String name;
    private LocalDate beginDate;
    private LocalTime beginTime;
    private String cabinet;
    private String description;
    private Long dayWeekId;
    private List<Long> studentsId;
    private List<Long> olympToursId;
    private List<Long> disciplinesIds;
    private List<Long> teachersIds;
}