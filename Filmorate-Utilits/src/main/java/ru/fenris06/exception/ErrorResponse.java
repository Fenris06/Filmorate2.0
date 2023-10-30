package ru.fenris06.exception;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String status;
    private String response;
    private String message;
    private String path;
    private LocalDateTime time;
}
