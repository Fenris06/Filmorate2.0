package ru.fenris06.client.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import ru.fenris06.client.BaseClient;
import ru.fenris06.dto.UserDto;


import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClient extends BaseClient {
    private static final String URI = "/users";
    private static final String FRIENDS_URI = "/friends";
    private static final String COMMON_URI = "/common";

    public UserClient(@Value("${client.url:http://localhost:8081}") String url) {
        super(url);
    }

    public UserDto createUser(UserDto userDto) {
        return (UserDto) create(userDto, URI);
    }

    public UserDto updateUser(UserDto userDto) {
        return (UserDto) update(userDto, URI);
    }

    public List<UserDto> getUsers() {
        Object[] users = get(URI);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(users)
                .map(o -> mapper.convertValue(o, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        Object user = get(URI, id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(user, UserDto.class);
    }

    public void addFriend(Long id, Long friendId) {
        put(URI, FRIENDS_URI, id, friendId);
    }

    public List<UserDto> getFriends(Long id) {
        Object[] users = get(URI, id, FRIENDS_URI);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(users)
                .map(o -> mapper.convertValue(o, UserDto.class))
                .collect(Collectors.toList());
    }

    public void deleteFriend(Long id, Long friendId) {
        delete(URI, FRIENDS_URI, id, friendId);
    }

    public List<UserDto> getCommon(Long id, Long otherId) {
        Object[] users = get(URI, id, FRIENDS_URI + COMMON_URI, otherId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(users)
                .map(o -> mapper.convertValue(o, UserDto.class))
                .collect(Collectors.toList());
    }
}

