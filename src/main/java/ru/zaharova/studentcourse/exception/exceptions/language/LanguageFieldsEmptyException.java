package ru.zaharova.studentcourse.exception.exceptions.language;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class LanguageFieldsEmptyException  extends BaseException {
    public LanguageFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
