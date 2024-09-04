package ru.zaharova.studentcourse.service;


import ru.zaharova.studentcourse.rest.dto.request.user.EditPasswordRequest;
import ru.zaharova.studentcourse.rest.dto.request.user.EditUserRequest;
import ru.zaharova.studentcourse.rest.dto.request.user.NewUserRequest;
import ru.zaharova.studentcourse.rest.dto.response.user.UserDto;

import java.util.List;

public interface UserService {
    Long addNewUser(NewUserRequest request);

    List<UserDto> findAllUsers();

    UserDto findById(Long id);

    void deleteById(Long id);

    UserDto edit(Long id, EditUserRequest request);

    UserDto editPassword(Long id, EditPasswordRequest request);
}
