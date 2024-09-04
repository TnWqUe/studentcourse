package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.Language;

import java.util.Optional;
@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
    Optional<Language> findById(Long id);
    // существует ли язык олимпиады с таким id
    boolean existsById(Long id);
}
