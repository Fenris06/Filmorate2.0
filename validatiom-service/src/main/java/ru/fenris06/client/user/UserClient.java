package ru.fenris06.client.user;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.fenris06.dto.UserDto;
import ru.fenris06.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClient {
    private final WebClient webClient;

    public UserClient(@Value("${client.url:http://localhost:8081}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public UserDto createUser(UserDto userDto) {
        return webClient
                .post()
                .uri("/users")
                .body(Mono.just(userDto), UserDto.class)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public UserDto updateUser(UserDto userDto) {
        return webClient
                .put()
                .uri("/users")
                .body(Mono.just(userDto), UserDto.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        clientResponse ->
                                Mono.error(() -> new NotFoundException("User id = " + userDto.getId() + " not found")))
                .bodyToMono(UserDto.class)
                .block();
    }

    public List<UserDto> getUsers() {
        Mono<UserDto[]> response = webClient
                .get()
                .uri("/users")
                .retrieve()
                .bodyToMono(UserDto[].class).log();
        UserDto[] userDtos = response.block();
        return Arrays.stream(userDtos)
                .collect(Collectors.toList());
    }
}
