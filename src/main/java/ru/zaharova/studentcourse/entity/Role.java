package ru.zaharova.studentcourse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * Репрезентация таблицы ролей пользователей
 */
@Entity
@Table(name = "usr_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity{
    /**
     * Название роли
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    /**
     * Список пользователей роли
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usr_usr_role",
            inverseJoinColumns = @JoinColumn(name = "id_usr_role"),
            joinColumns = @JoinColumn(name = "id_usr")
    )
    private List<User> users;
}
