package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.*;
import ru.zaharova.studentcourse.exception.exceptions.dayweek.DayWeekNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.discipline.DisciplineNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.electiveCourse.ElectiveCourseFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.electiveCourse.ElectiveCourseNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.olymptour.OlympTourNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.student.StudentNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.teacher.TeacherNotFoundException;
import ru.zaharova.studentcourse.repo.*;
import ru.zaharova.studentcourse.rest.dto.request.electivecourse.EditElectiveCourseRequest;
import ru.zaharova.studentcourse.rest.dto.request.electivecourse.NewElectiveCourseRequest;
import ru.zaharova.studentcourse.rest.dto.response.electiveCourse.ElectiveCourseDto;
import ru.zaharova.studentcourse.service.ElectiveCourseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectiveCourseServiceImpl implements ElectiveCourseService {
    private final ElectiveCourseRepo electiveCourseRepo;
    private final DayWeekRepo dayWeekRepo;
    private final DisciplineRepo disciplineRepo;
    private final StudentRepo studentRepo;
    private final TeacherRepo teacherRepo;
    private final OlympTourRepo olympTourRepo;

    @Override
    public Long addNewElectiveCourse(NewElectiveCourseRequest request) {
        if (!StringUtils.hasText(request.getCabinet()) ||
                !StringUtils.hasText(request.getName()) ||
                (request.getBeginTime() != null) ||
                (request.getDayWeekId() != null) ||
                (request.getBeginDate() != null)) {
            throw new ElectiveCourseFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (!dayWeekRepo.existsById(request.getDayWeekId())) {
            throw new DayWeekNotFoundException(
                    String.format("Добавление факультатива невозможно, когда день недели не найден. Day of week = %d",
                            request.getDayWeekId())
            );
        }

        ElectiveCourse electiveCourse = new ElectiveCourse();
        electiveCourse.setName(request.getName());
        electiveCourse.setCabinet(request.getCabinet());
        electiveCourse.setBeginDate(request.getBeginDate());
        electiveCourse.setBeginTime(request.getBeginTime());
        electiveCourse.setDescription(request.getDescription());
        electiveCourse.setBusyDay(dayWeekRepo.findById(request.getDayWeekId()).get());

        electiveCourseRepo.saveAndFlush(electiveCourse);
        return electiveCourse.getId();
    }

    @Override
    public ElectiveCourseDto edit(Long id, EditElectiveCourseRequest request) {
        Optional<ElectiveCourse> electiveCourseFromDb = electiveCourseRepo.findById(id);

        if (electiveCourseFromDb.isEmpty()) {
            throw new ElectiveCourseNotFoundException("Не найден факультатив с id = " + id);
        }

        if (!StringUtils.hasText(request.getCabinet()) ||
                !StringUtils.hasText(request.getName()) ||
                (request.getBeginTime() != null) ||
                (request.getDayWeekId() != null) ||
                (request.getBeginDate() != null)
        ) {
            throw new ElectiveCourseFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (request.getDisciplinesIds() != null) {
            for (Long disciplineId : request.getDisciplinesIds()) {
                if (!disciplineRepo.existsById(disciplineId)) {
                    throw new DisciplineNotFoundException(
                            "Невозможно изменить факультатив, так как некорректно введен id дициплины = " + disciplineId);
                }
            }
        }

        if (request.getStudentsId() != null) {
            for (Long studentId : request.getStudentsId()) {
                if (!studentRepo.existsById(studentId)) {
                    throw new StudentNotFoundException(
                            "Невозможно изменить факультатив, так как некорректно введен id студента = " + studentId);
                }
            }
        }

        if (request.getTeachersIds() != null) {
            for (Long teacherId : request.getTeachersIds()) {
                if (!teacherRepo.existsById(teacherId)) {
                    throw new TeacherNotFoundException(
                            "Невозможно изменить факультатив, так как некорректно введен id преподавателя = " + teacherId);
                }
            }
        }

        if (request.getOlympToursId() != null) {
            for (Long olympTourId : request.getOlympToursId()) {
                if (!olympTourRepo.existsById(olympTourId)) {
                    throw new OlympTourNotFoundException(
                            "Невозможно изменить факультатив, так как некорректно введен id олимпиадного тура = " + olympTourId);
                }
            }
        }

        /*ElectiveCourse electiveCourse = ElectiveCourse.builder()
                .name(request.getName())
                .beginDate(request.getBeginDate())
                .beginTime(request.getBeginTime())
                .cabinet(request.getCabinet())
                .description(request.getDescription())
                .busyDay(dayWeekRepo.findById(request.getDayWeekId()).get())
                .build();*/

        ElectiveCourse electiveCourse = electiveCourseFromDb.get();
        electiveCourse.setName(request.getName());
        electiveCourse.setBeginDate(request.getBeginDate());
        electiveCourse.setBeginTime(request.getBeginTime());
        electiveCourse.setCabinet(request.getCabinet());
        electiveCourse.setDescription(request.getDescription());
        electiveCourse.setBusyDay(dayWeekRepo.findById(request.getDayWeekId()).get());

        electiveCourse.setDisciplines(request.getDisciplinesIds().stream()
                .map(disciplineId -> disciplineRepo.findById(disciplineId).get())
                .collect(Collectors.toList())
        );

        electiveCourse.setOlympTours(request.getOlympToursId().stream()
                .map(olympTourId -> olympTourRepo.findById(olympTourId).get())
                .collect(Collectors.toList())
        );

        electiveCourse.setStudents(request.getStudentsId().stream()
                .map(studentId -> studentRepo.findById(studentId).get())
                .collect(Collectors.toList())
        );

        electiveCourse.setTeachers(request.getTeachersIds().stream()
                .map(teacherId -> teacherRepo.findById(teacherId).get())
                .collect(Collectors.toList())
        );

        electiveCourse.setId(id);
        electiveCourseRepo.save(electiveCourse);

        return buildDto(electiveCourse);

    }

    @Override
    public void deleteById(Long id) {
        if (!electiveCourseRepo.existsById(id)) {
            throw new ElectiveCourseNotFoundException("Не найден факультатив с id = " + id);
        }
        electiveCourseRepo.deleteById(id);
    }

    @Override
    public ElectiveCourseDto findById(Long id) {
        Optional<ElectiveCourse> electiveCourseFromDb = electiveCourseRepo.findById(id);
        if (electiveCourseFromDb.isEmpty()) {
            throw new ElectiveCourseNotFoundException("Не найден факультатив с id = " + id);
        }
        ElectiveCourse electiveCourse = electiveCourseFromDb.get();
        return buildDto(electiveCourse);
    }

    private ElectiveCourseDto buildDto(ElectiveCourse electiveCourse) {

        ElectiveCourseDto electiveCourseDto = ElectiveCourseDto.builder()
                .name(electiveCourse.getName())
                .beginDate(electiveCourse.getBeginDate())
                .beginTime(electiveCourse.getBeginTime())
                .cabinet(electiveCourse.getCabinet())
                .description(electiveCourse.getDescription())
                .dayWeekName(electiveCourse.getBusyDay().getName())
                .build();

        if (electiveCourse.getStudents() != null) {
            List<Long> studentsIds = electiveCourse.getStudents().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            electiveCourseDto.setStudentsId(studentsIds);
        }

        if (electiveCourse.getTeachers() != null) {
            List<Long> teachersIds = electiveCourse.getTeachers().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            electiveCourseDto.setTeachersIds(teachersIds);
        }

        if (electiveCourse.getDisciplines() != null) {
            List<Long> disciplinesIds = electiveCourse.getDisciplines().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            electiveCourseDto.setDisciplinesIds(disciplinesIds);
        }

        if (electiveCourse.getOlympTours() != null) {
            List<Long> olympToursIds = electiveCourse.getOlympTours().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            electiveCourseDto.setOlympToursId(olympToursIds);
        }

        return electiveCourseDto;
    }
}
