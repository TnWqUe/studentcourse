package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.organization.EditOrganizationRequest;
import ru.zaharova.studentcourse.rest.dto.request.organization.NewOrganizationRequest;
import ru.zaharova.studentcourse.rest.dto.response.organization.OrganizationDto;

public interface OrganizationService {
    Long addNewOrganization(NewOrganizationRequest request);

    OrganizationDto edit(Long id, EditOrganizationRequest request);

    void deleteById(Long id);

    OrganizationDto findById(Long id);
}
