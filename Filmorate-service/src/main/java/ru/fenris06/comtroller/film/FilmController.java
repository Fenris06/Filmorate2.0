package ru.fenris06.comtroller.film;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fenris06.dto.FilmDto;
import ru.fenris06.service.film.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public FilmDto create(@RequestBody FilmDto filmDto) {
        return filmService.create(filmDto);
    }

    @PutMapping
    public FilmDto update(@RequestBody FilmDto filmDto) {
        return filmService.update(filmDto);
    }

    @GetMapping
    public List<FilmDto> getFilms() {
        return filmService.getFilms();
    }
}
