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
import javax.validation.constraints.Min;
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
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userClient.updateUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userClient.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") @Min(1) Long id) {
        return userClient.getUser(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        userClient.addFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<UserDto> getFriends(@PathVariable("id") Long id) {
        return userClient.getFriends(id);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        userClient.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<UserDto> getCommon(@PathVariable("id") Long id, @PathVariable("otherId") Long otherId) {
        return userClient.getCommon(id, otherId);
    }
}
