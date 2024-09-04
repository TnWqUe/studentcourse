package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.dayweek.EditDayWeekRequest;
import ru.zaharova.studentcourse.rest.dto.request.dayweek.NewDayWeekRequest;
import ru.zaharova.studentcourse.rest.dto.response.dayweek.DayWeekDto;

public interface DayWeekService {
    Long addNewDayWeek(NewDayWeekRequest request);

    DayWeekDto edit(Long id, EditDayWeekRequest request);

    void deleteById(Long id);

    DayWeekDto findById(Long id);
}