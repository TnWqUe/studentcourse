package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.olymptour.EditOlympTourRequest;
import ru.zaharova.studentcourse.rest.dto.request.olymptour.NewOlympTourRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympTour.OlympTourDto;
import ru.zaharova.studentcourse.service.OlympTourService;


@RestController
@RequestMapping("/api/v1/olymp_tour")
@RequiredArgsConstructor
public class OlympTourController {
    private final OlympTourService olympTourService;
    @PostMapping
    public ResponseEntity<Long> addNewOlympTour(@RequestBody NewOlympTourRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( olympTourService.addNewOlympTour(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OlympTourDto> edit(@PathVariable Long id, @RequestBody EditOlympTourRequest request) {
        return ResponseEntity.ok( olympTourService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        olympTourService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OlympTourDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( olympTourService.findById(id));
    }
}
