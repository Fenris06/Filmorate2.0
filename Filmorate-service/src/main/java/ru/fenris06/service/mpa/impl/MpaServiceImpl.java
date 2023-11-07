package ru.fenris06.service.mpa.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fenris06.dto.MpaDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.mapper.mpa.MpaMapper;
import ru.fenris06.repository.mpa.MpaRepository;
import ru.fenris06.service.mpa.MpaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MpaServiceImpl implements MpaService {
    private final MpaRepository mpaRepository;

    @Override
    public List<MpaDto> getMpas() {
        return mpaRepository.findAll().stream()
                .map(MpaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MpaDto getMpa(Long id) {
        return MpaMapper.toDto(mpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mpa id = " + id + " not found")));
    }
}
