package ru.fenris06.mapper.genre;

import ru.fenris06.dto.GenreDto;
import ru.fenris06.model.genre.Genre;

public class GenreMapper {

    public static Genre fromDto(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        return genre;
    }

    public static GenreDto toDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }
}
