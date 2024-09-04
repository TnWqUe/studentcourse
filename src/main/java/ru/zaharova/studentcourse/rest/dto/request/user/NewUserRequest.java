package ru.zaharova.studentcourse.rest.dto.request.user;

import lombok.Builder;
import lombok.Data;

/**
 * Запрос на добавление нового пользователя
 */
@Data
@Builder
public class NewUserRequest {
    private String email;
    private String surname;
    private String username;
    private String patronymic;
    private String password;
}