package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.ElectiveCourse;

import java.util.Optional;

@Repository
public interface ElectiveCourseRepo extends JpaRepository<ElectiveCourse, Long> {
    Optional<ElectiveCourse> findById(Long id);
    // существует ли факуьтатив с таким id
    boolean existsById(Long id);
    Optional<ElectiveCourse> findByName(String name);
}