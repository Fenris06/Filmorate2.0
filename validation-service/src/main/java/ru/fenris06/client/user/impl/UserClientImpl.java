package ru.fenris06.client.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ru.fenris06.client.user.UserClient;
import ru.fenris06.dto.UserDto;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserClientImpl implements UserClient {
    private static final String URI = "/users";
    private static final String FRIENDS_URI = "/friends";
    private static final String COMMON_URI = "/common";
    private final WebClient webClient;

    public UserDto createUser(UserDto userDto) {
        return webClient
                .post()
                .uri(URI)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return webClient
                .put()
                .uri(URI)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public List<UserDto> getUsers() {
        return webClient
                .get()
                .uri(URI)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {
                })
                .block();
    }

    @Override
    public UserDto getUser(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public void addFriend(Long id, Long friendId) {
        webClient
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}" + FRIENDS_URI + "/{friendId}")
                        .build(id, friendId))
                .retrieve();
    }

    @Override
    public List<UserDto> getFriends(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}" + FRIENDS_URI)
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {
                })
                .block();
    }

    @Override
    public void deleteFriend(Long id, Long friendId) {
        webClient
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}" + FRIENDS_URI + "/{friendId}")
                        .build(id, friendId))
                .retrieve();
    }

    @Override
    public List<UserDto> getCommon(Long id, Long otherId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}" + FRIENDS_URI + COMMON_URI + "/{otherId}")
                        .build(id, otherId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {
                })
                .block();
    }
}
