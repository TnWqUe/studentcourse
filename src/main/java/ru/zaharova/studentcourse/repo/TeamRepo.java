package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.Team;

import java.util.Optional;


@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
    Optional<Team> findById(Long id);
    // существует ли пользователь с таким id
    boolean existsById(Long id);
}