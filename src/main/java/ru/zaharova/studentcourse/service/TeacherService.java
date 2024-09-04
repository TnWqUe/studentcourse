package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.teacher.EditTeacherRequest;
import ru.zaharova.studentcourse.rest.dto.request.teacher.NewTeacherRequest;
import ru.zaharova.studentcourse.rest.dto.response.teacher.TeacherDto;

public interface TeacherService {
    Long addNewTeacher(NewTeacherRequest request);

    TeacherDto edit(Long id, EditTeacherRequest request);

    void deleteById(Long id);

    TeacherDto findById(Long id);
}
