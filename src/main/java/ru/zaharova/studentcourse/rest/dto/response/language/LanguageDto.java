package ru.zaharova.studentcourse.rest.dto.response.language;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LanguageDto {
    private String name;
    private List<Long> olympToursIds;
}
