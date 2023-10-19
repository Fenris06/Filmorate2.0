package ru.fenris06.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String status;
    private final String response;
    private final String message;
    private final String path;
    private final LocalDateTime time;
}
