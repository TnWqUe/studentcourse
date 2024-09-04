package ru.zaharova.studentcourse.exception.exceptions.teacher;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;


public class TeacherNotFoundException extends BaseException {
    public TeacherNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
