package ru.fenris06.service.film.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.fenris06.dto.FilmDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.mapper.film.FilmMapper;
import ru.fenris06.model.film.Film;
import ru.fenris06.model.user.User;
import ru.fenris06.repository.film.FilmRepository;
import ru.fenris06.repository.user.UserRepository;
import ru.fenris06.service.film.FilmService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Override
    public FilmDto create(FilmDto filmDto) {
        return FilmMapper.toDto(filmRepository.save(FilmMapper.fromDto(filmDto)));
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        Film film = getFilmById(filmDto.getId());
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
    public List<FilmDto> getPopularFilms(Integer count) {
        return filmRepository.getPopularFilm(count)
                .stream()
                .map(FilmMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDto getFilm(Long id) {
        return FilmMapper.toDto(getFilmById(id));
    }

    private void setFilmFields(Film film, FilmDto filmDto) {
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
}
