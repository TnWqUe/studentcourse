package ru.zaharova.studentcourse.rest.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.electivecourse.EditElectiveCourseRequest;
import ru.zaharova.studentcourse.rest.dto.request.electivecourse.NewElectiveCourseRequest;
import ru.zaharova.studentcourse.rest.dto.response.electiveCourse.ElectiveCourseDto;
import ru.zaharova.studentcourse.service.ElectiveCourseService;

@RestController
@RequestMapping("/api/v1/elective_course")
@RequiredArgsConstructor
public class ElectiveCourseController {
    private final ElectiveCourseService electiveCourseService;
    @PostMapping
    public ResponseEntity<Long> addNewElectiveCourse(@RequestBody NewElectiveCourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( electiveCourseService.addNewElectiveCourse(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ElectiveCourseDto> edit(@PathVariable Long id, @RequestBody EditElectiveCourseRequest request) {
        return ResponseEntity.ok( electiveCourseService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        electiveCourseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ElectiveCourseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( electiveCourseService.findById(id));
    }
}
