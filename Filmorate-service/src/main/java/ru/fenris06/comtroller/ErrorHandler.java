package ru.fenris06.comtroller;

import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.fenris06.exception.ErrorResponse;
import ru.fenris06.exception.NotFoundException;



import java.time.LocalDateTime;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundException(final NotFoundException e, WebRequest webRequest) {
        return new ErrorResponse(
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());
    }
}
