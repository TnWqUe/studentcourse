package ru.zaharova.studentcourse.exception.exceptions.user;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

/**
 * Слабый пароль
 */
public class WeakPasswordException extends BaseException {
    public WeakPasswordException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
