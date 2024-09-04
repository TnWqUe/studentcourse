package ru.zaharova.studentcourse.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.organization.EditOrganizationRequest;
import ru.zaharova.studentcourse.rest.dto.request.organization.NewOrganizationRequest;
import ru.zaharova.studentcourse.rest.dto.response.organization.OrganizationDto;
import ru.zaharova.studentcourse.service.OrganizationService;


@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;
    @PostMapping
    public ResponseEntity<Long> addNewDepartment(@RequestBody NewOrganizationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.addNewOrganization(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrganizationDto> edit(@PathVariable Long id, @RequestBody EditOrganizationRequest request) {
        return ResponseEntity.ok(organizationService.edit(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        organizationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.findById(id));
    }
}
