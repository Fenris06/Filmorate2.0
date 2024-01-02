package ru.fenris06.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import ru.fenris06.exception.ErrorResponse;
import ru.fenris06.exception.NotFoundException;

import java.util.List;

public class NewBaseClient<T> {
    private final WebClient webClient;

    public NewBaseClient(String url) {
        this.webClient = WebClient.builder()
                .filter(errorHandler())
                .baseUrl(url)
                .build();

    }

    public T create(T body, String uri) {
        return (T) webClient
                .post()
                .uri(uri)
                .body(Mono.just(body), body.getClass())
                .retrieve()
                .bodyToMono(body.getClass())
                .block();
    }

    public T update(T body, String uri) {
        return (T) webClient
                .put()
                .uri(uri)
                .body(Mono.just(body), body.getClass())
                .retrieve()
                .bodyToMono(body.getClass())
                .block();
    }

    public List<T> get(String uri) {
        Mono<List<T>> response = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<T>>() {});
        List<T> userDtos = response.block();
        return userDtos;
        //TODO придумать как сделать get без Object
    }

    public List<T> get(String uri, Long id, String path) {
        Mono<List<T>> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri + "/{id}" + path)
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<T>>() {});
        List<T> userDtos = response.block();;
        return userDtos;
        //TODO придумать как сделать get без Object
    }

    public List<T> get(String uri, Long first, String path, Long secondId) {
        Mono<List<T>> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri + "/{id}" + path + "/{secondId}")
                        .build(first, secondId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<T>>() {});
        List<T> userDtos = response.block();
        return userDtos;
        //TODO придумать как сделать get без Object
    }

    public T get(String uri, Long id, T body) {
        return (T) webClient
                .get()
                .uri(uri + "/" + id)
                .retrieve()
                .bodyToMono(body.getClass())
                .block();
    }

    public Object[] getQuery(String uri, String path, Integer count) {
        Mono<Object[]> response = webClient.get().uri(uriBuilder -> uriBuilder
                        .path(uri + path)
                        .queryParam("count", "{count}").build(count))
                .retrieve()
                .bodyToMono(Object[].class).log();
        Object[] userDtos = response.block();
        return userDtos;
    }

    public void put(String url, String path, Long firstId, Long secondId) {
        webClient.put().uri(uriBuilder -> uriBuilder
                        .path(url + "/{firstId}" + path + "/{secondId}")
                        .build(firstId, secondId))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        //.subscribe();
    }

    public void delete(String url, String path, Long firstId, Long secondId) {
        webClient.delete().uri(uriBuilder -> uriBuilder
                        .path(url + "/{firstId}" + path + "/{secondId}")
                        .build(firstId, secondId))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                return clientResponse.bodyToMono(ErrorResponse.class)
                        .flatMap(errorBody -> Mono.error(new NotFoundException(errorBody.getMessage())));
            } else if (clientResponse.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new Exception(errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}
