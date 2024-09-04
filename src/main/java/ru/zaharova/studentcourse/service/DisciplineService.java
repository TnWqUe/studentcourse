package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.discipline.EditDisciplineRequest;
import ru.zaharova.studentcourse.rest.dto.request.discipline.NewDisciplineRequest;
import ru.zaharova.studentcourse.rest.dto.response.discipline.DisciplineDto;

public interface DisciplineService {
    Long addNewDiscipline(NewDisciplineRequest request);

    DisciplineDto edit(Long id, EditDisciplineRequest request);

    void deleteById(Long id);

    DisciplineDto findById(Long id);
}
