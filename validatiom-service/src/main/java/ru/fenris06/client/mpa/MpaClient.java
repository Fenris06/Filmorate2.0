package ru.fenris06.client.mpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fenris06.client.BaseClient;
import ru.fenris06.dto.MpaDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MpaClient extends BaseClient {
    public static final String MPA = "/mpa";

    public MpaClient(@Value("${client.url:http://localhost:8081}") String url) {
        super(url);
    }

    public List<MpaDto> getMpas() {
        Object[] mpas = get(MPA);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(mpas)
                .map(o -> mapper.convertValue(o, MpaDto.class))
                .collect(Collectors.toList());
    }

    public MpaDto getMpa(Long id) {
        Object mpa = get(MPA, id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(mpa, MpaDto.class);
    }
}
