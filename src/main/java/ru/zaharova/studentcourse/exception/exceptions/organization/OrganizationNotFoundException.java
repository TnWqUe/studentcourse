package ru.zaharova.studentcourse.exception.exceptions.organization;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class OrganizationNotFoundException extends BaseException {
    public OrganizationNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
