package ru.fenris06.client.film;

import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;

import java.util.List;

public interface FilmClient {

    FilmOutDto createFilm(FilmInputDto filmDto);

    FilmOutDto updateFilm(FilmInputDto filmDto);

    List<FilmOutDto> getFilms();

    FilmOutDto getFilm(Long id);

    void addLike(Long id, Long userId);

    void deleteLike(Long id, Long userId);

    List<FilmOutDto> getPopularFilms(Integer count);
}
