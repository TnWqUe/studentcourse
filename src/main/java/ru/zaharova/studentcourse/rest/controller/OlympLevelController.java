package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.olympLevel.EditOlympLevelRequest;
import ru.zaharova.studentcourse.rest.dto.request.olympLevel.NewOlympLevelRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympLevel.OlympLevelDto;
import ru.zaharova.studentcourse.service.OlympLevelService;


@RestController
@RequestMapping("/api/v1/olymp_level")
@RequiredArgsConstructor
public class OlympLevelController {
    private final OlympLevelService olympLevelService;
    @PostMapping
    public ResponseEntity<Long> addNewOlympLevel(@RequestBody NewOlympLevelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( olympLevelService.addNewOlympLevel(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OlympLevelDto> edit(@PathVariable Long id, @RequestBody EditOlympLevelRequest request) {
        return ResponseEntity.ok( olympLevelService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        olympLevelService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OlympLevelDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( olympLevelService.findById(id));
    }
}
