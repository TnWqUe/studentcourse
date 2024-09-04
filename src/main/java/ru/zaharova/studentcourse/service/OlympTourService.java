package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.olymptour.EditOlympTourRequest;
import ru.zaharova.studentcourse.rest.dto.request.olymptour.NewOlympTourRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympTour.OlympTourDto;

public interface OlympTourService {
    Long addNewOlympTour(NewOlympTourRequest request);

    OlympTourDto edit(Long id, EditOlympTourRequest request);

    void deleteById(Long id);

    OlympTourDto findById(Long id);
}
