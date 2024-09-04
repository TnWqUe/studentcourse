package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Репрезентация таблицы дней недели
 */
@Entity
@Table(name = "day_week")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayWeek extends AbstractEntity{
    /**
     * Название дня недели
     */
    @Column(name = "name", length = 11, nullable = false)
    private String name;
    /**
     * Список факультативов, проходящих в соответствующий день недели
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "busyDay")
    private List<ElectiveCourse> electiveCourses;
}
