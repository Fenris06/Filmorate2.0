package ru.fenris06.service.film.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.fenris06.dto.FilmInputDto;
import ru.fenris06.dto.FilmOutDto;

import ru.fenris06.dto.GenreDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.mapper.film.FilmMapper;
import ru.fenris06.model.film.Film;
import ru.fenris06.model.genre.Genre;
import ru.fenris06.model.mpa.Mpa;
import ru.fenris06.model.user.User;
import ru.fenris06.repository.film.FilmRepository;
import ru.fenris06.repository.genre.GenreRepository;
import ru.fenris06.repository.mpa.MpaRepository;
import ru.fenris06.repository.user.UserRepository;
import ru.fenris06.service.film.FilmService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final MpaRepository mpaRepository;
    private final GenreRepository genreRepository;

    @Override
    public FilmOutDto create(FilmInputDto filmInputDto) {
        Film film = FilmMapper.fromDto(filmInputDto);
        Mpa mpa = getMpa(filmInputDto.getMpa().getId());
        List<Genre> genres = getGenre(filmInputDto);
        film.setMpa(mpa);
        film.setGenres(genres);
        return FilmMapper.toDto(filmRepository.save(film));
    }

    @Override
    public FilmOutDto update(FilmInputDto filmInputDto) {
        Film film = getFilmById(filmInputDto.getId());
        setFilmFields(film, filmInputDto);
        Mpa mpa = getMpa(filmInputDto.getMpa().getId());
        List<Genre> genres = getGenre(filmInputDto);
        film.setMpa(mpa);
        film.setGenres(genres);
        return FilmMapper.toDto(filmRepository.save(film));
    }

    @Override
    public List<FilmOutDto> getFilms() {
        return filmRepository.findAll()
                .stream()
                .map(FilmMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addLike(Long id, Long userId) {
        Film film = getFilmById(id);
        User user = getUserById(userId);
        film.getLikes().add(user);
        filmRepository.save(film);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        Film film = getFilmById(id);
        User user = getUserById(userId);
        film.getLikes().remove(user);
    }

    @Override
    public List<FilmOutDto> getPopularFilms(Integer count) {
        return filmRepository.getPopularFilm(count)
                .stream()
                .map(FilmMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FilmOutDto getFilm(Long id) {
        return FilmMapper.toDto(getFilmById(id));
    }

    private void setFilmFields(Film film, FilmInputDto filmDto) {
        Optional.ofNullable(filmDto.getName()).ifPresent(film::setName);
        Optional.ofNullable(filmDto.getDescription()).ifPresent(film::setDescription);
        Optional.ofNullable(filmDto.getReleaseDate()).ifPresent(film::setReleaseDate);
        Optional.ofNullable(filmDto.getDuration()).ifPresent(film::setDuration);
    }

    private Film getFilmById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Film with id = " + id + " not found"));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User id = " + id + " not found"));
    }

    private Mpa getMpa(Long id) {
        return mpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mpa id = " + id + " not found"));
    }

    private List<Genre> getGenre(FilmInputDto filmInputDto) {
        if (filmInputDto.getGenres() == null || filmInputDto.getGenres().isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> ids = filmInputDto.getGenres().stream()
                .map(GenreDto::getId)
                .collect(Collectors.toList());
        return genreRepository.findAllById(ids);
    }
}
