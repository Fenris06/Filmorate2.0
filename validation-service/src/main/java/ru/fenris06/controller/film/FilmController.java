package ru.fenris06.controller.film;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fenris06.client.film.FilmClient;
import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;
import ru.fenris06.validation.Create;
import ru.fenris06.validation.Update;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Validated
@Tag(name = "Film-Controller", description = "Controller for films")
public class FilmController {
    private final FilmClient filmClient;

    @PostMapping
    @Validated(Create.class)
    @Operation(summary = "Add new film in database")
    public FilmOutDto create(@RequestBody @Valid FilmInputDto filmDto) {
        return filmClient.createFilm(filmDto);
    }

    @PutMapping
    @Validated(Update.class)
    @Operation(summary = "Update film in database")
    public FilmOutDto update(@RequestBody @Valid FilmInputDto filmDto) {
        return filmClient.updateFilm(filmDto);
    }

    @GetMapping
    @Operation(summary = "Return all films")
    public List<FilmOutDto> getFilms() {
        return filmClient.getFilms();
    }

    @GetMapping("{id}")
    @Operation(summary = "Return film by id")
    public FilmOutDto getFilm(@PathVariable("id") @Parameter(description = "User Id", required = true) Long id) {
        return filmClient.getFilm(id);
    }

    @PutMapping("{id}/like/{userId}")
    @Operation(summary = "Add new like to film")
    public void addLike(@PathVariable("id") @Parameter(description = "Film Id", required = true) Long id,
                        @PathVariable("userId") @Parameter(description = "User Id", required = true) Long userId) {
        filmClient.addLike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    @Operation(summary = "Delete like film")
    public void deleteLike(@PathVariable("id") @Parameter(description = "Film Id", required = true) Long id,
                           @PathVariable("userId") @Parameter(description = "User Id", required = true) Long userId) {
        filmClient.deleteLike(id, userId);

    }

    @GetMapping("/popular")
    @Operation(summary = "Return most popular films")
    public List<FilmOutDto> getPopularFilms(@RequestParam(name = "count", defaultValue = "10", required = false)
                                            @Parameter(description = "Count of films") Integer count) {
        return filmClient.getPopularFilms(count);
    }
}

