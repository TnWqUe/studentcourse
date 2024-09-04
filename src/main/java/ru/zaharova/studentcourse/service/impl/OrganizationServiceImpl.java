package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.AbstractEntity;
import ru.zaharova.studentcourse.entity.Organization;
import ru.zaharova.studentcourse.exception.exceptions.organization.OrganizationFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.organization.OrganizationNotFoundException;
import ru.zaharova.studentcourse.repo.OrganizationRepo;
import ru.zaharova.studentcourse.rest.dto.request.organization.EditOrganizationRequest;
import ru.zaharova.studentcourse.rest.dto.request.organization.NewOrganizationRequest;
import ru.zaharova.studentcourse.rest.dto.response.organization.OrganizationDto;
import ru.zaharova.studentcourse.service.OrganizationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepo organizationRepo;

    @Override
    public Long addNewOrganization(NewOrganizationRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new OrganizationFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        Organization organization = new Organization();
        organization.setName(request.getName());
        organizationRepo.saveAndFlush(organization);
        return organization.getId();
    }

    @Override
    public OrganizationDto edit(Long id, EditOrganizationRequest request) {
        Optional<Organization> organizationFromDb = organizationRepo.findById(id);

        if (organizationFromDb.isEmpty()) {
            throw new OrganizationFieldsEmptyException("Не найдена организация с id = " + id);
        }

        if (!StringUtils.hasText(request.getName())) {
            throw new OrganizationFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }


        Organization organization = organizationFromDb.get();
        organization.setName(request.getName());
        organizationRepo.save(organization);

        return buildDto(organization);
    }

    @Override
    public void deleteById(Long id) {
        if (!organizationRepo.existsById(id)) {
            throw new OrganizationNotFoundException("Не найдена организация с id = " + id);
        }
        organizationRepo.deleteById(id);
    }

    @Override
    public OrganizationDto findById(Long id) {
        Optional<Organization> organizationFromDb = organizationRepo.findById(id);
        if (organizationFromDb.isEmpty()) {
            throw new OrganizationNotFoundException("Не найдена организация с id = " + id);
        }
        Organization organization = organizationFromDb.get();
        return buildDto(organization);
    }

    private OrganizationDto buildDto(Organization organization) {

        OrganizationDto organizationDto = OrganizationDto.builder()
                .name(organization.getName())
                .build();

        if (organization.getDepartments() != null) {
            List<Long> departmentsIds = organization.getDepartments().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            organizationDto.setDepartmentsIds(departmentsIds);
            ;
        }

        return organizationDto;
    }
}
