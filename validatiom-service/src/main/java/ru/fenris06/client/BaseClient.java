package ru.fenris06.client;


import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.fenris06.exception.NotFoundException;

public class BaseClient {
    private final WebClient webClient;

    public BaseClient(String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public <T> Object create(T body, String uri) {
        return webClient
                .post()
                .uri(uri)
                .body(Mono.just(body), body.getClass())
                .retrieve()
                .bodyToMono(body.getClass())
                .block();
    }

    public <T> Object update(T body, String uri) {
        return webClient
                .put()
                .uri(uri)
                .body(Mono.just(body), body.getClass())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        clientResponse ->
                                Mono.error(() -> new NotFoundException(clientResponse.toString())))
                .bodyToMono(body.getClass())
                .block();
    }

    public Object[] get(String uri) {
        Mono<Object[]> response = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object[].class).log();
        Object[] userDtos = response.block();
        return userDtos;
    }
}
