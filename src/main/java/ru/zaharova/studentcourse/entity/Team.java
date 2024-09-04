package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
/**
 * Репрезентация таблицы команд, которые участвуют на олимпиадах
 */
@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team extends AbstractEntity{
    /**
     * Название команды
     */
    @Column(name = "name", length = 200, nullable = false)
    private String name;
    /**
     * Дата создания команды
     */
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    /**
     * Дата изменения команды
     */
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    /**
     * Описание команды
     */
    @Column(name = "description", length = 512)
    private String description;
    /**
     * Преподаватель, который собирает и ведет команду
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_teacher")
    private Teacher leader;
    /**
     * Олимпиадные этапы, в которых участвует команда
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_olymp_tour",
            inverseJoinColumns = @JoinColumn(name = "id_team"),
            joinColumns = @JoinColumn(name = "id_olymp_tour")
    )
    private List<OlympTour> olympTours;
    /**
     * Список студентов в команде
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "stud_team",
            inverseJoinColumns = @JoinColumn(name = "id_team"),
            joinColumns = @JoinColumn(name = "id_stud")
    )
    private List<Student> students;
}