package ru.fenris06.service.mpa;

import ru.fenris06.dto.MpaDto;

import java.util.List;

public interface MpaService {
    List<MpaDto> getMpas();

    MpaDto getMpa(Long id);
}
