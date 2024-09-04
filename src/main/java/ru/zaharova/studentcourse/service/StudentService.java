package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.student.EditStudentRequest;
import ru.zaharova.studentcourse.rest.dto.request.student.NewStudentRequest;
import ru.zaharova.studentcourse.rest.dto.response.student.StudentDto;

public interface StudentService {
    Long addNewStudent(NewStudentRequest request);

    StudentDto edit(Long id, EditStudentRequest request);

    void deleteById(Long id);

    StudentDto findById(Long id);
}
