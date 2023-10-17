package ru.fenris06.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fenris06.client.UserClient;
import ru.fenris06.dto.UserDto;
import ru.fenris06.validation.Create;

import javax.validation.Valid;

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
}
