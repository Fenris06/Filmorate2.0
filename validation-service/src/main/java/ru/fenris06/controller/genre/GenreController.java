package ru.fenris06.controller.genre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Genre-Controller", description = "Controller for film genres")
public class GenreController {
    private final GenreClient genreClient;

    @GetMapping
    @Operation(summary = "Get all genres")
    public List<GenreDto> getGenres() {
        return genreClient.getGenres();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get genres by id")
    public GenreDto getGenre(@PathVariable("id") Long id) {
        return genreClient.getGenre(id);
    }
}