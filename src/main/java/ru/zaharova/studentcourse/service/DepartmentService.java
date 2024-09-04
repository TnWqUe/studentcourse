package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.department.EditDepartmentRequest;
import ru.zaharova.studentcourse.rest.dto.request.department.NewDepartmentRequest;
import ru.zaharova.studentcourse.rest.dto.response.department.DepartmentDto;

public interface DepartmentService {
    Long addNewDepartment(NewDepartmentRequest request);

    DepartmentDto edit(Long id, EditDepartmentRequest request);

    void deleteById(Long id);

    DepartmentDto findById(Long id);
}
