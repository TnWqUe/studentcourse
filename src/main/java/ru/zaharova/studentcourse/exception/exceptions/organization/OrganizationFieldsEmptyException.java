package ru.zaharova.studentcourse.exception.exceptions.organization;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class OrganizationFieldsEmptyException extends BaseException {
    public OrganizationFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
