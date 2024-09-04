package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.department.EditDepartmentRequest;
import ru.zaharova.studentcourse.rest.dto.request.department.NewDepartmentRequest;
import ru.zaharova.studentcourse.rest.dto.response.department.DepartmentDto;
import ru.zaharova.studentcourse.service.DepartmentService;


@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<Long> addNewDepartment(@RequestBody NewDepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addNewDepartment(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDto> edit(@PathVariable Long id, @RequestBody EditDepartmentRequest request) {
        return ResponseEntity.ok(departmentService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }
}
