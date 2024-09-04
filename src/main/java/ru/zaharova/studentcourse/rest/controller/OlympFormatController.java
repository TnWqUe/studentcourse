package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.olympformat.EditOlympFormatRequest;
import ru.zaharova.studentcourse.rest.dto.request.olympformat.NewOlympFormatRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympFormat.OlympFormatDto;
import ru.zaharova.studentcourse.service.OlympFormatService;


@RestController
@RequestMapping("/api/v1/olymp_format")
@RequiredArgsConstructor
public class OlympFormatController {
    private final OlympFormatService olympFormatService;
    @PostMapping
    public ResponseEntity<Long> addNewOlympFormat(@RequestBody NewOlympFormatRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( olympFormatService.addNewOlympFormat(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OlympFormatDto> edit(@PathVariable Long id, @RequestBody EditOlympFormatRequest request) {
        return ResponseEntity.ok( olympFormatService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        olympFormatService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OlympFormatDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok( olympFormatService.findById(id));
    }
}
