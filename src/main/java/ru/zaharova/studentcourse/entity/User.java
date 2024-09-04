package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
/**
 * Репрезентация таблицы пользователей
 */
@Entity
@Table(name = "usr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity{
    /**
     * Фамилия
     */
    @Column(name = "surname", nullable = false, length = 40)
    private String surname;
    /**
     * Имя
     */
    @Column(name = "usrname", nullable = false, length = 40)
    private String username;
    /**
     * Отчество
     */
    @Column(name = "patronymic", nullable = false, length = 40)
    private String patronymic;
    /**
     * Дата создания аккаунта
     */
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    /**
     * Email пользователя
     */
    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;
    /**
     * Пароль пользователя
     */
    @Column(name = "password", nullable = false, length = 150)
    private String password;
    /**
     * Список ролей пользователя
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usr_usr_role",
            inverseJoinColumns = @JoinColumn(name = "id_usr"),
            joinColumns = @JoinColumn(name = "id_usr_role")
    )
    private List<Role> roles;
    /**
     * пользователь-студент
     */
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "user")
    private Student student;
    /**
     * пользователь-преподаватель
     */
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "user")
    private Teacher teacher;
}