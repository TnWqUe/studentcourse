package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.dayweek.EditDayWeekRequest;
import ru.zaharova.studentcourse.rest.dto.request.dayweek.NewDayWeekRequest;
import ru.zaharova.studentcourse.rest.dto.response.dayweek.DayWeekDto;
import ru.zaharova.studentcourse.service.DayWeekService;


@RestController
@RequestMapping("/api/v1/day_week")
@RequiredArgsConstructor
public class DayWeekController {
    private final DayWeekService dayWeekService;
    @PostMapping()
    public ResponseEntity<Long> addNewDayWeek(@RequestBody NewDayWeekRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( dayWeekService.addNewDayWeek(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DayWeekDto> edit(@PathVariable Long id, @RequestBody EditDayWeekRequest request) {
        return ResponseEntity.ok( dayWeekService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        dayWeekService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DayWeekDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( dayWeekService.findById(id));
    }
}
