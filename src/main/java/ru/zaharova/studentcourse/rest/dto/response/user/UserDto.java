package ru.zaharova.studentcourse.rest.dto.response.user;

import lombok.Builder;
import lombok.Data;
import ru.zaharova.studentcourse.entity.Student;
import ru.zaharova.studentcourse.entity.Teacher;

import java.time.LocalDate;
import java.util.List;

/**
 * данные пользователя (кроме пароля)
 */
@Data
@Builder
public class UserDto {
    private String username;
    private String surname;
    private String patronymic;
    private LocalDate createdAt;
    private String email;
    private List<Long> rolesIds;
    private Student student;
    private Teacher teacher;
}