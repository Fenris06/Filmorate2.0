package ru.fenris06.client.genre;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fenris06.client.BaseClient;
import ru.fenris06.dto.GenreDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreClient extends BaseClient {
    private static final String GENRE = "/genres";


    public GenreClient(@Value("${client.url:http://localhost:8081}") String url) {
        super(url);
    }

    public List<GenreDto> getGenres() {
        Object[] genres = get(GENRE);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Arrays.stream(genres)
                .map(o -> mapper.convertValue(o, GenreDto.class))
                .collect(Collectors.toList());
    }

    public GenreDto getGenre(Long id) {
        Object genre = get(GENRE, id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(genre, GenreDto.class);
    }
}
