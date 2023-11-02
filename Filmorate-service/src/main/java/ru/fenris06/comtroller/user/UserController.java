package ru.fenris06.comtroller.user;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import ru.fenris06.dto.UserDto;

import ru.fenris06.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto crateUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers() {
       return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id ) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        userService.addFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<UserDto> getFriends(@PathVariable("id") Long id) {
        return userService.getFriends(id);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend (@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        userService.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<UserDto> getCommon(@PathVariable("id") Long id, @PathVariable("otherId") Long otherId) {
        return userService.getCommon(id, otherId);
    }

}
