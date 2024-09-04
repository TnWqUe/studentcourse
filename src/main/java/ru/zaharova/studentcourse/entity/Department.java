package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы отделов (напр., кафедр)
 */
@Entity
@Table(name = "dept")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department extends AbstractEntity{
    /**
     * Название отдела
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    /**
     * id организации, к которой относится отдел
     */
    @JoinColumn(name = "id_orgn")
    private Organization parent;
    /**
     * Список студентов отдела
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Student> students;
}
