package ru.fenris06.client.mpa;

import ru.fenris06.dto.MpaDto;

import java.util.List;

public interface MpaClient {

    List<MpaDto> getMpas();

    MpaDto getMpa(Long id);
}
