package ru.zaharova.studentcourse.exception.exceptions.team;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class TeamFieldsEmptyException extends BaseException {
    public TeamFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
