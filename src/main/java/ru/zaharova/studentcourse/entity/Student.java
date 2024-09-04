package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы студентов
 */
@Entity
@Table(name = "stud")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends AbstractEntity{
    /**
     * номер группы
     */
    @Column(name = "stud_group", length = 15, nullable = false)
    private String studGroup;
    /**
     * отдел, к которому относится студент
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dept")
    private Department parent;
    /**
     * список факультативов, которые посещает студент
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stud_elective_course",
            inverseJoinColumns = @JoinColumn(name = "id_stud"),
            joinColumns = @JoinColumn(name = "id_elective_course")
    )
    private List<ElectiveCourse> electiveCourses;
    /**
     * список команд, в которые входит студент
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stud_team",
            inverseJoinColumns = @JoinColumn(name = "id_stud"),
            joinColumns = @JoinColumn(name = "id_team")
    )
    private List<Team> teams;
    /**
     * пользователь-студент
     */
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "id_usr")
    private User user;
}
