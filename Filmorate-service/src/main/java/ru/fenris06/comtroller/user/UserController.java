package ru.fenris06.comtroller.user;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import ru.fenris06.dto.UserDto;
import ru.fenris06.service.user.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto crateUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
