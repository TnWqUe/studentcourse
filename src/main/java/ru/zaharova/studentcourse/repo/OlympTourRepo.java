package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.OlympTour;

import java.util.Optional;


@Repository
public interface OlympTourRepo extends JpaRepository<OlympTour, Long> {
    Optional<OlympTour> findByName(String name);
    Optional<OlympTour> findById(Long id);
    // существует ли пользователь с таким id
    boolean existsById(Long id);
}