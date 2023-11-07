package ru.fenris06.mapper.film;

import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;
import ru.fenris06.dto.GenreDto;
import ru.fenris06.mapper.genre.GenreMapper;
import ru.fenris06.mapper.mpa.MpaMapper;
import ru.fenris06.model.film.Film;


import java.util.List;
import java.util.stream.Collectors;

public class FilmMapper {

    public static Film fromDto(FilmInputDto filmDto) {
        Film film = new Film();
        film.setName(filmDto.getName());
        film.setDescription(filmDto.getDescription());
        film.setReleaseDate(filmDto.getReleaseDate());
        film.setDuration(filmDto.getDuration());
        return film;
    }

    public static FilmOutDto toDto(Film film) {
        FilmOutDto filmOutDto = new FilmOutDto();
        filmOutDto.setId(film.getId());
        filmOutDto.setName(film.getName());
        filmOutDto.setDescription(film.getDescription());
        filmOutDto.setReleaseDate(film.getReleaseDate());
        filmOutDto.setDuration(film.getDuration());
        filmOutDto.setMpa(MpaMapper.toDto(film.getMpa()));
        List<GenreDto> genreDtos = film.getGenres().stream()
                .map(GenreMapper::toDto)
                .collect(Collectors.toList());
        filmOutDto.setGenres(genreDtos);
        return filmOutDto;
    }
}
