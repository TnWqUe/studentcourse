package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.*;
import ru.zaharova.studentcourse.exception.exceptions.olymptour.OlympTourNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.student.StudentNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.team.TeamFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.team.TeamNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.user.UserNotFoundException;
import ru.zaharova.studentcourse.repo.*;
import ru.zaharova.studentcourse.rest.dto.request.team.EditTeamRequest;
import ru.zaharova.studentcourse.rest.dto.request.team.NewTeamRequest;
import ru.zaharova.studentcourse.rest.dto.response.team.TeamDto;
import ru.zaharova.studentcourse.service.TeamService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeacherRepo teacherRepo;
    private final TeamRepo teamRepo;
    private final StudentRepo studentRepo;
    private final OlympTourRepo olympTourRepo;


    @Override
    public Long addNewTeam(NewTeamRequest request) {
        if (!StringUtils.hasText(request.getDescription()) ||
                !StringUtils.hasText(request.getName())) {
            throw new TeamFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (!teacherRepo.existsById(request.getTeacherId())){
            throw new UserNotFoundException(
                    String.format( "The team cannot be created with user who is not in the database. Teacher id = %d",
                            request.getTeacherId())
            );
        }

        /*
        Team team = Team.builder()
                .leader(teacherRepo.findById(request.getTeacherId()).get())
                .description(request.getDescription())
                .name(request.getName())
                .build();
        */

        Team team = new Team();
        team.setName(request.getName());
        team.setLeader(teacherRepo.findById(request.getTeacherId()).get());
        team.setDescription(request.getDescription());

        teamRepo.saveAndFlush(team);
        return team.getId();
    }

    @Override
    public TeamDto edit(Long id, EditTeamRequest request) {

        Optional<Team> teamFromDb = teamRepo.findById(id);

        if (teamFromDb.isEmpty()) {
            throw new TeamFieldsEmptyException("Не найдена команда с id = " + id);
        }

        if (!StringUtils.hasText(request.getName())||
                !StringUtils.hasText(request.getDescription()) ||
        request.getTeacherId()!=null) {
            throw new TeamFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if(request.getStudentsIds() != null){
            for (Long studentsId: request.getStudentsIds()){
                if(!studentRepo.existsById(studentsId)){
                    throw new StudentNotFoundException(
                            "Невозможно изменить команду, так как некорректно введен id student = "+studentsId);
                }
            }
        }

        if(request.getOlympToursIds() != null){
            for (Long olympToursId : request.getOlympToursIds()){
                if(!olympTourRepo.existsById(olympToursId)){
                    throw new OlympTourNotFoundException(
                            "Невозможно изменить команду, так как некорректно введен id olympTour = "+olympToursId);
                }
            }
        }

       /* Team team = Team.builder()
                .name(request.getName())
                .leader(teacherRepo.findById(request.getTeacherId()).get())
                .description(request.getDescription())
                .build();*/

        Team team = teamFromDb.get();
        team.setName(request.getName());
        team.setLeader(teacherRepo.findById(request.getTeacherId()).get());
        team.setDescription(request.getDescription());

        team.setStudents(request.getStudentsIds().stream()
                .map(studentId -> studentRepo.findById(studentId).get())
                .collect(Collectors.toList())
        );

        team.setOlympTours(request.getOlympToursIds().stream()
                .map(olympTourId -> olympTourRepo.findById(olympTourId).get())
                .collect(Collectors.toList())
        );

        return buildDto(team);
    }

    @Override
    public void deleteById(Long id) {
        if (!teamRepo.existsById(id)) {
            throw new TeamNotFoundException("Не найдена группа с id = " + id);
        }
        teamRepo.deleteById(id);
    }

    @Override
    public TeamDto findById(Long id) {
        Optional<Team> teamFromDb = teamRepo.findById(id);
        if (teamFromDb.isEmpty()) {
            throw new TeamNotFoundException("Не найдена группа с id = " + id);
        }
        Team team = teamFromDb.get();
        return buildDto(team);
    }
    private TeamDto buildDto(Team team) {

        TeamDto teamDto = TeamDto.builder()
                .name(team.getName())
                .description(team.getDescription())
                .build();

        if(team.getStudents() != null){
            List<Long> studentsIds = team.getStudents().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            teamDto.setStudentsIds(studentsIds);
        }

        if(team.getOlympTours() != null){
            List<Long> olympToursIds = team.getOlympTours().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            teamDto.setOlympToursIds(olympToursIds);
        }

        if(team.getLeader()!=null){
            teamDto.setTeacherId(teacherRepo.findById(team.getLeader().getId()).get().getId());
        }

        return teamDto;
    }
}
