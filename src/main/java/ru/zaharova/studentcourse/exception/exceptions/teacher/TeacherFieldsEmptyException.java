package ru.zaharova.studentcourse.exception.exceptions.teacher;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class TeacherFieldsEmptyException extends BaseException {
    public TeacherFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
