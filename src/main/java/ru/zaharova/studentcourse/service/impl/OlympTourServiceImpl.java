package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.*;
import ru.zaharova.studentcourse.exception.exceptions.electiveCourse.ElectiveCourseNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.language.LanguageNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.olympformat.OlympFormatNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.olymplevel.OlympLevelNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.olymptour.OlympTourFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.olymptour.OlympTourNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.team.TeamNotFoundException;
import ru.zaharova.studentcourse.repo.*;
import ru.zaharova.studentcourse.rest.dto.request.olymptour.EditOlympTourRequest;
import ru.zaharova.studentcourse.rest.dto.request.olymptour.NewOlympTourRequest;
import ru.zaharova.studentcourse.rest.dto.response.olympTour.OlympTourDto;
import ru.zaharova.studentcourse.service.OlympTourService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OlympTourServiceImpl implements OlympTourService {
    private final OlympTourRepo olympTourRepo;
    private final LanguageRepo languageRepo;
    private final TeamRepo teamRepo;
    private final ElectiveCourseRepo electiveCourseRepo;
    private final OlympLevelRepo olympLevelRepo;
    private final OlympFormatRepo olympFormatRepo;

    @Override
    public Long addNewOlympTour(NewOlympTourRequest request) {
        if (!StringUtils.hasText(request.getName()) || !StringUtils.hasText(request.getAddress()) || !StringUtils.hasText(request.getOrganizer()) || !StringUtils.hasText(request.getOlympUrl()) || (request.getIsOpenForRequests() != null) || (request.getBeginTour() != null) || (request.getEndTour() != null) || (request.getTourNumber() != null) || (request.getToursAmount() != null)) {
            throw new OlympTourFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (!olympLevelRepo.existsById(request.getOlympLevelId())) {
            throw new OlympLevelNotFoundException(String.format("Олимпиадный тур невозможно добавить, так как id олимпиадного уровня не найдено в БД. OlympLevel id = %d", request.getOlympLevelId()));
        }

        if (!olympFormatRepo.existsById(request.getOlympFormatId())) {
            throw new OlympFormatNotFoundException(String.format("Олимпиадный тур невозможно добавить, так как id олимпиадного формата не найдено в БД. OlympFormat id = %d", request.getOlympFormatId()));
        }

        OlympTour olympTour = new OlympTour();
        olympTour.setName(request.getName());
        olympTour.setAddress(request.getAddress());
        olympTour.setBeginTour(request.getBeginTour());
        olympTour.setEndTour(request.getEndTour());
        olympTour.setOlympUrl(request.getOlympUrl());
        olympTour.setFormat(olympFormatRepo.findById(request.getOlympFormatId()).get());
        olympTour.setLevel(olympLevelRepo.findById(request.getOlympLevelId()).get());
        olympTour.setIsOpenForRequests(request.getIsOpenForRequests());
        olympTour.setToursAmount(request.getToursAmount());
        olympTour.setOrganizer(request.getOrganizer());
        olympTour.setStartReg(request.getStartReg());
        olympTour.setEndReg(request.getEndReg());
        olympTour.setTourNumber(request.getTourNumber());
        olympTour.setText(request.getText());

        olympTourRepo.saveAndFlush(olympTour);
        return olympTour.getId();
    }

    @Override
    public OlympTourDto edit(Long id, EditOlympTourRequest request) {

        Optional<OlympTour> olympTourFromDb = olympTourRepo.findById(id);

        if (olympTourFromDb.isEmpty()) {
            throw new OlympTourFieldsEmptyException("Не найден олимпиадный тур с id = " + id);
        }

        if (!StringUtils.hasText(request.getName()) || !StringUtils.hasText(request.getAddress()) || !StringUtils.hasText(request.getOrganizer()) || !StringUtils.hasText(request.getOlympUrl()) || (request.getIsOpenForRequests() != null) || (request.getBeginTour() != null) || (request.getEndTour() != null) || (request.getTourNumber() != null) || (request.getToursAmount() != null) || (request.getOlympLevelId() != null) || (request.getOlympFormatId() != null)) {
            throw new OlympTourFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (!olympLevelRepo.existsById(request.getOlympLevelId())) {
            throw new OlympLevelNotFoundException(String.format("Олимпиадный тур невозможно изменить, так как id олимпиадного уровня не найдено в БД. OlympLevel id = %d", request.getOlympLevelId()));
        }

        if (!olympFormatRepo.existsById(request.getOlympFormatId())) {
            throw new OlympFormatNotFoundException(String.format("Олимпиадный тур невозможно изменить, так как id олимпиадного формата не найдено в БД. OlympFormat id = %d", request.getOlympFormatId()));
        }

        if (request.getTeamsIds() != null) {
            for (Long teamId : request.getTeamsIds()) {
                if (!teamRepo.existsById(teamId)) {
                    throw new TeamNotFoundException("Невозможно изменить олимпиадный тур, так как не найдена команда в БД. id team = " + teamId);
                }
            }
        }

        if (request.getElectiveCoursesIds() != null) {
            for (Long electiveCourseId : request.getElectiveCoursesIds()) {
                if (!electiveCourseRepo.existsById(electiveCourseId)) {
                    throw new ElectiveCourseNotFoundException("Невозможно изменить олимпиадный тур, так как не найден факультатив в БД. id electiveCourse = " + electiveCourseId);
                }
            }
        }

        if (request.getLanguagesIds() != null) {
            for (Long languagesId : request.getElectiveCoursesIds()) {
                if (!languageRepo.existsById(languagesId)) {
                    throw new LanguageNotFoundException("Невозможно изменить олимпиадный тур, так как не найден язык в БД. id language = " + languagesId);
                }
            }
        }

       // OlympTour olympTour = OlympTour.builder().name(request.getName()).address(request.getAddress()).endTour(request.getEndTour()).olympUrl(request.getOlympUrl()).beginTour(request.getBeginTour()).tourNumber(request.getTourNumber()).toursAmount(request.getToursAmount()).isOpenForRequests(request.getIsOpenForRequests()).level(olympLevelRepo.findById(request.getOlympLevelId()).get()).organizer(request.getOrganizer()).startReg(request.getStartReg()).endReg(request.getEndReg()).text(request.getText()).format(olympFormatRepo.findById(request.getOlympFormatId()).get()).build();

        OlympTour olympTour = olympTourFromDb.get();
        olympTour.setName(request.getName());
        olympTour.setAddress(request.getAddress());
        olympTour.setBeginTour(request.getBeginTour());
        olympTour.setEndTour(request.getEndTour());
        olympTour.setOlympUrl(request.getOlympUrl());
        olympTour.setFormat(olympFormatRepo.findById(request.getOlympFormatId()).get());
        olympTour.setLevel(olympLevelRepo.findById(request.getOlympLevelId()).get());
        olympTour.setIsOpenForRequests(request.getIsOpenForRequests());
        olympTour.setToursAmount(request.getToursAmount());
        olympTour.setOrganizer(request.getOrganizer());
        olympTour.setStartReg(request.getStartReg());
        olympTour.setEndReg(request.getEndReg());
        olympTour.setTourNumber(request.getTourNumber());
        olympTour.setText(request.getText());
        olympTour.setTeams(request.getTeamsIds().stream().map(studentId -> teamRepo.findById(studentId).get()).collect(Collectors.toList()));

        olympTour.setElectiveCourses(request.getElectiveCoursesIds().stream().map(studentId -> electiveCourseRepo.findById(studentId).get()).collect(Collectors.toList()));

        olympTour.setLanguages(request.getLanguagesIds().stream().map(languageId -> languageRepo.findById(languageId).get()).collect(Collectors.toList()));

        olympTour.setId(id);
        olympTourRepo.save(olympTour);

        return buildDto(olympTour);
    }

    @Override
    public void deleteById(Long id) {
        if (!olympTourRepo.existsById(id)) {
            throw new OlympTourNotFoundException("Не найден олимпиадный тур с id = " + id);
        }
        olympTourRepo.deleteById(id);
    }

    @Override
    public OlympTourDto findById(Long id) {

        Optional<OlympTour> olympTourFromDb = olympTourRepo.findById(id);
        if (olympTourFromDb.isEmpty()) {
            throw new OlympTourNotFoundException("Не найден олимпиадный тур с id = " + id);
        }
        OlympTour olympTour = olympTourFromDb.get();
        return buildDto(olympTour);
    }

    private OlympTourDto buildDto(OlympTour olympTour) {
        OlympTourDto olympTourDto = OlympTourDto.builder().name(olympTour.getName()).beginTour(olympTour.getBeginTour()).endTour(olympTour.getEndTour()).olympFormatId(olympTour.getFormat().getId()).olympLevelId(olympTour.getLevel().getId()).olympUrl(olympTour.getOlympUrl()).organizer(olympTour.getOrganizer()).tourNumber(olympTour.getTourNumber()).toursAmount(olympTour.getToursAmount()).address(olympTour.getAddress()).startReg(olympTour.getStartReg()).endReg(olympTour.getEndReg()).isOpenForRequests(olympTour.getIsOpenForRequests()).text(olympTour.getText()).build();

        if (olympTour.getElectiveCourses() != null) {
            List<Long> electiveCoursesIds = olympTour.getElectiveCourses().stream().map(AbstractEntity::getId).collect(Collectors.toList());
            olympTourDto.setElectiveCoursesIds(electiveCoursesIds);
        }

        if (olympTour.getLanguages() != null) {
            List<Long> languagesIds = olympTour.getLanguages().stream().map(AbstractEntity::getId).collect(Collectors.toList());
            olympTourDto.setElectiveCoursesIds(languagesIds);
        }

        if (olympTour.getTeams() != null) {
            List<Long> teamsIds = olympTour.getTeams().stream().map(AbstractEntity::getId).collect(Collectors.toList());
            olympTourDto.setElectiveCoursesIds(teamsIds);
        }

        return olympTourDto;
    }
}
