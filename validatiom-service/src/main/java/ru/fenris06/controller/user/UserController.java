package ru.fenris06.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fenris06.client.user.UserClient;
import ru.fenris06.dto.UserDto;
import ru.fenris06.validation.Create;
import ru.fenris06.validation.Update;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserClient userClient;

    @PostMapping
    @Validated(Create.class)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userClient.createUser(userDto);
    }

    @PutMapping
    @Validated(Update.class)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userClient.updateUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userClient.getUsers();
    }
}
