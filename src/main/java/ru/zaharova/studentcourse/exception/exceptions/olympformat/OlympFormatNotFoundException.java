package ru.zaharova.studentcourse.exception.exceptions.olympformat;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class OlympFormatNotFoundException extends BaseException {
    public OlympFormatNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
