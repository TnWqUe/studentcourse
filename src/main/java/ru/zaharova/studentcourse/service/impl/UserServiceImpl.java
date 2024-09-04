package ru.zaharova.studentcourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zaharova.studentcourse.entity.AbstractEntity;
import ru.zaharova.studentcourse.entity.User;
import ru.zaharova.studentcourse.exception.exceptions.user.UserFieldsEmptyException;
import ru.zaharova.studentcourse.exception.exceptions.user.UserNotFoundException;
import ru.zaharova.studentcourse.exception.exceptions.user.WeakPasswordException;
import ru.zaharova.studentcourse.repo.UserRepo;
import ru.zaharova.studentcourse.rest.dto.request.user.EditPasswordRequest;
import ru.zaharova.studentcourse.rest.dto.request.user.EditUserRequest;
import ru.zaharova.studentcourse.rest.dto.request.user.NewUserRequest;
import ru.zaharova.studentcourse.rest.dto.response.user.UserDto;
import ru.zaharova.studentcourse.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> all = userRepo.findAll();
        return all
                .stream()
                .map(user -> UserDto.builder()
                        .surname(user.getSurname())
                        .username(user.getUsername())
                        .patronymic(user.getPatronymic())
                        .email(user.getEmail())
                        .rolesIds(user.getRoles().stream().map(AbstractEntity::getId)
                                .collect(Collectors.toList()))
                        .createdAt(user.getCreatedAt())
                        .student(user.getStudent())
                        .teacher(user.getTeacher())
                        .build())
                .toList();
    }

    @Override
    public Long addNewUser(NewUserRequest request) {

        if (!StringUtils.hasText(request.getEmail()) ||
                !StringUtils.hasText(request.getSurname()) ||
                !StringUtils.hasText(request.getUsername()) ||
                !StringUtils.hasText(request.getPatronymic()) ||
                !StringUtils.hasText(request.getPassword())) {
            throw new UserFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (request.getPassword().length() < 8) {
            throw new WeakPasswordException("Пароль слишком слабый - необходимо как минимум 8 знаков");
        }

        User user = new User();
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setPatronymic(request.getPatronymic());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setCreatedAt(LocalDate.now());
        userRepo.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> userFromDb = userRepo.findById(id);
        if (userFromDb.isEmpty()) {
            throw new UserNotFoundException("Не найден пользователь с id = " + id);
        }
        User user = userFromDb.get();
        return buildDto(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("Не найден пользователь с id = " + id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserDto edit(Long id, EditUserRequest request) {
        Optional<User> userFromDb = userRepo.findById(id);

        if (userFromDb.isEmpty()) {
            throw new UserNotFoundException("Не найден пользователь с id = " + id);
        }

        if (!StringUtils.hasText(request.getEmail()) ||
                !StringUtils.hasText(request.getSurname()) ||
                !StringUtils.hasText(request.getUsername()) ||
                !StringUtils.hasText(request.getPatronymic()) ||
        !StringUtils.hasText(request.getEmail()) ) {
            throw new UserFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        User user = userFromDb.get();
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setPatronymic(request.getPatronymic());
        user.setEmail(request.getEmail());
        userRepo.save(user);
        return buildDto(user);
    }

    @Override
    public UserDto editPassword(Long id, EditPasswordRequest request) {
        Optional<User> userFromDb = userRepo.findById(id);

        if (userFromDb.isEmpty()) {
            throw new UserNotFoundException("Не найден пользователь с id = " + id);
        }

        if (!StringUtils.hasText(request.getPassword())) {
            throw new UserFieldsEmptyException("Некорректные поля в запросе - необходимо заполнить необходимые");
        }

        if (request.getPassword().length() < 8) {
            throw new WeakPasswordException("Пароль слишком слабый - необходимо как минимум 8 знаков");
        }

        User user = userFromDb.get();
        user.setPassword(request.getPassword());
        userRepo.save(user);
        return buildDto(user);
    }

    private UserDto buildDto(User user) {
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .surname(user.getSurname())
                .username(user.getUsername())
                .patronymic(user.getPatronymic())
                .student(user.getStudent())
                .teacher(user.getTeacher())
                .createdAt(user.getCreatedAt())
                .build();

        if(user.getRoles() != null){
            List<Long> rolesIds = user.getRoles().stream()
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            userDto.setRolesIds(rolesIds);
        }

        return userDto;
    }
}
