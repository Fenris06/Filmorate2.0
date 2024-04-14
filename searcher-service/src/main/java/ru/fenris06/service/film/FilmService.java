package ru.fenris06.service.film;

import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;


import java.util.List;

public interface FilmService {

    FilmOutDto create(FilmInputDto filmDto);

    FilmOutDto update(FilmInputDto filmDto);

    List<FilmOutDto> getFilms();

    void addLike(Long id, Long userId);

    void deleteLike(Long id, Long userId);

    List<FilmOutDto> getPopularFilms(Integer count);

    FilmOutDto getFilm(Long id);
}
