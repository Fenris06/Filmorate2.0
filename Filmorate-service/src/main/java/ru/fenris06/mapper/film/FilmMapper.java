package ru.fenris06.mapper.film;

import ru.fenris06.dto.FilmDto;
import ru.fenris06.model.film.Film;

public class FilmMapper {

    public static Film fromDto(FilmDto filmDto) {
        Film film = new Film();
        film.setName(filmDto.getName());
        film.setDescription(filmDto.getDescription());
        film.setReleaseDate(filmDto.getReleaseDate());
        film.setDuration(filmDto.getDuration());
        return film;
    }

    public static FilmDto toDto(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setName(film.getName());
        filmDto.setDescription(film.getDescription());
        filmDto.setReleaseDate(film.getReleaseDate());
        filmDto.setDuration(film.getDuration());
        return filmDto;
    }
}
