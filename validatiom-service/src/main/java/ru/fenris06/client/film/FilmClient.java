package ru.fenris06.client.film;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.fenris06.dto.FilmDto;
import ru.fenris06.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmClient {
    private final WebClient webClient;

    public FilmClient(@Value("${client.url:http://localhost:8081}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public FilmDto create(FilmDto filmDto) {
        return webClient
                .post()
                .uri("/films")
                .body(Mono.just(filmDto), FilmDto.class)
                .retrieve()
                .bodyToMono(FilmDto.class)
                .block();
    }

    public FilmDto update(FilmDto filmDto) {
        return webClient
                .put()
                .uri("/films")
                .body(Mono.just(filmDto), FilmDto.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        clientResponse -> Mono.error(
                                () -> new NotFoundException("Film with id = " + filmDto.getId() + " not found")))
                .bodyToMono(FilmDto.class)
                .block();
    }

    public List<FilmDto> getFilms() {
        Mono<FilmDto[]> response = webClient
                .get()
                .uri("/films")
                .retrieve()
                .bodyToMono(FilmDto[].class);
        FilmDto[] films = response.block();
        return Arrays.stream(films)
                .collect(Collectors.toList());
    }
}
