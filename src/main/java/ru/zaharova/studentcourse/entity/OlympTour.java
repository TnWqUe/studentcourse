package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
/**
 * Репрезентация таблицы туров (этапов) олимпиад
 */
@Entity
@Table(name = "olymp_tour")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OlympTour extends AbstractEntity{
    /**
     * Название тура (этапа) олимпиады
     */
    @Column(name = "name", length = 250, nullable = false)
    private String name;
    /**
     * Ссылка на олимпиаду
     */
    @Column(name = "olymp_url", length = 200, nullable = false)
    private String olympUrl;
    /**
     * Организатор олимпиады
     */
    @Column(name = "organizer", length = 250, nullable = false)
    private String organizer;
    /**
     * Количество этапов олимпиады
     */
    @Column(name = "tours_amount", nullable = false)
    private Integer toursAmount;
    /**
     * Номер этапа олимпиады
     */
    @Column(name = "tour_number", nullable = false)
    private Integer tourNumber;
    /**
     * Дата начала регистрации
     */
    @Column(name = "start_reg")
    private LocalDate startReg;
    /**
     * Дата окончания регистрации
     */
    @Column(name = "end_reg")
    private LocalDate endReg;
    /**
     * Открыта ли регистрация
     */
    @Column(name = "is_open_for_requests")
    private Boolean isOpenForRequests;
    /**
     * Дата начала этапа олимпиады
     */
    @Column(name = "begin_tour", nullable = false)
    private LocalDate beginTour;
    /**
     * Дата окончания этапа олимпиады
     */
    @Column(name = "end_tour", nullable = false)
    private LocalDate endTour;
    /**
     * Адресс
     */
    @Column(name = "address", length = 512, nullable = false)
    private String address;
    /**
     * Комментарий по дополнению описания этапа олимпиады
     */
    @Column(name = "comment_text")
    private String text;
    /**
     * Уровень олимпиады
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_olymp_level")
    private OlympLevel level;
    /**
     * Формат олимпиады
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_olymp_format")
    private OlympFormat format;
    /**
     * Список команд для участия на этапе олимпиады
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_olymp_tour",
            inverseJoinColumns = @JoinColumn(name = "id_olymp_tour"),
            joinColumns = @JoinColumn(name = "id_team")
    )
    private List<Team> teams;
    /**
     * Список языков на этапе олимпиады
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lang_olymp_tour",
            inverseJoinColumns = @JoinColumn(name = "id_olymp_tour"),
            joinColumns = @JoinColumn(name = "id_lang")
    )
    private List<Language> languages;
    /**
     * Список факультативов по подготовке к этапу олимпиады
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "elective_course_olymp_tour",
            inverseJoinColumns = @JoinColumn(name = "id_olymp_tour"),
            joinColumns = @JoinColumn(name = "id_elective_course")
    )
    private List<ElectiveCourse> electiveCourses;
}