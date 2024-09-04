package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.language.EditLanguageRequest;
import ru.zaharova.studentcourse.rest.dto.request.language.NewLanguageRequest;
import ru.zaharova.studentcourse.rest.dto.response.language.LanguageDto;
import ru.zaharova.studentcourse.service.LanguageService;


@RestController
@RequestMapping("/api/v1/language")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;
    @PostMapping
    public ResponseEntity<Long> addNewDepartment(@RequestBody NewLanguageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( languageService.addNewLanguage(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LanguageDto> edit(@PathVariable Long id, @RequestBody EditLanguageRequest request) {
        return ResponseEntity.ok( languageService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        languageService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( languageService.findById(id));
    }
}
