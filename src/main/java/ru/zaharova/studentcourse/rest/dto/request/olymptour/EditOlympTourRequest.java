package ru.zaharova.studentcourse.rest.dto.request.olymptour;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EditOlympTourRequest {
    private String name;
    private String olympUrl;
    private String organizer;
    private Integer toursAmount;
    private Integer tourNumber;
    private LocalDate startReg;
    private LocalDate endReg;
    private Boolean isOpenForRequests;
    private LocalDate beginTour;
    private LocalDate endTour;
    private String address;
    private String text;
    private Long olympLevelId;
    private Long olympFormatId;
    private List<Long> teamsIds;
    private List<Long> languagesIds;
    private List<Long> electiveCoursesIds;
}