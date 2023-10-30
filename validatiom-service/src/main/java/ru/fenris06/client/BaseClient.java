package ru.fenris06.client;


import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.fenris06.exception.ErrorResponse;
import ru.fenris06.exception.NotFoundException;

public class BaseClient {
    private final WebClient webClient;

    public BaseClient(String url) {
        this.webClient = WebClient.builder()
                .filter(errorHandler())
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
        //TODO придумать как сделать get без Object
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

