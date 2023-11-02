package ru.fenris06.service.film;

import ru.fenris06.dto.FilmDto;


import java.util.List;

public interface FilmService {

    FilmDto create(FilmDto filmDto);

    FilmDto update(FilmDto filmDto);

    List<FilmDto> getFilms();
}
