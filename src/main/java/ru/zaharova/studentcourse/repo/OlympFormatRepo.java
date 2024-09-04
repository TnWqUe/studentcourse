package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.OlympFormat;

import java.util.Optional;
@Repository
public interface OlympFormatRepo extends JpaRepository<OlympFormat, Long> {
    Optional<OlympFormat> findById(Long id);
    // существует ли олимпиадный формат с таким id
    boolean existsById(Long id);
}
