package ru.fenris06.client.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import ru.fenris06.client.BaseClient;
import ru.fenris06.dto.FilmDto;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmClient extends BaseClient {
    private static final String URI = "/films";

    public FilmClient(@Value("${client.url:http://localhost:8081}") String url) {
        super(url);
    }

    public FilmDto createFilm(FilmDto filmDto) {
        return (FilmDto) create(filmDto, URI);
    }

    public FilmDto updateFilm(FilmDto filmDto) {
        return (FilmDto) update(filmDto, URI);
    }

    public List<FilmDto> getFilms() {
        Object[] films = get(URI);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(films)
                .map(o -> mapper.convertValue(o, FilmDto.class))
                .collect(Collectors.toList());
    }
}
