package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.olympformat.EditOlympFormatRequest;
import ru.zaharova.studentcourse.rest.dto.request.olympformat.NewOlympFormatRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympFormat.OlympFormatDto;

public interface OlympFormatService {
    Long addNewOlympFormat(NewOlympFormatRequest request);

    OlympFormatDto edit(Long id, EditOlympFormatRequest request);

    void deleteById(Long id);

    OlympFormatDto findById(Long id);
}
