package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы уровней олимпиад
 */
@Entity
@Table(name = "olymp_level")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OlympLevel extends AbstractEntity{
    /**
     * Название уровня олимпиады
     */
    @Column(name = "name", length = 40, nullable = false)
    private String name;
    /**
     * Список олимпиад, использующие соответствующий уровень олимпиады
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "level")
    private List<OlympTour> olympTours;
}
