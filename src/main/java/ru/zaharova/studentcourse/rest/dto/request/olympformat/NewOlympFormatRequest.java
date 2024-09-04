package ru.zaharova.studentcourse.rest.dto.request.olympformat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOlympFormatRequest {
    private String name;
}
