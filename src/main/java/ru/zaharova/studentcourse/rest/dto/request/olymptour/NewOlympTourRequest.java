package ru.zaharova.studentcourse.rest.dto.request.olymptour;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NewOlympTourRequest {
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
    private String name;
}