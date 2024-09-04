package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.AbstractEntity;
import ru.zaharova.studentcourse.entity.Discipline;
import ru.zaharova.studentcourse.exception.exceptions.discipline.DisciplineFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.discipline.DisciplineNotFoundException;
import ru.zaharova.studentcourse.repo.DisciplineRepo;
import ru.zaharova.studentcourse.rest.dto.request.discipline.EditDisciplineRequest;
import ru.zaharova.studentcourse.rest.dto.request.discipline.NewDisciplineRequest;
import ru.zaharova.studentcourse.rest.dto.response.discipline.DisciplineDto;
import ru.zaharova.studentcourse.service.DisciplineService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepo disciplineRepo;

    @Override
    public Long addNewDiscipline(NewDisciplineRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new DisciplineFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        Discipline discipline = new Discipline();
        discipline.setName(request.getName());
        disciplineRepo.saveAndFlush(discipline);
        return discipline.getId();
    }

    @Override
    public DisciplineDto edit(Long id, EditDisciplineRequest request) {
        Optional<Discipline> disciplineFromDb = disciplineRepo.findById(id);

        if (disciplineFromDb.isEmpty()) {
            throw new DisciplineNotFoundException("Не найдена дисциплина с id = " + id);
        }

        if (!StringUtils.hasText(request.getName())) {
            throw new DisciplineFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        Discipline discipline = disciplineFromDb.get();
        discipline.setName(request.getName());
        disciplineRepo.save(discipline);
        return buildDto(discipline);
    }

    @Override
    public void deleteById(Long id) {
        if (!disciplineRepo.existsById(id)) {
            throw new DisciplineNotFoundException("Не найдена дисциплина с id = " + id);
        }
        disciplineRepo.deleteById(id);
    }

    @Override
    public DisciplineDto findById(Long id) {
        Optional<Discipline> disciplineFromDb = disciplineRepo.findById(id);
        if (disciplineFromDb.isEmpty()) {
            throw new DisciplineNotFoundException("Не найдена дисциплина с id = " + id);
        }
        Discipline discipline = disciplineFromDb.get();
        return buildDto(discipline);
    }

    private DisciplineDto buildDto(Discipline discipline) {
        DisciplineDto disciplineDto = DisciplineDto.builder()
                .name(discipline.getName())
                .build();

        if (discipline.getElectiveCourses() != null) {
            List<Long> electiveCoursesIds = discipline.getElectiveCourses().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            disciplineDto.setElectiveCoursesIds(electiveCoursesIds);
        }

        return disciplineDto;
    }
}
