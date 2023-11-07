package ru.fenris06.client.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import ru.fenris06.client.BaseClient;
import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmClient extends BaseClient {
    private static final String URI = "/films";
    private static final String LIKE = "/like";
    private static final String POPULAR = "/popular";

    public FilmClient(@Value("${client.url:http://localhost:8081}") String url) {
        super(url);
    }

    public FilmOutDto createFilm(FilmInputDto filmDto) {
        Object film = create(filmDto, URI);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(film, FilmOutDto.class);
    }

    public FilmOutDto updateFilm(FilmInputDto filmDto) {
        Object film = update(filmDto, URI);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(film, FilmOutDto.class);
    }

    public List<FilmOutDto> getFilms() {
        Object[] films = get(URI);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(films)
                .map(o -> mapper.convertValue(o, FilmOutDto.class))
                .collect(Collectors.toList());
    }

    public FilmOutDto getFilm(Long id) {
        Object film = get(URI, id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(film, FilmOutDto.class);
    }

    public void addLike(Long id, Long userId) {
        put(URI, LIKE, id, userId);
    }

    public void deleteLike(Long id, Long userId) {
        delete(URI, LIKE, id, userId);
    }

    public List<FilmOutDto> getPopularFilms(Integer count) {
        Object[] films = getQuery(URI, POPULAR, count);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(films)
                .map(o -> mapper.convertValue(o, FilmOutDto.class))
                .collect(Collectors.toList());
    }
}
