package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.AbstractEntity;
import ru.zaharova.studentcourse.entity.Teacher;
import ru.zaharova.studentcourse.entity.User;
import ru.zaharova.studentcourse.exception.exceptions.electiveCourse.ElectiveCourseNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.teacher.TeacherFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.teacher.TeacherNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.team.TeamNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.user.UserNotFoundException;
import ru.zaharova.studentcourse.repo.ElectiveCourseRepo;
import ru.zaharova.studentcourse.repo.TeacherRepo;
import ru.zaharova.studentcourse.repo.TeamRepo;
import ru.zaharova.studentcourse.repo.UserRepo;
import ru.zaharova.studentcourse.rest.dto.request.teacher.EditTeacherRequest;
import ru.zaharova.studentcourse.rest.dto.request.teacher.NewTeacherRequest;
import ru.zaharova.studentcourse.rest.dto.response.teacher.TeacherDto;
import ru.zaharova.studentcourse.service.TeacherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;

    private final ElectiveCourseRepo electiveCourseRepo;

    @Override
    public Long addNewTeacher(NewTeacherRequest request) {

        if (!StringUtils.hasText(request.getMpeiUrl()) ||
                request.getUserId()==null) {
            throw new TeacherFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (!userRepo.existsById(request.getUserId())){
            throw new UserNotFoundException(
                    String.format( "The teacher cannot be created with user who is not in the database. User id = %d",
                            request.getUserId())
            );
        }

        Teacher teacher = new Teacher();
        teacher.setMpeiUrl(request.getMpeiUrl());
        teacher.setUser(userRepo.findById(request.getUserId()).get());

        teacherRepo.saveAndFlush(teacher);

        Optional<User> userFromDb = userRepo.findById(request.getUserId());

        User user = userFromDb.get();

        user.setTeacher(teacher);
        userRepo.save(user);

        return teacher.getId();
    }

    @Override
    public TeacherDto edit(Long id, EditTeacherRequest request) {

        Optional<Teacher> teacherFromDb = teacherRepo.findById(id);

        if (teacherFromDb.isEmpty()) {
            throw new TeacherFieldsEmptyException("Не найден пользователь с id = " + id);
        }

        if (!StringUtils.hasText(request.getMpeiUrl())) {
            throw new TeacherFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if(request.getTeamIds() != null){
            for (Long teamId : request.getTeamIds()){
                if(!teamRepo.existsById(teamId)){
                    throw new TeamNotFoundException(
                                    "Невозможно изменить номер team, так как некорректно введен id team = "+teamId);
                }
            }
        }

        if(request.getElectiveCourseIds() != null){
            for (Long electiveCourseId : request.getElectiveCourseIds()){
                if(!electiveCourseRepo.existsById(electiveCourseId)){
                    throw new ElectiveCourseNotFoundException(
                            "Невозможно изменить номер electiveCourse, так как некорректно введен id electiveCourse = "+electiveCourseId);
                }
            }
        }

        Teacher teacher = teacherFromDb.get();
        teacher.setMpeiUrl(request.getMpeiUrl());
        teacher.setTeams(request.getTeamIds().stream()
                .map(teamId -> teamRepo.findById(teamId).get())
                .collect(Collectors.toList())
        );

        teacher.setElectiveCourses(request.getElectiveCourseIds().stream()
                        .map(electiveCourseId -> electiveCourseRepo.findById(electiveCourseId).get())
                        .collect(Collectors.toList())
                );

        teacherRepo.save(teacher);
        return buildDto(teacher);
    }

    @Override
    public void deleteById(Long id) {
        if (!teacherRepo.existsById(id)) {
            throw new TeacherNotFoundException("Не найден преподаватель с id = " + id);
        }
        teacherRepo.deleteById(id);
    }

    @Override
    public TeacherDto findById(Long id) {
        Optional<Teacher> teacherFromDb = teacherRepo.findById(id);
        if (teacherFromDb.isEmpty()) {
            throw new TeacherNotFoundException("Не найден преподаватель с id = " + id);
        }
        Teacher teacher = teacherFromDb.get();
        return buildDto(teacher);
    }

    private TeacherDto buildDto(Teacher teacher) {

        TeacherDto teacherDto = TeacherDto.builder()
                .mpeiUrl(teacher.getMpeiUrl())
                .build();

        if(teacher.getTeams()!= null){
            List<Long> teamsIds = teacher.getTeams().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            teacherDto.setTeamIds(teamsIds);
        }

        if(teacher.getElectiveCourses()!= null){
            List<Long> electiveCoursesIds = teacher.getElectiveCourses().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            teacherDto.setElectiveCourseIds(electiveCoursesIds);
        }

        return teacherDto;
    }
}
