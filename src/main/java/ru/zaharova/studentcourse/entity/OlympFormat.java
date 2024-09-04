package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы форматов олимпиад
 */
@Entity
@Table(name = "olymp_format")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OlympFormat extends AbstractEntity{
    /**
     * Название формата олимпиады
     */
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    /**
     * Список олимпиад, использующие соответствующий формат олимпиады
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "format")
    private List<OlympTour> olympTours;
}
