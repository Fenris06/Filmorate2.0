package ru.fenris06.service.genre;

import ru.fenris06.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getGenres();

    GenreDto getGenre(Long id);
}
