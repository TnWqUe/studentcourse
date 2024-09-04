package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/**
 * Репрезентация таблицы факультативов
 */
@Entity
@Table(name = "elective_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElectiveCourse extends AbstractEntity{
    /**
     * Название факультатива
     */
    @Column(name = "name", length = 150, nullable = false)
    private String name;
    /**
     * Дата начала занятий по факультативу
     */
    @Column(name = "begin_date", nullable = false)
    private LocalDate beginDate;
    /**
     * Время начала занятий по факультативу
     */
    @Column(name = "begin_time", nullable = false)
    private LocalTime beginTime;
    /**
     * Номер аудитории, в которой проходит факультатив
     */
    @Column(name = "cabinet", length = 10, nullable = false)
    private String cabinet;
    /**
     * Описание факультатива
     */
    @Column(name = "description")
    private String description;
    /**
     * День недели, в который проходит факультатив
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_day_week")
    private DayWeek busyDay;
    /**
     * Список студентов, которые посещают факультатив
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stud_elective_course",
            inverseJoinColumns = @JoinColumn(name = "id_elective_course"),
            joinColumns = @JoinColumn(name = "id_stud")
    )
    private List<Student> students;
    /**
     * Список олимпиадных этапов, к которым готовит факультатив
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "elective_course_olymp_tour",
            inverseJoinColumns = @JoinColumn(name = "id_elective_course"),
            joinColumns = @JoinColumn(name = "id_olymp_tour")
    )
    private List<OlympTour> olympTours;
    /**
     * Список преподавателей-организаторов факультатива
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "teacher_elective_course",
            inverseJoinColumns = @JoinColumn(name = "id_elective_course"),
            joinColumns = @JoinColumn(name = "id_teacher")
    )
    private List<Teacher> teachers;
    /**
     * Список дисциплин, по которым ориентирован факультатива
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "elective_course_discipline",
            inverseJoinColumns = @JoinColumn(name = "id_elective_course"),
            joinColumns = @JoinColumn(name = "id_discipline")
    )
    private List<Discipline> disciplines;
}
