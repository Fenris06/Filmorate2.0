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
        return filmClient.create(filmDto);
    }

    @PutMapping
    @Validated(Update.class)
    public FilmDto update(@RequestBody @Valid FilmDto filmDto) {
        return filmClient.update(filmDto);
    }

    @GetMapping
    public List<FilmDto> getFilms() {
        return filmClient.getFilms();
    }
}

