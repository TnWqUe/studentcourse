package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.Organization;

import java.util.Optional;
@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(Long id);
    // существует ли организация с таким id
    boolean existsById(Long id);
}
