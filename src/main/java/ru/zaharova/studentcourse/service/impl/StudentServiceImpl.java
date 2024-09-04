package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.AbstractEntity;
import ru.zaharova.studentcourse.entity.Student;
import ru.zaharova.studentcourse.entity.User;
import ru.zaharova.studentcourse.exception.exceptions.electiveCourse.ElectiveCourseNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.student.StudentFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.student.StudentNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.teacher.TeacherFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.team.TeamNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.user.UserNotFoundException;
import ru.zaharova.studentcourse.repo.*;
import ru.zaharova.studentcourse.rest.dto.request.student.EditStudentRequest;
import ru.zaharova.studentcourse.rest.dto.request.student.NewStudentRequest;
import ru.zaharova.studentcourse.rest.dto.response.student.StudentDto;
import ru.zaharova.studentcourse.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;
    private final ElectiveCourseRepo electiveCourseRepo;
    private final DepartmentRepo departmentRepo;


    @Override
    public Long addNewStudent(NewStudentRequest request) {
        if (!StringUtils.hasText(request.getStudGroup()) ||
                request.getDepartmentId()==null) {
            throw new StudentFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (!userRepo.existsById(request.getUserId())){
            throw new UserNotFoundException(
                    String.format( "The student cannot be created with user who is not in the database. User id = %d",
                            request.getUserId())
            );
        }

        Student student = new Student();
        student.setUser(userRepo.findById(request.getUserId()).get());
        student.setStudGroup(request.getStudGroup());
        student.setParent(departmentRepo.findById(request.getDepartmentId()).get());

        studentRepo.saveAndFlush(student);

        Optional<User> userFromDb = userRepo.findById(request.getUserId());
        User user = userFromDb.get();
        user.setStudent(student);
        userRepo.save(user);

        return student.getId();
    }

    @Override
    public StudentDto edit(Long id, EditStudentRequest request) {
        Optional<Student> studentFromDb = studentRepo.findById(id);

        if (studentFromDb.isEmpty()) {
            throw new TeacherFieldsEmptyException("Не найден пользователь с id = " + id);
        }

        if (!StringUtils.hasText(request.getStudGroup())||
                request.getDepartmentId()==null) {
            throw new TeacherFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }


        if(request.getTeamsIds() != null){
            for (Long teamId : request.getTeamsIds()){
                if(!teamRepo.existsById(teamId)){
                    throw new TeamNotFoundException(
                            "Невозможно изменить номер team, так как некорректно введен id team = "+teamId);
                }
            }
        }

        if(request.getElectiveCoursesIds() != null){
            for (Long electiveCourseId : request.getElectiveCoursesIds()){
                if(!electiveCourseRepo.existsById(electiveCourseId)){
                    throw new ElectiveCourseNotFoundException(
                            "Невозможно изменить номер electiveCourse, так как некорректно введен id electiveCourse = "+electiveCourseId);
                }
            }
        }

        Student student = Student.builder()
                .parent(departmentRepo.findById(request.getDepartmentId()).get())
                .studGroup(request.getStudGroup())
                .build();

        student.setTeams(request.getTeamsIds().stream()
                .map(studentId -> teamRepo.findById(studentId).get())
                .collect(Collectors.toList())
        );

        student.setElectiveCourses(request.getElectiveCoursesIds().stream()
                .map(studentId -> electiveCourseRepo.findById(studentId).get())
                .collect(Collectors.toList())
        );

        studentRepo.save(student);
        return buildDto(student);
    }

    @Override
    public void deleteById(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException("Не найден студент с id = " + id);
        }
        studentRepo.deleteById(id);
    }

    @Override
    public StudentDto findById(Long id) {
        Optional<Student> studentFromDb = studentRepo.findById(id);
        if (studentFromDb.isEmpty()) {
            throw new StudentNotFoundException("Не найден преподаватель с id = " + id);
        }
        Student student = studentFromDb.get();
        return buildDto(student);
    }
    private StudentDto buildDto(Student student) {

        StudentDto studentDto = StudentDto.builder()
                .studGroup(student.getStudGroup())
                .departmentId(student.getParent().getId())
                .build();

        if(student.getTeams()!= null){
            List<Long> teamsIds = student.getTeams().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            studentDto.setTeamsIds(teamsIds);;
        }

        if(student.getElectiveCourses()!= null){
            List<Long> electiveCoursesIds = student.getElectiveCourses().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            studentDto.setElectiveCoursesIds(electiveCoursesIds);
        }

        return studentDto;
    }
}
