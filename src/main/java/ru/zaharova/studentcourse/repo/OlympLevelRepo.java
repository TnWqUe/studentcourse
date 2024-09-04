package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.OlympLevel;

import java.util.Optional;
@Repository
public interface OlympLevelRepo extends JpaRepository<OlympLevel, Long> {
    Optional<OlympLevel> findById(Long id);
    // существует ли олимпиадный уровень с таким id
    boolean existsById(Long id);
}
