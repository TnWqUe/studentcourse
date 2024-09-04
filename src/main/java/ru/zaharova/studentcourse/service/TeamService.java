package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.team.EditTeamRequest;
import ru.zaharova.studentcourse.rest.dto.request.team.NewTeamRequest;
import ru.zaharova.studentcourse.rest.dto.response.team.TeamDto;

public interface TeamService {
    Long addNewTeam(NewTeamRequest request);

    TeamDto edit(Long id, EditTeamRequest request);

    void deleteById(Long id);

    TeamDto findById(Long id);
}
