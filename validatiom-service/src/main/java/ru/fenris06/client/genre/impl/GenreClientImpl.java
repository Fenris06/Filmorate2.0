package ru.fenris06.client.genre.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.fenris06.client.genre.GenreClient;
import ru.fenris06.dto.GenreDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreClientImpl implements GenreClient {
    private final WebClient webClient;
    private static final String GENRE = "/genres";


    @Override
    public List<GenreDto> getGenres() {
        return webClient
                .get()
                .uri(GENRE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GenreDto>>() {
                })
                .block();
    }

    @Override
    public GenreDto getGenre(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GENRE + "/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(GenreDto.class)
                .block();
    }
}
