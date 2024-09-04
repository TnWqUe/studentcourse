package ru.zaharova.studentcourse.exception.exceptions.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * предназначен уже для обработки ошибок в методах
 */
@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus status;
    public BaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
