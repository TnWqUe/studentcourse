package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы преподавателей
 */
@Entity
@Table(name = "teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends AbstractEntity{
    /**
     * Мэишная ссылка преподавателя
     */
    @Column(name = "mpei_url", length = 200, nullable = false)
    private String mpeiUrl;
    /**
     * список команд, которые контролирует преподаватель
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leader")
    private List<Team> teams;
    /**
     * список факультативов, которые ведет преподаватель
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_elective_course",
            inverseJoinColumns = @JoinColumn(name = "id_teacher"),
            joinColumns = @JoinColumn(name = "id_elective_course")
    )
    private List<ElectiveCourse> electiveCourses;
    /**
     * пользователь-преподаватель
     */
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "id_usr")
    private User user;
}
