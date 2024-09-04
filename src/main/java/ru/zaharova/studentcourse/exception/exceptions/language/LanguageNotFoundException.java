package ru.zaharova.studentcourse.exception.exceptions.language;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;


public class LanguageNotFoundException  extends BaseException {
    public LanguageNotFoundException(String message)  {
        super(HttpStatus.NOT_FOUND,message);
    }
}
