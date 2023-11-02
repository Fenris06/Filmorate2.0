package ru.fenris06.service.film;

import ru.fenris06.dto.FilmDto;


import java.util.List;

public interface FilmService {

    FilmDto create(FilmDto filmDto);

    FilmDto update(FilmDto filmDto);

    List<FilmDto> getFilms();

    void addLike(Long id, Long userId);

    void deleteLike(Long id, Long userId);

    List<FilmDto> getPopularFilms(Integer count);

    FilmDto getFilm(Long id);
}
