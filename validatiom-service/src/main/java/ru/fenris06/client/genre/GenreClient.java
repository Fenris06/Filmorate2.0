package ru.fenris06.client.genre;

import ru.fenris06.dto.GenreDto;

import java.util.List;

public interface GenreClient {

    List<GenreDto> getGenres();

    GenreDto getGenre(Long id);
}
