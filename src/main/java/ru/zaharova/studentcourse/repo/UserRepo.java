package ru.zaharova.studentcourse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharova.studentcourse.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    //поиск пароля по id пользователя
    //   @Query(value = "select u.usr_psw from usr u where id = :id", nativeQuery = true)
   // String findPasswordByUserId(@Param("id") Long id);

    // существует ли пользователь с таким id
    boolean existsById(Long id);
    // существует ли пользователь с таким именем
    boolean existsByUsername(String username);
}

