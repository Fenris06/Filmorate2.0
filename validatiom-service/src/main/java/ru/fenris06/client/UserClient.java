package ru.fenris06.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.fenris06.dto.UserDto;

@Service
public class UserClient {
    private final WebClient webClient;

    public UserClient(@Value("${client.url:http://localhost:8081}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_RANGE, MediaType.APPLICATION_JSON_VALUE)
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

}
