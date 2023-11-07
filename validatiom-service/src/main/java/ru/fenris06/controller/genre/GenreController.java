package ru.fenris06.controller.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fenris06.client.genre.GenreClient;
import ru.fenris06.dto.GenreDto;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreClient genreClient;

    @GetMapping
    public List<GenreDto> getGenres() {
        return genreClient.getGenres();
    }

    @GetMapping("/{id}")
    public GenreDto getGenre(@PathVariable("id") Long id) {
        return genreClient.getGenre(id);
    }
}