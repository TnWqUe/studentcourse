package ru.zaharova.studentcourse.exception.exceptions.olymplevel;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;


public class OlympLevelNotFoundException  extends BaseException {
    public OlympLevelNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
