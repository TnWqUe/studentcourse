package ru.zaharova.studentcourse.rest.dto.request.user;

import lombok.Data;

/**
 * Запрос на изменение пароля пользователя
 */
@Data
public class EditPasswordRequest {
    private String password;
}