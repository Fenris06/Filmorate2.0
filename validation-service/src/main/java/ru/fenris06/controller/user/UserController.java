package ru.fenris06.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User-Controller", description = "Controller for users")
public class UserController {
    private final UserClient userClient;

    @PostMapping
    @Validated(Create.class)
    @Operation(summary = "Controller for create user")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userClient.createUser(userDto);
    }

    @PutMapping
    @Validated(Update.class)
    @Operation(summary = "Controller for update user")
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userClient.updateUser(userDto);
    }

    @GetMapping
    @Operation(summary = "Controller for get all users")
    public List<UserDto> getUsers() {
        return userClient.getUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Controller for get user by id")
    public UserDto getUser(@PathVariable("id") @Min(1) @Parameter(description = "User Id", required = true) Long id) {
        return userClient.getUser(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    @Operation(summary = "Controller for add user friend")
    public void addFriend(@PathVariable("id") @Parameter(description = "User Id", required = true) Long id, @PathVariable("friendId") @Parameter(description = "User Id", required = true) Long friendId) {
        userClient.addFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    @Operation(summary = "Controller for get user friends by user id")
    public List<UserDto> getFriends(@PathVariable("id") @Parameter(description = "User Id", required = true) Long id) {
        return userClient.getFriends(id);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    @Operation(summary = "Controller for delete user friends by user id")
    public void deleteFriend(@PathVariable("id") @Parameter(description = "User Id", required = true) Long id, @PathVariable("friendId") @Parameter(description = "User Id", required = true) Long friendId) {
        userClient.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    @Operation(summary = "Controller for get common users friends by users ids")
    public List<UserDto> getCommon(@PathVariable("id") @Parameter(description = "User Id", required = true) Long id, @PathVariable("otherId") @Parameter(description = "User Id", required = true) Long otherId) {
        return userClient.getCommon(id, otherId);
    }
}
