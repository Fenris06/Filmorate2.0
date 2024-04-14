package ru.fenris06.mapper.mpa;

import ru.fenris06.dto.MpaDto;
import ru.fenris06.model.mpa.Mpa;

public class MpaMapper {

    public static Mpa fromDto(MpaDto mpaDto) {
        Mpa mpa = new Mpa();
        mpa.setId(mpaDto.getId());
        return mpa;
    }

    public static MpaDto toDto(Mpa mpa) {
        MpaDto mpaDto = new MpaDto();
        mpaDto.setId(mpa.getId());
        mpaDto.setName(mpa.getName());
        return mpaDto;
    }
}
