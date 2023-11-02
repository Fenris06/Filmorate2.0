package ru.fenris06.service.user;

import ru.fenris06.dto.UserDto;
import ru.fenris06.model.user.User;


import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    List<UserDto> getUsers();

    UserDto getUser(Long id);

    void addFriend(Long id, Long friendId);

    List<UserDto> getFriends(Long id);

    void deleteFriend(Long id, Long friendId);

    List<UserDto> getCommon(Long id, Long otherId);
}
