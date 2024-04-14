package ru.fenris06.client.user;


import ru.fenris06.dto.UserDto;

import java.util.List;

public interface UserClient {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    List<UserDto> getUsers();

    UserDto getUser(Long id);

    void addFriend(Long id, Long friendId);

    List<UserDto> getFriends(Long id);

    void deleteFriend(Long id, Long friendId);

    List<UserDto> getCommon(Long id, Long otherId);
}
