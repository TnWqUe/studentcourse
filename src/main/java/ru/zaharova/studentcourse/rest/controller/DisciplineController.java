package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.discipline.EditDisciplineRequest;
import ru.zaharova.studentcourse.rest.dto.request.discipline.NewDisciplineRequest;
import ru.zaharova.studentcourse.rest.dto.response.discipline.DisciplineDto;
import ru.zaharova.studentcourse.service.DisciplineService;


@RestController
@RequestMapping("/api/v1/discipline")
@RequiredArgsConstructor
public class DisciplineController {
    private final DisciplineService disciplineService;
    @PostMapping
    public ResponseEntity<Long> addNewDiscipline(@RequestBody NewDisciplineRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( disciplineService.addNewDiscipline(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DisciplineDto> edit(@PathVariable Long id, @RequestBody EditDisciplineRequest request) {
        return ResponseEntity.ok( disciplineService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        disciplineService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( disciplineService.findById(id));
    }
}
