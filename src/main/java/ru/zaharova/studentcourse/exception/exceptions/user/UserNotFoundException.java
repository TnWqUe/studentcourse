package ru.zaharova.studentcourse.exception.exceptions.user;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

/**
 * Пользователь не найден
 */
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
