package ru.zaharova.studentcourse.rest.dto.request.olympLevel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOlympLevelRequest {
    private String name;
}
