package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.language.EditLanguageRequest;
import ru.zaharova.studentcourse.rest.dto.request.language.NewLanguageRequest;
import ru.zaharova.studentcourse.rest.dto.response.language.LanguageDto;

public interface LanguageService {
    Long addNewLanguage(NewLanguageRequest request);

    LanguageDto edit(Long id, EditLanguageRequest request);

    void deleteById(Long id);

    LanguageDto findById(Long id);
}
