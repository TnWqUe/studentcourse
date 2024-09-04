package ru.zaharova.studentcourse.rest.dto.request.user;

import lombok.Data;

import java.util.List;

/**
 * Запрос на изменение пользователя
 */
@Data
public class EditUserRequest {
    private Long id;
    private String surname;
    private String username;
    private String patronymic;
    private String email;
    private List<Long> rolesIds;
}