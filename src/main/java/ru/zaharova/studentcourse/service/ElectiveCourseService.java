package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.electivecourse.EditElectiveCourseRequest;
import ru.zaharova.studentcourse.rest.dto.request.electivecourse.NewElectiveCourseRequest;
import ru.zaharova.studentcourse.rest.dto.response.electiveCourse.ElectiveCourseDto;

public interface ElectiveCourseService {
    Long addNewElectiveCourse(NewElectiveCourseRequest request);

    ElectiveCourseDto edit(Long id, EditElectiveCourseRequest request);

    void deleteById(Long id);

    ElectiveCourseDto findById(Long id);
}
