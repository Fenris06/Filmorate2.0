package ru.fenris06.client.mpa;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.fenris06.dto.MpaDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MpaClientImpl implements MpaClient {
    private final WebClient webClient;
    public static final String MPA = "/mpa";


    @Override
    public List<MpaDto> getMpas() {
        return webClient
                .get()
                .uri(MPA)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MpaDto>>() {
                })
                .block();
    }

    @Override
    public MpaDto getMpa(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(MPA + "/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(MpaDto.class)
                .block();
    }
}
