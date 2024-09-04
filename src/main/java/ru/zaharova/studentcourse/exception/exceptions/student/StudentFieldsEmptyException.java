package ru.zaharova.studentcourse.exception.exceptions.student;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;


public class StudentFieldsEmptyException extends BaseException {
    public StudentFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
