package ru.zaharova.studentcourse.rest.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaharova.studentcourse.rest.dto.request.user.EditUserRequest;
import ru.zaharova.studentcourse.rest.dto.request.user.NewUserRequest;
import ru.zaharova.studentcourse.rest.dto.response.user.UserDto;
import ru.zaharova.studentcourse.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> edit(@PathVariable Long id, @RequestBody EditUserRequest request) {
        return ResponseEntity.ok(userService.edit(id, request));
    }

}