package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * Репрезентация таблицы олимпиадных организаций
 */
@Entity
@Table(name = "orgn")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization extends AbstractEntity{
    /**
     * Название организации
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    /**
     * Список отделов организации
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Department> departments;
}
