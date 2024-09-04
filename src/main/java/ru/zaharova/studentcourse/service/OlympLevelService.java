package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.olympLevel.EditOlympLevelRequest;
import ru.zaharova.studentcourse.rest.dto.request.olympLevel.NewOlympLevelRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympLevel.OlympLevelDto;

public interface OlympLevelService {
    Long addNewOlympLevel(NewOlympLevelRequest request);

    OlympLevelDto edit(Long id, EditOlympLevelRequest request);

    void deleteById(Long id);

    OlympLevelDto findById(Long id);
}
