package ru.zaharova.studentcourse.exception.exceptions.discipline;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class DisciplineNotFoundException extends BaseException {
    public DisciplineNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
