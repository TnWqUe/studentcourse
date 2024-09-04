package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.AbstractEntity;
import ru.zaharova.studentcourse.entity.DayWeek;
import ru.zaharova.studentcourse.exception.exceptions.dayweek.DayWeekFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.dayweek.DayWeekNotFoundException;
import ru.zaharova.studentcourse.repo.DayWeekRepo;
import ru.zaharova.studentcourse.rest.dto.request.dayweek.EditDayWeekRequest;
import ru.zaharova.studentcourse.rest.dto.request.dayweek.NewDayWeekRequest;
import ru.zaharova.studentcourse.rest.dto.response.dayweek.DayWeekDto;
import ru.zaharova.studentcourse.service.DayWeekService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayWeekServiceImpl implements DayWeekService {
    private final DayWeekRepo dayWeekRepo;

    @Override
    public Long addNewDayWeek(NewDayWeekRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new DayWeekFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        DayWeek dayWeek = new DayWeek();
        dayWeek.setName(request.getName());
        dayWeekRepo.saveAndFlush(dayWeek);
        return dayWeek.getId();
    }

    @Override
    public DayWeekDto edit(Long id, EditDayWeekRequest request) {

        Optional<DayWeek> dayWeekFromDb = dayWeekRepo.findById(id);

        if (dayWeekFromDb.isEmpty()) {
            throw new DayWeekNotFoundException("Не найден день недели с id = " + id);
        }

        if (!StringUtils.hasText(request.getName())) {
            throw new DayWeekFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

       /* DayWeek dayWeek = DayWeek.builder()
                .name(request.getName())
              // .electiveCourses(dayWeekFromDb.get().getElectiveCourses())
                .build();

        dayWeek.setId(id);*/
        DayWeek dayWeek = dayWeekFromDb.get();
        dayWeek.setName(request.getName());
        dayWeekRepo.save(dayWeek);
        return buildDto(dayWeek);
    }

    @Override
    public void deleteById(Long id) {
        if (!dayWeekRepo.existsById(id)) {
            throw new DayWeekNotFoundException("Не найден день недели с id = " + id);
        }
        dayWeekRepo.deleteById(id);
    }

    @Override
    public DayWeekDto findById(Long id) {
        List<DayWeek> all = dayWeekRepo.findAll();
        Optional<DayWeek> dayWeekFromDb = dayWeekRepo.findById(id);
        if (dayWeekFromDb.isEmpty()) {
            throw new DayWeekNotFoundException("Не найден день недели с id = " + id);
        }
        DayWeek dayWeek = dayWeekFromDb.get();
        return buildDto(dayWeek);
    }

    private DayWeekDto buildDto(DayWeek dayWeek) {
        DayWeekDto dayWeekDto = DayWeekDto.builder().name(dayWeek.getName()).build();

        if (dayWeek.getElectiveCourses() != null) {
            List<Long> electiveCoursesIds = dayWeek.getElectiveCourses().stream().map(AbstractEntity::getId).collect(Collectors.toList());
            dayWeekDto.setElectiveCoursesIds(electiveCoursesIds);
        }

        return dayWeekDto;
    }
}
