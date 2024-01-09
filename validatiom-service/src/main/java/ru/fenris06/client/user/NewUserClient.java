package ru.fenris06.client.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fenris06.client.NewBaseClient;
import ru.fenris06.dto.UserDto;


import java.util.List;


@Service
public class NewUserClient extends NewBaseClient<UserDto> {
    private static final String URI = "/users";
    private static final String FRIENDS_URI = "/friends";
    private static final String COMMON_URI = "/common";

    public NewUserClient(@Value("${client.url:http://localhost:8081}") String url) {
        super(url);
    }

    public UserDto createUser(UserDto userDto) {
        return create(userDto, URI);
    }

    public UserDto updateUser(UserDto userDto) {
        return update(userDto, URI);
    }

    public List<UserDto> getUsers() {
        return get(URI);
    }

    public UserDto getUser(Long id) {
        return get(URI, id, new UserDto());
    }

    public void addFriend(Long id, Long friendId) {
        put(URI, FRIENDS_URI, id, friendId);
    }

    public List<UserDto> getFriends(Long id) {
        return get(URI, id, FRIENDS_URI);
    }

    public void deleteFriend(Long id, Long friendId) {
        delete(URI, FRIENDS_URI, id, friendId);
    }

    public List<UserDto> getCommon(Long id, Long otherId) {
        return get(URI, id, FRIENDS_URI + COMMON_URI, otherId);
    }
}
