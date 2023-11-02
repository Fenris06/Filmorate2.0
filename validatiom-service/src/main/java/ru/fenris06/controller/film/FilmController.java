package ru.fenris06.controller.film;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fenris06.client.film.FilmClient;
import ru.fenris06.dto.FilmDto;
import ru.fenris06.validation.Create;
import ru.fenris06.validation.Update;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Validated
public class FilmController {
    private final FilmClient filmClient;

    @PostMapping
    @Validated(Create.class)
    public FilmDto create(@RequestBody @Valid FilmDto filmDto) {
        return filmClient.createFilm(filmDto);
    }

    @PutMapping
    @Validated(Update.class)
    public FilmDto update(@RequestBody @Valid FilmDto filmDto) {
        return filmClient.updateFilm(filmDto);
    }

    @GetMapping
    public List<FilmDto> getFilms() {
        return filmClient.getFilms();
    }

    @GetMapping("{id}")
    public FilmDto getFilm(@PathVariable("id") Long id) {
        return filmClient.getFilm(id);
    }

    @PutMapping("{id}/like/{userId}")
    public void addLike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        filmClient.addLike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    public void deleteLike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        filmClient.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<FilmDto> getPopularFilms(@RequestParam(name = "count", defaultValue = "10", required = false) Integer count) {
        return filmClient.getPopularFilms(count);
    }
}

