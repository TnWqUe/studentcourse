package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы дисциплин
 */
@Entity
@Table(name = "discipline")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discipline extends AbstractEntity{
    /**
     * Название дисциплины
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    /**
     * Список факультативов по данной дисциплине
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "elective_course_discipline",
            inverseJoinColumns = @JoinColumn(name = "id_discipline"),
            joinColumns = @JoinColumn(name = "id_elective_course")
    )
    private List<ElectiveCourse> electiveCourses;

}
