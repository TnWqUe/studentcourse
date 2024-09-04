package ru.zaharova.studentcourse.exception.exceptions.department;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;


public class DepartmentNotFoundException   extends BaseException {
    public DepartmentNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
