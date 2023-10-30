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
}

