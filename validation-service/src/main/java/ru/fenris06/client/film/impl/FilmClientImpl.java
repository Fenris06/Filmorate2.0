package ru.fenris06.client.film.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.fenris06.client.film.FilmClient;
import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmClientImpl implements FilmClient {
    private final WebClient webClient;
    private static final String URI = "/films";
    private static final String LIKE = "/like";
    private static final String POPULAR = "/popular";


    @Override
    public FilmOutDto createFilm(FilmInputDto filmDto) {
        return webClient
                .post()
                .uri(URI)
                .bodyValue(filmDto)
                .retrieve()
                .bodyToMono(FilmOutDto.class)
                .block();
    }

    @Override
    public FilmOutDto updateFilm(FilmInputDto filmDto) {
        return webClient
                .put()
                .uri(URI)
                .bodyValue(filmDto)
                .retrieve()
                .bodyToMono(FilmOutDto.class)
                .block();
    }

    @Override
    public List<FilmOutDto> getFilms() {
        return webClient
                .get()
                .uri(URI)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FilmOutDto>>() {
                })
                .block();
    }

    @Override
    public FilmOutDto getFilm(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(FilmOutDto.class)
                .block();
    }

    @Override
    public void addLike(Long id, Long userId) {
        webClient
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}" + LIKE + "/{userId}")
                        .build(id, userId))
                .retrieve();
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        webClient
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + "/{id}" + LIKE + "/{userId}")
                        .build(id, userId))
                .retrieve();
    }

    @Override
    public List<FilmOutDto> getPopularFilms(Integer count) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI + POPULAR)
                        .queryParam("count", "{count}")
                        .build(count))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FilmOutDto>>() {
                })
                .block();
    }
}
