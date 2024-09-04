package ru.zaharova.studentcourse.exception.exceptions.discipline;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class DisciplineFieldsEmptyException extends BaseException {
    public DisciplineFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
