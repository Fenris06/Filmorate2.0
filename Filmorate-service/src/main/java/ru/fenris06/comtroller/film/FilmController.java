package ru.fenris06.comtroller.film;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;
import ru.fenris06.service.film.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public FilmOutDto create(@RequestBody FilmInputDto filmDto) {
        return filmService.create(filmDto);
    }

    @PutMapping
    public FilmOutDto update(@RequestBody FilmInputDto filmDto) {
        return filmService.update(filmDto);
    }

    @GetMapping
    public List<FilmOutDto> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("{id}")
    public FilmOutDto getFilm(@PathVariable("id") Long id) {
        return filmService.getFilm(id);
    }

    @PutMapping("{id}/like/{userId}")
    public void addLike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    public void deleteLike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<FilmOutDto> getPopularFilms(@RequestParam(name = "count", defaultValue = "10", required = false) Integer count) {
        return filmService.getPopularFilms(count);
    }
}
