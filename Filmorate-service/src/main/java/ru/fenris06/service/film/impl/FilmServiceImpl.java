package ru.fenris06.service.film.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fenris06.dto.FilmDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.mapper.film.FilmMapper;
import ru.fenris06.model.film.Film;
import ru.fenris06.repository.film.FilmRepository;
import ru.fenris06.service.film.FilmService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    @Override
    public FilmDto create(FilmDto filmDto) {
        return FilmMapper.toDto(filmRepository.save(FilmMapper.fromDto(filmDto)));
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        Film film = filmRepository.findById(filmDto.getId())
                .orElseThrow(()-> new NotFoundException("Film with id = " + filmDto.getId() + " not found"));
        setFilmFields(film, filmDto);
        return FilmMapper.toDto(filmRepository.save(film));
    }

    @Override
    public List<FilmDto> getFilms() {
        return filmRepository.findAll()
                .stream()
                .map(FilmMapper::toDto)
                .collect(Collectors.toList());
    }

    private void setFilmFields(Film film, FilmDto filmDto) {
        Optional.ofNullable(filmDto.getName()).ifPresent(film::setName);
        Optional.ofNullable(filmDto.getDescription()).ifPresent(film::setDescription);
        Optional.ofNullable(filmDto.getReleaseDate()).ifPresent(film::setReleaseDate);
        Optional.ofNullable(filmDto.getDuration()).ifPresent(film::setDuration);
    }
}
