package ru.zaharova.studentcourse.exception.exceptions.electiveCourse;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class ElectiveCourseFieldsEmptyException extends BaseException {
    public ElectiveCourseFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
