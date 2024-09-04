package ru.zaharova.studentcourse.rest.dto.request.electivecourse;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class NewElectiveCourseRequest {
    private String name;
    private LocalDate beginDate;
    private LocalTime beginTime;
    private String cabinet;
    private String description;
    private Long dayWeekId;
}
