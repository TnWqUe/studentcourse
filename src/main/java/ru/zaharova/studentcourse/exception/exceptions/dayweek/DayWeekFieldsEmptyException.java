package ru.zaharova.studentcourse.exception.exceptions.dayweek;

import org.springframework.http.HttpStatus;
import ru.zaharova.studentcourse.exception.exceptions.common.BaseException;

public class DayWeekFieldsEmptyException  extends BaseException {
    public DayWeekFieldsEmptyException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
