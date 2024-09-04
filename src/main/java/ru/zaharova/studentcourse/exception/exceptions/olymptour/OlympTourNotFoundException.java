package ru.zaharova.studentcourse.exception.exceptions.olymptour;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;


public class OlympTourNotFoundException extends BaseException {
    public OlympTourNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
