package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы языков олимпиад
 */
@Entity
@Table(name = "lang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Language extends AbstractEntity{
    /**
     * Название языка
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    /**
     * Список олимпиад, задания которых написаны на соответствующем языке
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "lang_olymp_tour",
            inverseJoinColumns = @JoinColumn(name = "id_lang"),
            joinColumns = @JoinColumn(name = "id_olymp_tour")
    )
    private List<OlympTour> olympTours;
}
