package ru.fenris06.service.genre.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fenris06.dto.GenreDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.mapper.genre.GenreMapper;
import ru.fenris06.repository.genre.GenreRepository;
import ru.fenris06.service.genre.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    @Override
    public List<GenreDto> getGenres() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto getGenre(Long id) {
        return GenreMapper.toDto(genreRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Genre id " + id + " not found")));
    }
}
